package be.icc.service.imp;

import be.icc.dto.CityDto;
import be.icc.entity.City;
import be.icc.repository.CityRepository;
import be.icc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Student on 11-12-18.
 */
@Service
@Transactional
public class CityServiceImp implements CityService {


    @Autowired
    CityRepository cityRepository;


    @Override
    public CityDto add(CityDto city) {
        return cityRepository.save(city.toEntity()).toDto();
    }

    @Override
    public CityDto createOrGetIfExists(String name, String stateName, String countryName) {
        City foundCity = cityRepository.findByName(name.toUpperCase());
        if (foundCity == null) {
            City newCity = new City();
            newCity.setName(name.toUpperCase());
            return cityRepository.save(newCity).toDto();
        } else {
            return foundCity.toDto();
        }
    }

    @Override
    public CityDto findById(Long id) {
        return cityRepository.findOne(id).toDto();
    }

    @Override
    public Set<CityDto> findAll() {
        Set<CityDto> cities=new HashSet<>();
        for (City c:cityRepository.findAll()) {
            cities.add(c.toDto());
        }
        return cities;
    }
}
