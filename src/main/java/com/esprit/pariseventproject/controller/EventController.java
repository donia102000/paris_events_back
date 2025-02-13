package com.esprit.pariseventproject.controller;

import com.esprit.pariseventproject.dto.AddressTypeEventDTO;
import com.esprit.pariseventproject.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }



    @GetMapping("/address-price")
    public List<AddressTypeEventDTO> getAddressPriceCounts() {
      return   eventService.getEventCountsByAddress();
    }

}
