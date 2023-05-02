package ch.es.pl.quotes.spec;//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.BidhubApiApi;
import org.openapitools.client.api.QuotesEndPointApi;
import org.openapitools.client.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class UserSteps {

    private int userId;
    private int statusCode;

    private User user;

    private final BidhubApiApi api = new BidhubApiApi();


    @Given("I have a user with id {int}")
    public void iHaveAUserWithId(int userId) {
        this.userId = userId;
    }

    @When("I send a GET request to the \\/users\\/{int} endpoint")
    public void iSendAGETRequestToTheUsersEndpoint(String endpoint) {
            try {
                ApiResponse<User> response = api.getUserWithHttpInfo(userId);
                user = response.getData();
                statusCode = response.getStatusCode();
            } catch (ApiException e) {
                statusCode = e.getCode();
            }
    }

    @Then("I receive a {int} status code")
    public void iShouldGetTheUserWithId(int arg0) {
        assertEquals(arg0, statusCode);
    }

}
