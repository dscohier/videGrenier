package be.icc.repository;

import be.icc.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Scohier Dorian on 06-05-19.
 */
public interface BasketRepository extends JpaRepository<Panier,Long> {

}
