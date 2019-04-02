package be.icc.dto;

import be.icc.entity.Authority;
import be.icc.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

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
public class UserDto implements UserDetails {
    private Long id;
    private Set<Authority> authorities;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String picture;
    private double averageRatingSeller;
    private double averageRatingBuyer;
    private Set<CommentDto> commentByBuyer;
    private Set<CommentDto> commentBySeller;
    private PanierDto panier;
    private Set<ProductDto> productToSell;
    private Set<BidderDto> bidders;
    private CityDto city;
    private Date creationDate;

    public User toEntity(){
        User user= new User();
        user.setId(this.getId());
        user.setPicture(this.getPicture());
        for (Authority a: this.getAuthorities()) {
            user.getAuthorities().add(a);
        }
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        user.setAverageRatingBuyer(this.getAverageRatingBuyer());
        user.setAverageRatingSeller(this.getAverageRatingSeller());
        for(CommentDto comment : getCommentByBuyer()) {
            user.getCommentByBuyer().add(comment.toEntity());
        }
        for(CommentDto comment : getCommentBySeller()) {
            user.getCommentBySeller().add(comment.toEntity());
        }
        for(ProductDto product : getProductToSell()) {
            user.getProductToSell().add(product.toEntity());
        }
        for(BidderDto bidder : getBidders()) {
            user.getBidders().add(bidder.toEntity());
        }
        if(getPanier()!=null)  user.setPanier(this.getPanier().toEntity());
        if(getCity()!=null)  user.setCity(this.getCity().toEntity());
        user.setCreationDate(this.getCreationDate());
        return user;
    }


    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        if(authorities==null){
            authorities=new HashSet<>();
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public Set<CommentDto> getCommentByBuyer() {
        if (commentByBuyer == null) {
            commentByBuyer = new HashSet<>();
        }
        return commentByBuyer;
    }

    public void setCommentByBuyer(Set<CommentDto> commentByBuyer) {
        this.commentByBuyer = commentByBuyer;
    }

    public Set<CommentDto> getCommentBySeller() {
        if (commentBySeller == null) {
            commentBySeller = new HashSet<>();
        }
        return commentBySeller;
    }

    public void setCommentBySeller(Set<CommentDto> commentBySeller) {
        this.commentBySeller = commentBySeller;
    }

    public PanierDto getPanier() {
        return panier;
    }

    public void setPanier(PanierDto panier) {
        this.panier = panier;
    }

    public Set<ProductDto> getProductToSell() {
        if (productToSell == null) {
            productToSell = new HashSet<>();
        }
        return productToSell;
    }

    public void setProductToSell(Set<ProductDto> productToSell) {
        this.productToSell = productToSell;
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

    public double getAverageRatingSeller() {
        return averageRatingSeller;
    }

    public void setAverageRatingSeller(double averageRatingSeller) {
        this.averageRatingSeller = averageRatingSeller;
    }

    public double getAverageRatingBuyer() {
        return averageRatingBuyer;
    }

    public void setAverageRatingBuyer(double averageRatingBuyer) {
        this.averageRatingBuyer = averageRatingBuyer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (!id.equals(userDto.id)) return false;
        return email.equals(userDto.email);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
