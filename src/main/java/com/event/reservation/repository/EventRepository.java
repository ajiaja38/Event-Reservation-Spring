package com.event.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.reservation.model.entity.Event;

import jakarta.transaction.Transactional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
  
  @Query(
    value = "SELECT * FROM mst_event WHERE is_over = ?1",
    nativeQuery = true
  )
  List<Event> findAllEventByDeleted(boolean deleted);

  @Query(
    value = "SELECT * FROM mst_event WHERE event_id = ?1",
    nativeQuery =  true
  )
  Optional<Event> findEventById(String id);

  @Modifying
  @Transactional
  @Query(
    value = "UPDATE mst_event SET is_over = true WHERE event_id = ?1",
    nativeQuery = true
  )
  void softDeleteEvent(String id);

}
