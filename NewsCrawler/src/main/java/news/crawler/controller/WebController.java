package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    private  WebService webService;
    @GetMapping("/events")
    public List<EventDTO> getEvents() throws IOException {
        String url = "https://www.it-world.ru/news/";
        return webService.getEvents(url);
    }

    @GetMapping("/events/2")
    public List<EventDTO> getEvents2() throws IOException {
        String url = "https://it.novyny.live/ru/news/";

        return webService.getEvents2(url);
    }
    @GetMapping("/events/3")
    public List<EventDTO> getEvents3() throws IOException {
        String url = "https://it.novyny.live/ru/news/";

        return webService.getEvents3(url);
    }
    @GetMapping("/events/4")
    public List<EventDTO> getEvents4() throws IOException {
        String url = "https://it.novyny.live/ru/news/";

        return webService.getEvents4(url);
    }


}
