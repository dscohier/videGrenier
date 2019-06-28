package be.icc.repository;

import be.icc.entity.Bidder;
import be.icc.entity.Category;
import be.icc.entity.Product;
import be.icc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
public interface ProductRepository  extends PagingAndSortingRepository<Product,Long>, ProductRepositoryCustom  {

    Page<Product> findByCategoryAndEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(Category category, Date date, Pageable pageable);

    Page<Product> findByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(Date date, Pageable pageable);

    Page<Product> findBySellerOrderByCreationDateDesc(User seller, Pageable pageable);

    Page<Product> findByCategoryIn(List<Category> category, Pageable pageable);

    List<Product> findDistinctProductByBiddersInAndEndDateAfter(List<Bidder> bidders, Date date);

    List<Product> findFirst6ByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(Date date);

    List<Product> findFirst6ByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByViewDesc(Date date);

    List<Product> findBySellerAndIsSellTrue(User seller);
}
