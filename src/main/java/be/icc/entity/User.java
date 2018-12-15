package be.icc.entity;

import be.icc.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
    @ManyToMany
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

    @OneToMany
    private Set<Comment> myComments;
    @OneToMany
    private Set<Comment> myAppreciations;
    @OneToOne
    private Panier panier;
    @OneToMany
    private Set<Product> productToSell;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "HistoryAuctionned",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> auctionedProduct;

    @ManyToOne
    private City city;

    public User() {
    }
    public UserDto toDto(){
        UserDto user= new UserDto();
        user.setId(this.getId());
        for (Authority a: this.getAuthorities()) {
            user.getAuthorities().add(a);
        }
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        for(Comment comment : getMyComments()) {
            user.getMyComments().add(comment.toDto());
        }
        for(Comment comment : getMyAppreciations()) {
            user.getMyAppreciations().add(comment.toDto());
        }

        for(Product product : getProductToSell()) {
            user.getProductToSell().add(product.toDto());
        }
        for(Product product : getAuctionedProduct()) {
            user.getAuctionedProduct().add(product.toDto());
        }
        if(getPanier()!=null)  user.setPanier(this.getPanier().toDto());
        if(getCity()!=null)  user.setCity(this.getCity().toDto());
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

    public Set<Comment> getMyComments() {
        if(myComments==null){
            myComments=new HashSet<>();
        }
        return myComments;
    }

    public void setMyComments(Set<Comment> myComments) {
        this.myComments = myComments;
    }

    public Set<Comment> getMyAppreciations() {
        if(myAppreciations==null){
            myAppreciations=new HashSet<>();
        }
        return myAppreciations;
    }

    public void setMyAppreciations(Set<Comment> myAppreciations) {
        this.myAppreciations = myAppreciations;
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

    public Set<Product> getAuctionedProduct() {
        if (auctionedProduct == null) {
            auctionedProduct = new HashSet<>();
        }
        return auctionedProduct;
    }

    public void setAuctionedProduct(Set<Product> auctionedProduct) {
        this.auctionedProduct = auctionedProduct;
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
