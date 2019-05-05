package be.icc.dto;

import be.icc.entity.City;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
public class CityDto {

    private Long id;
    private String name;
    private String country;

    public CityDto() {
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

    public City toEntity() {
        City city = new City();
        city.setId(this.getId());
        city.setName(this.getName());
        city.setCountry(this.getCountry());
        return city;
    }
}
