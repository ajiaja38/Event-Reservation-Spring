package com.event.reservation.service;

import com.event.reservation.model.dto.req.LoginUserDto;
import com.event.reservation.model.dto.res.LoginResponseDto;

public interface AuthService {
  LoginResponseDto login(LoginUserDto loginUserDto);
}
