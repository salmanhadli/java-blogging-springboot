package com.project.blogging.users.DTOS;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.NonNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {

  @NonNull
  private String username;

  @NonNull
  private String password;

  @NonNull
  private String email;
}
