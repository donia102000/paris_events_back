package com.esprit.pariseventproject.dao.repository;

import com.esprit.pariseventproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventId(String eventId);

    @Query("SELECT e.addressName, " +
            "SUM(CASE WHEN e.priceType = 'payant' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN e.priceType = 'gratuit' THEN 1 ELSE 0 END) " +
            "FROM Event e WHERE e.addressName IS NOT NULL " +
            "GROUP BY e.addressName")
    List<Object[]> getEventCountsByAddress();
}
