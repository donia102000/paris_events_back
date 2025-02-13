package com.esprit.pariseventproject.service;

import com.esprit.pariseventproject.dao.repository.EventRepository;
import com.esprit.pariseventproject.dto.AddressTypeEventDTO;
import com.esprit.pariseventproject.entities.Event;
import com.esprit.pariseventproject.implement.IEventService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.esprit.pariseventproject.utils.JsonUtils.*;


@Service
@EnableScheduling
public class EventService implements IEventService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final EventRepository eventRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    @Value("${api.paris.url}")
    private String apiUrl;



    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Scheduled(cron = "0 * * * * *") // Toutes les minutes
    public void scheduledFetch() {
        fetchAndSaveEvents();
    }

    // Récupère une valeur texte en toute sécurité

    public void fetchAndSaveEvents() {
        try {
            // Appel de l'API pour récupérer les données
            String response = restTemplate.getForObject(apiUrl, String.class);
            JsonNode rootNode = objectMapper.readTree(response).get("results");

            List<Event> events = new ArrayList<>();

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    Event event = new Event();

                    event.setEventId(getSafeText(node, "id"));
                    event.setTitle(getSafeText(node, "title"));
                    event.setUrl(getSafeText(node, "url"));
                    event.setLeadText(getSafeText(node, "lead_text"));
                    event.setDescription(getSafeText(node, "description"));

                    // Dates (Conversion String -> LocalDate)
                    event.setDateStart(getSafeLocalDateTime(node, "date_start"));
                   event.setDateEnd(getSafeLocalDateTime(node, "date_end"));

                    // Adresse

                    event.setAddressName(getSafeText(node, "address_name"));
                    event.setAddressStreet(getSafeText(node, "address_street"));
                    event.setAddressZipcode(getSafeText(node, "address_zipcode"));
                    event.setAddressCity(getSafeText(node, "address_city"));

                    // Latitude & Longitude
                    if (node.has("lat_lon")) {
                        JsonNode latLon = node.get("lat_lon");
                        event.setLatitude(getSafeDouble(latLon, "lat"));
                        event.setLongitude(getSafeDouble(latLon, "lon"));
                    }

                    // Image & Couverture
                    event.setCoverUrl(getSafeText(node, "cover_url"));
                    event.setCoverAlt(getSafeText(node, "cover_alt"));
                    event.setCoverCredit(getSafeText(node, "cover_credit"));
                    event.setImageCover(getSafeText(node, "image_cover"));

                    // Contact
                    event.setContactUrl(getSafeText(node, "contact_url"));
                    event.setContactPhone(getSafeText(node, "contact_phone"));
                    event.setContactMail(getSafeText(node, "contact_mail"));
                    event.setContactFacebook(getSafeText(node, "contact_facebook"));
                    event.setContactTwitter(getSafeText(node, "contact_twitter"));

                    // Tarification
                    event.setPriceType(getSafeText(node, "price_type"));
                    event.setPriceDetail(getSafeText(node, "price_detail"));

                    // Accès
                    event.setAccessType(getSafeText(node, "access_type"));
                    event.setAccessLink(getSafeText(node, "access_link"));
                    event.setAccessLinkText(getSafeText(node, "access_link_text"));

                    // Programme & Localisation
                    event.setPrograms(getSafeText(node, "programs"));
                    event.setAddressUrl(getSafeText(node, "address_url"));
                    event.setAddressUrlText(getSafeText(node, "address_url_text"));
                    event.setAddressText(getSafeText(node, "address_text"));

                    // Audience
                    event.setAudience(getSafeText(node, "audience"));
                    event.setChildrens(getSafeText(node, "childrens"));
                    event.setGroupe(getSafeText(node, "group"));
                    event.setLocale(getSafeText(node, "locale"));

                    // Classement et poids
                    event.setRank(getSafeDouble(node, "rank"));
                    event.setWeight(getSafeInteger(node, "weight"));

                    // Date de mise à jour (Conversion String -> LocalDateTime)
                    event.setUpdatedAt(getSafeLocalDateTime(node, "updated_at"));
                    event.setPmr(getSafeBoolean(node, "pmr"));
                    event.setBlind(getSafeBoolean(node, "blind"));
                    event.setDeaf(getSafeBoolean(node, "deaf"));
                    event.setTransport(getSafeText(node, "transport"));

                    if (!eventRepository.existsByEventId(event.getEventId())) {
                        events.add(event);
                    }
                }
            }

            // Sauvegarde de tous les événements dans la base de données
            eventRepository.saveAll(events);

            logger.info(" Events saved successfully!");

        } catch (Exception e) {
          logger.error("Error fetching events: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public List<AddressTypeEventDTO> getEventCountsByAddress() {
        List<Object[]> results = eventRepository.getEventCountsByAddress();

        return results.stream().map(result ->
                new AddressTypeEventDTO(
                        (String) result[0],   // addressName
                        ((Number) result[1]).longValue(), // payant count
                        ((Number) result[2]).longValue()  // gratuit count
                )
        ).collect(Collectors.toList());
    }



}
