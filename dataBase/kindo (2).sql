-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 13, 2020 at 06:45 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kindo`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`name`, `description`) VALUES
('art', 'amazing art class'),
('athletism', 'sports are the best'),
('gymnastics', 'hey sports'),
('msms', 'dkdk'),
('read', 'reading books'),
('sing', 'good voice'),
('swimming', 'good for shape'),
('writing', 'good hand writing');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
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
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`, `cin`) VALUES
(17, 'kindo', 'kindo', 'kindo@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$RUp503DP3vHf3uYSIIiiM.RGrTLrNyOdxLGFJvImyjhgEHv8aoAvS', NULL, 'confirmation_token', NULL, 'roles', 'Admin', 5442121);

-- --------------------------------------------------------

--
-- Table structure for table `child`
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
-- Dumping data for table `child`
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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `nb_child` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `teacher_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `name`, `nb_child`, `level`, `teacher_name`) VALUES
(56, 'lions', 0, 2, 'aziz'),
(57, 'smurfs', 1, 1, 'aziz'),
(58, 'turtles', 4, 3, 'maissa'),
(59, 'birds', 0, 2, 'maissa'),
(60, 'tigers', -1, 4, 'maissa'),
(61, 'papillon', 0, 2, 'aziz'),
(62, 'future', 1, 4, 'aziz'),
(63, 'rabbit', 24, 2, 'nada'),
(64, 'cats', 25, 5, 'maissa');

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
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
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`id`, `subject`, `teacher_id`, `parent_id`, `type`, `message`, `response`, `status`) VALUES
(5, 'insult', NULL, NULL, 'teacher', 'teacher insult my child', 'kjsndkjcdn', 1),
(6, 'dirt', NULL, NULL, 'restaurant services', 'food in restaurant are dirty', 'oifdj', 1),
(7, 'ajbdn,', NULL, NULL, 'teacher', 'dfnkgd', 'sama7ni', 1);

-- --------------------------------------------------------

--
-- Table structure for table `driver`
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`id`, `firstname`, `lastname`, `age`, `phonenumber`, `adress`) VALUES
(2, 'kikihjdeksghdfcj', 'gthjk', 26, 948276195, 'ghjk');

-- --------------------------------------------------------

--
-- Table structure for table `event`
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
-- Dumping data for table `event`
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
-- Table structure for table `forum`
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
-- Dumping data for table `forum`
--

INSERT INTO `forum` (`id`, `message`, `event_id`, `user_id`) VALUES
(1, 'yess i like it', 1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `fos_user`
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(14, 'kindo', 'kindo', 'mohamedaziz.mathlouthi@esprit.tn', 'mohamedaziz.mathlouthi@esprit.tn', 1, NULL, '$2y$13$MoyM1qHegw0TG4mGCDoImOAERs2HAZTPpdjBfcUR1zxc56jzxOBey', '2020-04-13 18:08:11', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'),
(17, 'aziz100', 'aziz100', 'aziz13mth@gmail.com', 'aziz13mth@gmail.com', 1, NULL, '$2y$13$6P40YBSmKHP.PUMCc7bjtOU2U78nP8m7/Qu.HGzIVkquoeYiEvEya', '2020-04-13 03:16:21', NULL, NULL, 'a:1:{i:0;s:12:\"ROLE_TEACHER\";}'),
(18, 'aziz100aziz100', 'aziz100aziz100', 'aziz13mth@gmail.commm', 'aziz13mth@gmail.commm', 1, NULL, '$2y$13$lS5pnlxpZMCdZeV6ZVBlgOY2/EVv7DIHmX1Xab84A7av1gkYpITEa', '2020-04-13 03:17:55', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}'),
(19, 'khaoula', 'khaoula', 'khaoula.mekni@esprit.tn', 'khaoula.mekni@esprit.tn', 1, NULL, '$2y$13$a1Zw.VH3vEXlwezggthD8OLYPnqcrVUc8XxEIDrahbgZ5sWB7Qnie', '2020-04-13 17:30:48', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
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
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `date_day`, `plate1`, `plate2`, `plate3`) VALUES
(12, '2020-02-28', 'salade', 'couscous', 'yaourt');

-- --------------------------------------------------------

--
-- Table structure for table `parent`
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
-- Dumping data for table `parent`
--

INSERT INTO `parent` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`, `nbr_child`) VALUES
(1000, 'ahmed', 'username_canonical', 'ahmedrais988@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$jV5uvEizuRQXQuX9G3ZLqu/HaAMr3/TVBE/Eod5pWR12ZyaW1fFSG', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1004, 'khaoula', 'username_canonical', 'khaoula.mekni@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$aLUQ6YYY/C2Ut5dvGIY25ec5VqtTZ1BCB2epIJuEi2Fg6Q9VYvijO', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1005, 'nessrine', 'username_canonical', 'maissa.belfekih@esprit.tn', 'email_canonical', 1, 'salt', '$2a$10$kB.JsASi6xCu.Q5Vptl3GegVD3vJqxJ..i4K2iT8tH.IHcHP6fO7.', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1007, 'aziz1', 'username_canonical', 'zizou13091998@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$3d23EmYrqV9EWDuwqswSzecJRJlQsfUZsQWLvEVkYcOr8jkHr3CAC', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0),
(1010, 'khaoula2', 'username_canonical', 'nada.shniter@gmail.com', 'email_canonical', 1, 'salt', '$2a$10$wUz6hph2JzixWywC5mTKpe.1gH8mr.UQyq1RkjS7nRLEshM/nCILm', NULL, 'confirmation_token', NULL, 'roles', 'Parent', 0);

-- --------------------------------------------------------

--
-- Table structure for table `participation`
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
-- Dumping data for table `participation`
--

INSERT INTO `participation` (`parent_id`, `event_id`) VALUES
(19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `publication`
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
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `subject`, `content`, `class_name`, `teacher_id`) VALUES
(29, 'to do', 'repeat all activities', 'lions', 1001),
(32, 'Remarks', 'today class activities were fruitful', 'smurfs', 1001),
(33, 'activity sing', 'good', 'birds', 1001),
(34, 'to doff', 'dkdkdk', 'papillon', 1001);

-- --------------------------------------------------------

--
-- Table structure for table `save_nurse`
--

DROP TABLE IF EXISTS `save_nurse`;
CREATE TABLE IF NOT EXISTS `save_nurse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) NOT NULL,
  `date_end` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `save_nurse`
--

INSERT INTO `save_nurse` (`id`, `date_begin`, `date_end`) VALUES
(11, '2020-01-28', '2020-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `save_resto`
--

DROP TABLE IF EXISTS `save_resto`;
CREATE TABLE IF NOT EXISTS `save_resto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_begin` varchar(10) NOT NULL,
  `date_end` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `save_resto`
--

INSERT INTO `save_resto` (`id`, `date_begin`, `date_end`) VALUES
(5, '2020-01-12', '2020-02-28');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
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
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity1` varchar(255) DEFAULT NULL,
  `activity2` varchar(255) DEFAULT NULL,
  `activity3` varchar(255) DEFAULT NULL,
  `activity4` varchar(255) DEFAULT NULL,
  `class` varchar(20) NOT NULL,
  `date` varchar(255) NOT NULL,
  `jour` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_table`
--

INSERT INTO `time_table` (`id`, `activity1`, `activity2`, `activity3`, `activity4`, `class`, `date`, `jour`) VALUES
(112, 'athletism', 'null', 'null', 'null', 'lions', '23/29-Mars', 1),
(113, 'athletism', 'null', 'null', 'hhull', 'lions', '23/29-Mars', 2),
(114, 'art', 'null', 'null', 'null', 'lions', '23/29-Mars', 3),
(115, 'athletism', 'null', 'null', 'null', 'lions', '23/29-Mars', 4),
(116, 'athletism', 'null', 'null', 'null', 'lions', '23/29-Mars', 5),
(117, 'gymnastics', 'art', 'sing', 'art', 'lions', '24/29-Mars', 1),
(118, 'athletism', 'art', 'gymnastics', 'artss', 'lions', '24/29-Mars', 2),
(119, 'art', 'swimming', 'gymnastics', 'athletism', 'lions', '24/29-Mars', 3),
(120, 'art', 'msms', 'gymnastics', 'art', 'lions', '24/29-Mars', 4),
(121, 'art', 'msms', 'athletism', 'null', 'lions', '24/29-Mars', 5);

-- --------------------------------------------------------

--
-- Table structure for table `traffectation`
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `traffectation`
--

INSERT INTO `traffectation` (`id`, `grade`, `driver`, `vehicule`) VALUES
(4, 56, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
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
-- Dumping data for table `user`
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
-- Table structure for table `vehicule`
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
-- Dumping data for table `vehicule`
--

INSERT INTO `vehicule` (`id`, `brand`, `capacity`, `status`, `type`) VALUES
(1, 'Chevrolet', 50, 1, 'Petrol');

-- --------------------------------------------------------

--
-- Table structure for table `view`
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
-- Dumping data for table `view`
--

INSERT INTO `view` (`id`, `nb_stars`, `subject`, `message`, `SenderName`) VALUES
(24, 5, 'dmf:c', 'mdslf;', 'ahmed'),
(25, 4, 'restauration services', 'very delicious', 'ahmed'),
(26, 3, 'nursery services', 'average', 'maissa'),
(29, 5, 'Service restauration', 'kdkhfqbsekjf', 'ahmed'),
(30, 0, 'Service restauration', 'kdkhfqbsekjf', 'ahmed');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `traffectation`
--
ALTER TABLE `traffectation`
  ADD CONSTRAINT `FK_22129FC411667CD9` FOREIGN KEY (`driver`) REFERENCES `driver` (`id`),
  ADD CONSTRAINT `FK_22129FC4292FFF1D` FOREIGN KEY (`vehicule`) REFERENCES `vehicule` (`id`),
  ADD CONSTRAINT `FK_22129FC4595AAE34` FOREIGN KEY (`grade`) REFERENCES `class` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
