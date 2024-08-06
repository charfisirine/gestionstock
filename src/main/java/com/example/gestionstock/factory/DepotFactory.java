package com.example.gestionstock.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.DepotDTO;
import com.example.gestionstock.dto.StockDTO;

public class DepotFactory {

    public static Depot depotDTOToDepot(DepotDTO depotDTO) {
        Depot depot = new Depot();
        depot.setIdDepot(depotDTO.getIdDepot());
        depot.setNomDepot(depotDTO.getNomDepot());

        Collection<Stock> stocks = new ArrayList<>();
        for (StockDTO stockDTO : depotDTO.getStocks()) {
            Stock stock = StockFactory.stockDTOTOStock(stockDTO);
            stocks.add(stock);
            stock.setDepot(depot);
        }
        depot.setStocks(stocks); // Setting stocks in the depot
        return depot;
    }

    public static DepotDTO depotToDepotDTO(Depot depot) {
        if (depot != null) {
            DepotDTO depotDTO = new DepotDTO();
            depotDTO.setIdDepot(depot.getIdDepot());
            depotDTO.setNomDepot(depot.getNomDepot());
            depotDTO.setStocks(StockFactory.stocksToStockDTOs(depot.getStocks()));
            return depotDTO;
        } else {
            return null;
        }
    }

    public static DepotDTO lazyDepotToDepotDTO(Depot depot) {
        if (depot != null) {
            DepotDTO depotDTO = new DepotDTO();
            depotDTO.setIdDepot(depot.getIdDepot());
            depotDTO.setNomDepot(depot.getNomDepot());
            return depotDTO;
        } else {
            return null;
        }
    }

    public static Collection<DepotDTO> depotsToDepotDTOs(Collection<Depot> depots) {
        List<DepotDTO> depotDTOs = new ArrayList<>();
        for (Depot depot : depots) {
            DepotDTO depotDTO = lazyDepotToDepotDTO(depot);
            depotDTOs.add(depotDTO);
        }
        return depotDTOs;
    }
}
