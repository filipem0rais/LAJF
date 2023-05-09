//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.exceptions.UserNotFoundException;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.openapitools.api.UsersApi;
import org.openapitools.model.Token;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class UsersController implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;


    @Override
    public ResponseEntity<User> getUser(Integer id) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            UserEntity userEntity = opt.get();
            User user = new User();
            user.setIdUser(userEntity.getIdUser());
            user.setUseName(userEntity.getUseName());
            user.setUseLastName(userEntity.getUseLastName());
            user.setUseEmail(userEntity.getUseEmail());
            user.setUseCredit(userEntity.getUseCredit());

            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new UserNotFoundException(id);
        }
    }




}
