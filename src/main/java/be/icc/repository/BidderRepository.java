package be.icc.repository;

import be.icc.entity.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
public interface BidderRepository extends JpaRepository<Bidder,Long> {

}
