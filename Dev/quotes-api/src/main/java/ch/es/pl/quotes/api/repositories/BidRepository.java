//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 

package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.BidEntity;
import ch.es.pl.quotes.api.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BidRepository extends JpaRepository<BidEntity, Integer> {
    @Query("SELECT MAX(b.bidAmount) FROM BidEntity b WHERE b.item = :item")
    Double findHighestBidAmountByItem(@Param("item") ItemEntity item);
}
