package com.event.reservation.model.entity;

import java.util.UUID;

import com.event.reservation.utils.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
@Table(name = "mst_role")
public class Role {
  
  @Id
  @Column(name = "id_role")
  private String id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role_name")
  private ERole role;

  @PrePersist
  public void prefixId() {
    this.id = "role-" + UUID.randomUUID();
  }

}
