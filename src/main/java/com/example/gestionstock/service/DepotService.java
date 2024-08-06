package com.example.gestionstock.service;

import java.util.Collection;

import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.dto.DepotDTO;
import com.example.gestionstock.factory.DepotFactory;
import com.example.gestionstock.repository.DepotRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepotService {

    private final DepotRepository depotRepository;

    public DepotService(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }



    @Transactional(readOnly = true)
    public Depot findOne(Integer id) {
        return depotRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Collection<DepotDTO> findAll() {
        Collection<Depot> result = depotRepository.findAll();
        return DepotFactory.depotsToDepotDTOs(result);
    }

    public DepotDTO add(DepotDTO depotDTO) {
        Depot depot = DepotFactory.depotDTOToDepot(depotDTO);
        depot = depotRepository.save(depot);
        return DepotFactory.depotToDepotDTO(depot);
    }

    public DepotDTO update(DepotDTO depotDTO) {
        Depot depotInBase = depotRepository.findById(depotDTO.getIdDepot()).orElse(null);
        Preconditions.checkArgument(depotInBase != null, "Depot has been deleted");

        Depot depot = DepotFactory.depotDTOToDepot(depotDTO);
        depot = depotRepository.save(depot);
        return DepotFactory.depotToDepotDTO(depot);
    }

    public void deleteDepot(Integer id) {
        depotRepository.deleteById(id);
    }
}
