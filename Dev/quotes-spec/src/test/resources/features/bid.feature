#
#Feature: Gérer les enchères
#
#  Scenario: Récupérer une enchère avec un ID valide
#    Given que je dispose d'un ID d'enchère valide
#    When j'envoie une requete GET a /bids/5
#    Then le code de statut de la réponse devrait être 200
#    And le corps de la réponse devrait contenir une enchère avec l'ID donné
#
#  Scenario: Tenter de récupérer une enchère avec un ID invalide
#    Given que je dispose d'un ID d'enchère invalide
#    When j'envoie une requete GET a /bids/999
#    Then le code de statut de la réponse devrait être 404
#    And le corps de la réponse devrait contenir une erreur indiquant que l'enchère n'a pas été trouvée
#
#  Scenario: Créer une nouvelle enchère
#    Given que je dispose d'un token d'utilisateur valide et des informations d'enchère valides
#    When j'envoie une requete POST a /bids avec le token et les informations d'enchère
#    Then le code de statut de la réponse devrait être 201
#    And le corps de la réponse devrait contenir l'ID de la nouvelle enchère
#
#  Scenario: Tenter de créer une nouvelle enchère avec un token d'utilisateur invalide
#    Given que je dispose d'un token d'utilisateur invalide et des informations d'enchère valides
#    When j'envoie une requete POST a /bids avec le token et les informations d'enchère
#    Then le code de statut de la réponse devrait être 403
#
#  Scenario: Mettre à jour une enchère existante
#    Given que je dispose d'un token d'utilisateur valide et des informations d'enchère valides
#    When j'envoie une requete PUT a /bids/5 avec le token et les informations d'enchère
#    Then le code de statut de la réponse devrait être 204
#
#  Scenario: Tenter de mettre à jour une enchère avec un ID invalide
#    Given que je dispose d'un token d'utilisateur valide et des informations d'enchère valides
#    When j'envoie une requete PUT a /bids/999 avec le token et les informations d'enchère
#    Then le code de statut de la réponse devrait être 404
#
#  Scenario: Supprimer une enchère existante
#    Given que je dispose d'un token d'utilisateur valide et d'un ID d'enchère valide
#    When j'envoie une requete DELETE a /bids/5 avec le token
#    Then le code de statut de la réponse devrait être 204
#
#  Scenario: Tenter de supprimer une enchère avec un ID invalide
#    Given que je dispose d'un token d'utilisateur valide et d'un ID d'enchère invalide
#    When j'envoie une requete DELETE a /bids/999 avec le token
#    Then le code de statut de la réponse devrait être 404
