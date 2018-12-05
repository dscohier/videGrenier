package be.icc.dto;

import be.icc.entity.Authority;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
public class AuthorityDto {
    private Long id;
    private String authority;


    public AuthorityDto() {
    }

    public Authority toEntity(){
        Authority aut= new Authority();
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
