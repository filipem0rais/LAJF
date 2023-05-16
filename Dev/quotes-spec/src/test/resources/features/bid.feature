Feature: Gérer les offres

  Scenario: Récupérer une offre avec un ID valide
    Given que je dispose d'un ID d'offre valide
    When j'envoie une requête GET à /bids/5
    Then le code de statut de la réponse devrait être 200
    And le corps de la réponse devrait contenir une offre avec l'ID donné

  Scenario: Tenter de récupérer une offre avec un ID invalide
    Given que je dispose d'un ID d'offre invalide
    When j'envoie une requête GET à /bids/999
    Then le code de statut de la réponse devrait être 404
    And le corps de la réponse devrait contenir une erreur indiquant que l'offre n'a pas été trouvée

  Scenario: Créer une nouvelle offre
    Given que je dispose des informations d'une nouvelle offre
    When j'envoie une requête POST à /bids avec les informations de l'offre
    Then le code de statut de la réponse devrait être 201
    And le corps de la réponse devrait contenir l'ID de la nouvelle offre créée

  Scenario: Tenter de créer une offre sans informations requises
    Given que je dispose d'informations incomplètes pour une nouvelle offre
    When j'envoie une requête POST à /bids avec les informations incomplètes
    Then le code de statut de la réponse devrait être 400
    And le corps de la réponse devrait contenir une erreur indiquant que les informations sont manquantes

  Scenario: Mettre à jour une offre existante
    Given que je dispose d'un ID d'offre valide et des informations mises à jour
    When j'envoie une requête PUT à /bids/{id} avec les nouvelles informations
    Then le code de statut de la réponse devrait être 200
    And le corps de la réponse devrait contenir l'offre mise à jour

  Scenario: Tenter de mettre à jour une offre avec un ID invalide
    Given que je dispose d'un ID d'offre invalide et des informations mises à jour
    When j'envoie une requête PUT à /bids/{id} avec les nouvelles informations
    Then le code de statut de la réponse devrait être 404
    And le corps de la réponse devrait contenir une erreur indiquant que l'offre n'a pas été trouvée

  Scenario: Supprimer une offre existante
    Given que je dispose d'un ID d'offre valide
    When j'envoie une requête DELETE à /bids/{id}
    Then le code de statut de la réponse devrait être 200
    And le corps de la réponse devrait confirmer que l'offre a été supprimée

  Scenario: Tenter de supprimer une offre avec un ID invalide
    Given que je dispose d'un ID d'offre invalide
    When j'envoie une requête DELETE à /bids/{id}
    Then le code de statut de la réponse devrait être 404
    And le corps de la réponse devrait contenir une erreur indiquant que l'offre n'a pas été trouvée
