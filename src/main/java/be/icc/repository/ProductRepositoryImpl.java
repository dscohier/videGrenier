package be.icc.repository;

import be.icc.dto.ProductDto;
import be.icc.entity.Category;
import be.icc.entity.City;
import be.icc.entity.Product;
import be.icc.entity.User;
import be.icc.enumClass.CategoryEnum;
import be.icc.enumClass.TypeOfSaleEnum;
import be.icc.form.FilterProductsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;
/**
 * Created by Scohier Dorian on 15-05-2019.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    EntityManager em;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<ProductDto> findProductsByCriteria(FilterProductsForm filterProductsForm) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.and(cb.equal(product.get("isSell"), false)));
        whereCategorieIn(filterProductsForm, cb, predicates, product);
        whereIsAuction(filterProductsForm, cb, predicates, product);
        whereCityIs(filterProductsForm, cb, predicates, product);
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(product.get("creationDate")));

        List<Product> products = em.createQuery(cq).getResultList();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product productEntity : products) {
            productDtos.add(productEntity.toDto());
        }
        return productDtos;
    }

    private void whereCityIs(FilterProductsForm filterProductsForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (isNotBlank(filterProductsForm.getCity()) && isNotBlank(filterProductsForm.getCountry())) {
            City city = cityRepository.findByNameAndCountry(filterProductsForm.getCity().split(",")[0], filterProductsForm.getCountry());
            Join<Product, User> user = product.join("seller");
            predicates.add(cb.and(cb.equal(user.get("city"), city)));
        }
    }

    private void whereIsAuction(FilterProductsForm filterProductsForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (filterProductsForm.getTypeOfSale().length != 0 && filterProductsForm.getTypeOfSale().length != 2) {
            TypeOfSaleEnum typeOfSale = TypeOfSaleEnum.valueOf(filterProductsForm.getTypeOfSale()[0]);
            if (typeOfSale == TypeOfSaleEnum.DIRECT_SALE) {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), false)));
            } else {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), true)));
            }
        }
    }

    private void whereCategorieIn(FilterProductsForm filterProductsForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        List<CategoryEnum> categoryEnums = new ArrayList<>();
        for (String categorie : filterProductsForm.getCategories()) {
            categoryEnums.add(CategoryEnum.valueOf(categorie));
        }

        List<Category> categories = new ArrayList<>();
        for (CategoryEnum categoryEnum : categoryEnums) {
            Category category = categoryRepository.findByCategory(categoryEnum);
            if (category != null) {
                categories.add(category);
            }
        }

        if (!categories.isEmpty()) {
            Predicate[] categoriesPredicate = new Predicate[categories.size()];
            for (int i = 0; i < categories.size(); i++) {
                categoriesPredicate[i] = cb.equal(product.get("category"), categories.get(i));
            }
            predicates.add(cb.or(categoriesPredicate));
        }
    }
}
