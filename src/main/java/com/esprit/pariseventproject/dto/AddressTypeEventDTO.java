package com.esprit.pariseventproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressTypeEventDTO {

        private String addressName;
        private Long eventPayantCount;
        private Long eventGratuitCount;
}
