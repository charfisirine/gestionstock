package com.example.service;

import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.dto.ArticleDTO;
import com.example.gestionstock.factory.ArticleFactory;
import com.example.gestionstock.repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Article findOne(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Collection<ArticleDTO> findAll() {
        Collection<Article> result = articleRepository.findAll();
        return ArticleFactory.articlesToCategorieDTOs(result);
    }


    public ArticleDTO add(ArticleDTO article2) {
        // Convert ArticleDTO to Article entity
        Article article = ArticleFactory.articleDTOTOArticle(article2);
        // Save the Article entity
        article = articleRepository.save(article);
        // Convert the saved Article entity back to ArticleDTO
        return ArticleFactory.articleTOarticleDTO(article);
    }
    

    public ArticleDTO update(ArticleDTO articleDTO) {
        //verifier si l'article existe dans la base ou non 
        Article articleInBase = articleRepository.findOne(articleDTO.getIdArticle());
        Preconditions.checkArgument(articleInBase != null, "Categore has been deleted");
        // Convert ArticleDTO to Article entity
        Article article = ArticleFactory.articleDTOTOArticle(articleDTO);
        // Save the Article entity
        article = articleRepository.save(article);
        // Convert the saved Article entity back to ArticleDTO
        return ArticleFactory.articleTOarticleDTO(article);
    }


    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}
