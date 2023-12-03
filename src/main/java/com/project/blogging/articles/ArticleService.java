package com.project.blogging.articles;

import com.project.blogging.articles.DTOS.CreateArticleRequest;
import com.project.blogging.articles.DTOS.UpdateArticleRequest;
import com.project.blogging.users.UserEntity;
import com.project.blogging.users.UserRepository;
import com.project.blogging.users.UserService.UserNotFoundException;
import java.util.List;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private ArticlesRepository articlesRepository;
  private UserRepository userRepository;

  public ArticleService(
    ArticlesRepository articlesRepository,
    UserRepository userRepository
  ) {
    this.articlesRepository = articlesRepository;
    this.userRepository = userRepository;
  }

  public List<ArticleEntity> getAllArticles() {
    return articlesRepository.findAll();
  }

  public ArticleEntity getArticleBySlug(String slug) {
    ArticleEntity article = articlesRepository.findBySlug(slug);
    if (article == null) throw new ArticleNotFoundException(slug);
    return article;
  }

  public ArticleEntity createArticle(
    CreateArticleRequest article,
    Long authorId
  ) {
    UserEntity author = userRepository
      .findById(authorId)
      .orElseThrow(() -> new UserNotFoundException(authorId));
    return articlesRepository.save(
      ArticleEntity
        .builder()
        .title(article.getTitle())
        .subtitle(article.getSubtitle())
        // TODO: create a proper slugification function
        .slug(article.getTitle().toLowerCase().replaceAll("\\s", "-"))
        .body(article.getBody())
        .author(author)
        .build()
    );
  }

  public ArticleEntity updateArticle(
    UpdateArticleRequest articleRequest,
    Long articleId
  ) {
    ArticleEntity article = articlesRepository
      .findById(articleId)
      .orElseThrow(() -> new ArticleNotFoundException(articleId));

    if (articleRequest.getTitle() != null) {
      article.setTitle(articleRequest.getTitle());
    }

    if (articleRequest.getBody() != null) {
      article.setBody(articleRequest.getBody());
    }

    if (articleRequest.getSubtitle() != null) {
      article.setSubtitle(articleRequest.getSubtitle());
    }

    return articlesRepository.save(article);
  }

  static class ArticleNotFoundException extends IllegalIdentifierException {

    public ArticleNotFoundException(String slug) {
      super("Article not found by: " + slug);
    }

    public ArticleNotFoundException(Long id) {
      super("Article not found");
    }
  }
}
