CREATE DATABASE IF NOT EXISTS `touristpacks`;
USE `touristpacks`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rol` enum('admin','client') NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `mobile` int(9) unsigned DEFAULT NULL,
  `card` int(10) unsigned DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `date_expire` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `types` (
  `code` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `activities` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `description` text DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `quality` enum('1','2','3','4','5') DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activity_type1_idx` (`type`),
  CONSTRAINT `fk_activity_type1` FOREIGN KEY (`type`) REFERENCES `types` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `packs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT 'Mi Pack',
  `description` text DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `final_date` datetime NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pack_user_idx` (`user_id`),
  CONSTRAINT `fk_pack_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `packs_details` (
  `id_pack` int(10) unsigned NOT NULL,
  `num_line` int(2) NOT NULL,
  `id_activity` int(4) NOT NULL,
  `num_places` int(2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `final_date` datetime DEFAULT NULL,
  `duration` time DEFAULT NULL,
  PRIMARY KEY (`id_pack`,`num_line`),
  KEY `fk_pack_activity_activity_1_idx` (`id_activity`),
  KEY `fk_pack_activity_pack1_idx` (`id_pack`),
  CONSTRAINT `fk_pack_activity_activity1` FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pack_activity_pack1` FOREIGN KEY (`id_pack`) REFERENCES `packs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `users` (`id`, `rol`, `nombre`, `mail`, `mobile`, `card`, `password`, `date_expire`) VALUES
  (1, 'admin', 'admin', 'admin@admin.com', NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL),
  (2, 'client', 'client', 'client@client.com', NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL);
;

INSERT INTO `types` (`code`, `nombre`) VALUES
  ('1', 'Hôtel'),
  ('2', 'Loisirs'),
('3', 'Restaurant');

INSERT INTO `activities` (`id`, `nombre`, `description`, `imagen`, `url`, `quality`, `type`, `price`, `location`) VALUES
  (1, 'Hôtel 1', 'Hôtel 1 Description', 'museoPicasso.jpg', 'https://www.museopicassomalaga.org/', '1', '1', '50.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
  (2, 'Loisirs 1', 'Loisirs 1 Description', 'catedral-malaga.jpg', 'https://malagacatedral.com/visita-cultural/visita-a-la-catedral/', '2', '2', '75.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8'),
  (3, 'Restaurant 1', 'Restaurant 1 Description ', 'jardin-botanico.jpg','https://laconcepcion.malaga.eu/', '2', '3', '75.00', 'https://goo.gl/maps/U2mNTBzMkzn22CGR8');
;

INSERT INTO `packs` (`id`, `nombre`, `description`, `creation_date`, `start_date`, `final_date`, `user_id`) VALUES
  (1, 'Pack 1', 'Pack 1 Description', '2023-06-01 10:00:00', '2023-06-10 10:00:00', '2023-06-20 10:00:00', 1),
  (2, 'Pack 2', 'Pack 2 Description', '2023-06-02 09:00:00', '2023-06-15 09:00:00', '2023-06-25 09:00:00', 1);

INSERT INTO `packs_details` (`id_pack`, `num_line`, `id_activity`, `num_places`, `start_date`, `final_date`, `duration`) VALUES
  (1, 1, 1, 2, '2023-06-10 10:00:00', '2023-06-11 10:00:00', '01:00:00'),
  (1, 2, 2, 1, '2023-06-15 09:00:00', '2023-06-16 09:00:00', '01:00:00');
