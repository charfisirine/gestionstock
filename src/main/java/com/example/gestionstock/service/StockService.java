package com.example.gestionstock.service;

import java.time.format.DateTimeParseException;
import java.util.Collection;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.StockDTO;
import com.example.gestionstock.factory.StockFactory;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.repository.StockRepository;
import com.google.common.base.Preconditions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final ArticleRepository articleRepository;
    private final DepotRepository depotRepository;

    public StockService(StockRepository stockRepository, ArticleRepository articleRepository, DepotRepository depotRepository) {
        this.stockRepository = stockRepository;
        this.articleRepository = articleRepository;
        this.depotRepository = depotRepository;
    }

    @Transactional(readOnly = true)
    public StockDTO findOne(Integer id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        return StockFactory.stockTOStockDTO(stock);
    }

    @Transactional(readOnly = true)
    public Collection<StockDTO> findAll() {
        Collection<Stock> result = stockRepository.findAll();
        return StockFactory.stocksToStockDTOs(result);
    }

    public StockDTO add(StockDTO stockDTO) {
        Stock stock = StockFactory.stockDTOTOStock(stockDTO);
        stock = stockRepository.save(stock);
        return StockFactory.stockTOStockDTO(stock);
    }

    public StockDTO update(StockDTO stockDTO) {
        Stock stockInBase = stockRepository.findById(stockDTO.getIdStock()).orElse(null);
        Preconditions.checkArgument(stockInBase != null, "Stock has been deleted");

        Stock stock = StockFactory.stockDTOTOStock(stockDTO);
        stock = stockRepository.save(stock);
        return StockFactory.stockTOStockDTO(stock);
    }

    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Collection<StockDTO> getStockByArticleAndDepot(int idArticle, int idDepot) {
        Collection<Stock> stocks = stockRepository.findByArticleIdArticleAndDepotIdDepot(idArticle, idDepot);
        return StockFactory.stocksToStockDTOs(stocks);
    }


    @Transactional
    public StockDTO addQuantity(String designationArticle, String nomDepot, Integer quantity, LocalDate datePeremption) {

        // Trouver l'article et le dépôt par leurs noms
        Article article = articleRepository.findByDesignationArticle(designationArticle)
                .orElseThrow(() -> new EntityNotFoundException("Article non trouvé"));
        Depot depot = depotRepository.findByNomDepot(nomDepot)
                .orElseThrow(() -> new EntityNotFoundException("Depot non trouvé"));

        // Trouver le stock existant pour l'article, le dépôt et la date de péremption donnés
        Stock existingStock = stockRepository.findByArticle_IdArticleAndDepot_IdDepotAndDatePeremption(
                article.getIdArticle(), depot.getIdDepot(), datePeremption);

        if (existingStock != null) {
            // Mettre à jour la quantité du stock existant
            existingStock.setQuantity(existingStock.getQuantity() + quantity);
        } else {
            // Créer un nouveau stock si aucun stock existant n'est trouvé
            Stock newStock = new Stock();
            newStock.setArticle(article);
            newStock.setDepot(depot);
            newStock.setQuantity(quantity);
            newStock.setDatePeremption(datePeremption);
            existingStock = stockRepository.save(newStock);
        }

        return StockFactory.stockTOStockDTO(existingStock);

            }

    @Transactional
    public String subtractQuantity(String designationArticle, String nomDepot, Integer quantity) {
        // Trouver tous les stocks pour l'article et le dépôt donnés, triés par date de péremption (la plus proche en premier)
        List<Stock> stocks = stockRepository.findByArticle_DesignationArticleAndDepot_NomDepotOrderByDatePeremptionAsc(designationArticle, nomDepot);

        if (stocks.isEmpty()) {
            throw new EntityNotFoundException("Aucun stock trouvé pour l'article et le dépôt donnés");
        }

        int remainingQuantity = quantity;

        for (Stock stock : stocks) {
            if (remainingQuantity <= 0) {
                break;
            }

            if (stock.getQuantity() <= remainingQuantity) {
                remainingQuantity -= stock.getQuantity();
                stock.setQuantity(0);
            } else {
                stock.setQuantity(stock.getQuantity() - remainingQuantity);
                remainingQuantity = 0;
            }

            stockRepository.save(stock);
        }

        if (remainingQuantity > 0) {
            throw new IllegalArgumentException("Quantité insuffisante dans le stock pour l'article et le dépôt donnés");
        }

        return "Quantité soustraite avec succès";
    }


}