package be.icc.entity;

import be.icc.dto.CityDto;

import javax.persistence.*;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

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

    public CityDto toDto() {
        CityDto cityDto = new CityDto();
        cityDto.setId(this.getId());
        cityDto.setName(this.getName());
        return cityDto;
    }
}
