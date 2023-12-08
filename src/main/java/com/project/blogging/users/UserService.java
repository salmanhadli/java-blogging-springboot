package com.project.blogging.users;

import com.project.blogging.users.DTOS.CreateUserRequest;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  public UserRepository userRepository;

  // public UserService(UserRepository userRepository) {
  //   this.userRepository = userRepository;
  // }

  public UserEntity createUser(CreateUserRequest body) {
    // TODO: encrypt the password and save it
    var newUser = UserEntity
      .builder()
      .username(body.getUsername())
      // .password(body.getPassword())
      .email(body.getEmail())
      .build();

    return this.userRepository.save(newUser);
  }

  public UserEntity getUser(String username) {
    return userRepository.findByUsername(username);
  }

  public UserEntity getUser(Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  public UserEntity loginUser(String username, String password) {
    var user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UserNotFoundException(username);
    }
    // TODO: match password
    return user;
  }

  public static class UserNotFoundException extends IllegalIdentifierException {

    public UserNotFoundException(String username) {
      super("User " + username + " not found");
    }

    public UserNotFoundException(Long userId) {
      super("User  not found");
    }
  }
}
