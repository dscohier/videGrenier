package be.icc.dto;

import be.icc.entity.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
public class ProductDto {
    private Long id;
    private CategoryDto category;
    private String name;
    private String description;
    private String picture;
    private double price;
    private boolean isAuction;
    private boolean isSell;
    private Date endDate;
    private Set<UserDto> bidders;
    private UserDto seller;

    public ProductDto() {
    }

    public Product toEntity(){
        Product product = new Product();
        product.setCategory(this.getCategory().toEntity());
        for(UserDto user : getBidders()) {
            product.getBidders().add(user.toEntity());
        }
        product.setSeller(this.getSeller().toEntity());
        product.setPicture(this.getPicture());
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setAuction(this.isAuction());
        product.setSell(this.isSell());
        product.setEndDate(this.getEndDate());
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAuction() {
        return isAuction;
    }

    public void setAuction(boolean auction) {
        isAuction = auction;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<UserDto> getBidders() {
        if (bidders == null) {
            bidders = new HashSet<>();
        }
        return bidders;
    }

    public void setBidders(Set<UserDto> bidders) {
        this.bidders = bidders;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }
}
