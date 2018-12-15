package be.icc.dto;

import be.icc.entity.Authority;
import be.icc.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

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
    
    private Set<CommentDto> myComments;
    private Set<CommentDto> myAppreciations;
    private PanierDto panier;
    private Set<ProductDto> productToSell;
    private Set<ProductDto> auctionedProduct;
    private CityDto city;

    public User toEntity(){
        User user= new User();
        user.setId(this.getId());
        for (Authority a: this.getAuthorities()) {
            user.getAuthorities().add(a);
        }
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        for(CommentDto comment : getMyComments()) {
            user.getMyComments().add(comment.toEntity());
        }
        for(CommentDto comment : getMyAppreciations()) {
            user.getMyAppreciations().add(comment.toEntity());
        }
        for(ProductDto product : getProductToSell()) {
            user.getProductToSell().add(product.toEntity());
        }
        for(ProductDto product : getAuctionedProduct()) {
            user.getAuctionedProduct().add(product.toEntity());
        }
        if(getPanier()!=null)  user.setPanier(this.getPanier().toEntity());
        if(getCity()!=null)  user.setCity(this.getCity().toEntity());
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

    public Set<CommentDto> getMyComments() {
        if (myComments == null) {
            myComments = new HashSet<>();
        }
        return myComments;
    }

    public void setMyComments(Set<CommentDto> myComments) {
        this.myComments = myComments;
    }

    public Set<CommentDto> getMyAppreciations() {
        if (myAppreciations == null) {
            myAppreciations = new HashSet<>();
        }
        return myAppreciations;
    }

    public void setMyAppreciations(Set<CommentDto> myAppreciations) {
        this.myAppreciations = myAppreciations;
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

    public Set<ProductDto> getAuctionedProduct() {
        if (auctionedProduct == null) {
            auctionedProduct = new HashSet<>();
        }
        return auctionedProduct;
    }

    public void setAuctionedProduct(Set<ProductDto> auctionedProduct) {
        this.auctionedProduct = auctionedProduct;
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
