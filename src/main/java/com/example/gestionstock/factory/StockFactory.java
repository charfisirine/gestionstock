package com.example.gestionstock.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.StockDTO;
import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;

public class StockFactory {

    public static Stock stockDTOTOStock(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setIdStock(stockDTO.getIdStock());
        stock.setQuantity(stockDTO.getQuantity());
        stock.setDatePeremption(stockDTO.getDatePeremption());

        if (stockDTO.getIdArticle() != null) {
            Article article = new Article();
            article.setIdArticle(stockDTO.getIdArticle());
            stock.setArticle(article);
        }

        if (stockDTO.getIdDepot() != null) {
            Depot depot = new Depot();
            depot.setIdDepot(stockDTO.getIdDepot());
            stock.setDepot(depot);
        }

        return stock;
    }

    public static StockDTO stockTOStockDTO(Stock stock) {
        if (stock != null) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setIdStock(stock.getIdStock());
            stockDTO.setQuantity(stock.getQuantity());
            stockDTO.setDatePeremption(stock.getDatePeremption());

            if (stock.getArticle() != null) {
                stockDTO.setIdArticle(stock.getArticle().getIdArticle());
            }

            if (stock.getDepot() != null) {
                stockDTO.setIdDepot(stock.getDepot().getIdDepot());
            }

            return stockDTO;
        } else {
            return null;
        }
    }

    public static Collection<StockDTO> stocksToStockDTOs(Collection<Stock> stocks) {
        List<StockDTO> stockDTOs = new ArrayList<>();
        for (Stock stock : stocks) {
            StockDTO stockDTO = stockTOStockDTO(stock);
            stockDTOs.add(stockDTO);
        }
        return stockDTOs;
    }
}
