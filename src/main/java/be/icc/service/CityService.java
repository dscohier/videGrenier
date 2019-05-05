package be.icc.service;

import be.icc.dto.CityDto;

import java.util.Set;

/**
 * Created by Scohier Dorian on 11-12-18.
 */
public interface CityService {

   CityDto add(CityDto city);
   CityDto createOrGetIfExists(String name, String country);
   CityDto findById(Long id);
   Set<CityDto> findAll();

}
