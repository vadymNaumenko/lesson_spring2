package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class WebController {

    @Autowired
    private EventService eventService;

    @GetMapping("/find")
    public String helloTest(Model model) {
        model.addAttribute("source", new SourceConfigDTO());
        return "webForm";
    }

    @PostMapping("/events/test")
    public String getEventsTest(Model model, @ModelAttribute("source") SourceConfigDTO source) {
//        List<EventDTO> events = eventService.parseTest(source);
        List<EventDTO> events = eventService.parseTest(source);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<EventShortDTO> events = eventService.findAll();
        model.addAttribute("events", events);
        return "mvc-events";
    }

    @GetMapping("/events/page/{number}")
    public String getEventsNumber(@PathVariable Integer number, Model model) {
        List<EventShortDTO> events;
        if (number != null) {
            events = eventService.findByPageable(number);
        } else {
            events = eventService.findByPageable(0);
        }
        model.addAttribute("events", events);
        model.addAttribute("page", number);
        return "mvc-events";
    }

//    @GetMapping("/events/pages") // page 2 with pageable
//    public String getEventsPage(Model model, @PathVariable Pageable pageable) {
//        Page<EventShortDTO> events;
//        events = eventService.findByPage(pageable);
//        model.addAttribute("events", events);
//
//        return "mvc-events";
//    }

    @GetMapping("/events/{title}")
    public String getEventByTitle(@PathVariable String title, Model model) {
        EventDTO events = eventService.findByTitle(title);
        model.addAttribute("event", events);
        return "post";
    }

}
