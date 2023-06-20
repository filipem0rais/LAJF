Feature: Gérer les enchères

  Scenario: Récupérer une enchère avec un ID valide
    Given que je dispose d'un ID d'enchère valide
    When j'envoie une requete GET a /bids/1
    Then le code de statut de la réponse devrait être 200
    And le corps de la réponse devrait contenir une enchère avec l'ID donné

  Scenario: Tenter de récupérer une enchère avec un ID invalide
    Given que je dispose d'un ID d'enchère invalide
    When j'envoie une requete GET a /bids/999
    Then le code de statut de la réponse devrait être 404
    And le corps de la réponse devrait contenir une erreur indiquant que l'enchère n'a pas été trouvée

  Scenario: Créer une nouvelle enchère
    Given que je dispose d'un token d'utilisateur valide et des informations d'enchère valides
    When j'envoie une requete POST a /bids avec le token et les informations d'enchère
    Then le code de statut de la bidréponse devrait être 201
    And le corps de la bidréponse devrait contenir l'ID de la nouvelle enchère