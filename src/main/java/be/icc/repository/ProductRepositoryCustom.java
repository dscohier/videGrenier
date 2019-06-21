package be.icc.repository;

import be.icc.entity.Product;
import be.icc.entity.User;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * Created by Scohier Dorian on 15-05-2019.
 */
interface ProductRepositoryCustom {
    Page<Product> findProductsByCriteria(FilterProductsForm filterProductsForm, Pageable page);

    Page<Product> findSalesByCriteria(FilterSalesForm filterSalesForm, User seller, Pageable page);

}
