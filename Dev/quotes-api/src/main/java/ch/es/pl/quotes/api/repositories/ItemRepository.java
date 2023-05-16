//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 15.05.2023
// 


package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
}
