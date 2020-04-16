-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 16 avr. 2020 à 17:39
-- Version du serveur :  5.5.48
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `kindo`
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `activity`
--

INSERT INTO `activity` (`id`, `name`, `description`, `path`, `date_start`, `date_end`) VALUES
(1, 'dance', 'The Bootstrap grid system has four classes: xs (phones), sm (tablets), md (desktops), and lg (larger desktops). The classes can be combined to create more dynamic and flexible layouts.  Tip: Each class scales up, so if you wish to set the same widths for', 'gateau.jpg', '2020-01-01 00:00:00', '2020-02-01 00:00:00'),
(2, 'Exploring', 'Encourage your child to experiment with different colors and designs to see what happens when the light hits their suncatcher.', 'fete.jpg', '2020-03-26 16:00:00', '2020-03-30 17:00:00'),
(3, 'hhh', 'mlnjbn', 'cropped-favicon-192x192.png', '2020-04-13 04:00:00', '2020-04-15 01:00:00'),
(4, 'Design Challenge: Building a Bridge', 'Tht is made out of marshmallows and toothpicks, focusing on its function', 'preschool-art (1).jpg', '2020-04-08 08:00:00', '2020-01-08 10:00:00'),
(5, 'READ', 'Each step in the design thinking process is essential, so make sure to complete each step in the instructions.', 'preschool-art.jpg', '2020-04-07 13:00:00', '2020-04-08 14:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `username_canonical` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_canonical` varchar(255) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) NOT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext NOT NULL,
  `account_type` varchar(255) NOT NULL,
  `cin` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`, `cin`) VALUES
(17, 'kindo', 'kindo', 'kindo@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$RUp503DP3vHf3uYSIIiiM.RGrTLrNyOdxLGFJvImyjhgEHv8aoAvS', NULL, 'confirmation_token', NULL, 'roles', 'Admin', 5442121);

-- --------------------------------------------------------

--
-- Structure de la table `child`
--

DROP TABLE IF EXISTS `child`;
CREATE TABLE IF NOT EXISTS `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `level` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `save_nurs_id` int(11) DEFAULT NULL,
  `save_resto_id` int(11) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `child`
--

INSERT INTO `child` (`id`, `lastname`, `firstname`, `level`, `parent_id`, `class_id`, `save_nurs_id`, `save_resto_id`, `age`, `photo`) VALUES
(17, 'kdjfc', 'jldfn', '3', 16, 58, NULL, NULL, 6, ''),
(18, 'kdf', 'dlkc', '3', 15, 58, NULL, NULL, 6, ''),
(20, 'rais', 'oussema', '4', NULL, 60, NULL, NULL, 3, 'C:\\Users\\Administrator\\Desktop\\ahmed\\ahmed.jpg'),
(21, 'rais', 'salim', '3', NULL, 58, NULL, NULL, 5, ''),
(22, 'rais', 'hedi', '3', NULL, 58, NULL, NULL, 2, 'C:\\Users\\Administrator\\Desktop\\ahmed\\ahmed.jpg'),
(23, 'rais', 'hedi', '3', NULL, 58, NULL, NULL, 2, 'C:\\Users\\Administrator\\Desktop\\ahmed\\ahmed.jpg'),
(24, 'qsdqsd', 'qsdqsdqsd', '3', 12, NULL, NULL, NULL, 4, '');

-- --------------------------------------------------------

--
-- Structure de la table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `nb_child` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ED4B199F41807E1D` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `class`
--

INSERT INTO `class` (`id`, `name`, `nb_child`, `level`, `teacher_id`) VALUES
(66, 'nhfnn', 20, 1, 17),
(67, 'dskfb', 20, 15, 18),
(68, 'dskfb', 20, 15, 18),
(69, 'dskfb', 9, 1, 20);

-- --------------------------------------------------------

--
-- Structure de la table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE IF NOT EXISTS `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  `response` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `complaint`
--

INSERT INTO `complaint` (`id`, `subject`, `teacher_id`, `parent_id`, `type`, `message`, `response`, `status`) VALUES
(5, 'insult', NULL, NULL, 'teacher', 'teacher insult my child', 'kjsndkjcdn', 1),
(6, 'dirt', NULL, NULL, 'restaurant services', 'food in restaurant are dirty', 'oifdj', 1),
(7, 'ajbdn,', NULL, NULL, 'teacher', 'dfnkgd', 'sama7ni', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `driver`
--

INSERT INTO `driver` (`id`, `firstname`, `lastname`, `age`, `phonenumber`, `adress`) VALUES
(2, 'kikihjdeksghdfcj', 'gthjk', 26, 948276195, 'ghjk'),
(3, 'qsqsd', 'qsd', 3, 4, 'qsdqsd');

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `hour_begin` int(2) NOT NULL,
  `hour_end` int(2) NOT NULL,
  `capacity` int(11) NOT NULL,
  `picture` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `name`, `description`, `date`, `hour_begin`, `hour_end`, `capacity`, `picture`) VALUES
(1, 'pool activity', 'kiki do you love me', '2021-01-01 00:00:00', 12, 16, 11, 'a81df99f82513787598059bcb145ee39'),
(2, 'a happy event', 'barcha jawww', '2022-01-01 00:00:00', 12, 16, 12, 'a2d26750747369a4d80ce049a4f04d85'),
(3, 'a big event', 'jawik behi', '2021-04-01 00:00:00', 12, 16, 12, '8e908b3c8133f7a43ed291b90c565451'),
(4, 'a nice event', 'jawik behi', '2020-07-01 00:00:00', 12, 16, 12, 'd33e50f41120ade9ef9cc683f2a9b1e8'),
(5, 'art activity', 'jawik behi', '2020-07-01 00:00:00', 12, 16, 12, '6e87a30252b4ad6a11c8b676a12983b0'),
(6, 'khoukha', 'jawik behi', '2021-01-01 00:00:00', 12, 16, 12, '75f137d3d1436a6d3f06efce76d67c41');

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

DROP TABLE IF EXISTS `forum`;
CREATE TABLE IF NOT EXISTS `forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_852BBECD71F7E88B` (`event_id`),
  KEY `IDX_852BBECDA76ED395` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `forum`
--

INSERT INTO `forum` (`id`, `message`, `event_id`, `user_id`) VALUES
(1, 'yess i like it', 1, 19);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(14, 'kindo', 'kindo', 'mohamedaziz.mathlouthi@esprit.tn', 'mohamedaziz.mathlouthi@esprit.tn', 1, NULL, '$2y$13$MoyM1qHegw0TG4mGCDoImOAERs2HAZTPpdjBfcUR1zxc56jzxOBey', '2020-04-16 17:17:54', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'),
(17, 'aziz100', 'aziz100', 'aziz13mth@gmail.com', 'aziz13mth@gmail.com', 1, NULL, '$2y$13$6P40YBSmKHP.PUMCc7bjtOU2U78nP8m7/Qu.HGzIVkquoeYiEvEya', '2020-04-13 03:16:21', NULL, NULL, 'a:1:{i:0;s:12:\"ROLE_TEACHER\";}'),
(18, 'aziz100aziz100', 'aziz100aziz100', 'aziz13mth@gmail.commm', 'aziz13mth@gmail.commm', 1, NULL, '$2y$13$lS5pnlxpZMCdZeV6ZVBlgOY2/EVv7DIHmX1Xab84A7av1gkYpITEa', '2020-04-16 17:32:27', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}'),
(19, 'khaoula', 'khaoula', 'khaoula.mekni@esprit.tn', 'khaoula.mekni@esprit.tn', 1, NULL, '$2y$13$a1Zw.VH3vEXlwezggthD8OLYPnqcrVUc8XxEIDrahbgZ5sWB7Qnie', '2020-04-13 17:30:48', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}'),
(20, 'maissa', 'maissa', 'maissa.belfekih@esprit.tn', 'maissa.belfekih@esprit.tn', 1, NULL, '$2y$13$ng8tid6qZwO2vT.8ALJ/HuLo7OkE77rQznjGvg2no6C21AWYjXNA6', '2020-04-16 14:39:55', NULL, NULL, 'a:1:{i:0;s:12:\"ROLE_TEACHER\";}');

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
-- Structure de la table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_day` varchar(10) NOT NULL,
  `plate1` varchar(255) NOT NULL,
  `plate2` varchar(255) NOT NULL,
  `plate3` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id`, `date_day`, `plate1`, `plate2`, `plate3`) VALUES
(12, '2020-02-28', 'salade', 'couscous', 'yaourt');

-- --------------------------------------------------------

--
-- Structure de la table `parent`
--

DROP TABLE IF EXISTS `parent`;
CREATE TABLE IF NOT EXISTS `parent` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `username_canonical` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_canonical` varchar(180) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) NOT NULL,
  `password_requested_at` varchar(255) DEFAULT NULL,
  `roles` longtext NOT NULL,
  `account_type` varchar(255) NOT NULL,
  `nbr_child` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `parent`
--

INSERT INTO `parent` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`, `nbr_child`) VALUES
(1000, 'ahmed', 'username_canonical', 'ahmedrais988@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$jV5uvEizuRQXQuX9G3ZLqu/HaAMr3/TVBE/Eod5pWR12ZyaW1fFSG', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1004, 'khaoula', 'username_canonical', 'khaoula.mekni@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$aLUQ6YYY/C2Ut5dvGIY25ec5VqtTZ1BCB2epIJuEi2Fg6Q9VYvijO', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1005, 'nessrine', 'username_canonical', 'maissa.belfekih@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$kB.JsASi6xCu.Q5Vptl3GegVD3vJqxJ..i4K2iT8tH.IHcHP6fO7.', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1007, 'aziz1', 'username_canonical', 'zizou13091998@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$3d23EmYrqV9EWDuwqswSzecJRJlQsfUZsQWLvEVkYcOr8jkHr3CAC', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1010, 'khaoula2', 'username_canonical', 'nada.shniter@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$wUz6hph2JzixWywC5mTKpe.1gH8mr.UQyq1RkjS7nRLEshM/nCILm', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0);

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`parent_id`, `event_id`) VALUES
(19, 1);

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `class_name` varchar(250) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id`, `subject`, `content`, `class_name`, `teacher_id`) VALUES
(29, 'to do', 'repeat all activities', 'lions', 1001),
(32, 'Remarks', 'today class activities were fruitful', 'smurfs', 1001),
(33, 'activity sing', 'good', 'birds', 1001),
(34, 'to doff', 'dkdkdk', 'papillon', 1001);

-- --------------------------------------------------------

--
-- Structure de la table `save_nurse`
--

DROP TABLE IF EXISTS `save_nurse`;
CREATE TABLE IF NOT EXISTS `save_nurse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) NOT NULL,
  `date_end` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `save_nurse`
--

INSERT INTO `save_nurse` (`id`, `date_begin`, `date_end`) VALUES
(11, '2020-01-28', '2020-03-01');

-- --------------------------------------------------------

--
-- Structure de la table `save_resto`
--

DROP TABLE IF EXISTS `save_resto`;
CREATE TABLE IF NOT EXISTS `save_resto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) NOT NULL,
  `date_end` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `save_resto`
--

INSERT INTO `save_resto` (`id`, `date_begin`, `date_end`) VALUES
(5, '2020-01-12', '2020-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `username_canonical` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_canonical` varchar(180) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` varchar(255) DEFAULT NULL,
  `confirmation_token` varchar(180) NOT NULL,
  `password_requested_at` varchar(255) DEFAULT NULL,
  `roles` longtext NOT NULL,
  `account_type` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) NOT NULL,
  `username_canonical` varchar(180) NOT NULL,
  `email` varchar(180) NOT NULL,
  `email_canonical` varchar(180) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) NOT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext NOT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`) VALUES
(17, 'kindo', 'kindo', 'kindo@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$RUp503DP3vHf3uYSIIiiM.RGrTLrNyOdxLGFJvImyjhgEHv8aoAvS', NULL, 'confirmation_token', NULL, 'roles', 'Admin'),
(1000, 'ahmed', 'username_canonical', 'ahmedrais988@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$8aKH2DLIPxdABchPAC0K.uY/84h.EttebwSYzngqlEUWoLSzD8fRi', NULL, 'confirmation_token', NULL, 'roles', 'Parent'),
(1001, 'maissa', 'username_canonical', 'maissabelfekih@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$qX7aFcAcLjP5Vtu1kTW9Y.IFEMYXEJjciIkUikMQDUkB3DcQu8qGS', NULL, 'confirmation_token', NULL, 'roles', 'Teacher'),
(1002, 'aziz', 'username_canonical', 'aziz13mth@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$cv4Nemki4jRLw9vfWsLMOuIzUAF1NhcQ793.BzdMD/tq6z9ysVZTa', NULL, 'confirmation_token', NULL, 'roles', 'Teacher'),
(1003, 'nada1', 'username_canonical', 'nada.chnniter@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$gLLFRBmRJj/mXXdrjy2nAu./lGr/XYM1G2HfO05szSI/YML0P46V2', NULL, 'confirmation_token', NULL, 'roles', 'Teacher'),
(1004, 'khaoula', 'username_canonical', 'khaoula.mekni@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$pehjKLe.TBbGYF4IRePMb.Hf5Ah3e.B7jJ9jwaaxwI0myeC3tyPiy', NULL, 'confirmation_token', NULL, 'roles', 'Parent'),
(1005, 'nessrine', 'username_canonical', 'maissa.belfekih@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$kB.JsASi6xCu.Q5Vptl3GegVD3vJqxJ..i4K2iT8tH.IHcHP6fO7.', NULL, 'confirmation_token', NULL, 'roles', 'Parent'),
(1006, 'nada', 'username_canonical', 'nada.chniter@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$.J9NgW3jXyPavUI6tboqNec1Fb3etOrqzfegax4cgHAbV.xizSz4S', NULL, 'confirmation_token', NULL, 'roles', 'Teacher'),
(1007, 'aziz1', 'username_canonical', 'zizou13091998@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$JDtGDa31IKEk45blT4HA8uNgrEMEu5LSy3E4yxfizBvGnJ0a2BpBC', NULL, 'confirmation_token', NULL, 'roles', 'Parent'),
(1010, 'khaoula2', 'username_canonical', 'nada.shniter@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$wUz6hph2JzixWywC5mTKpe.1gH8mr.UQyq1RkjS7nRLEshM/nCILm', NULL, 'confirmation_token', NULL, 'roles', 'Parent');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id`, `brand`, `capacity`, `status`, `type`) VALUES
(1, 'Chevrolet', 50, 0, 'Petrol');

-- --------------------------------------------------------

--
-- Structure de la table `view`
--

DROP TABLE IF EXISTS `view`;
CREATE TABLE IF NOT EXISTS `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nb_stars` int(11) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `SenderName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `view`
--

INSERT INTO `view` (`id`, `nb_stars`, `subject`, `message`, `SenderName`) VALUES
(24, 5, 'dmf:c', 'mdslf;', 'ahmed'),
(25, 4, 'restauration services', 'very delicious', 'ahmed'),
(26, 3, 'nursery services', 'average', 'maissa'),
(29, 5, 'Service restauration', 'kdkhfqbsekjf', 'ahmed'),
(30, 0, 'Service restauration', 'kdkhfqbsekjf', 'ahmed');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `FK_ED4B199F41807E1D` FOREIGN KEY (`teacher_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `interest`
--
ALTER TABLE `interest`
  ADD CONSTRAINT `FK_6C3E1A673D8E604F` FOREIGN KEY (`parent`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_6C3E1A67AC74095A` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`);

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
