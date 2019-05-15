package be.icc.repository;

import be.icc.dto.ProductDto;
import be.icc.form.FilterForm;

import java.util.List;
/**
 * Created by Scohier Dorian on 15-05-2019.
 */
interface ProductRepositoryCustom {
    List<ProductDto> findProductsByCriteria(FilterForm filterForm);
}
