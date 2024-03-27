package com.event.reservation.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.reservation.model.entity.Role;
import com.event.reservation.repository.RoleRepository;
import com.event.reservation.service.RoleService;
import com.event.reservation.utils.enums.ERole;

@Service
public class RoleServiceImpl implements RoleService {
  
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role getOrSave(ERole role) {
    Optional<Role> optionalRole = roleRepository.findByRole(role);

    if(optionalRole.isPresent()) {
      return optionalRole.get();
    }

    Role currentRole = Role.builder()
    .role(role)
    .build();

    return roleRepository.save(currentRole);
  }

}
