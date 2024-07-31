package com.example.gestionstock.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.util.RestPreconditions;
import com.example.service.ArticleService;
import com.example.gestionstock.domain.Article;
import com.example.gestionstock.dto.ArticleDTO;
import com.example.gestionstock.factory.ArticleFactory;

@RestController
@RequestMapping("/api/articles")
public class ArticleRessource {
    @Autowired
     private  ArticleService articleService;
     private static final String ENTITY_NAME = "Article";    
    
    @GetMapping
    public Collection<ArticleDTO> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public Article findOne(@PathVariable Integer id) {
        Article article = articleService.findOne(id);

        RestPreconditions.checkFound(article, ENTITY_NAME+"Article not found");

        return article;
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO article, BindingResult bindingResults)
            throws URISyntaxException, MethodArgumentNotValidException {

        if (article.getIdArticle() != null) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "code", "Post does not allow an article with an ID"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        ArticleDTO result = articleService.add(article);
        return ResponseEntity.created(new URI("/api/articles/" + result.getIdArticle())).body(result);
    }



 

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Integer id, @RequestBody ArticleDTO articleDTO, BindingResult bindingResults)
            throws MethodArgumentNotValidException {

        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        if (!id.equals(articleDTO.getIdArticle())) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "idArticle", "ID in path and body must match"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        ArticleDTO result = articleService.update(articleDTO);
        return ResponseEntity.ok(result);
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    

}
