package be.icc.entity;

import be.icc.dto.PanierDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Product> products;

    public Panier() {
    }

    public PanierDto toDto(){
        PanierDto panier= new PanierDto();
        panier.setId(this.getId());
        for (Product product: this.getProducts()) {
            panier.getProducts().add(product.toDto());
        }
        return panier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        if (products == null) {
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
