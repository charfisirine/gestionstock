package com.example.gestionstock.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.net.URI;
import java.net.URISyntaxException;
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
    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) throws URISyntaxException{
        Article result=articleRepository.save(article);
        return ResponseEntity.created(new URI("/api/articles/" + result.getIdArticle())).body(result);
    }
    @PutMapping
    public ResponseEntity<Article> updateArticle(@RequestBody Article article){
        Article result=articleRepository.save(article);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}

