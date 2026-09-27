# AutoLoc

Plateforme de gestion de location de véhicules multi-agences.

Projet réalisé dans le cadre du cours Architecture des Systèmes d'Information (UP ASI, ESPRIT).

## Objectifs du projet

- Gérer le parc de véhicules de plusieurs agences
- Permettre la réservation et la location de véhicules
- Suivre les contrats, retours et paiements
- Exposer une API REST documentée (Swagger) et testée

## Acteurs

| Acteur | Rôle |
|--------|------|
| Client | Consulte les véhicules, réserve, suit ses locations |
| Agent d'agence | Enregistre les locations et les retours, gère les clients |
| Responsable d'agence | Gère les véhicules et le personnel de son agence, consulte les statistiques |
| Administrateur | Gère les agences, les utilisateurs et la configuration globale |

## Cas d'utilisation (v0)

- Client : rechercher un véhicule, réserver, annuler une réservation
- Agent : créer une location, enregistrer un retour, encaisser un paiement
- Responsable : ajouter/modifier un véhicule, consulter le tableau de bord
- Administrateur : créer une agence, gérer les comptes

## Stack technique

Java 17, Spring Boot, Spring Data JPA, MySQL, Maven, Postman, IntelliJ IDEA