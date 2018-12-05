package be.icc.entity;

import be.icc.dto.OrderDto;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private Set<Product> products;
    @Column(nullable = false)
    private Date date;

    public Order() {
    }

    public OrderDto toDto(){
        OrderDto order = new OrderDto();
        order.setId(this.getId());
        for (Product product: this.getProducts()) {
            order.getProducts().add(product.toDto());
        }
        order.setDate(this.getDate());
        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        if(products == null){
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
