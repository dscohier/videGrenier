package be.icc.entity;

import be.icc.dto.CityDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;

    public City() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CityDto toDto() {
        CityDto cityDto = new CityDto();
        cityDto.setId(this.getId());
        cityDto.setName(this.getName());
        cityDto.setCountry(this.getCountry());
        return cityDto;
    }
}
