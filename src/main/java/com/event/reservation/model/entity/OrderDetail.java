package com.event.reservation.model.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trx_order_detail")
public class OrderDetail {
  
  @Id
  @Column(name = "order_detail_id")
  private String id;

  private int quantity;

  private String price;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private Order order;
  
  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @PrePersist
  public void prefixId() {
    this.id = "order-detail-" + UUID.randomUUID();
  }
  
}
