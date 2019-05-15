package be.icc.repository;

import be.icc.controller.CategoryEnum;
import be.icc.controller.TypeOfSaleEnum;
import be.icc.dto.ProductDto;
import be.icc.entity.Category;
import be.icc.entity.City;
import be.icc.entity.Product;
import be.icc.entity.User;
import be.icc.form.FilterForm;
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
    public List<ProductDto> findProductsByCriteria(FilterForm filterForm) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.and(cb.equal(product.get("isSell"), false)));
        whereCategorieIn(filterForm, cb, predicates, product);
        whereIsAuction(filterForm, cb, predicates, product);
        whereCityIs(filterForm, cb, predicates, product);
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(product.get("creationDate")));

        List<Product> products = em.createQuery(cq).getResultList();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product productEntity : products) {
            productDtos.add(productEntity.toDto());
        }
        return productDtos;
    }

    private void whereCityIs(FilterForm filterForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (isNotBlank(filterForm.getCity()) && isNotBlank(filterForm.getCountry())) {
            City city = cityRepository.findByNameAndCountry(filterForm.getCity().split(",")[0], filterForm.getCountry());
            Join<Product, User> user = product.join("seller");
            predicates.add(cb.and(cb.equal(user.get("city"), city)));
        }
    }

    private void whereIsAuction(FilterForm filterForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (filterForm.getTypeOfSale().length != 0 && filterForm.getTypeOfSale().length != 2) {
            TypeOfSaleEnum typeOfSale = TypeOfSaleEnum.valueOf(filterForm.getTypeOfSale()[0]);
            if (typeOfSale == TypeOfSaleEnum.DIRECT_SALE) {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), false)));
            } else {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), true)));
            }
        }
    }

    private void whereCategorieIn(FilterForm filterForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        List<CategoryEnum> categoryEnums = new ArrayList<>();
        for (String categorie : filterForm.getCategories()) {
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
