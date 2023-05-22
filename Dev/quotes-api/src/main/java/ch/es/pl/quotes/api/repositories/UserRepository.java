package ch.es.pl.quotes.api.repositories;

import ch.es.pl.quotes.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByIdUser(int id);

    Optional<UserEntity> findByUseEmailAndUsePassword(String email, String password);

    Optional<UserEntity> findByUseEmail(String email);
}