package com.project.blogging.users;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
// @Getter
// @Setter
@Builder
// @ToString
// @RequiredArgsConstructor
// @AllArgsConstructor
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Data
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // @Column(nullable = false)
  private Long id;

  // @Column(nullable = false)
  // @NonNull
  private String username;

  // @Column(nullable = false)
  // @NonNull
  private String email;

  // @Column(nullable = true)
  // @Nullable
  private String bio;

  // @Column(nullable = true)
  // @Nullable
  private String image;
}
