//package news.crawler.service;
//
//import news.crawler.controller.dto.EventDTO;
//import news.crawler.domain.SourceConfig2;
//import org.apache.commons.lang3.time.DateUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
////@Service
//public class WebService {
//
//    SourceConfig2 sourceConfig = new SourceConfig2();
//
//    public List<EventDTO> getEvents(String sourceurl) throws IOException {
//        List<EventDTO> events = new ArrayList<>();
//
//        // ?PAGEN_1=1&IBLOCK_CODE=news
//
//        String nextPage = "?PAGEN_1=1&IBLOCK_CODE=news";
//
//        String[] sFirst = nextPage.split("=");
//        String[] sLast = nextPage.split("&");
//
//
//        for (int i = 1; i < 4; i++) {
//
//            String newPage2 = sFirst[0] + "=" + i + "&" + sLast[1];
//            String pageSourceUrl = sourceurl + newPage2;
//
//            Document doc = Jsoup.connect(pageSourceUrl).get();
//            Elements links = doc.select("h2");
//
//            for (Element element : links) {
//                String title = element.text();
//                String url = element.select("a").attr("href");
//                //events.add(new EventDTO(title, url));
//            }
//
//        }
//
//
//        return events;
//    }
//
//
//    //TODO with fullText and with photo
//    public List<EventDTO> getEvents5(String sourceurl) throws IOException {
//        List<EventDTO> events = new ArrayList<>();
//
//        Document doc = Jsoup.connect(sourceurl).get();
//        Elements links = doc.select(".all-news__list a");
//
//        for (Element e : links) {
//            String url = e.attr("href");
//            String title = e.select("span h3").text();
//            if (!title.isEmpty()) {
//                Document page = Jsoup.connect(url).get();
//                Elements elementsWithText = page.select(".content__wrapp p");
//                String dateTime = page.select(".content__info-create").text();
//                String photo = page.select(".content__main-image img").attr("src");
//
//                List<String> fullText = new ArrayList<>(); // StringBuilder
//
//                for (Element par : elementsWithText) {
//                    fullText.add(par.text());
//                }
//
////                events.add(new EventDTO(title, url, fullText, photo, parseFromNovyny(dateTime)));
//
//            }
//        }
//
//        return events;
//    }
//    private LocalDateTime parseFromNovyny(String dateTime) {
//
//
//        String[] paterns = {"dd MM yyyy HH:mm","d MM yyyy HH:mm"};
//
//        try {
//            Date date =  DateUtils.parseDate("21 11 2023 13:56",paterns);
//
//            Instant instant = date.toInstant();
//            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//            System.out.println(localDateTime);
//
//            Date date2 =  DateUtils.parseDate("1 11 2023 13:56",paterns);
//
//            System.out.println(date2);
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        //////////////////////////////////////
//
//        String[] arr = dateTime.split(" ");
//        if (arr[0].length() == 1) {
//            arr[0] = "0" + arr[0];
//        }
//
//        char a = arr[1].toLowerCase().charAt(0);
//
//
//        if((int)a >= 97 && (int)a <= 122){
//
//            // english or other switch
//
//        } else if (a == 'а'||a == 'ф'||a == 'м'||a == 'и'||a == 'с'||a == 'о'||a == 'н'||a == 'д') {
//            switch (arr[1].toLowerCase()) {
//                case "января":
//                    arr[1] = "01";
//                    break;
//                case "февраля":
//                    arr[1] = "02";
//                    break;
//                case "марта":
//                    arr[1] = "03";
//                    break;
//                case "апреля":
//                    arr[1] = "04";
//                    break;
//                case "майя":
//                    arr[1] = "05";
//                    break;
//                case "июня":
//                    arr[1] = "06";
//                    break;
//                case "июля":
//                    arr[1] = "07";
//                    break;
//                case "августа":
//                    arr[1] = "08";
//                    break;
//                case "сентября":
//                    arr[1] = "09";
//                    break;
//                case "октября":
//                    arr[1] = "10";
//                    break;
//                case "ноября":
//                    arr[1] = "11";
//                    break;
//                case "декабря":
//                    arr[1] = "12";
//                    break;
//                default:
//                    return LocalDateTime.now();
//            }
//        }
//
//
//        return LocalDateTime.parse(String.join(" ", arr), DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"));
//    }
//
////    //TODO Automatic
////    public List<EventDTO> setEventsAuto(SourceConfig sourceConfig) throws IOException {
////        List<EventDTO> events = new ArrayList<>();
////
////        sourceConfig.setSourceUrl("https://news.liga.net/ua");
////
////        sourceConfig.setPathToTitle(".news-nth-title:not(.news-nth-title-category news-mobile-time) "); // title
////
////        Document doc = Jsoup.connect(sourceConfig.getSourceUrl()).get();
////
////        scanTitle(sourceConfig.getPathToTitle(), doc, events);
////
////        sourceConfig.setPathToHrefForPage(".news-nth-title a"); //href
////
//////        String sourceurl = sourceConfig.getSourceUrl();
////
////
//////            Elements links = doc.select(".all-news__list a");
//////
//////            for (Element e : links) {
//////                String url = e.attr("href");
//////                String title = e.select("span h3").text();
//////                if (!title.isEmpty()) {
//////                    Document page = Jsoup.connect(url).get();
//////                    Elements elementsWithText = page.select(".content__wrapp p");
//////
//////                    String photo = page.select(".content__main-image img").attr("src");
//////                    System.out.println(photo);
//////
//////                    List<String> fullText = new ArrayList<>();
//////
//////                    for (Element par : elementsWithText) {
//////                        fullText.add(par.text());
//////                    }
//////
//////                    events.add(new EventDTO(title, url, fullText,photo));
//////                }
//////            }
////
////
////        return events;
////    }
//
//
//    public void scanFullText(String path, List<EventDTO> events) {
//
//    }
//
//    public void scanTitle(String path, Document doc, List<EventDTO> events) {
//        List<String> strs = new ArrayList<>();
//        Elements links = doc.select(path); // ".news-nth-title a"
//        for (int i = 0; i < links.size(); i++) {
//
//            strs.add(links.get(i).text());
//        }
//        System.out.println(strs);
//        System.out.println("d");
//    }
//
//
//}
