package com.event.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.reservation.model.dto.req.CreateUserDto;
import com.event.reservation.model.dto.res.UserResponseDto;
import com.event.reservation.service.UserService;
import com.event.reservation.utils.constant.ApiPathConstant;
import com.event.reservation.utils.res.Response;
import com.event.reservation.utils.res.ResponseMessage;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.USER
)
public class UserController {
  
  @Autowired
  private UserService userService;

  @PostMapping("signup")
  public ResponseEntity<Response<UserResponseDto>> registerUserHandler(
    @RequestBody CreateUserDto createUserDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<UserResponseDto>(
        HttpStatus.CREATED.value(),
        "User Created Successfully",
        this.userService.createUser(createUserDto)
      )
    );
  }

  @GetMapping
  public ResponseEntity<Response<List<UserResponseDto>>> getAllUsersHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<List<UserResponseDto>>(
        HttpStatus.OK.value(),
        "Successfully Get All Users",
        this.userService.getAllUsers()
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Response<UserResponseDto>> getUserByUserIdHandler(
    @PathVariable String id
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<UserResponseDto>(
        HttpStatus.OK.value(),
        "Successfully Get User By Id",
        this.userService.getUserByUserId(id)
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<Response<UserResponseDto>> updateUserHandler(
    @PathVariable String id,
    @RequestBody CreateUserDto createUserDto
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<UserResponseDto>(
        HttpStatus.OK.value(),
        "Successfully Update User",
        this.userService.updateUser(id, createUserDto)
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseMessage> deleteUserHandler(
    @PathVariable String id
  ) {
    this.userService.deleteUser(id);
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new ResponseMessage(
        HttpStatus.OK.value(),
        "Successfully Delete User"
      )
    );
  }
  
}
