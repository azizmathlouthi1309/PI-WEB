-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 03 juin 2020 à 08:54
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `kinda`
--

-- --------------------------------------------------------

--
-- Structure de la table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL,
  `account_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cin` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `child`
--

DROP TABLE IF EXISTS `child`;
CREATE TABLE IF NOT EXISTS `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `level` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `save_nurs_id` int(11) DEFAULT NULL,
  `save_resto_id` int(11) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `child`
--

INSERT INTO `child` (`id`, `lastname`, `firstname`, `level`, `parent_id`, `class_id`, `save_nurs_id`, `save_resto_id`, `age`, `photo`) VALUES
(1, 'chniter', 'nadanada', '2', 1, 5, 7, 3, 3, '');

-- --------------------------------------------------------

--
-- Structure de la table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nb_child` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ED4B199F41807E1D` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE IF NOT EXISTS `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `response` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `driver`
--

DROP TABLE IF EXISTS `driver`;
CREATE TABLE IF NOT EXISTS `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `phonenumber` int(11) NOT NULL,
  `adress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `hour_begin` int(11) NOT NULL,
  `hour_end` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbre` int(11) DEFAULT NULL,
  `nbreN` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `name`, `description`, `date`, `hour_begin`, `hour_end`, `capacity`, `picture`, `nbre`, `nbreN`) VALUES
(2, 'Pool activity', 'swimming', '2020-07-01 00:00:00', 12, 16, 10, '52bfbd5a1f4176cb187e4498ee29bacd', 1, NULL),
(3, 'a happy event', 'eating,playing', '2021-01-01 00:00:00', 12, 13, 12, 'c470f98f166735724dc1350096b47d11', NULL, NULL),
(4, 'art activity', 'drawing', '2022-04-06 00:00:00', 12, 16, 11, 'e241b9420274211cf7731e641e71c90d', 1, NULL),
(5, 'big event', 'dance, celebrate', '2020-08-01 00:00:00', 13, 15, 15, '111fb0ca441176adb7c36b48b1ef0b60', NULL, NULL),
(6, 'let\'s celebrate', 'nice oneee', '2021-07-07 00:00:00', 15, 17, 20, 'b3d7a338ff8e60a951b4e73789bdff1d', NULL, NULL),
(7, 'Cake party', 'cooking,eating,playing..', '2020-06-11 00:00:00', 14, 17, 22, '6200373b4a5988d5c5e879e2f56e5f41', NULL, NULL),
(9, 'validation', 'party ,event', '2021-01-01 00:00:00', 12, 13, -1, '3a56ddc2774da60d78a5c564fe859700', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

DROP TABLE IF EXISTS `forum`;
CREATE TABLE IF NOT EXISTS `forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_852BBECD71F7E88B` (`event_id`),
  KEY `IDX_852BBECDA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `forum`
--

INSERT INTO `forum` (`id`, `event_id`, `user_id`, `message`) VALUES
(1, 2, 2, 'yess i like it'),
(3, 3, 2, 'yess i like it'),
(4, 3, 2, 'good'),
(5, 2, 3, 'j\'adoreeee'),
(6, 2, 2, 'c\'est magnifique'),
(7, 2, 2, 'magnifique');

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(1, 'nadou', 'nadou', 'nada.chniter@esprit.tn', 'nada.chniter@esprit.tn', 1, NULL, '$2y$13$anxCa1PqvMo0JAfIydzdD.2FU8oUWexnY.2ShPcFjH.qwb7wytKyK', '2020-04-16 23:04:21', NULL, NULL, 'a:2:{i:0;s:11:\"ROLE_PARENT\";i:1;s:10:\"ROLE_ADMIN\";}'),
(2, 'khaoula', 'khaoula', 'khaoula.mekni@esprit.tn', 'khaoula.mekni@esprit.tn', 1, NULL, '$2y$13$mv7S2L1RrKhyjMd9ny3C/e.nqfkvjfKwLYRjvQ7kgFdGrFVMsF7aC', '2020-06-03 08:16:44', NULL, NULL, 'a:2:{i:0;s:11:\"ROLE_PARENT\";i:1;s:10:\"ROLE_ADMIN\";}'),
(3, 'maissa', 'maissa', 'maissa.belfekih@esprit.tn', 'maissa.belfekih@esprit.tn', 1, NULL, '$2y$13$SmfWJlDVVtHKZ6mQ6bKWEOblHIFBmV7n8b9tXFbBkzoinDLpkhgA.', '2020-06-03 08:13:27', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}');

-- --------------------------------------------------------

--
-- Structure de la table `interest`
--

DROP TABLE IF EXISTS `interest`;
CREATE TABLE IF NOT EXISTS `interest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity` int(11) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_6C3E1A67AC74095A` (`activity`),
  KEY `IDX_6C3E1A673D8E604F` (`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_49CA4E7D727ACA70` (`parent_id`),
  KEY `IDX_49CA4E7D71F7E88B` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `likes`
--

INSERT INTO `likes` (`id`, `parent_id`, `event_id`) VALUES
(2, 2, 4),
(3, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_day` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `plate1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `plate2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `plate3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id`, `date_day`, `plate1`, `plate2`, `plate3`) VALUES
(1, '22-05-2020', 'a', 'b', 'c'),
(2, '20-08-2020', 'nada', 'loubia', 'yummy');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `parent_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`parent_id`,`event_id`),
  KEY `IDX_AB55E24F727ACA70` (`parent_id`),
  KEY `IDX_AB55E24F71F7E88B` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`parent_id`, `event_id`) VALUES
(2, 2),
(2, 4),
(2, 9),
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `class_name` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `save_nurse`
--

DROP TABLE IF EXISTS `save_nurse`;
CREATE TABLE IF NOT EXISTS `save_nurse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `date_end` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Child_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_EFD918232414A04D` (`Child_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `save_resto`
--

DROP TABLE IF EXISTS `save_resto`;
CREATE TABLE IF NOT EXISTS `save_resto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `date_end` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Child_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_B48620D12414A04D` (`Child_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `save_resto`
--

INSERT INTO `save_resto` (`id`, `date_begin`, `date_end`, `etat`, `Child_id`) VALUES
(1, '14-12-2013', '20-12-2020', NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity1` int(11) DEFAULT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B35B6E3A8CE2354` (`activity1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `traffectation`
--

DROP TABLE IF EXISTS `traffectation`;
CREATE TABLE IF NOT EXISTS `traffectation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) DEFAULT NULL,
  `driver` int(11) DEFAULT NULL,
  `vehicule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_22129FC4595AAE34` (`grade`),
  KEY `IDX_22129FC411667CD9` (`driver`),
  KEY `IDX_22129FC4292FFF1D` (`vehicule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL,
  `account_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `capacity` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `view`
--

DROP TABLE IF EXISTS `view`;
CREATE TABLE IF NOT EXISTS `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nb_stars` int(11) NOT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SenderName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `FK_ED4B199F41807E1D` FOREIGN KEY (`teacher_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `forum`
--
ALTER TABLE `forum`
  ADD CONSTRAINT `FK_852BBECD71F7E88B` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK_852BBECDA76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `interest`
--
ALTER TABLE `interest`
  ADD CONSTRAINT `FK_6C3E1A673D8E604F` FOREIGN KEY (`parent`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_6C3E1A67AC74095A` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`);

--
-- Contraintes pour la table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `FK_49CA4E7D71F7E88B` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK_49CA4E7D727ACA70` FOREIGN KEY (`parent_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F71F7E88B` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK_AB55E24F727ACA70` FOREIGN KEY (`parent_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `save_nurse`
--
ALTER TABLE `save_nurse`
  ADD CONSTRAINT `FK_EFD918232414A04D` FOREIGN KEY (`Child_id`) REFERENCES `child` (`id`);

--
-- Contraintes pour la table `save_resto`
--
ALTER TABLE `save_resto`
  ADD CONSTRAINT `FK_B48620D12414A04D` FOREIGN KEY (`Child_id`) REFERENCES `child` (`id`);

--
-- Contraintes pour la table `time_table`
--
ALTER TABLE `time_table`
  ADD CONSTRAINT `FK_B35B6E3A8CE2354` FOREIGN KEY (`activity1`) REFERENCES `activity` (`id`);

--
-- Contraintes pour la table `traffectation`
--
ALTER TABLE `traffectation`
  ADD CONSTRAINT `FK_22129FC411667CD9` FOREIGN KEY (`driver`) REFERENCES `driver` (`id`),
  ADD CONSTRAINT `FK_22129FC4292FFF1D` FOREIGN KEY (`vehicule`) REFERENCES `vehicule` (`id`),
  ADD CONSTRAINT `FK_22129FC4595AAE34` FOREIGN KEY (`grade`) REFERENCES `class` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
