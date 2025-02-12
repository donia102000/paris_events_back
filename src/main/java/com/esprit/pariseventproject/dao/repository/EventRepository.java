package com.esprit.pariseventproject.dao.repository;

import com.esprit.pariseventproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventId(String eventId);


}
