package com.project.blogging.users;

import com.project.blogging.users.DTOS.CreateUserRequest;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  // @Autowired
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserService(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public UserEntity createUser(CreateUserRequest body) {
    UserEntity newUser = modelMapper.map(body, UserEntity.class);
    // TODO: encrypt the password and save it
    // newUser.setPassword();
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
    // TODO: check and match password
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
