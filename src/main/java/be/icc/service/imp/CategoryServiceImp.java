package be.icc.service.imp;

import be.icc.controller.CategoryEnum;
import be.icc.dto.CategoryDto;
import be.icc.entity.Category;
import be.icc.repository.CategoryRepository;
import be.icc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
@Service
@Transactional
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto createOrGetIfExists(CategoryEnum category) {

        Category foundCategory = categoryRepository.findByCategory(category);
        if (foundCategory == null) {
            Category newCategory = new Category();
            newCategory.setCategory(category);
            return categoryRepository.save(newCategory).toDto();
        } else {
            return foundCategory.toDto();
        }
    }

    @Override
    public Set<CategoryDto> findAll() {
        Set<CategoryDto> categories = new HashSet<>();
        for (Category c : categoryRepository.findAll()) {
            categories.add(c.toDto());
        }
        return categories;
    }
}
