package com.mv.hexagonal.payments.adapters.in.controller;

import com.mv.hexagonal.payments.adapters.in.controller.mapper.UserMapper;
import com.mv.hexagonal.payments.adapters.in.controller.request.UserRequest;
import com.mv.hexagonal.payments.application.ports.in.CreateUserInputPort;
import com.mv.hexagonal.payments.application.ports.in.FindUserByIdInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final CreateUserInputPort createUserInputPort;
  private final FindUserByIdInputPort findUserByIdInputPort;
  private final UserMapper userMapper;

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody UserRequest userRequest) {
    var userDomain = userMapper.toUser(userRequest);
    createUserInputPort.create(userDomain);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    var user = findUserByIdInputPort.find(id);
    return ResponseEntity.ok().body(userMapper.toUserResponse(user));
  }
}
