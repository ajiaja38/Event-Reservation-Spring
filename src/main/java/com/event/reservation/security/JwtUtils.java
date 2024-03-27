package com.event.reservation.security;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.event.reservation.model.entity.AppUser;

@Component
public class JwtUtils {
  
  private String jwtSecret = "SuperSecretAccessTokeni90sdfji323kofi0sdfi093f3rasko0s";

  private String appName = "Tokonyadia App";

  private long jwtExpiration = 8000;

  public String generatedToken(AppUser appUser) {
    
    return JWT.create()
    .withIssuer(this.appName)
    .withSubject(appUser.getId())
    .withExpiresAt(Instant.now().plusSeconds(this.jwtExpiration))
    .withIssuedAt(Instant.now())
    .sign(getAlgorithm());
  }

  public String getUserIdByToken(String token) {
    JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getSubject();
  }

  public boolean verifyJwtToken(String token) {
    JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
    DecodedJWT decodedJWT =  verifier.verify(token);
    return decodedJWT.getIssuer().equals(this.appName);
  }

  public Map<String, String> getUserInfoByToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
      DecodedJWT decodedJWT =  verifier.verify(token);
      
      Map<String, String> userInfo = new HashMap<>();
      userInfo.put("userId", decodedJWT.getSubject());
      
      return userInfo;
    } catch (JWTVerificationException e) {
      throw new RuntimeException();
    }
  }

  private Algorithm getAlgorithm () {
    return Algorithm.HMAC256(this.jwtSecret.getBytes());
  }
  
}
