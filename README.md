GSB - Application Android de Gestion des Visites Médicales

📄 Présentation du projet

Cette application mobile Android permet de gérer les visites médicales des délégués pharmaceutiques de l’entreprise fictive Galaxy Swiss Bourdin (GSB).

Les fonctionnalités principales incluent la gestion :
	•	des praticiens (médecins, spécialistes)
	•	des visites effectuées (compte-rendus)

Le but est de faciliter la collecte, la consultation et la mise à jour de ces données sur mobile.

🏢 Organisation support

Galaxy Swiss Bourdin (GSB) est une entreprise fictive utilisée dans le cadre du BTS SIO. Elle commercialise des produits pharmaceutiques et dispose de nombreux délégués visitant les praticiens.

🏋️‍️ Compétences E6 mobilisées
	•	Concevoir et développer une solution applicative
	•	Assurer la maintenance corrective ou évolutive d’une solution applicative
	•	Gérer les données

🔧 Ressources matérielles et logicielles utilisées
	•	Matériel : PC de développement
	•	Système d’exploitation : Windows / Linux / MacOS
	•	IDE : Android Studio
	•	Langages : Java / XML
	•	Base de données : SQLite (intégrée à Android)
	•	Architecture : Activités + DataBinding

👥 Modalités d’accès aux productions
	•	Code source disponible dans l’archive : GSB_AndroidStudio.zip
	•	Projet Android Studio standard :
	•	Importable directement
	•	Contient le code, les ressources XML, et les fichiers de configuration Gradle.

📊 Fonctionnalités principales

Gestion des Praticiens
	•	Lister tous les praticiens existants
	•	Afficher les détails d’un praticien (spécialité, téléphone, etc.)
	•	Ajouter un nouveau praticien
	•	Modifier les informations d’un praticien
	•	Supprimer un praticien

Gestion des Visites
	•	Lister toutes les visites programmées ou réalisées
	•	Afficher les détails d’une visite (date, compte-rendu)
	•	Ajouter une nouvelle visite pour un praticien
	•	Modifier ou supprimer une visite existante

🔄 Architecture de l’application
	•	Activities : chaque écran principal correspond à une Activity
	•	DataBinding : liaison entre les données et les vues
	•	SQLite : stockage local des données utilisateurs (praticiens et visites)

Principaux fichiers
	•	/app/src/main/java/com/example/gsb/
	•	MainActivity.java : page d’accueil
	•	PraticienActivity.java : gestion des praticiens
	•	VisiteActivity.java : gestion des visites
	•	CreatePraticienActivity.java : ajout de praticien
	•	UpdateVisiteActivity.java : mise à jour de visite
	•	/app/src/main/res/layout/
	•	activity_main.xml
	•	activity_praticien.xml
	•	activity_visite.xml

🛠️ Stack Technique
	•	Android SDK : 30+
	•	Gradle : gestionnaire de dépendances
	•	DataBinding : pour la gestion propre des vues
	•	Java : langage principal
	•	SQLite : pour la persistance locale

📈 Schéma de données

Table Praticiens
	•	id_praticien (PK)
	•	nom
	•	prenom
	•	specialite
	•	telephone

Table Visites
	•	id_visite (PK)
	•	date_visite
	•	compte_rendu
	•	id_praticien (FK)

🛡️ Backend/API Associé : Gestion des Rapports de Visite

🔖 Présentation

Cette API REST en Node.js / Express.js permet de gérer les rapports de visite réalisés par les visiteurs médicaux.

🛠️ Technologies utilisées
	•	Node.js / Express.js
	•	MongoDB ou PostgreSQL
	•	JWT pour l’authentification sécurisée
	•	Express-rate-limit pour la protection brute-force

🚀 Fonctionnalités de l’API
	•	Gestion des utilisateurs (inscription, connexion)
	•	CRUD complet sur les rapports de visite
	•	Authentification par jeton (JWT)
	•	Sécurisation avancée (chiffrement, protection XSS, limitation de requêtes)

🔢 Déploiement de l’API

# Cloner le projet
 git clone https://github.com/Bili-and-sheep/GSB_SaperliExpress.git
 cd api-auth

# Installer les dépendances
 npm init -y
 npm install

# Lancer l'API
 npx ts-node server.ts

📆 Période de réalisation
	•	De : Septembre 2024
	•	À : Avril 2025
	•	Lieu : Lycée Gabriel Fauré - Annecy
	•	Modalité : Travail individuel

⸻

Réalisé dans le cadre de l’épreuve E6 - BTS SIO 2025 - Option SLAM.
