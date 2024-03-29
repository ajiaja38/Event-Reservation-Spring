package com.event.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.reservation.model.entity.Event;
import com.event.reservation.service.EventService;
import com.event.reservation.utils.constant.ApiPathConstant;
import com.event.reservation.utils.res.Response;
import com.event.reservation.utils.res.ResponseMessage;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.EVENT
)
public class EventController {
  
  @Autowired
  private EventService eventService;

  @PostMapping
  public ResponseEntity<Response<Event>> createEventHandler(
    @RequestBody Event event
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<Event>(
        HttpStatus.CREATED.value(),
        "Event Created Successfully",
        this.eventService.createEvent(event)
      )
    );
  }

  @GetMapping
  public ResponseEntity<Response<List<Event>>> getAllEventHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<List<Event>>(
        HttpStatus.OK.value(),
        "Successfully Get All Events",
        this.eventService.getAllEvent()
      )
    );
  }

  @GetMapping({"{id}"})
  public ResponseEntity<Response<Event>> getEventByIdHandler(
    @PathVariable String id
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<Event>(
        HttpStatus.OK.value(),
        "Successfully Get Event By Id",
        this.eventService.getEvent(id)
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<Response<Event>> updateEventHandler(
    @PathVariable String id,
    @RequestBody Event event
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<Event>(
        HttpStatus.OK.value(),
        "Successfully Update Event",
        this.eventService.updateEvent(event)
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseMessage> deleteEventHandler(
    @PathVariable String id
  ) {
    this.eventService.deleteEvent(id);
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new ResponseMessage(
        HttpStatus.OK.value(),
        "Successfully Delete Event"
      )
    );
  }

}
