package be.icc.repository;

import be.icc.controller.CategoryEnum;
import be.icc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Student on 03-01-19.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategory(CategoryEnum category);
}
