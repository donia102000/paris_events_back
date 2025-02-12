package com.esprit.pariseventproject.controller;

import com.esprit.pariseventproject.service.EventService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

   /* @GetMapping("/fetch")
    public String fetchEvents() {
        eventService.fetchAndSaveEvents();
        return "✅ Events fetched and saved!";
    }*/

}
