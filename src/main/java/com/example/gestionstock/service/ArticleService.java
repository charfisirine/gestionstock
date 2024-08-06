package com.example.gestionstock.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.ArticleDTO;
import com.example.gestionstock.factory.ArticleFactory;
import com.example.gestionstock.repository.ArticleRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

//    @Value("${delete-article-without-check}")
//    private boolean deleteArticleWithoutCheck;

    @Transactional(readOnly = true)
    public Article findOne(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Collection<ArticleDTO> findAll() {
        Collection<Article> result = articleRepository.findAll();
        return ArticleFactory.articlesToCategorieDTOs(result);
    }

    public ArticleDTO add(ArticleDTO articleDTO) {
        Article article = ArticleFactory.articleDTOTOArticle(articleDTO);
        article = articleRepository.save(article);
        return ArticleFactory.articleTOarticleDTO(article);
    }

    public ArticleDTO update(ArticleDTO articleDTO) {
        Article articleInBase = articleRepository.findById(articleDTO.getIdArticle()).orElse(null);
        Preconditions.checkArgument(articleInBase != null, "Article has been deleted");

        Article article = ArticleFactory.articleDTOTOArticle(articleDTO);
        article = articleRepository.save(article);
        return ArticleFactory.articleTOarticleDTO(article);
    }

    public void deleteArticle(Integer id) {

        articleRepository.deleteById(id);
    }
}
