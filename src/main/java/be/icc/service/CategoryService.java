package be.icc.service;

import be.icc.dto.CategoryDto;
import be.icc.enumClass.CategoryEnum;

import java.util.Set;

/**
 * Created by Scohier Dorian on 02-01-19.
 */
public interface CategoryService {

    CategoryDto createOrGetIfExists(CategoryEnum category);
    Set<CategoryDto> findAll();
}
