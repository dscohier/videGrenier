package be.icc.service.imp;

import be.icc.dto.PanierDto;
import be.icc.repository.PanierRepository;
import be.icc.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Scohier Dorian on 12-12-18.
 */
@Service
@Transactional
public class PanierServiceImp implements PanierService {
    @Autowired
    PanierRepository panierRepository;

    @Override
    public PanierDto add(PanierDto panier) {
        return panierRepository.save(panier.toEntity()).toDto();
    }

}
