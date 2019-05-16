package be.icc.entity;

import be.icc.dto.CategoryDto;
import be.icc.enumClass.CategoryEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Product> products;
    @Column(nullable = false)
    private CategoryEnum category;

    public Category() {
    }
    public CategoryDto toDto(){
        CategoryDto category = new CategoryDto();
        for(Product product : getProducts()) {
            category.getProducts().add(product.toDto());
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

    public Set<Product> getProducts() {
        if (products == null) {
            products = new HashSet();
        }
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
