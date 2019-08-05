package be.icc.scheduling;

import be.icc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("scheduler")
public class Scheduler {

    @Autowired
    ProductService productService;

    public void closeAuctions() {
        List<Long> ids = productService.closeAuctions();
        System.out.println(ids.size() + " auctions has been closed with id :");
        for (Long id : ids) {
            System.out.print(" " + id + ",");
        }
    }
}
