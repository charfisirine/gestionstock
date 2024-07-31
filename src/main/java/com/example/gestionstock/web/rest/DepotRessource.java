
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
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.domain.Depot;

@RestController
@RequestMapping("/api/depots")
public class DepotRessource 
{
    @Autowired
    DepotRepository depotRepository;
    
    @GetMapping
    public List<Depot>findAll(){ 
        return depotRepository.findAll();
    }
    @GetMapping("/{id}")
    public Depot findOne(@PathVariable Integer id){ 
        return depotRepository.findById(id).orElse(null);
    }
    @PostMapping
    public ResponseEntity<Depot> addDepot(@RequestBody Depot depot) throws URISyntaxException{
        Depot result=depotRepository.save(depot);
        return ResponseEntity.created(new URI("/api/depots/" + result.getIdDepot())).body(result);
    }
    @PutMapping
    public ResponseEntity<Depot> updateDepot(@RequestBody Depot depot){
        Depot  result=depotRepository.save(depot);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable Integer id) {
        if (depotRepository.existsById(id)) {
            depotRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}




