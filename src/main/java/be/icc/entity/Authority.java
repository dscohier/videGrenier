package be.icc.entity;

import be.icc.dto.AuthorityDto;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    public Authority() {
    }
    public AuthorityDto toDto(){
        AuthorityDto aut= new AuthorityDto();
        aut.setId(this.getId());
        aut.setAuthority(this.getAuthority());
        return aut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
