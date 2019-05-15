package be.icc.repository;

import be.icc.dto.ProductDto;
import be.icc.form.FilterForm;

import java.util.List;

interface ProductRepositoryCustom {
    List<ProductDto> findProductsByCriteria(FilterForm filterForm);
}
