package be.icc.service;

import be.icc.controller.CategoryEnum;
import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Product;

import java.util.List;

/**
 * Created by Scohier Dorian on 02-01-19.
 */
public interface ProductService {

    ProductDto add(ProductDto productDto);

    ProductDto update(Product productDto);

    ProductDto findById(Long id);

    List<ProductDto> findByCategoryAndSalable(CategoryEnum categoryEnum);

    List<ProductDto> findAllSalableProduct();

    Product findEntityById(Long id);

    List<ProductDto> findBySeller(UserDto seller);

    List<ProductDto> findByCategoryIn(List<CategoryEnum> categoryEnums);
}
