package ch.es.pl.quotes.spec;//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.RegisterUser;
import org.openapitools.model.LoginUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class UserSteps {

    private static final String BASE_URL = "http://localhost:9090/api"; // Remplacez ceci par l'URL de base de votre API
    private Integer userId;
    private ResponseEntity<String> response;
    private HttpClientErrorException httpClientErrorException;
    private Integer bidId;
    private String token;
    private String bidInfo;
    private String email;
    private String password;
    private String userInfo;


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
        }
        if (this.httpClientErrorException != null) {
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


    @Given("que je dispose d'un ID d'enchère valide")
    public void i_have_a_valid_bid_id() {
        this.bidId = 5; // TODO: replace this with a valid bid ID
    }

    @Given("que je dispose d'un ID d'enchère invalide")
    public void i_have_an_invalid_bid_id() {
        this.bidId = 999; // TODO: replace this with an invalid bid ID
    }

    @Given("que je dispose d'un token d'utilisateur valide et des informations d'enchère valides")
    public void i_have_a_valid_user_token_and_valid_bid_info() {
        this.token = "valid_token"; // TODO: replace this with a valid user token
        this.bidInfo = "valid_bid_info"; // TODO: replace this with valid bid info
    }

    @Given("que je dispose d'un token d'utilisateur invalide et des informations d'enchère valides")
    public void i_have_an_invalid_user_token_and_valid_bid_info() {
        this.token = "invalid_token"; // TODO: replace this with an invalid user token
        this.bidInfo = "valid_bid_info"; // TODO: replace this with valid bid info
    }

    @When("j'envoie une requete GET a \\/bids\\/{int}")
    public void i_send_a_get_request_to_bids(int id) {
        String url = BASE_URL + "/bids/" + id;
        RestTemplate restTemplate = new RestTemplate();
        try {
            this.response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

    @When("j'envoie une requete POST a \\/bids avec le token et les informations d'enchère")
    public void i_send_a_post_request_to_bids_with_the_token_and_bid_info() {
        String url = BASE_URL + "/bids";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(this.bidInfo, headers);
        try {
            this.response = restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

    @When("j'envoie une requete PUT a \\/bids\\/{int} avec le token et les informations d'enchère")
    public void i_send_a_put_request_to_bids_with_the_token_and_bid_info(int id) {
        String url = BASE_URL + "/bids/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(this.bidInfo, headers);
        try {
            this.response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

    @When("j'envoie une requete DELETE a \\/bids\\/{int} avec le token")
    public void i_send_a_delete_request_to_bids_with_the_token(int id) {
        String url = BASE_URL + "/bids/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
            this.response = null;
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

/*    @Then("le code de statut de la réponse devrait être {int}")
    public void the_response_status_code_should_be(int statusCode) {
        if (this.httpClientErrorException != null) {
            assertThat(this.httpClientErrorException.getStatusCode().value()).isEqualTo(statusCode);
        } else {
            assertThat(this.response.getStatusCodeValue()).isEqualTo(statusCode);
        }
    }*/

    @Then("le corps de la réponse devrait contenir une enchère avec l'ID donné")
    public void the_response_body_should_contain_a_bid_with_the_given_id() {
        assertThat(this.response.getBody()).contains("\"id\":" + this.bidId);
    }

    @Then("le corps de la réponse devrait contenir une erreur indiquant que l'enchère n'a pas été trouvée")
    public void the_response_body_should_contain_an_error_indicating_the_bid_was_not_found() {
        assertThat(this.httpClientErrorException.getMessage()).contains("Not Found");
    }

    @Then("le corps de la réponse devrait contenir l'ID de la nouvelle enchère")
    public void the_response_body_should_contain_the_id_of_the_new_bid() {
        // TODO: Implement this step
    }

    @Given("que je dispose d'informations d'utilisateur valides")
    public void queJeDisposeDInformationsDUtilisateurValides() {
        this.email = "email@example.com";
        this.password = "secretPassword";
    }

    @When("j'envoie une requete POST a \\/register avec les informations d'utilisateur")
    public void jEnvoieUneRequetePOSTARegisterAvecLesInformationsDUtilisateur() {
        String url = BASE_URL + "/auth/register";
        RestTemplate restTemplate = new RestTemplate();
        RegisterUser user = new RegisterUser();
        user.setUseEmail(this.email);
        user.setUsePassword(this.password);
        user.setUseName("John");
        user.setUseLastName("Doe");

        try {
            this.response = restTemplate.postForEntity(url, user, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

    @Given("que je dispose d'informations d'utilisateur avec un email déjà utilisé")
    public void queJeDisposeDInformationsDUtilisateurAvecUnEmailDejaUtilise() {
        this.email = "email@example.com";
        this.password = "secretPassword2";
    }

    @Given("que je dispose d'un email et d'un mot de passe valides")
    public void queJeDisposeDUnEmailEtDUnMotDePasseValides() {
        this.email = "email@example.com";
        this.password = "secretPassword";
    }

    @When("j'envoie une requete POST a \\/auth avec l'email et le mot de passe")
    public void jEnvoieUneRequetePOSTAAuthAvecLEmailEtLeMotDePasse() {
        String url = BASE_URL + "/auth/login";
        RestTemplate restTemplate = new RestTemplate();


        LoginUser request = new LoginUser();
        request.setUseEmail(this.email);
        request.setUsePassword(this.password);

        try {
            this.response = restTemplate.postForEntity(url, request, String.class);
        } catch (HttpClientErrorException e) {
            this.httpClientErrorException = e;
        }
    }

    @And("le corps de la réponse devrait contenir un token")
    public void leCorpsDeLaReponseDevraitContenirUnToken() {
        assertThat(this.response.getBody()).contains("token");
    }

    @Given("que je dispose d'un email valide et d'un mot de passe invalide")
    public void queJeDisposeDUnEmailValideEtDUnMotDePasseInvalide() {
        this.email = "email@example.com";
        this.password = "invalid_password";
    }
}
