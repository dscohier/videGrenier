package be.icc.dto;

import be.icc.entity.Product;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
    private long price;
    private boolean isAuction;
    private boolean isSell;
    private Date endDate;
    private Set<BidderDto> bidders;
    private Date creationDate;
    private UserDto seller;
    private long view;

    private transient UserDto buyer;

    public ProductDto() {
    }

    public Product toEntity(){
        Product product = new Product();
        product.setId(this.getId());
        product.setCategory(this.getCategory().toEntity());
        for(BidderDto bidder : getBidders()) {
            product.getBidders().add(bidder.toEntity());
        }
        product.setSeller(this.getSeller().toEntity());
        product.setPicture(this.getPicture());
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setAuction(this.isAuction());
        product.setSell(this.isSell());
        product.setEndDate(this.getEndDate());
        product.setCreationDate(this.getCreationDate());
        product.setView(this.getView());
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

    public Set<BidderDto> getBidders() {
        if (bidders == null) {
            bidders = new HashSet<>();
        }
        return bidders;
    }

    public void setBidders(Set<BidderDto> bidders) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String displayPicture() {
        String picture = "";
        try {
            String imgName = this.getPicture();
            BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);
            baos.flush();
            byte[] imageInByteArray = baos.toByteArray();
            baos.close();
            picture = DatatypeConverter.printBase64Binary(imageInByteArray);
        }catch(IOException e){
            System.out.println("Error: "+e);
        } finally {
            return picture;
        }
    }

    public UserDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDto buyer) {
        this.buyer = buyer;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }
}
