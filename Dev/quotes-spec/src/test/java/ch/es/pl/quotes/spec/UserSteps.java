package ch.es.pl.quotes.spec;//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps {

    private static final String BASE_URL = "http://localhost:9090/api"; // Remplacez ceci par l'URL de base de votre API
    private Integer userId;
    private ResponseEntity<String> response;
    private HttpClientErrorException httpClientErrorException;


    @Given("que je dispose d'un ID d'utilisateur valide")
    public void i_have_a_valid_user_id() {
        this.userId = 5; // TODO: replace this with a valid user ID

    }

    @Given("que je dispose d'un ID d'utilisateur invalide")
    public void i_have_an_invalid_user_id() {
        this.userId = 999; // TODO: replace this with an invalid user ID

    }

    @When("j'envoie une requete GET a \\/users\\/{int}")
    public void jEnvoieUneRequeteGETAUsers(int id) {
        String url = BASE_URL + "/users/" + id;
        RestTemplate restTemplate = new RestTemplate();
        try {
            this.response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }


    @Then("le code de statut de la réponse devrait être {int}")
    public void the_response_status_code_should_be(int statusCode) {
        if (this.httpClientErrorException != null) {
            assertThat(this.httpClientErrorException.getStatusCode().value()).isEqualTo(statusCode);
        } else {
            assertThat(this.response.getStatusCodeValue()).isEqualTo(statusCode);
        } if (this.httpClientErrorException != null) {
            assertThat(this.httpClientErrorException.getStatusCode().value()).isEqualTo(statusCode);
        } else {
            assertThat(this.response.getStatusCodeValue()).isEqualTo(statusCode);
        }
    }

    @Then("le corps de la réponse devrait contenir un utilisateur avec l'ID donné")
    public void the_response_body_should_contain_a_user_with_the_given_id() {
        assertThat(this.response.getBody()).contains("\"id\":" + this.userId);

    }

    @Then("le corps de la réponse devrait contenir une erreur indiquant que l'utilisateur n'a pas été trouvé")
    public void the_response_body_should_contain_an_error_indicating_the_user_was_not_found() {
        assertThat(this.httpClientErrorException.getMessage()).contains("Not Found");

    }



}
