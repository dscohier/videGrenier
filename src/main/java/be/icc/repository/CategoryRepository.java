package be.icc.repository;

import be.icc.entity.Category;
import be.icc.enumClass.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Scohier Dorian on 03-01-19.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategory(CategoryEnum category);
}
