package com.example.gestionstock.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.StockDTO;

public class StockFactory {

    public static Stock stockDTOTOStock(StockDTO stockDTO){      
    Stock stock = new Stock();
    stock.setIdStock(stockDTO.getIdStock());
    // stock.setArticle(article);
     //stock.setDepot(depot);
    stock.setQuantity(stockDTO.getQuantity());
    stock.setDatePeremption(stockDTO.getDatePeremption());
    return stock;
    
    }

    public static StockDTO stockTOStockDTO(Stock stock) {
        if (stock != null) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setIdStock(stock.getIdStock());
            stockDTO.setQuantity(stock.getQuantity());
            stockDTO.setDatePeremption(stock.getDatePeremption()); 
            stockDTO.setIdArticle(stock.getArticle().getIdArticle());
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
