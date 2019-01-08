package be.icc.service;

import be.icc.controller.CategoryEnum;
import be.icc.dto.CategoryDto;

/**
 * Created by Student on 02-01-19.
 */
public interface CategoryService {

    CategoryDto createOrGetIfExists(CategoryEnum category);
}
