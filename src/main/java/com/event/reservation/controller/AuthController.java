package com.event.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.reservation.model.dto.req.LoginUserDto;
import com.event.reservation.model.dto.res.LoginResponseDto;
import com.event.reservation.service.impl.AuthServiceImpl;
import com.event.reservation.utils.constant.ApiPathConstant;
import com.event.reservation.utils.res.Response;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.AUTH
)
public class AuthController {
  
  @Autowired
  private AuthServiceImpl authServiceImpl;

  @PostMapping("signin")
  public ResponseEntity<Response<LoginResponseDto>> loginHandler(
    @RequestBody LoginUserDto loginUserDto
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<LoginResponseDto>(
        HttpStatus.OK.value(),
        "Login Successfully",
        this.authServiceImpl.login(loginUserDto)
      )
    );
  }

}
