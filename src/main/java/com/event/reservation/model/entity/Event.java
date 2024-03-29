package com.event.reservation.model.entity;

import java.util.Date;
import java.util.UUID;

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

  private String price;

  private String quota;

  private Date startTime;

  private Date endTime;

  @Column(name = "is_over")
  private boolean isOver;

  @PrePersist
  public void prefixId() {
    this.id = "event-" + UUID.randomUUID();
  }

}
