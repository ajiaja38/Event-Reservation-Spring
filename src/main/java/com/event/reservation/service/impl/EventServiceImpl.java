package com.event.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.event.reservation.model.entity.Event;
import com.event.reservation.repository.EventRepository;
import com.event.reservation.service.EventService;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;
  
  @Override
  public Event createEvent(Event event) {
    
    Event newEvent = Event.builder()
    .eventName(event.getEventName())
    .detailEvent(event.getDetailEvent())
    .price(event.getPrice())
    .quota(event.getQuota())
    .startTime(event.getStartTime())
    .endTime(event.getEndTime())
    .isOver(false)
    .build();

    return this.eventRepository.save(newEvent);
  }

  @Override
  public List<Event> getAllEvent() {
    return this.eventRepository.findAllEventByDeleted(false);
  }

  @Override
  public Event getEvent(String id) {
    return this.eventRepository.findEventById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!")
    );
  }

  @Override
  public Event updateEvent(String id, Event event) {
    Event existingEvent = this.getEvent(id);

    existingEvent.setEventName(event.getEventName());
    existingEvent.setDetailEvent(event.getDetailEvent());
    existingEvent.setPrice(event.getPrice());
    existingEvent.setQuota(event.getQuota());
    existingEvent.setStartTime(event.getStartTime());
    existingEvent.setEndTime(event.getEndTime());

    return this.eventRepository.save(existingEvent);
  }

  @Override
  public void deleteEvent(String id) {
    try {
      this.eventRepository.softDeleteEvent(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
    }
  }
  
}
