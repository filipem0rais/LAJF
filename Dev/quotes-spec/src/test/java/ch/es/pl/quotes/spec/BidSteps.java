package ch.es.pl.quotes.spec;//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
//

import ch.es.pl.quotes.api.entities.BidEntity;
import ch.es.pl.quotes.api.repositories.BidRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidSteps {

    @Autowired
    private BidRepository bidRepository;

    private Integer bidId;
    private ResponseEntity<BidEntity> response;

    @Given("que je dispose d'un ID d'offre valide")
    public void que_je_dispose_d_un_ID_d_offre_valide() {
        // vous devez avoir une façon de créer ou de récupérer un ID d'offre valide
        bidId = //get or create valid bid ID;
    }

    @Given("que je dispose d'un ID d'offre invalide")
    public void que_je_dispose_d_un_ID_d_offre_invalide() {
        // pour un ID invalide, vous pouvez simplement utiliser un ID qui n'existe pas dans votre base de données
        bidId = 999999;
    }

    @When("j'envoie une requête GET à {string}")
    public void j_envoie_une_requete_GET_a(String path) {
        // Vous devez adapter cette partie en fonction de la manière dont vous faites les requêtes HTTP dans votre code
        response = // HTTP GET request to path + bidId;
    }

    @Then("le code de statut de la réponse devrait être {int}")
    public void le_code_de_statut_de_la_reponse_devrait_etre(Integer expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCodeValue());
    }

    @Then("le corps de la réponse devrait contenir une offre avec l'ID donné")
    public void le_corps_de_la_reponse_devrait_contenir_une_offre_avec_l_ID_donné() {
        assertTrue(response.hasBody());
        assertEquals(bidId, response.getBody().getIdBid());
    }

    @Then("le corps de la réponse devrait contenir une erreur indiquant que l'offre n'a pas été trouvée")
    public void le_corps_de_la_reponse_devrait_contenir_une_erreur_indiquant_que_l_offre_n_a_pas_ete_trouvee() {
        // Cette assertion dépendra de la façon dont votre API retourne les erreurs
        // Par exemple, si vous renvoyez un objet d'erreur spécifique, vous pouvez vérifier s'il est présent dans le corps de la réponse
        assertTrue(response.hasBody());
        assertEquals("Bid not found", response.getBody().getErrorMessage());
    }

    // Continuez de cette façon pour les autres étapes de vos scénarios
}
