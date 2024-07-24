package com.example.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionstock.domain.Article;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

}
