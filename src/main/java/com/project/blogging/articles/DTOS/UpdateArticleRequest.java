package com.project.blogging.articles.DTOS;

import org.springframework.lang.Nullable;

import io.micrometer.common.lang.NonNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {

  @Nullable
  private String title;

  @Nullable
  private String body;

  @Nullable
  private String subtitle;
}
