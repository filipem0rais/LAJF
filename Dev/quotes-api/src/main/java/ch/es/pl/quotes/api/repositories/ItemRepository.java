//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 15.05.2023
// 


package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.CategoryEntity;
import ch.es.pl.quotes.api.entities.ItemEntity;
import ch.es.pl.quotes.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> findByCategory(CategoryEntity category);

    @Query("SELECT i FROM ItemEntity i WHERE i.user = :user AND i.iteOnSale = false AND i.bids IS EMPTY")
    List<ItemEntity> findUnsoldItemsByUser(@Param("user") UserEntity user);


    @Query("SELECT i FROM ItemEntity i WHERE i.iteOnSale = true ORDER BY i.iteDatePublication DESC")
    List<ItemEntity> findLastItemsOnSale(@Param("limit") Integer limit);


    List<ItemEntity> findByCategoryAndIteOnSale(CategoryEntity categoryEntity, boolean b);

    List<ItemEntity> findByIteOnSale(boolean b);

    @Query("SELECT i FROM ItemEntity i JOIN i.bids b WHERE b.user = :user AND i.iteOnSale = true")
    List<ItemEntity> findItemsOnSaleWithUserBid(UserEntity user);

    @Query("SELECT i FROM ItemEntity i JOIN i.bids b WHERE b.user = :user AND i.iteOnSale = false AND i.itePickedUp = false AND b.bidAmount = (SELECT MAX(b2.bidAmount) FROM BidEntity b2 WHERE b2.item = i)")
    List<ItemEntity> findWonItemsNotPickedUp(UserEntity user);

    List<ItemEntity> findByUserAndIteOnSaleAndItePickedUp(UserEntity user, Boolean iteOnSale, Boolean itePickedUp);


    List<ItemEntity> findByUserAndIteOnSale(UserEntity user, boolean b);
}
