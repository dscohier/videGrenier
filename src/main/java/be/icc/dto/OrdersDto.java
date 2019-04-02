package be.icc.dto;

import be.icc.entity.Orders;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrdersDto {
    private Long id;
    private Set<ProductDto> products;
    private Date date;
    private UserDto user;

    public OrdersDto() {
    }

    public Orders toEntity(){
        Orders order= new Orders();
        order.setId(this.getId());
        for (ProductDto product: this.getProducts()) {
            order.getProducts().add(product.toEntity());
        }
        order.setDate(this.getDate());
        order.setUser(this.getUser().toEntity());
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
