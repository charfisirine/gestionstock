package com.example.gestionstock.web.rest;

import com.example.gestionstock.dto.StockDTO;
import com.example.gestionstock.service.StockService;
import com.example.gestionstock.util.RestPreconditions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/api/stocks")
public class StockRessource {

    @Autowired
    private StockService stockService;

    private static final String ENTITY_NAME = "Stock";

    @GetMapping
    public Collection<StockDTO> findAll() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public StockDTO findOne(@PathVariable Integer id) {
        StockDTO stock = stockService.findOne(id);
        RestPreconditions.checkFound(stock, ENTITY_NAME + " not found");
        return stock;
    }

    @PostMapping
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stock, BindingResult bindingResults) throws URISyntaxException, MethodArgumentNotValidException {
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (stock.getIdStock() != null) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "idStock", "Post does not allow a stock with an ID"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        StockDTO result = stockService.add(stock);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getIdStock())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Integer id, @RequestBody StockDTO stockDTO, BindingResult bindingResults)
            throws MethodArgumentNotValidException {

        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        if (stockDTO.getIdStock() == null) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "idStock", "Put does not allow a stock without an ID"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        StockDTO result = stockService.update(stockDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/article/{idArticle}/depot/{idDepot}")
    public Collection<StockDTO> getStockByArticleAndDepot(@PathVariable int idArticle, @PathVariable int idDepot) {
        return stockService.getStockByArticleAndDepot(idArticle, idDepot);
    }


    @PostMapping("/addQuantity")
    public ResponseEntity<StockDTO> addQuantity(@RequestParam String designationArticle,
                                                @RequestParam String nomDepot,
                                                @RequestParam Integer quantity,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePeremption) {
        try {
            StockDTO stockDTO = stockService.addQuantity(designationArticle, nomDepot, quantity, datePeremption);
            return ResponseEntity.ok(stockDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PostMapping("/subtract")
    public ResponseEntity<String> subtractQuantity(
            @RequestParam String designationArticle,
            @RequestParam String nomDepot,
            @RequestParam Integer quantity) {
        try {
            String result = stockService.subtractQuantity(designationArticle, nomDepot, quantity);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    }



