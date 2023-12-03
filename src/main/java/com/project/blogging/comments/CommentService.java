package com.project.blogging.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }
}
