package com.event.reservation.model.dto.req;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
  
  private String name;
  private String email;
  private String phone;
  private String password;
  private Date birthDate;

}
