'use strict'; // Включение строгого режима JavaScript для улучшения безопасности и предотвращения ошибок

// Получение элементов из DOM
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

// Инициализация переменных
var stompClient = null;
var username = null;

// Цвета для аватарок
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// Функция для соединения с сервером WebSocket
function connect(event) {
    // Получение имени пользователя из формы
    username = document.querySelector('#name').value.trim();

    // Проверка наличия имени пользователя
    if(username) {
        // Скрытие страницы ввода имени, отображение чата
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        // Инициализация соединения с WebSocket сервером
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        // Установка соединения с сервером
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault(); // Предотвращение отправки формы
}

// Функция обработки успешного подключения к серверу
function onConnected() {
    // Подписка на публичный топик
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Отправка информации о присоединении пользователя
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    // Скрытие индикатора подключения
    connectingElement.classList.add('hidden');
}

// Функция обработки ошибки при соединении с сервером
function onError(error) {
    // Отображение сообщения об ошибке
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

// Функция отправки сообщения
function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        // Формирование объекта сообщения
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        // Отправка сообщения на сервер
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = ''; // Очистка поля ввода сообщения
    }
    event.preventDefault(); // Предотвращение отправки формы
}

// Функция обработки полученного сообщения
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    // Определение типа сообщения и его отображение
    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        // Создание элемента аватарки пользователя
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        // Создание элемента имени пользователя
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    // Создание элемента текста сообщения
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement); // Добавление сообщения в область чата
    messageArea.scrollTop = messageArea.scrollHeight; // Прокрутка вниз к последнему сообщению
}

// Функция для генерации цвета аватарки пользователя
function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// Обработчики событий форм
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)


// 'use strict';
//
// var usernamePage = document.querySelector('#username-page');
// var chatPage = document.querySelector('#chat-page');
// var usernameForm = document.querySelector('#usernameForm');
// var messageForm = document.querySelector('#messageForm');
// var messageInput = document.querySelector('#message');
// var messageArea = document.querySelector('#messageArea');
// var connectingElement = document.querySelector('.connecting');
//
// var stompClient = null;
// var username = null;
//
// var colors = [
//     '#2196F3', '#32c787', '#00BCD4', '#ff5652',
//     '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
// ];
//
// function connect(event) {
//     username = document.querySelector('#name').value.trim();
//
//     if(username) {
//         usernamePage.classList.add('hidden');
//         chatPage.classList.remove('hidden');
//
//         var socket = new SockJS('/ws');
//         stompClient = Stomp.over(socket);
//
//         stompClient.connect({}, onConnected, onError);
//     }
//     event.preventDefault();
// }
//
//
// function onConnected() {
//     // Subscribe to the Public Topic
//     stompClient.subscribe('/topic/public', onMessageReceived);
//
//     // Tell your username to the server
//     stompClient.send("/app/chat.addUser",
//         {},
//         JSON.stringify({sender: username, type: 'JOIN'})
//     )
//
//     connectingElement.classList.add('hidden');
// }
//
//
// function onError(error) {
//     connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
//     connectingElement.style.color = 'red';
// }
//
//
// function sendMessage(event) {
//     var messageContent = messageInput.value.trim();
//     if(messageContent && stompClient) {
//         var chatMessage = {
//             sender: username,
//             content: messageInput.value,
//             type: 'CHAT'
//         };
//         stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
//         messageInput.value = '';
//     }
//     event.preventDefault();
// }
//
//
// function onMessageReceived(payload) {
//     var message = JSON.parse(payload.body);
//
//     var messageElement = document.createElement('li');
//
//     if(message.type === 'JOIN') {
//         messageElement.classList.add('event-message');
//         message.content = message.sender + ' joined!';
//     } else if (message.type === 'LEAVE') {
//         messageElement.classList.add('event-message');
//         message.content = message.sender + ' left!';
//     } else {
//         messageElement.classList.add('chat-message');
//
//         var avatarElement = document.createElement('i');
//         var avatarText = document.createTextNode(message.sender[0]);
//         avatarElement.appendChild(avatarText);
//         avatarElement.style['background-color'] = getAvatarColor(message.sender);
//
//         messageElement.appendChild(avatarElement);
//
//         var usernameElement = document.createElement('span');
//         var usernameText = document.createTextNode(message.sender);
//         usernameElement.appendChild(usernameText);
//         messageElement.appendChild(usernameElement);
//     }
//
//     var textElement = document.createElement('p');
//     var messageText = document.createTextNode(message.content);
//     textElement.appendChild(messageText);
//
//     messageElement.appendChild(textElement);
//
//     messageArea.appendChild(messageElement);
//     messageArea.scrollTop = messageArea.scrollHeight;
// }
//
//
// function getAvatarColor(messageSender) {
//     var hash = 0;
//     for (var i = 0; i < messageSender.length; i++) {
//         hash = 31 * hash + messageSender.charCodeAt(i);
//     }
//     var index = Math.abs(hash % colors.length);
//     return colors[index];
// }
//
// usernameForm.addEventListener('submit', connect, true)
// messageForm.addEventListener('submit', sendMessage, true)