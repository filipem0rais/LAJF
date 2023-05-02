//
// Auteur : Filipe Dias Morais
// Projet : LAJF-API
// Date   : 02.05.2023
// 


package org.lajf.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.lajf.model.User;
import org.lajf.model.dto.UserUpdateDTO;
import org.lajf.repository.UserRepository;
import org.lajf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Obtenir tous les utilisateurs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Utilisateurs non trouvés")
            })
    @GetMapping
    public ResponseEntity<List<UserUpdateDTO>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserUpdateDTO> userUpdateDTOs = users.stream()
                    .map(UserUpdateDTO::fromUser)
                    .collect(Collectors.toList());

            if (userUpdateDTOs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userUpdateDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

@Operation(summary = "Obtenir un utilisateur par son id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> getUserById(@PathVariable int id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User user = userData.get();
            UserUpdateDTO userUpdateDTO = UserUpdateDTO.fromUser(user);
            return new ResponseEntity<>(userUpdateDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Créer un nouvel utilisateur",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Utilisateur créé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateDTO.class))),
                    @ApiResponse(responseCode = "409", description = "L'email existe déjà")
            })
    @PostMapping
    public ResponseEntity<UserUpdateDTO> createUser(@RequestBody UserUpdateDTO userUpdate) {
        if (userRepository.existsByEmail(userUpdate.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Renvoie un statut HTTP 409 (CONFLICT) si l'email existe déjà
        } else {
            User newUser = new User();

            // Copiez les attributs de userUpdate vers newUser
            newUser.setName(userUpdate.getName());
            newUser.setLastName(userUpdate.getLastName());
            newUser.setEmail(userUpdate.getEmail());
            newUser.setPassword(userUpdate.getPassword());
            newUser.setCredit(userUpdate.getCredit());

            // Enregistrez le nouvel utilisateur
            User updatedUser = userRepository.save(newUser);
            UserUpdateDTO updatedUserDTO = UserUpdateDTO.fromUser(updatedUser);

            return new ResponseEntity<>( updatedUserDTO, HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Mettre à jour un utilisateur",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            })
    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable int id, @RequestBody UserUpdateDTO userUpdate) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).orElse(null);

            assert user != null;
            user.setName(userUpdate.getName());
            user.setLastName(userUpdate.getLastName());
            user.setEmail(userUpdate.getEmail());
            user.setPassword(userUpdate.getPassword());
            user.setCredit(userUpdate.getCredit());

            // Enregistrez les modifications
            User updatedUser = userRepository.save(user);
            UserUpdateDTO updatedUserDTO = UserUpdateDTO.fromUser(updatedUser);

            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Supprimer un utilisateur",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Utilisateur supprimé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
