package be.icc.service;

import be.icc.entity.Panier;

/**
 * Created by Scohier Dorian on 06-05-19.
 */
public interface CartService {

    Panier findEntityById(Long id);
    Panier update(Panier panier);

}
