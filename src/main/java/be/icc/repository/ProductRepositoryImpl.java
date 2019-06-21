package be.icc.repository;

import be.icc.dto.ProductDto;
import be.icc.entity.Category;
import be.icc.entity.City;
import be.icc.entity.Product;
import be.icc.entity.User;
import be.icc.enumClass.CategoryEnum;
import be.icc.enumClass.SellOrNotEnum;
import be.icc.enumClass.TypeOfSaleEnum;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> findProductsByCriteria(FilterProductsForm filterProductsForm, Pageable page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.and(cb.equal(product.get("isSell"), false)));
        if (filterProductsForm.getCategories() != null) {
            whereCategorieIn(filterProductsForm.getCategories(), cb, predicates, product);
        }
        if (filterProductsForm.getTypeOfSale() != null) {
            whereIsAuction(filterProductsForm.getTypeOfSale(), cb, predicates, product);
        }
        whereCityIs(filterProductsForm, cb, predicates, product);
        whereTitleLike(filterProductsForm, cb, predicates, product);

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(product.get("creationDate")));
        int totalRows = em.createQuery(cq).getResultList().size();
        List<Product> products = em.createQuery(cq).setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize()).getResultList();

        return new PageImpl<>(products, page, totalRows);
    }

    private List<ProductDto> productToDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product productEntity : products) {
            productDtos.add(productEntity.toDto());
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> findSalesByCriteria(FilterSalesForm filterSalesForm, User seller) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        whereIsSell(filterSalesForm.getSellOrNot(), cb, predicates, product);
        whereCategorieIn(filterSalesForm.getCategories(), cb, predicates, product);
        whereIsAuction(filterSalesForm.getTypeOfSale(), cb, predicates, product);
        predicates.add(cb.and(cb.equal(product.get("seller"), seller)));
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(product.get("creationDate")));

        List<Product> products = em.createQuery(cq).getResultList();

        return productToDto(products);
    }

    private void whereIsSell(String[] sellOrNot, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (sellOrNot.length != 0 && sellOrNot.length != 2) {
            SellOrNotEnum sellOrNotEnum = SellOrNotEnum.valueOf(sellOrNot[0]);
            if (sellOrNotEnum == SellOrNotEnum.SOLD) {
                predicates.add(cb.and(cb.equal(product.get("isSell"), true)));
            } else {
                predicates.add(cb.and(cb.equal(product.get("isSell"), false)));
            }
        }
    }

    private void whereCityIs(FilterProductsForm filterProductsForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (isNotBlank(filterProductsForm.getCity()) && isNotBlank(filterProductsForm.getCountry())) {
            City city = cityRepository.findByNameAndCountry(filterProductsForm.getCity().split(",")[0], filterProductsForm.getCountry());
            Join<Product, User> user = product.join("seller");
            predicates.add(cb.and(cb.equal(user.get("city"), city)));
        }
    }

    private void whereTitleLike(FilterProductsForm filterProductsForm, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (isNotBlank(filterProductsForm.getTitle())) {
            predicates.add(cb.and(cb.like(cb.upper(product.get("name")), "%" + filterProductsForm.getTitle() + "%")));
        }
    }

    private void whereIsAuction(String[] typeOfSaleName, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        if (typeOfSaleName.length != 0 && typeOfSaleName.length != 2) {
            TypeOfSaleEnum typeOfSale = TypeOfSaleEnum.valueOf(typeOfSaleName[0]);
            if (typeOfSale == TypeOfSaleEnum.DIRECT_SALE) {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), false)));
            } else {
                predicates.add(cb.and(cb.equal(product.get("isAuction"), true)));
            }
        }
    }

    private void whereCategorieIn(String[] categoriesName, CriteriaBuilder cb, List<Predicate> predicates, Root<Product> product) {
        List<CategoryEnum> categoryEnums = new ArrayList<>();
        for (String categorie : categoriesName) {
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
