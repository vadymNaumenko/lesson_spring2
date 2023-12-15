package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventShortDTO> findAll() {
        return eventService.findAll();
    }
    @GetMapping("/{title}")
    public List<EventShortDTO> findByTitle(@PathVariable String title){
        return eventService.findAllByTitle(title);
    }

    @GetMapping("/page/{number}")
    public List<EventShortDTO> showPage(@PathVariable Integer number){
        return eventService.findByPageable(number);
    }

}
