//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.exceptions.UserNotFoundException;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.openapitools.api.UsersApi;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;


    @Override
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authHeader) {
        Optional<UserEntity> opt = userRepository.findById(getUserIdFromToken(authHeader));
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
            throw new UserNotFoundException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    private Integer getUserIdFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return claims.getBody().get("id", Integer.class);
    }
}
