package be.icc.dto;

import be.icc.entity.Bidder;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
@Entity
public class BidderDto {

    private Long id;
    private UserDto user;
    private double price;
    private Date insertionDate;
    private Long productId;

    public BidderDto() {
    }

    public Bidder toEntity(){
        Bidder bidder = new Bidder();
        bidder.setId(this.getId());
        bidder.setPrice(this.getPrice());
        bidder.setProductId(this.getProductId());
        bidder.setUser(this.getUser().toEntity());
        bidder.setInsertionDate(this.getInsertionDate());
        return bidder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidderDto bidderDto = (BidderDto) o;
        return Objects.equals(id, bidderDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
