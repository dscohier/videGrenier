package be.icc.service;

import be.icc.dto.BidderDto;
import be.icc.entity.Bidder;
import be.icc.entity.User;

import java.util.List;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
public interface BidderService {

    BidderDto save(BidderDto bidderDto);

    List<Bidder> findByUser(User user);
}
