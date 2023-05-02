package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.QuoteEntity;
import ch.es.pl.quotes.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
}