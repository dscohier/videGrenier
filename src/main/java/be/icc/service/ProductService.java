package be.icc.service;

import be.icc.dto.ProductDto;

/**
 * Created by Student on 02-01-19.
 */
public interface ProductService {

    ProductDto add(ProductDto productDto);

    ProductDto findById(Long id);
}
