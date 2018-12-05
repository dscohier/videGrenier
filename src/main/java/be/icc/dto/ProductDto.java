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
    private Set<CategoryDto> categories;
    private String name;
    private String description;
    private long price;
    private boolean isAuction;
    private boolean isSell;
    private Date endDate;
    private Set<UserDto> bidders;

    public ProductDto() {
    }

    public Product toEntity(){
        Product product = new Product();
        for(CategoryDto category : getCategories()) {
            product.getCategories().add(category.toEntity());
        }
        for(UserDto user : getBidders()) {
            product.getBidders().add(user.toEntity());
        }
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

    public Set<CategoryDto> getCategories() {
        if(categories == null){
            categories = new HashSet();
        }
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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
        return bidders;
    }

    public void setBidders(Set<UserDto> bidders) {
        this.bidders = bidders;
    }
}
