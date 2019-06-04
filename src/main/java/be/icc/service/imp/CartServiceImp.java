package be.icc.service.imp;

import be.icc.entity.Panier;
import be.icc.repository.CartRepository;
import be.icc.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Scohier Dorian on 06-05-19.
 */
@Service
@Transactional
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Panier findEntityById(Long id) {
        return cartRepository.findOne(id);
    }

    @Override
    public Panier update(Panier panier) {
        return cartRepository.save(panier);
    }
}
