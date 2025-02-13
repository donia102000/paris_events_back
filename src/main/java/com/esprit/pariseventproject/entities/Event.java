package com.esprit.pariseventproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String eventId;
    private String url;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String leadText;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    private String addressName;
    private String addressStreet;
    private String addressZipcode;
    private String addressCity;

    private Double latitude;
    private Double longitude;

    private String coverUrl;
    private String coverAlt;
    private String coverCredit;

    private String contactUrl;
    private String contactPhone;
    private String contactMail;
    private String contactFacebook;
    private String contactTwitter;

    private String priceType;
    @Column(columnDefinition = "TEXT")
    private String priceDetail;
    private String accessType;
    private String accessLink;
    private String accessLinkText;

    private String imageCover;
    private String programs;
    private String addressUrl;
    private String addressUrlText;
    private String addressText;

    private String audience;
    @Column(columnDefinition = "TEXT")
    private String childrens;
    private String groupe;
    private String locale;

    private Double rank;
    private Integer weight;

    private LocalDateTime updatedAt;

    private Boolean pmr;       // Accès aux personnes à mobilité réduite
    private Boolean blind;      // Accessible aux malvoyants
    private Boolean deaf;       // Accessible aux malentendants
    @Column(columnDefinition = "TEXT")
    private String transport;   // Info sur les transports (ex: métro)
}
