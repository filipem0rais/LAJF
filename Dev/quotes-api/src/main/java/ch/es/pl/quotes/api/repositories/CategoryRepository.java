//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 16.05.2023
// 


package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    List<CategoryEntity> findByCatParent(CategoryEntity categoryEntity);
}
