package be.icc.service.imp;

import be.icc.entity.Panier;
import be.icc.repository.BasketRepository;
import be.icc.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Scohier Dorian on 06-05-19.
 */
@Service
@Transactional
public class BasketServiceImp implements BasketService {

    @Autowired
    BasketRepository basketRepository;

    @Override
    public Panier findEntityById(Long id) {
        return basketRepository.findOne(id);
    }

    @Override
    public Panier update(Panier panier) {
        return basketRepository.save(panier);
    }
}
