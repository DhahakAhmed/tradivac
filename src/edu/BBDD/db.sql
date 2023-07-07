-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 07 juil. 2023 à 20:12
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tradivac`
--

-- --------------------------------------------------------

--
-- Structure de la table `activities`
--

CREATE TABLE `activities` (
  `id` int(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `description` text DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `quality` enum('1','2','3','4','5') DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `activities`
--

INSERT INTO `activities` (`id`, `nombre`, `description`, `imagen`, `url`, `quality`, `type`, `price`, `location`) VALUES
(1, 'Hôtel 2', 'Hôtel 1 Description', 'museoPicasso.jpg', 'https://www.museopicassomalaga.org/', '1', '2', '50.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
(2, 'Loisirs 1', 'Loisirs 1 Description', 'catedral-malaga.jpg', 'https://malagacatedral.com/visita-cultural/visita-a-la-catedral/', '2', '2', '75.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
(3, 'Restaurant 1', 'Restaurant 1 Description ', 'jardin-botanico.jpg', 'https://laconcepcion.malaga.eu/', '2', '2', '75.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
(4, '31311', 'description pour hotel avec nombre 31311', '88.jpg', 'https://google.com', '5', '1', '120.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
(5, 'dar salma', 'Maison d\'Hotes Dar Salma, Sfax : Consultez les 7 avis de voyageurs, photos, et les meilleures offres pour Maison d\'Hotes Dar Salma, classé n°5 sur 9 ...\nMaison d\'Hotes Dar Salma: localisation :', '88.jpg', 'https://maison-dhote.tn/hotels-tunisie/dar-selma/', '5', '1', '150.00', NULL),
(6, 'restaurant2', 'test restaurant', 'alacenaFrancis.jpg', 'https://malagacatedral.com/visita-cultural/visita-a-la-catedral/', '5', '3', '1500.00', NULL),
(7, 'resto ', 'Restaurant 1 Description ', 'aquamijas.jpg', 'https://www.museopicassomalaga.org/', '5', '1', '1500.00', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `packs`
--

CREATE TABLE `packs` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) DEFAULT 'Mi Pack',
  `description` text DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `final_date` datetime NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `packs`
--

INSERT INTO `packs` (`id`, `nombre`, `description`, `creation_date`, `start_date`, `final_date`, `user_id`) VALUES
(1, 'Pack 1', 'Pack 1 Description', '2023-06-01 10:00:00', '2023-06-10 10:00:00', '2023-06-20 10:00:00', 1),
(2, 'Pack 2', 'Pack 2 Description', '2023-06-02 09:00:00', '2023-06-15 09:00:00', '2023-06-25 09:00:00', 1),
(3, '10', '', '2023-06-04 15:25:30', '2023-06-29 00:00:00', '2023-06-30 00:00:00', 1),
(4, '10', 'malla jaw ^ ^ ', '2023-06-04 15:41:58', '2023-06-14 00:00:00', '2023-06-15 00:00:00', 1),
(5, '10', 'qssq', '2023-06-04 15:43:32', '2023-06-15 00:00:00', '2023-06-16 00:00:00', 1),
(6, '10', 'qsqsqs', '2023-06-04 15:44:29', '2023-06-23 00:00:00', '2023-06-24 00:00:00', 1),
(7, '10', 'qsqssqsqqs', '2023-06-04 15:47:04', '2023-06-15 00:00:00', '2023-06-16 00:00:00', 1),
(8, '10', 'Malla jaw ^ ^ ', '2023-06-04 15:47:04', '2023-06-15 00:00:00', '2023-06-16 00:00:00', 1),
(9, '10', '', '2023-06-06 17:29:44', '2023-06-23 00:00:00', '2023-06-24 00:00:00', 1),
(10, '10', 'test', '2023-06-06 17:29:44', '2023-06-23 00:00:00', '2023-06-24 00:00:00', 1),
(11, '1', '', '2023-06-06 18:12:19', '2023-07-08 00:00:00', '2028-07-14 00:00:00', 1),
(12, '2', 'test', '2023-06-06 18:15:28', '2027-06-18 00:00:00', '2030-06-07 00:00:00', 1),
(13, '3', 'description', '2023-06-06 18:20:55', '2023-06-23 00:00:00', '2024-06-14 00:00:00', 1),
(14, '1', '', '2023-07-03 18:39:57', '2023-07-28 00:00:00', '2023-07-31 00:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `packs_details`
--

CREATE TABLE `packs_details` (
  `id_pack` int(10) UNSIGNED NOT NULL,
  `num_line` int(2) NOT NULL,
  `id_activity` int(4) NOT NULL,
  `num_places` int(2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `final_date` datetime DEFAULT NULL,
  `duration` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `packs_details`
--

INSERT INTO `packs_details` (`id_pack`, `num_line`, `id_activity`, `num_places`, `start_date`, `final_date`, `duration`) VALUES
(1, 1, 1, 2, '2023-06-10 10:00:00', '2023-06-11 10:00:00', '01:00:00'),
(1, 2, 2, 1, '2023-06-15 09:00:00', '2023-06-16 09:00:00', '01:00:00'),
(3, 1, 3, 1, '2023-06-30 00:00:00', '2023-07-01 00:00:00', '12:00:00'),
(4, 1, 3, 1, '2023-06-15 00:00:00', '2023-06-16 00:00:00', '12:00:00'),
(5, 1, 3, 1, '2023-06-16 00:00:00', '2023-06-17 00:00:00', '12:00:00'),
(6, 1, 3, 1, '2023-06-24 00:00:00', '2023-06-25 00:00:00', '12:00:00'),
(7, 1, 3, 1, '2023-06-16 00:00:00', '2023-06-17 00:00:00', '12:00:00'),
(8, 1, 3, 1, '2023-06-16 00:00:00', '2023-06-17 00:00:00', '12:00:00'),
(9, 1, 3, 3, '2023-06-24 00:00:00', '2023-06-25 00:00:00', '14:00:00'),
(10, 1, 2, 3, '2023-06-24 00:00:00', '2023-06-25 00:00:00', '12:00:00'),
(11, 1, 3, 3, '2025-06-14 00:00:00', '2025-06-15 00:00:00', '12:00:00'),
(12, 1, 2, 7, '2029-06-16 00:00:00', '2029-06-17 00:00:00', '12:00:00'),
(13, 1, 1, 4, '2023-06-25 00:00:00', '2023-06-26 00:00:00', '12:00:00'),
(14, 1, 1, 5, '2023-07-29 00:00:00', '2023-07-30 00:00:00', '12:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id_service` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `type` enum('excursion','hebergement','restauration') NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `excursion_plage` tinyint(1) DEFAULT NULL,
  `excursion_foret` tinyint(1) DEFAULT NULL,
  `monument` tinyint(1) DEFAULT NULL,
  `hebergement_type` varchar(255) DEFAULT NULL,
  `hebergement_jardin` tinyint(1) DEFAULT NULL,
  `hebergement_piscine` tinyint(1) DEFAULT NULL,
  `hebergement_terrasse` tinyint(1) DEFAULT NULL,
  `hebergement_balcon` tinyint(1) DEFAULT NULL,
  `hebergement_nb_chambre` int(11) DEFAULT NULL,
  `hebergement_classification_taille` enum('studio','T1','T1 bis','T2','T2 bis','T3','T3T4','T4','T5') DEFAULT NULL,
  `hebergement_classification_etage` enum('loft','duplex','triplex','souplex') DEFAULT NULL,
  `hebergement_classification_type` enum('appartement','condo','jumelee','individuelle','en_rangee','chambre','plain-pied','bateaux-maison') DEFAULT NULL,
  `hebergement_meublee` tinyint(1) DEFAULT NULL,
  `hebergement_parking` tinyint(1) DEFAULT NULL,
  `excursion_type` varchar(255) DEFAULT NULL,
  `restauration_type` varchar(255) DEFAULT NULL,
  `restauration_petit_dejeuner` tinyint(1) DEFAULT NULL,
  `restauration_repas_sur_demande` tinyint(1) DEFAULT NULL,
  `restauration_cuisine_commune` tinyint(1) DEFAULT NULL,
  `restauration_a_proximite` tinyint(1) DEFAULT NULL,
  `restauration_dejeuner` tinyint(1) DEFAULT NULL,
  `restauration_diner` tinyint(1) DEFAULT NULL,
  `restauration_collation` tinyint(1) DEFAULT NULL,
  `restauration_brunch` tinyint(1) DEFAULT NULL,
  `restauration_gouter` tinyint(1) DEFAULT NULL,
  `restauration_souper` varchar(100) DEFAULT NULL,
  `restauration_dessert` tinyint(1) DEFAULT NULL,
  `restauration_boisson` tinyint(1) DEFAULT NULL,
  `heberegement_addresse` varchar(1024) DEFAULT NULL,
  `hebergement_ville` varchar(255) DEFAULT NULL,
  `restauration_options_dietetiques` enum('vegetarien','vegetalien','sans_gluten','vegan','pescetarien','casher','halal','autre') DEFAULT NULL,
  `hebergement_wifi` tinyint(1) DEFAULT NULL,
  `hebergement_chauffage` varchar(100) DEFAULT NULL,
  `hebergement_climatisation` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id_service`, `id_user`, `type`, `description`, `prix`, `destination`, `date_debut`, `date_fin`, `excursion_plage`, `excursion_foret`, `monument`, `hebergement_type`, `hebergement_jardin`, `hebergement_piscine`, `hebergement_terrasse`, `hebergement_balcon`, `hebergement_nb_chambre`, `hebergement_classification_taille`, `hebergement_classification_etage`, `hebergement_classification_type`, `hebergement_meublee`, `hebergement_parking`, `excursion_type`, `restauration_type`, `restauration_petit_dejeuner`, `restauration_repas_sur_demande`, `restauration_cuisine_commune`, `restauration_a_proximite`, `restauration_dejeuner`, `restauration_diner`, `restauration_collation`, `restauration_brunch`, `restauration_gouter`, `restauration_souper`, `restauration_dessert`, `restauration_boisson`, `heberegement_addresse`, `hebergement_ville`, `restauration_options_dietetiques`, `hebergement_wifi`, `hebergement_chauffage`, `hebergement_climatisation`) VALUES
(1, 60, 'excursion', 'excursion description ', 10, 'destination descritpion', '2023-07-01', '2023-07-01', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 60, 'hebergement', 'hebergement description ', 10, 'destination descritpion', '2023-07-01', '2023-07-01', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 60, 'restauration', 'restauration description ', 10, 'destination descritpion', '2023-07-01', '2023-07-01', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `types`
--

CREATE TABLE `types` (
  `code` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `types`
--

INSERT INTO `types` (`code`, `nombre`) VALUES
('1', 'Hôtel'),
('2', 'Loisirs'),
('3', 'Restaurant');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `is_online` tinyint(1) DEFAULT NULL,
  `status` enum('active','inactive','pending','banned') DEFAULT NULL,
  `role` enum('admin','prestataire_service','touriste') NOT NULL,
  `language` enum('english','french') DEFAULT NULL,
  `sexe` enum('male','female') DEFAULT NULL,
  `profile_picture` varchar(1024) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `passport_picture` varchar(1024) DEFAULT NULL,
  `passport_id` varchar(255) DEFAULT NULL,
  `cin_picture` varchar(1024) DEFAULT NULL,
  `cin_id` varchar(255) DEFAULT NULL,
  `driver_license_picture` varchar(1024) DEFAULT NULL,
  `driver_license_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `first_name`, `last_name`, `user_name`, `email`, `phone`, `address`, `creation_date`, `birth_date`, `update_date`, `is_online`, `status`, `role`, `language`, `sexe`, `profile_picture`, `country`, `passport_picture`, `passport_id`, `cin_picture`, `cin_id`, `driver_license_picture`, `driver_license_id`, `password`) VALUES
(53, 'wassim', 'akkari', 'wassou_admin_1', 'wassou93@live.fr', '+216-50855655', 'Rue Siliana N3, Tunis', '2023-02-12', '2023-02-12', '2023-02-12', 1, 'active', 'admin', 'english', 'male', 'link_wassim_picture', 'Tunisie', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(54, 'mahmoud', 'akkari', 'wassou_admin_1', 'wassou93@live.fr', '+216-50855655', 'Rue Siliana N3, Tunis', '2023-02-12', '2023-02-12', '2023-02-12', 1, 'active', 'admin', 'english', 'male', 'link_wassim_picture', 'Tunisie', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(56, 'wassim', 'akkari', 'wassou_touriste_2', 'wassou93@live.fr', '+216-50855655', 'Rue Siliana N3, Tunis', '2023-02-12', '2023-02-12', '2023-02-12', 1, 'active', 'admin', 'english', 'male', 'link_wassim_picture', 'Tunisie', 'wassim_passport_picture', '22AGUKGIGIU1', 'wassim_cin_picture', '22AGUKGIGIU1', NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(57, 'aaa', 'aaa', 'aaaaaa97', 'aaaa@aaa.aaa', '97802465a', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'admin', 'english', 'male', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(58, 'touriste', 'touriste_prenom', 'touristetouriste_prenom697', 'touriste@mail.com', '1111-2222-3333', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'banned', 'touriste', 'french', 'female', '', '', '', '', '', '', NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(59, 'admin', 'admin_prenom', 'adminadmin_prenom781', 'admin@mail.com', '111-222-333', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'admin', 'english', 'male', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(60, 'prest', 'prest_prenom', 'prestprest_prenom50', 'prest@mail.com', '111-222-333', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'prestataire_service', 'english', 'male', '', '', '', '', '', '', '', '', 'e10adc3949ba59abbe56e057f20f883e'),
(61, 'aa', 'aa', 'aaaa99', 'aa', 'aa', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'admin', 'english', 'male', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(62, 'aaa', 'aaa', 'aaaaaa22', 'aaa', 'aaa', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'admin', 'english', 'male', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e'),
(63, 'nom', 'prenom', 'nomprenom', 'nom@mail.com', '1234567', '', '2023-06-10', '2023-06-10', '2023-06-10', 0, 'pending', 'admin', 'english', 'male', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_activity_type1_idx` (`type`);

--
-- Index pour la table `packs`
--
ALTER TABLE `packs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pack_user_idx` (`user_id`);

--
-- Index pour la table `packs_details`
--
ALTER TABLE `packs_details`
  ADD PRIMARY KEY (`id_pack`,`num_line`),
  ADD KEY `fk_pack_activity_activity_1_idx` (`id_activity`),
  ADD KEY `fk_pack_activity_pack1_idx` (`id_pack`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`),
  ADD KEY `service_FK` (`id_user`);

--
-- Index pour la table `types`
--
ALTER TABLE `types`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activities`
--
ALTER TABLE `activities`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `packs`
--
ALTER TABLE `packs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `packs_details`
--
ALTER TABLE `packs_details`
  ADD CONSTRAINT `fk_pack_activity_activity1` FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pack_activity_pack1` FOREIGN KEY (`id_pack`) REFERENCES `packs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `service_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
