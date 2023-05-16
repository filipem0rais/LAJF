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
import org.openapitools.model.RegisterUser;
import org.openapitools.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        Optional<UserEntity> opt = userRepository.findByUseEmail(email);
        if (opt.isPresent()) {
            UserEntity userEntity = opt.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(password, userEntity.getUsePassword())) {
                return ResponseEntity.badRequest().build();
            }
            String token = Jwts.builder()
                    .setSubject(userEntity.getUseEmail())
                    .claim("id", userEntity.getIdUser())
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

    @Override
    public ResponseEntity<Void> registerUser(RegisterUser user) {

        Optional<UserEntity> opt = userRepository.findByUseEmail(user.getUseEmail());
        if (opt.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUseName(user.getUseName());
        userEntity.setUseLastName(user.getUseLastName());
        userEntity.setUseEmail(user.getUseEmail());


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getUsePassword());
        userEntity.setUsePassword(hashedPassword); // Set hashed password


        userEntity.setUseCredit(0);

        userEntity = userRepository.save(userEntity);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


}
