package com.event.reservation.service;

import com.event.reservation.model.entity.Role;
import com.event.reservation.utils.enums.ERole;

public interface RoleService {
  Role getOrSave(ERole role);
}
