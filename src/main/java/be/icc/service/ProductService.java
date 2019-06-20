package be.icc.service;

import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Bidder;
import be.icc.entity.Product;
import be.icc.enumClass.CategoryEnum;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by Scohier Dorian on 02-01-19.
 */
public interface ProductService {

    ProductDto add(ProductDto productDto);

    ProductDto update(Product productDto);

    ProductDto findById(Long id);

    Page<Product> findByCategoryAndSalable(CategoryEnum categoryEnum, Pageable pageable);

    Page<Product> findAllSalableProduct(Pageable pageable);

    Product findEntityById(Long id);

    Page<Product> findBySeller(UserDto seller, Pageable pageable);

    Page<Product> findByCategoryIn(List<CategoryEnum> categoryEnums, Pageable pageable);

    List<ProductDto> findProductsByCriteria(FilterProductsForm filterProductsForm);

    List<ProductDto> findSalesByCriteria(FilterSalesForm filterSalesForm, String username);

    Page<Product> findDistinctProductByBiddersInAndEndDateAfter(List<Bidder> bidders, Date date, Pageable pageable);

    List<ProductDto>  findLastAdded();

    List<ProductDto> findMostViewed();
}
