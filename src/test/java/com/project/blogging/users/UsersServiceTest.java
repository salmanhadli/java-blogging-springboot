package com.project.blogging.users;

import com.project.blogging.users.DTOS.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTest {

  @Autowired
  UserService userService;

//   @MockBean
//   UserRepository userRepository;

  @Test
  void can_create_users() {
    var user = userService.createUser(
      new CreateUserRequest("SalmanHadli", "passowrd", "Sal@Man.com")
    );

    Assertions.assertNotNull(user);
    Assertions.assertEquals("SalmanHadli", user.getUsername());
  }
}
