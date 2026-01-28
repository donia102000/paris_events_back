# 🎭 Paris Events – Backend

## 🧠 Description

**Paris Events Backend** est une application Spring Boot qui collecte, traite et stocke automatiquement les événements organisés à Paris à partir d’une API open-data officielle.

Ce backend fournit une API REST permettant au frontend d’accéder à des données propres, structurées et sans doublons afin de visualiser la répartition des événements gratuits et payants.

---

## 🎯 Objectifs du projet

- Étudier la répartition des événements **gratuits et payants** par adresse  
- Fournir des données fiables pour une **visualisation claire** côté frontend  
- Automatiser le traitement des données pour éviter les doublons  
- Garantir une base de données propre et cohérente  

---

## 🔄 Pipeline de traitement des données

### 🔹 Étape 1 — Récupération des données (API Open Data)

- Connexion à l’API Open Data de la Ville de Paris via requête **HTTP GET**
- Utilisation de paramètres de requête pour filtrer les résultats :
  - Événements gratuits
  - Événements payants
- Les données sont reçues au format **JSON**

---

### 🔹 Étape 2 — Extraction et filtrage

Avant l’insertion en base, les données subissent plusieurs traitements :

- Suppression des valeurs **nulles**
- Normalisation des formats :
  - Dates
  - Types d’événements
  - Adresses
- Vérification et suppression des **doublons** grâce à l’ID unique de chaque événement
- Gestion des erreurs et système de **logs** pour :
  - Les problèmes de récupération API
  - Les erreurs de parsing JSON
  - Les données corrompues

---

### 🔹 Étape 3 — Stockage en base de données

- Les événements sont stockés dans une table principale : **`events`**
- Un **Scheduler** s’exécute automatiquement **toutes les minutes**
- Fonctionnement du scheduler :
  - Ajout des nouveaux événements s’ils n’existent pas
  - Mise à jour des événements existants en cas de modification
  - Aucune duplication grâce à la vérification par ID

---

## 🚀 API REST (Spring Boot)

Le backend expose des endpoints REST permettant au frontend de récupérer les données.

### 📌 Endpoints principaux

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/events` | Liste de tous les événements |
| GET | `/api/events/free` | Événements gratuits |
| GET | `/api/events/paid` | Événements payants |
| GET | `/api/events/address/{address}` | Événements par adresse |

---

## 🧱 Stack Technique

- **Java 17+**
- **Spring Boot**
- Spring Web
- Spring Data JPA
- Scheduler (`@Scheduled`)
- Base de données relationnelle (MySQL / PostgreSQL)
- Maven

---

## 🗄️ Structure de la base de données

### Table : `events`

| Champ | Description |
|------|-------------|
| id | Identifiant unique de l’événement (API Open Data) |
| title | Nom de l’événement |
| description | Description |
| address | Adresse de l’événement |
| date_start | Date de début |
| date_end | Date de fin |
| price_type | Gratuit ou Payant |
| last_update | Date de dernière mise à jour |

---

## ▶️ Lancement du projet en local

### 🔧 Prérequis

- Java 17 ou supérieur  
- Maven  
- Docker & Docker Compose  

---

### 💻 Lancement sans Docker 

```bash
git clone https://github.com/donia102000/paris_events_back.git
cd paris_events_back
mvn clean install
mvn spring-boot:run
Projet Frontend associé
````
---

## 🐳 Lancement avec Docker

Pour démarrer tous les services avec Docker Compose :  

```bash
docker compose up --build
```
---
### L’interface utilisateur Angular consommant cette API est disponible ici :

👉 https://github.com/donia102000/paris_events_front

