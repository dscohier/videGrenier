package be.icc.form;

import be.icc.enumClass.CategoryEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Scohier Dorian on 18-12-18.
 */
public class AddProductForm {
    private CategoryEnum category;
    @NotBlank(message = "{error.notBlank}")
    @Length(max = 40, message = "{error.addProduct.titleMaxLength}")
    private String name;
    @NotBlank(message = "{error.notBlank}")
    private String description;

    private MultipartFile file;

    private double price;
    private double priceAuction;

    private String auctionOrFixPrice;

    @DateTimeFormat(pattern = "dd/mm/yyyy hh:mm")
    private Date endDate;
    private String endDateString;
    private String endTimeString = "00:00";

    private boolean isEndDateCorrect;
    private boolean isPriceCorrect;

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
            if (!endTimeString.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                isEndDateCorrect = false;
                return isEndDateCorrect;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                endDate = dateFormat.parse(endDateString + " " + endTimeString);
            } catch (ParseException e) {
                isEndDateCorrect = false;
                return isEndDateCorrect;
            }
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

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public double getPriceAuction() {
        return priceAuction;
    }

    public void setPriceAuction(double priceAuction) {
        this.priceAuction = priceAuction;
    }

    @AssertTrue(message = "{error.add.min1}")
    public boolean getIsPriceCorrect() {
        isPriceCorrect = false;
        if ("auction".equals(auctionOrFixPrice)) {
            if (priceAuction >= 1) {
                isPriceCorrect = true;
                price = priceAuction;
            }
        } else {
            if (price >= 1) {
                isPriceCorrect = true;
            }
        }
        return isPriceCorrect;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }
}