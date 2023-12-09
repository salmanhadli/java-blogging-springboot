package com.project.blogging.users;

import com.project.blogging.common.DTOS.ErrorResponse;
import com.project.blogging.users.DTOS.CreateUserRequest;
import com.project.blogging.users.DTOS.LoginUserRequest;
import com.project.blogging.users.DTOS.UserResponse;
import com.project.blogging.users.UserService.UserNotFoundException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @PostMapping("")
  ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest body) {
    UserEntity newUser = userService.createUser(body);
    URI newUserURI = URI.create("/users/" + newUser.getId());
    return ResponseEntity
      .created(newUserURI)
      .body(modelMapper.map(newUser, UserResponse.class));
  }

  @PostMapping("/login")
  ResponseEntity<UserResponse> loginUser(
    @RequestBody LoginUserRequest request
  ) {
    UserEntity savedUser =
      this.userService.loginUser(request.getUsername(), request.getPassword());
    return ResponseEntity.ok(
      this.modelMapper.map(savedUser, UserResponse.class)
    );
  }

  @ExceptionHandler({ UserService.UserNotFoundException.class })
  ResponseEntity<ErrorResponse> handleException(Exception e) {
    String message = "Something Went Wrong";
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    if (e instanceof UserService.UserNotFoundException) {
      // return ResponseEntity.notFound().build();
      // return ResponseEntity
      //   .status(HttpStatus.NOT_EXTENDED)
      //   .body(ErrorResponse.builder().message(e.getMessage()).build());
      message = e.getMessage();
      status = HttpStatus.NOT_FOUND;
    }
    ErrorResponse response = ErrorResponse.builder().message(message).build();
    return ResponseEntity.status(status).body(response);
  }
}
