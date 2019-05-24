package be.icc.repository;

import be.icc.dto.ProductDto;
import be.icc.entity.User;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;

import java.util.List;
/**
 * Created by Scohier Dorian on 15-05-2019.
 */
interface ProductRepositoryCustom {
    List<ProductDto> findProductsByCriteria(FilterProductsForm filterProductsForm);

    List<ProductDto> findSalesByCriteria(FilterSalesForm filterSalesForm, User seller);

}
