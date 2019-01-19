package be.icc.service.imp;

import be.icc.dto.ProductDto;
import be.icc.entity.Product;
import be.icc.repository.ProductRepository;
import be.icc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Student on 02-01-19.
 */
@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = productDto.toEntity();
        ProductDto productSaved = productRepository.save(product).toDto();
        return productSaved;
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findOne(id).toDto();
    }
}
