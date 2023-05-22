Feature: Authentification et enregistrement des utilisateurs

  Scenario: Enregistrer un nouvel utilisateur
    Given que je dispose d'informations d'utilisateur valides
    When j'envoie une requete POST a /register avec les informations d'utilisateur
    Then le code de statut de la réponse devrait être 201

  Scenario: Tenter d'enregistrer un utilisateur avec un email déjà utilisé
    Given que je dispose d'informations d'utilisateur avec un email déjà utilisé
    When j'envoie une requete POST a /register avec les informations d'utilisateur
    Then le code de statut de la réponse devrait être 409

  Scenario: Authentifier un utilisateur avec des informations valides
    Given que je dispose d'un email et d'un mot de passe valides
    When j'envoie une requete POST a /auth avec l'email et le mot de passe
    Then le code de statut de la réponse devrait être 200
    And le corps de la réponse devrait contenir un token

  Scenario: Tenter d'authentifier un utilisateur avec un mot de passe invalide
    Given que je dispose d'un email valide et d'un mot de passe invalide
    When j'envoie une requete POST a /auth avec l'email et le mot de passe
    Then le code de statut de la réponse devrait être 400


