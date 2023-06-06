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

import java.util.Optional;

public interface BidRepository extends JpaRepository<BidEntity, Integer> {
    @Query("SELECT MAX(b.bidAmount) FROM BidEntity b WHERE b.item = :item")
    Double findHighestBidAmountByItem(@Param("item") ItemEntity item);

    @Query("SELECT b FROM BidEntity b WHERE b.item = :item AND b.bidAmount = (SELECT MAX(b2.bidAmount) FROM BidEntity b2 WHERE b2.item = b.item)")
    Optional<BidEntity> findHighestBidByItem(@Param("item") ItemEntity item);


    Integer countBidsByItem(ItemEntity itemEntity);


}
