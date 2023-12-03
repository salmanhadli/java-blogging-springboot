package com.project.blogging.articles.DTOS;

import io.micrometer.common.lang.NonNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {

  @NonNull
  private String title;

  @NonNull
  private String body;

  private String subtitle;
}
