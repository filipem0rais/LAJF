//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 16.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.CategoryEntity;
import ch.es.pl.quotes.api.repositories.CategoryRepository;
import org.openapitools.api.CategoriesApi;
import org.openapitools.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController implements CategoriesApi {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryEntity -> {
            Category category = new Category();
            category.setIdCategory(categoryEntity.getIdCategory());
            category.setCatName(categoryEntity.getCatName());
            categories.add(category);
        });
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getCategory(Integer id) {
        Optional<CategoryEntity> opt = categoryRepository.findById(id);
        if (opt.isPresent()) {
            CategoryEntity categoryEntity = opt.get();
            Category category = new Category();
            category.setIdCategory(categoryEntity.getIdCategory());
            category.setCatName(categoryEntity.getCatName());
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCatName(category.getCatName());
        categoryRepository.save(categoryEntity);
        category.setIdCategory(categoryEntity.getIdCategory());
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Integer id) {
        Optional<CategoryEntity> opt = categoryRepository.findById(id);
        if (opt.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category) {
        Optional<CategoryEntity> opt = categoryRepository.findById(category.getIdCategory());
        if (opt.isPresent()) {
            CategoryEntity categoryEntity = opt.get();
            categoryEntity.setCatName(category.getCatName());
            categoryRepository.save(categoryEntity);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
