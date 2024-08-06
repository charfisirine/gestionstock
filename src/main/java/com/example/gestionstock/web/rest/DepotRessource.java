package com.example.gestionstock.web.rest;

import com.example.gestionstock.domain.Depot;
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

import com.example.gestionstock.util.RestPreconditions;
import com.example.gestionstock.service.DepotService;

import com.example.gestionstock.dto.DepotDTO;

@RestController
@RequestMapping("/api/depots")
public class DepotRessource {

    private final DepotService depotService;
    private static final String ENTITY_NAME = "Depot";

    public DepotRessource(DepotService depotService) {
        this.depotService = depotService;
    }

    @GetMapping
    public Collection<DepotDTO> findAll() {
        return depotService.findAll();
    }

    @GetMapping("/{id}")
    public Depot findOne(@PathVariable Integer id) {
        Depot depot = depotService.findOne(id);
        RestPreconditions.checkFound(depot, ENTITY_NAME + " not found");
        return depot;
    }





    @PostMapping
    public ResponseEntity<DepotDTO> addDepot(@RequestBody DepotDTO depotDTO, BindingResult bindingResults)
            throws URISyntaxException, MethodArgumentNotValidException {

        if (depotDTO.getIdDepot() != null) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "idDepot", "Post does not allow a depot with an ID"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        DepotDTO result = depotService.add(depotDTO);
        return ResponseEntity.created(new URI("/api/depots/" + result.getIdDepot())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepotDTO> updateDepot(@PathVariable Integer id, @RequestBody DepotDTO depotDTO, BindingResult bindingResults)
            throws MethodArgumentNotValidException {

        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        if (!id.equals(depotDTO.getIdDepot())) {
            bindingResults.addError(new FieldError(ENTITY_NAME, "idDepot", "ID in path and body must match"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }

        DepotDTO result = depotService.update(depotDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable Integer id) {
        depotService.deleteDepot(id);
        return ResponseEntity.ok().build();
    }
}
