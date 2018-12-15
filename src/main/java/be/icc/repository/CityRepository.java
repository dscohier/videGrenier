package be.icc.repository;

import be.icc.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Student on 11-12-18.
 */
public interface CityRepository extends JpaRepository<City,Long> {

    City findByName(String name);

}
