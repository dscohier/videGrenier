package be.icc.service.imp;

import be.icc.dto.BidderDto;
import be.icc.entity.Bidder;
import be.icc.entity.User;
import be.icc.repository.BidderRepository;
import be.icc.repository.ProductRepository;
import be.icc.service.BidderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
@Service
@Transactional
public class BidderServiceImp implements BidderService {

    @Autowired
    BidderRepository bidderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public BidderDto save (BidderDto bidder) {
        Bidder bidderEntity = bidder.toEntity();
        return bidderRepository.save(bidderEntity).toDto();
    }

    @Override
    public List<Bidder> findByUser(User user) {
        return bidderRepository.findByUser(user);
    }

}
