package com.event.reservation.model.dto.res;

import java.util.Date;
import java.util.List;

import com.event.reservation.utils.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {
  private String id;
  private String name;
  private String email;
  private String phone;
  private List<ERole> roles;
  private Date birthDate;
}
