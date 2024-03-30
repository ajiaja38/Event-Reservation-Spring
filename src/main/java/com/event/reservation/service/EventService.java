package com.event.reservation.service;

import java.util.List;

import com.event.reservation.model.entity.Event;

public interface EventService {
  Event createEvent(Event event);
  List<Event> getAllEvent();
  Event getEvent(String id);
  Event updateEvent(String id, Event event);
  void deleteEvent(String id);
}
