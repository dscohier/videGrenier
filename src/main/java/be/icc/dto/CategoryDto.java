package be.icc.dto;

import be.icc.entity.Category;
import be.icc.enumClass.CategoryEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
public class CategoryDto {

    private Long id;
    private Set<ProductDto> products;
    private CategoryEnum category;

    public CategoryDto() {
    }
    public Category toEntity(){
        Category category = new Category();
        for(ProductDto product : getProducts()) {
            category.getProducts().add(product.toEntity());
        }
        category.setId(this.getId());
        category.setCategory(this.getCategory());
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductDto> getProducts() {
        if (products == null) {
            products = new HashSet();
        }
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
