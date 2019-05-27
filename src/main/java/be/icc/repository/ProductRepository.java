package be.icc.repository;

import be.icc.entity.Bidder;
import be.icc.entity.Category;
import be.icc.entity.Product;
import be.icc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryCustom  {

   List<Product> findByCategoryAndIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(Category category, Date date);

   List<Product> findByIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(Date date);

    List<Product> findBySellerOrderByCreationDateDesc(User seller);

    List<Product> findByCategoryIn(List<Category> category);

    List<Product> findDistinctProductByBiddersInAndEndDateAfter(List<Bidder> bidders, Date date);
}
