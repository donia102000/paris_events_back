package com.esprit.pariseventproject.implement;

import com.esprit.pariseventproject.dto.AddressTypeEventDTO;
import com.esprit.pariseventproject.entities.Event;

import java.util.List;

public interface IEventService {

        void fetchAndSaveEvents();
        List<AddressTypeEventDTO> getEventCountsByAddress();


}
