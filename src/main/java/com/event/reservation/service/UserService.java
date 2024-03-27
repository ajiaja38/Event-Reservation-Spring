package com.event.reservation.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.event.reservation.model.dto.req.CreateUserDto;
import com.event.reservation.model.dto.res.UserResponseDto;
import com.event.reservation.model.entity.AppUser;

public interface UserService extends UserDetailsService {
  UserResponseDto createUser(CreateUserDto createUserDto);
  List<UserResponseDto> getAllUsers();
  UserResponseDto getUserByUserId(String id);
  AppUser loadUserByUserId(String id);
  UserResponseDto updateUser(String id, CreateUserDto createUserDto);
  void deleteUser(String id);
}
