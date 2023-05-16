//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 15.05.2023
// 


package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.CategoryEntity;
import ch.es.pl.quotes.api.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> findByCategory(CategoryEntity category);
}
