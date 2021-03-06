package be.icc.service.imp;

import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.*;
import be.icc.enumClass.CategoryEnum;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;
import be.icc.repository.CategoryRepository;
import be.icc.repository.OrderRepository;
import be.icc.repository.ProductRepository;
import be.icc.repository.UserRepository;
import be.icc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

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
        Product product = productRepository.findOne(id);
        if (product != null) {
            return product.toDto();
        }
        return null;
    }

    @Override
    public Page<Product> findByCategoryAndSalable(CategoryEnum categoryEnum, Pageable pageable) {
        Category category = categoryRepository.findByCategory(categoryEnum);
        return productRepository.findByCategoryAndEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(category, new Date(), pageable);
    }

    @Override
    public Page<Product> findAllSalableProduct(Pageable pageable) {
        return productRepository.findByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(new Date(), pageable);
    }

    @Override
    public Product findEntityById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Page<Product> findBySeller(UserDto seller, Pageable pageable) {
       return productRepository.findBySellerOrderByCreationDateDesc(seller.toEntity(), pageable);
    }

    @Override
    public Page<Product> findByCategoryIn(List<CategoryEnum> categoryEnums, Pageable pageable) {
        List<Category> categories = new ArrayList<>();
        for (CategoryEnum categoryEnum : categoryEnums) {
           Category category = categoryRepository.findByCategory(categoryEnum);
           if (category != null) {
               categories.add(category);
           }
        }
        return productRepository.findByCategoryIn(categories, pageable);
    }

    @Override
    public Page<Product> findProductsByCriteria(FilterProductsForm filterProductsForm, Pageable page) {
        return productRepository.findProductsByCriteria(filterProductsForm, page);
    }

    @Override
    public Page<Product> findSalesByCriteria(FilterSalesForm filterSalesForm, String username, Pageable page) {
        return productRepository.findSalesByCriteria(filterSalesForm, userRepository.findByUsername(username), page);
    }

    @Override
    public List<ProductDto> findDistinctProductByBiddersInAndEndDateAfter(List<Bidder> bidders, Date date) {
        List<Product> products =  productRepository.findDistinctProductByBiddersInAndEndDateAfter(bidders, date);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public List<ProductDto> findLastAdded() {
        List<Product> products = productRepository.findFirst6ByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByCreationDateDesc(new Date());
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public List<ProductDto> findMostViewed() {
        List<Product> products = productRepository.findFirst6ByEndDateAfterOrEndDateIsNullAndIsSellFalseOrderByViewDesc(new Date());
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;    }

    @Override
    public List<ProductDto> findBySellerAndIsSellTrue(User seller) {
        List<Product> products = productRepository.findBySellerAndIsSellTrue(seller);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(product.toDto());
        }
        return productsDto;
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Long> closeAuctions() {
        List<Product> products = productRepository.findByEndDateBeforeAndIsSellFalse(new Date());
        List<Long> ids = new ArrayList<>();
        for (Product product : products) {
            Orders order = new Orders();
            order.setDate(new Date());
            order.setUser(((Bidder) product.getBidders().toArray()[product.getBidders().size()-1]).getUser());
            HashSet<Product> productWin = new HashSet<>();
            productWin.add(product);
            order.setProducts(productWin);
            orderRepository.save(order);
            product.setSell(true);
            productRepository.save(product);
            ids.add(product.getId());
        }
        return ids;
    }
}
