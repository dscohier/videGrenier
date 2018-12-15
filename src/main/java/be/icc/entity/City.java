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
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CityDto toDto() {
        CityDto cityDto = new CityDto();
        cityDto.setId(this.getId());
        cityDto.setName(this.getName());
        cityDto.setState(this.getState());
        return cityDto;
    }
}
