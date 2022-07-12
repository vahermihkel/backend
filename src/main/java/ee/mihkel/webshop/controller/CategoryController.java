package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.Category;
import ee.mihkel.webshop.model.SubCategory;
import ee.mihkel.webshop.repository.CategoryRepository;
import ee.mihkel.webshop.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("sub-categories")
    public List<SubCategory> getSubCategories() {
        return subCategoryRepository.findAll();
    }

    @GetMapping("sub-categories/{categoryId}")
    public List<SubCategory> getSubCategories(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return subCategoryRepository.getAllByCategory(category);
    }

    @PostMapping("categories")
    public String addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return "Edukalt uus kategooria lisatud";
    }

    @PostMapping("sub-categories")
    public String addSubCategory(@RequestBody SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
        return "Edukalt uus alamkategooria lisatud";
    }
}
