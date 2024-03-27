package com.event.reservation.model.dto.res;

import java.util.List;

import com.event.reservation.utils.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
  
  private String email;
  private List<ERole> role;
  private String token;

}
