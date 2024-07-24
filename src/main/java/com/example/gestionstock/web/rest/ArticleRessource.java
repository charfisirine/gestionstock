package com.example.gestionstock.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.domain.Article;

@RestController
@RequestMapping("/api/articles")
public class ArticleRessource {
    @Autowired
    ArticleRepository articleRepository;
    
    @GetMapping
    public List<Article>findAll(){ 
        return articleRepository.findAll();
    }
    @GetMapping("/{id}")
    public Article findOne(@PathVariable Integer id){ 
        return articleRepository.findById(id).orElse(null);
    }
}

