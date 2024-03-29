package com.event.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.reservation.model.dto.req.CreateUserDto;
import com.event.reservation.model.dto.res.UserResponseDto;
import com.event.reservation.model.entity.AppUser;
import com.event.reservation.model.entity.Role;
import com.event.reservation.model.entity.User;
import com.event.reservation.repository.UserRepository;
import com.event.reservation.service.RoleService;
import com.event.reservation.service.UserService;
import com.event.reservation.utils.enums.ERole;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleService roleService;

  @Override
  public UserResponseDto createUser(CreateUserDto createUserDto) {
    try {
      List<ERole> roles = new ArrayList<>();
      roles.add(ERole.USER);

      List<Role> userRoles = new ArrayList<>();

      for(ERole role : roles) {
        Role roleResult = this.roleService.getOrSave(role);
        userRoles.add(roleResult);
      }
      
      User user = User.builder()
      .name(createUserDto.getName())
      .email(createUserDto.getEmail())
      .phone(createUserDto.getPhone())
      .password(this.passwordEncoder.encode(createUserDto.getPassword()))
      .roles(userRoles)
      .birthDate(createUserDto.getBirthDate())
      .build();

      this.userRepository.save(user);

      return UserResponseDto.builder()
      .name(user.getName())
      .email(user.getEmail())
      .phone(user.getPhone())
      .birthDate(user.getBirthDate())
      .roles(roles)
      .build();

    } catch (DataIntegrityViolationException e) {
      throw e;
    }
  }

  @Override
  public UserResponseDto createUserAdmin(CreateUserDto createUserDto) {
    try {
      List<ERole> roles = new ArrayList<>();
      roles.add(ERole.ADMIN);

      List<Role> userRoles = new ArrayList<>();

      for(ERole role : roles) {
        Role roleResult = this.roleService.getOrSave(role);
        userRoles.add(roleResult);
      }
      
      User user = User.builder()
      .name(createUserDto.getName())
      .email(createUserDto.getEmail())
      .phone(createUserDto.getPhone())
      .password(this.passwordEncoder.encode(createUserDto.getPassword()))
      .roles(userRoles)
      .birthDate(createUserDto.getBirthDate())
      .build();

      this.userRepository.save(user);

      return UserResponseDto.builder()
      .name(user.getName())
      .email(user.getEmail())
      .phone(user.getPhone())
      .birthDate(user.getBirthDate())
      .roles(roles)
      .build();

    } catch (DataIntegrityViolationException e) {
      throw e;
    }
  }

  @Override
  public List<UserResponseDto> getAllUsers() {
    List<User> users = this.userRepository.findAllUserByDeleted(false);
    List<UserResponseDto> userResponseDtos = new ArrayList<>();

    for(User user : users) {
      userResponseDtos.add(UserResponseDto.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .phone(user.getPhone())
      .birthDate(user.getBirthDate())
      .roles(user.getRoles().stream().map(r -> r.getRole()).toList())
      .build());
    }

    return userResponseDtos;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findUserByEmail(username).orElseThrow(
      () -> new UsernameNotFoundException("User not found!")
    );

    return AppUser.builder()
    .id(user.getId())
    .email(user.getEmail())
    .password(user.getPassword())
    .roles(user.getRoles())
    .build();
  }

  @Override
  public AppUser loadUserByUserId(String id) {
    User user = this.userRepository.findByIdAndDeleted(id, false).orElseThrow(
      () -> new UsernameNotFoundException("User not found!")
    );

    return AppUser.builder()
    .id(user.getId())
    .email(user.getEmail())
    .password(user.getPassword())
    .roles(user.getRoles())
    .build();
  }

  @Override
  public UserResponseDto getUserByUserId(String id) {
    User user = this.userRepository.findByIdAndDeleted(id, false).orElseThrow(
      () -> new UsernameNotFoundException("User not found!")
    );

    return UserResponseDto.builder()
    .id(user.getId())
    .name(user.getName())
    .email(user.getEmail())
    .phone(user.getPhone())
    .birthDate(user.getBirthDate())
    .roles(user.getRoles().stream().map(r -> r.getRole()).toList())
    .build();
  }

  @Override
  public UserResponseDto updateUser(String id, CreateUserDto createUserDto) {
    User existingUser = this.userRepository.findByIdAndDeleted(id, false).orElseThrow(
      () -> new UsernameNotFoundException("User not found!")
    );
    
    existingUser.setName(createUserDto.getName());
    existingUser.setEmail(createUserDto.getEmail());
    existingUser.setPhone(createUserDto.getPhone());
    existingUser.setPassword(this.passwordEncoder.encode(createUserDto.getPassword()));
    existingUser.setBirthDate(createUserDto.getBirthDate());

    this.userRepository.save(existingUser);

    return UserResponseDto.builder()
    .id(existingUser.getId())
    .name(existingUser.getName())
    .email(existingUser.getEmail())
    .phone(existingUser.getPhone())
    .birthDate(existingUser.getBirthDate())
    .roles(existingUser.getRoles().stream().map(r -> r.getRole()).toList())
    .build();
  }

  @Override
  public void deleteUser(String id) {
    this.getUserByUserId(id);
    this.userRepository.softDeleteUser(id);
  }
  
}
