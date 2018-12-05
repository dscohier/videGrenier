package be.icc.dto;

import be.icc.entity.Panier;

import java.util.HashSet;
import java.util.Set;

public class PanierDto {
    private Long id;
    private Set<ProductDto> products;

    public PanierDto() {
    }
    
    public Panier toEntity(){
        Panier panier= new Panier();
        panier.setId(this.getId());
        for (ProductDto product: this.getProducts()) {
            panier.getProducts().add(product.toEntity());
        }
        return panier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductDto> getProducts() {
        if (products == null) {
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
