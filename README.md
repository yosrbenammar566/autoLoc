# AutoLoc

## Présentation

**AutoLoc** est une plateforme de gestion de location de véhicules multi-agences.

Le projet est réalisé dans le cadre du cours **Architecture des Systèmes d'Information (ASI)** à **ESPRIT**, année universitaire 2026-2027.

L'objectif est de développer une application permettant de gérer les agences, les véhicules, les employés, les clients, les réservations, les contrats, les paiements et les opérations de maintenance.

## Objectifs du projet

* Gérer le parc de véhicules de plusieurs agences
* Gérer les clients et les employés
* Permettre la réservation et la location de véhicules
* Gérer les contrats de location
* Suivre les paiements
* Gérer les opérations de maintenance des véhicules
* Exposer une API REST
* Assurer la persistance des données dans une base de données MySQL

## Acteurs

Le système comporte quatre acteurs principaux :

* **Client** : consulte les véhicules, effectue des réservations et suit ses locations.
* **Agent d'agence** : enregistre les locations et les retours et gère les clients.
* **Responsable d'agence** : gère les véhicules et le personnel de son agence et consulte les statistiques.
* **Administrateur** : gère les agences, les utilisateurs et la configuration globale.

## Modèle métier

Les principales entités du projet sont :

* `Agence`
* `Vehicule`
* `Client`
* `Employe`
* `Equipement`
* `Reservation`
* `Contrat`
* `Paiement`
* `Maintenance`

Les principales énumérations sont :

* `CategorieVehicule`
* `StatutVehicule`
* `RoleEmploye`
* `StatutReservation`
* `ModePaiement`

## Architecture du projet

```text
src/
└── main/
    ├── java/
    │   └── tn/esprit/autoloc/
    │       ├── domain/
    │       │   ├── Agence.java
    │       │   ├── Vehicule.java
    │       │   ├── Client.java
    │       │   ├── Employe.java
    │       │   ├── Equipement.java
    │       │   ├── Reservation.java
    │       │   ├── Contrat.java
    │       │   ├── Paiement.java
    │       │   ├── Maintenance.java
    │       │   └── Enums
    │       ├── repository/
    │       ├── service/
    │       └── web/
    │           ├── controller/
    │           └── dto/
    │
    └── resources/
        └── application.properties
```

## Technologies utilisées

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Postman
* IntelliJ IDEA

## Atelier 1 — Modélisation des entités

Dans le cadre de l'Atelier 1, les entités JPA du domaine AutoLoc sont créées avec :

* `@Entity`
* `@Table`
* `@Id`
* `@GeneratedValue(strategy = GenerationType.IDENTITY)`
* `@Getter`
* `@Setter`
* `@NoArgsConstructor`
* `@AllArgsConstructor`

Les associations entre les entités seront ajoutées dans les ateliers suivants.

## Gestion du schéma de base de données

Le projet utilise Hibernate pour générer et mettre à jour le schéma de la base de données à partir des entités JPA.

En environnement de développement, la stratégie utilisée est :

```properties
spring.jpa.hibernate.ddl-auto=update
```

Cette configuration permet de mettre à jour automatiquement le schéma lors de l'évolution des entités.

## Base de données

Base de données utilisée :

```text
MySQL
```

Nom de la base :

```text
autoloc_db
```

## Lancement du projet

### Avec Maven Wrapper

Sous Windows :

```bash
./mvnw spring-boot:run
```

ou :

```bash
mvnw.cmd spring-boot:run
```



Exemple de commit :



## État actuel du projet

### Atelier 1

* [x] Création du projet Spring Boot
* [x] Configuration de la base de données MySQL
* [x] Création de l'entité `Vehicule`
* [x] Création des autres entités du domaine
* [x] Création des énumérations
* [x] Génération des tables avec Hibernate
* [ ] Ajout des associations entre les entités — Atelier 2
* [ ] Création des repositories
* [ ] Développement de la couche service
* [ ] Développement des contrôleurs REST
* [ ] Création des DTO
* [ ] Tests de l'API
