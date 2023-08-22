-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 22, 2023 at 06:23 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asocijacije_rmi`
--

-- --------------------------------------------------------

--
-- Table structure for table `konacno_resenje`
--

DROP TABLE IF EXISTS `konacno_resenje`;
CREATE TABLE IF NOT EXISTS `konacno_resenje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `konacno_resenje`
--

INSERT INTO `konacno_resenje` (`id`, `name`) VALUES
(23, 'BANKA'),
(25, 'BRAZIL'),
(19, 'JAPAN'),
(20, 'PUTOVANJE'),
(21, 'BICIKL'),
(24, 'SALON');

-- --------------------------------------------------------

--
-- Table structure for table `resenje_kolona_a`
--

DROP TABLE IF EXISTS `resenje_kolona_a`;
CREATE TABLE IF NOT EXISTS `resenje_kolona_a` (
  `id_konacno_resenje` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  `one` varchar(255) COLLATE utf16_bin NOT NULL,
  `two` varchar(255) COLLATE utf16_bin NOT NULL,
  `three` varchar(255) COLLATE utf16_bin NOT NULL,
  `four` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id_konacno_resenje`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `resenje_kolona_a`
--

INSERT INTO `resenje_kolona_a` (`id_konacno_resenje`, `name`, `one`, `two`, `three`, `four`) VALUES
(24, 'NAMEŠTAJ', 'KTITOR', 'KUHINJA', 'KANCELARIJA', 'LUJ'),
(23, 'ČEK', 'KNJIŽICA', 'POTPIS', 'POKRIĆE', 'PENZIJA'),
(19, 'OSTRVO', 'TROPSKO', 'PLAŽA', 'PALME', 'IZOLACIJA'),
(20, 'AVION', 'KRILA', 'PILOTSKA KABINA', 'LETENJE', 'AERODROM'),
(21, 'BRZINA', 'TRKA', 'ADRENALIN', 'REKORD', 'SPRINT'),
(25, 'NAPREDAK', 'POBOLJŠANJE', 'USPON', 'POSAO', 'KRUŠEVAC');

-- --------------------------------------------------------

--
-- Table structure for table `resenje_kolona_b`
--

DROP TABLE IF EXISTS `resenje_kolona_b`;
CREATE TABLE IF NOT EXISTS `resenje_kolona_b` (
  `id_konacno_resenje` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  `one` varchar(255) COLLATE utf16_bin NOT NULL,
  `two` varchar(255) COLLATE utf16_bin NOT NULL,
  `three` varchar(255) COLLATE utf16_bin NOT NULL,
  `four` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id_konacno_resenje`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `resenje_kolona_b`
--

INSERT INTO `resenje_kolona_b` (`id_konacno_resenje`, `name`, `one`, `two`, `three`, `four`) VALUES
(24, 'FRIZER', 'MAŠINICA', 'MAKAZE', 'KOSA', 'BOJA'),
(19, 'SUNCE', 'TOPLOTA', 'SVETLOST', 'LETO', 'DAN'),
(20, 'MORE', 'PLAŽA', 'TALASI', 'PESAK', 'OKEAN'),
(21, 'PRIRODA', 'VOŽNJA PO STAZI', 'PARKOVI', 'REKREACIJA', 'PEJZAŽ'),
(25, 'RED', 'TEMPLARI', 'VITEŠKI', 'HOSPITALCI', 'RAD I DISCIPLINA'),
(23, 'POSLOVNA', 'PREPISKA', 'PRATNJA', 'SARADNJA', 'RADNA');

-- --------------------------------------------------------

--
-- Table structure for table `resenje_kolona_c`
--

DROP TABLE IF EXISTS `resenje_kolona_c`;
CREATE TABLE IF NOT EXISTS `resenje_kolona_c` (
  `id_konacno_resenje` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  `one` varchar(255) COLLATE utf16_bin NOT NULL,
  `two` varchar(255) COLLATE utf16_bin NOT NULL,
  `three` varchar(255) COLLATE utf16_bin NOT NULL,
  `four` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id_konacno_resenje`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `resenje_kolona_c`
--

INSERT INTO `resenje_kolona_c` (`id_konacno_resenje`, `name`, `one`, `two`, `three`, `four`) VALUES
(24, 'AUTOMOBIL', 'MOTOR', 'MARKA', 'TRKA', 'GEPEK'),
(23, 'KRV', 'ZRNCE', 'PRITISAK', 'SLIKA', 'ANALIZA'),
(19, 'TOJOTA', 'AUTOMOBIL', 'PROIZVODNJA', 'POUZDANOST', 'CIVIC'),
(20, 'PLANINA', 'VRH', 'SNEG', 'STAZA', 'PRIRODA'),
(21, 'ZDRAVLJE', 'VEŽBANJE', 'FITNES', 'AKTIVNOST', 'ENDORFIN'),
(25, 'ORAH', 'LJUSKA', 'INDIJA', 'KOKOS', 'ULJE');

-- --------------------------------------------------------

--
-- Table structure for table `resenje_kolona_d`
--

DROP TABLE IF EXISTS `resenje_kolona_d`;
CREATE TABLE IF NOT EXISTS `resenje_kolona_d` (
  `id_konacno_resenje` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  `one` varchar(255) COLLATE utf16_bin NOT NULL,
  `two` varchar(255) COLLATE utf16_bin NOT NULL,
  `three` varchar(255) COLLATE utf16_bin NOT NULL,
  `four` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id_konacno_resenje`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `resenje_kolona_d`
--

INSERT INTO `resenje_kolona_d` (`id_konacno_resenje`, `name`, `one`, `two`, `three`, `four`) VALUES
(24, 'LEPOTA', 'UNUTRAŠNJA', 'PRIRODA', 'APOLON', 'AFRODITA'),
(19, 'MORE', 'PLAŽA', 'TALASI', 'PESAK', 'OKEAN'),
(20, 'GRAD', 'ZGRADA', 'SAOBRAĆAJ', 'KULTURA', 'ISTRAŽIVANJE'),
(21, 'ZUPČANIK', 'PRENOS SNAGE', 'LANAC', 'BRZINA', 'MENJAČ'),
(25, 'KAFA', 'TURSKA', 'ZRNO', 'BELA', 'CRNA'),
(23, 'BLAGAJNA', 'SAVA CENTAR', 'BIOSKOP', 'KASA', 'DRŽAVA');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
