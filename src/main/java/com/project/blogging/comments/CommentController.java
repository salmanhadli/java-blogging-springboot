package com.project.blogging.comments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/{article-slug}/comment")
public class CommentController {

  @GetMapping("")
  String getComments() {
    return "COMMENTS";
  }
}
