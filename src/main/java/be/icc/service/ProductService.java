package be.icc.service;

import be.icc.dto.ProductDto;
import be.icc.entity.Product;

/**
 * Created by Student on 02-01-19.
 */
public interface ProductService {

    ProductDto add(ProductDto productDto);

    ProductDto update(Product productDto);

    ProductDto findById(Long id);

    Product findEntityById(Long id);

}
