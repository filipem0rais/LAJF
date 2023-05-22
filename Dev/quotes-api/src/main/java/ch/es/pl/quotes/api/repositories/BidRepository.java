//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 

package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<BidEntity, Integer> {
}
