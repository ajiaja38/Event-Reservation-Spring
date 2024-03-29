package com.event.reservation.model.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "mst_user")
public class User {
  
  @Id
  @Column(name = "user_id")
  private String id;

  private String name;

  @Column(unique = true)
  private String email;

  private String phone;

  @JsonIgnore
  private boolean deleted;

  private String password;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
  private Date birthDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "trx_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;

  @PrePersist
  public void prefixId() {
    this.id = "user-" + UUID.randomUUID();
  }

}
