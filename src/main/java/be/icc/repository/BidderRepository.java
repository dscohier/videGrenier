package be.icc.repository;

import be.icc.entity.Bidder;
import be.icc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
public interface BidderRepository extends JpaRepository<Bidder,Long> {

    List<Bidder> findByUser(User user);
}
