package be.icc.dto;

import be.icc.entity.Order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    private Long id;
    private Set<ProductDto> products;
    private Date date;

    public OrderDto() {
    }

    public Order toEntity(){
        Order order= new Order();
        order.setId(this.getId());
        for (ProductDto product: this.getProducts()) {
            order.getProducts().add(product.toEntity());
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

    public Set<ProductDto> getProducts() {
        if (products == null) {
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
