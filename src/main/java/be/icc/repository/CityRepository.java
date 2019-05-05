package be.icc.repository;

import be.icc.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Scohier Dorian on 11-12-18.
 */
public interface CityRepository extends JpaRepository<City,Long> {

    City findByNameAndCountry(String name, String country);

}
