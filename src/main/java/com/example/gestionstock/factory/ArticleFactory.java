package com.example.gestionstock.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.ArticleDTO;
import com.example.gestionstock.dto.StockDTO;

public class ArticleFactory {


        public static Article articleDTOTOArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setIdArticle(articleDTO.getIdArticle());
        article.setDesignationArticle(articleDTO.getDesignationArticle());

        Collection<Stock> stocks = new ArrayList<>();
        for (StockDTO stockDTO : articleDTO.getStocks()) { 
            Stock stock = StockFactory.stockDTOTOStock(stockDTO);
            stocks.add(stock);
            stock.setArticle(article);
        }
        article.setStocks(stocks); // Setting stocks in the article
        return article;
    }



    
    public static ArticleDTO articleTOarticleDTO(Article article) {
        if (article != null) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setIdArticle(article.getIdArticle());
            articleDTO.setDesignationArticle(article.getDesignationArticle());
            articleDTO.setStocks(StockFactory.stocksToStockDTOs(article.getStocks()));
            return articleDTO;
        } else {
            return null;
        }
    }

    public static ArticleDTO lazyArticleTOarticleDTO(Article article) {
        if (article != null) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setIdArticle(article.getIdArticle());
            articleDTO.setDesignationArticle(article.getDesignationArticle());
            return articleDTO;
        } else {
            return null;
        }
    }


    public static Collection<ArticleDTO> articlesToCategorieDTOs(Collection<Article> articles) {
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        for (Article article : articles) {
            ArticleDTO articleDTO = lazyArticleTOarticleDTO(article);
            articleDTOs.add(articleDTO);
        }
        return articleDTOs;
    }
}
