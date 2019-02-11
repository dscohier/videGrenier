package be.icc.form;

import be.icc.controller.CategoryEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Created by Student on 18-12-18.
 */
public class AddProductForm {
    private CategoryEnum category;
    @NotBlank(message = "{error.notBlank}")
    private String name;
    @NotBlank(message = "{error.notBlank}")
    private String description;

    private MultipartFile file;

    @Min(value = 1, message = "{error.add.min1}")
    private double price;

    private String auctionOrFixPrice;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date endDate;
    private boolean isEndDateCorrect;

    private Long id;

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MultipartFile  getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuctionOrFixPrice() {
        return auctionOrFixPrice;
    }

    public void setAuctionOrFixPrice(String auctionOrFixPrice) {
        this.auctionOrFixPrice = auctionOrFixPrice;
    }

    @AssertTrue(message = "{error.add.endDateInvalid}")
    public boolean getIsEndDateCorrect() {
        if("auction".equals(getAuctionOrFixPrice())) {
            if(endDate == null || endDate.before(new Date())) {
               isEndDateCorrect = false;
            } else {
                isEndDateCorrect = true;
            }
        } else {
            isEndDateCorrect = true;
        }
        return isEndDateCorrect;
    }

    public void setEndDateCorrect(boolean endDateCorrect) {
        isEndDateCorrect = endDateCorrect;
    }
}