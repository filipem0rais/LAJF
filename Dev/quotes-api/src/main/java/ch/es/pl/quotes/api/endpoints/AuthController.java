//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.openapitools.api.AuthApi;
import org.openapitools.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class AuthController implements AuthApi {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;



    @Override
    public ResponseEntity<Token> authenticateUser(String email, String password) {
        Optional<UserEntity> opt = userRepository.findByEmailAndPassword(email, password);
        if (opt.isPresent()) {
            UserEntity userEntity = opt.get();
            if (! password.equals(userEntity.getPassword())) {
                return ResponseEntity.badRequest().build();
            }
            String token = Jwts.builder()
                    .setSubject(userEntity.getEmail())
                    .claim("id", userEntity.getId())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
            Token tokenObj = new Token();
            tokenObj.setToken(token);
            return new ResponseEntity<Token>(tokenObj, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
