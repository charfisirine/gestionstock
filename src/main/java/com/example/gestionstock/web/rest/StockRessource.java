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
import com.example.gestionstock.repository.StockRepository;
import com.example.gestionstock.util.RestPreconditions;
import com.example.service.StockService;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.StockDTO;

@RestController
@RequestMapping("/api/stocks")
public class StockRessource {
    @Autowired
    StockService stockService;
    private static final String ENTITY_NAME = "Stock";    
    
    @GetMapping
    public Collection<Stock> findAll() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public StockDTO findOne(@PathVariable Integer id) {
        Stock stock = stockService.findOne(id);
        RestPreconditions.checkFound(stock, ENTITY_NAME+"Stock not found");
        return stock;
    }


    
    @PostMapping
    public ResponseEntity<Stock> addArticle(@RequestBody Stock stock, BindingResult bindingResults)
            throws URISyntaxException, MethodArgumentNotValidException {

        if (stock.getIdStock() != null) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "Id stock", "Post does not allow a stock with an ID"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        Stock result = stockRepository.save(stock);
        return ResponseEntity.created(new URI("/api/Stocks/" + result.getIdStock())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        if (stockRepository.existsById(id)) {
            Stock result = stockRepository.save(stock);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}