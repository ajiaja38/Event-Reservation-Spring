package com.event.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.reservation.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
  @Query(
    value = "SELECT * FROM t_user WHERE deleted = 1",
    nativeQuery = true
  )
  List<User> findAllUserByDeleted(boolean deleted);

  @Query(
    value = "SELECT * FROM t_user WHERE user_id = 1 AND deleted = 2",
    nativeQuery = true
  )
  Optional<User> findByIdAndDeleted(String id, boolean deleted);

  @Query(
    value = "SELECT * FROM t_user WHERE email = 1",
    nativeQuery = true
  )
  Optional<User> findUserByEmail(String email);
}
