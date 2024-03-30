package com.event.reservation.model.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mst_event")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Event {
  
  @Id
  @Column(name = "event_id")
  private String id;

  private String eventName;

  private String detailEvent;

  private int price;

  private int quota;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
  private Date startTime;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
  private Date endTime;

  @Column(name = "is_over")
  private boolean isOver;

  @PrePersist
  public void prefixId() {
    this.id = "event-" + UUID.randomUUID();
  }

}
