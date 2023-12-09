package com.project.blogging.common.DTOS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private String message;
}
