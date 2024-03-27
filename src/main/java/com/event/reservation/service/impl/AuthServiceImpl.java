package com.event.reservation.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.event.reservation.model.dto.req.LoginUserDto;
import com.event.reservation.model.dto.res.LoginResponseDto;
import com.event.reservation.model.entity.AppUser;
import com.event.reservation.model.entity.Role;
import com.event.reservation.security.JwtUtils;
import com.event.reservation.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public LoginResponseDto login(LoginUserDto loginUserDto) {
    try {
      Authentication authentication = this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          loginUserDto.getEmail(),
          loginUserDto.getPassword()
        )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      AppUser appUser = (AppUser) authentication.getPrincipal();

      String token = this.jwtUtils.generatedToken(appUser);

      return LoginResponseDto.builder()
      .email(appUser.getEmail())
      .role(appUser.getRoles()
        .stream()
        .map(Role::getRole)
        .collect(Collectors.toList())
      )
      .token(token)
      .build();
      
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "Invalid Credentials"
      );
    }
  }
  
}
