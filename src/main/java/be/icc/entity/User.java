package be.icc.entity;

import be.icc.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique=true)
    private String email;
    @Column(nullable = false, unique=true)
    private String username;
    @Column
    private String picture;
    @Column(nullable = false)
    private double averageRatingSeller;
    @Column(nullable = false)
    private double averageRatingBuyer;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> commentByBuyer;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> commentBySeller;
    @OneToOne
    private Panier panier;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Product> productToSell;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bidder> bidders;
    @Column(nullable = false)
    private Date creationDate;
    @ManyToOne
    private City city;

    public User() {
    }
    public UserDto toDto(){
        UserDto user= new UserDto();
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
        for(Comment comment : getCommentByBuyer()) {
            user.getMyComments().add(comment.toDto());
        }
        for(Comment comment : getCommentBySeller()) {
            user.getMyAppreciations().add(comment.toDto());
        }

        for(Product product : getProductToSell()) {
            user.getProductToSell().add(product.toDto());
        }
        for(Bidder bidder : getBidders()) {
            user.getBidders().add(bidder.toDto());
        }
        if(getPanier()!=null)  user.setPanier(this.getPanier().toDto());
        if(getCity()!=null)  user.setCity(this.getCity().toDto());
        user.setCreationDate(this.getCreationDate());
        return user;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Set<Authority> getAuthorities() {
        if(authorities==null){
            authorities=new HashSet<>();
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Comment> getCommentByBuyer() {
        if(commentByBuyer ==null){
            commentByBuyer =new HashSet<>();
        }
        return commentByBuyer;
    }

    public void setCommentByBuyer(Set<Comment> commentByBuyer) {
        this.commentByBuyer = commentByBuyer;
    }

    public Set<Comment> getCommentBySeller() {
        if(commentBySeller ==null){
            commentBySeller =new HashSet<>();
        }
        return commentBySeller;
    }

    public void setCommentBySeller(Set<Comment> commentBySeller) {
        this.commentBySeller = commentBySeller;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Set<Product> getProductToSell() {
        if (productToSell == null) {
            productToSell = new HashSet<>();
        }
        return productToSell;
    }

    public void setProductToSell(Set<Product> productToSell) {
        this.productToSell = productToSell;
    }

    public Set<Bidder> getBidders() {
        if (bidders == null) {
            bidders = new HashSet<>();
        }
        return bidders;
    }

    public void setBidders(Set<Bidder> bidders) {
        this.bidders = bidders;
    }

    @Override
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
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
