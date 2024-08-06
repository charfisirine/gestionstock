package com.example.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionstock.domain.Article;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Optional<Article> findByDesignationArticle(String designationArticle);

}
