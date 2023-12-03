package com.project.blogging.articles;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.project.blogging.users.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "articles")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(nullable = false)
  private Long id;

  @NonNull
  private String title;

  @NonNull
  @Column(unique = true)
  private String slug;

  @Nullable
  private String subtitle;

  @NonNull
  private String body;

  @CreatedDate
  private Date createdAt;

  @ManyToOne
  @NonNull
  @JoinColumn(name = "authorId", nullable = false)
  private UserEntity author;

  // TODO: add tags
}
