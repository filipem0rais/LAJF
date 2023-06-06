#Feature: Récupérer un utilisateur
#
#  Scenario: Récupérer un utilisateur avec un ID valide
#    Given que je dispose d'un ID d'utilisateur valide
#    When j'envoie une requete GET a /users/5
#    Then le code de statut de la réponse devrait être 200
#    And le corps de la réponse devrait contenir un utilisateur avec l'ID donné
#
#  Scenario: Tenter de récupérer un utilisateur avec un ID invalide
#    Given que je dispose d'un ID d'utilisateur invalide
#    When j'envoie une requete GET a /users/999
#    Then le code de statut de la réponse devrait être 404
#    And le corps de la réponse devrait contenir une erreur indiquant que l'utilisateur n'a pas été trouvé

