//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.QuoteEntity;
import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.exceptions.QuoteNotFoundException;
import ch.es.pl.quotes.api.repositories.UserRepository;
import org.openapitools.api.UsersApi;
import org.openapitools.model.Quote;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsersController implements UsersApi {

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<User> getUser(Integer id) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            UserEntity userEntity = opt.get();
            User user = new User();
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setLastName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setCredit(userEntity.getCredit());

            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new QuoteNotFoundException(id);
        }
    }
}
