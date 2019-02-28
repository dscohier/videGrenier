package be.icc.entity;

import be.icc.dto.BidderDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Bidder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private double price;
    @Column(nullable = false, name = "product_id")
    private Long productId;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertionDate;

    public Bidder() {
    }

    public BidderDto toDto(){
        BidderDto bidderDto = new BidderDto();
        bidderDto.setId(this.getId());
        bidderDto.setPrice(this.getPrice());
        bidderDto.setUser(this.getUser().toDto());
        bidderDto.setInsertionDate(this.getInsertionDate());
        bidderDto.setProductId(this.getProductId());
        return bidderDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
}
