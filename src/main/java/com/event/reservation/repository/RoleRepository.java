package com.event.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.reservation.model.entity.Role;
import com.event.reservation.utils.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  @Query(
    value = "SELECT * FROM t_role WHERE role = 1",
    nativeQuery = true
  )
  Optional<Role> findByRole(ERole role);
}
