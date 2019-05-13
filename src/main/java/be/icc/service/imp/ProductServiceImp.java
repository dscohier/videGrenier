package be.icc.service.imp;

import be.icc.controller.CategoryEnum;
import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Category;
import be.icc.entity.Product;
import be.icc.repository.CategoryRepository;
import be.icc.repository.ProductRepository;
import be.icc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Scohier Dorian on 02-01-19.
 */
@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = productDto.toEntity();
        ProductDto productSaved = productRepository.save(product).toDto();
        return productSaved;
    }

    @Override
    public ProductDto update(Product product) {
        Product productSaved = productRepository.save(product);
        return productSaved.toDto();
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findOne(id).toDto();
    }

    @Override
    public List<ProductDto> findByCategoryAndSalable(CategoryEnum categoryEnum) {
        Category category = categoryRepository.findByCategory(categoryEnum);
        List<Product> products = productRepository.findByCategoryAndIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(category, new Date());
        ArrayList<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public List<ProductDto> findAllSalableProduct() {
        List<Product> products = productRepository.findByIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(new Date());
        ArrayList<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public Product findEntityById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<ProductDto> findBySeller(UserDto seller) {
        List<Product> products = productRepository.findBySellerOrderByCreationDateDesc(seller);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public List<ProductDto> findByCategoryIn(List<CategoryEnum> categoryEnums) {
        List<Category> categories = new ArrayList<>();
        for (CategoryEnum categoryEnum : categoryEnums) {
           Category category = categoryRepository.findByCategory(categoryEnum);
           if (category != null) {
               categories.add(category);
           }
        }
        List<Product> products = productRepository.findByCategoryIn(categories);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }
}
