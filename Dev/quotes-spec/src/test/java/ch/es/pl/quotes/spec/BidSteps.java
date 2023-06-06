/*
package ch.es.pl.quotes.spec;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class BidSteps {

    private static final String BASE_URL = "http://localhost:9090/api"; // Remplacez ceci par l'URL de base de votre API
    private Integer bidId;
    private ResponseEntity<String> response;
    private HttpClientErrorException httpClientErrorException;
    private String token;
    private String bidInfo;

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

    @Then("le code de statut de la réponse devrait être {int}")
    public void the_response_status_code_should_be(int statusCode) {
        if (this.httpClientErrorException != null) {
            assertThat(this.httpClientErrorException.getStatusCode().value()).isEqualTo(statusCode);
        } else {
            assertThat(this.response.getStatusCodeValue()).isEqualTo(statusCode);
        }
    }

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
}*/
