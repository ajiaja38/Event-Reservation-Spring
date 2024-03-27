package com.event.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.reservation.model.entity.Role;
import com.event.reservation.utils.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  Optional<Role> findByRole(ERole role);
}
