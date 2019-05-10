package be.icc.service;

import be.icc.controller.CategoryEnum;
import be.icc.dto.CategoryDto;

import java.util.Set;

/**
 * Created by Scohier Dorian on 02-01-19.
 */
public interface CategoryService {

    CategoryDto createOrGetIfExists(CategoryEnum category);
    Set<CategoryDto> findAll();
}
