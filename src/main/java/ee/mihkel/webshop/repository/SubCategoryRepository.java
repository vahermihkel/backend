package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.model.Category;
import ee.mihkel.webshop.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> getAllByCategory(Category category);
}
