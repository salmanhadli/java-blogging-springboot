package com.project.blogging.users;

import com.project.blogging.users.DTOS.CreateUserRequest;
import com.project.blogging.users.DTOS.CreateUserResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  public UserService userService;

  @Autowired
  public ModelMapper modelMapper;

  // public UserController(UserService userService, ModelMapper modelMapper) {
  //   this.userService = userService;
  //   this.modelMapper = modelMapper;
  // }

  @PostMapping("/create")
  public ResponseEntity<CreateUserResponse> signupUser(
    @RequestBody CreateUserRequest body
  ) {
    System.out.println(
      body.toString() + " ------------------------------------------"
    );
    UserEntity newUser = userService.createUser(body);
    modelMapper.map(newUser, CreateUserResponse.class);
    System.out.println(newUser.toString());
    return new ResponseEntity<>(null);
    // URI newUserURI = URI.create("/users/" + newUser.getId());
    // return ResponseEntity
    //   .created(newUserURI)
    //   .body(modelMapper.map(newUser, CreateUserResponse.class));
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser() {
    return ResponseEntity.ok("Working");
  }
}
