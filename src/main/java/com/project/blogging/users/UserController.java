package com.project.blogging.users;

import com.project.blogging.users.DTOS.CreateUserRequest;
import com.project.blogging.users.DTOS.CreateUserResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  ResponseEntity<CreateUserResponse> signupUser(
    @RequestBody CreateUserRequest body
  ) {
    UserEntity newUser = userService.createUser(body);
    URI newUserURI = URI.create("/users/" + newUser.getId());
    return ResponseEntity
      .created(newUserURI)
      .body(modelMapper.map(newUser, CreateUserResponse.class));
  }

  @PostMapping("/login")
  void loginUser() {}
}
