package com.project.blogging.comments;
import org.springframework.lang.Nullable;

import com.project.blogging.articles.ArticleEntity;
import com.project.blogging.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.lang.NonNull;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(nullable = false)
  private Long id;

  @Nullable
  @Column(nullable = true)
  private String title;

  @NonNull
  @Column(nullable = true)
  private String body;

  @CreatedDate
  @Column
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "articleId", nullable = false)
  private ArticleEntity article;

  @ManyToOne
  @JoinColumn(name = "authorId", nullable = false)
  private UserEntity author;
}
