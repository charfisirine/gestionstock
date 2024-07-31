package com.example.service;

import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.dto.StockDTO;
import com.example.gestionstock.factory.StockFactory;
import com.example.gestionstock.repository.StockRepository;


@Service
@Transactional
public class StockService {
@Autowired
    StockRepository stockRepository;


@Transactional(readOnly = true)
public Stock  findOne(Integer id){
    return stockRepository.findById(id).orElse(null);
}
@Transactional(readOnly = true)

public Collection<StockDTO>findAll(){
    List<Stock> result=stockRepository.findAll();
    return StockFactory.stocksToStockDTOs(result);
}
public Stock  add(Stock stock){
    return stockRepository.save(stock);
}
public Stock  update(Stock stock){
    return stockRepository.save(stock);
}
public void  deleteStock(Integer id){
     stockRepository.deleteById(id);
}




}
