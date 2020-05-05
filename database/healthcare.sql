-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 12:21 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+05:30";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`id`, `name`, `type`, `description`, `address`, `phone`) VALUES
(1, 'Hemas', 'Eye', 'Eye Hospital', '14/B', '078834893'),
(2, 'Json', 'Efdsfsye', 'General Hospital', '126/D', '078834893'),
(3, 'x mas', 'Eye', 'General Hospital', '14/C', '078834893'),
(4, 'u mask', 'Eye', 'General Hospital', '16/D', '078834893'),
(5, 'k mask', 'Eye', 'General Hospital', '26/D', '078834893'),
(6, 'k mask', 'Efdsfsye', 'General Hospital', '26/D', '078834893'),
(7, 'luky mask', 'Efdsfsye', 'General Hospital', '26/D', '078834893'),
(20, 'Dhanujarana', 'D', 'Hello World', '14/B', '0718834893'),
(21, 'Dhanujarana', 'D', 'Hello World', '14/B', '0718834893');

-- --------------------------------------------------------

--
-- Table structure for table `reghospital`
--

CREATE TABLE `reghospital` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `licence` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reghospital`
--

INSERT INTO `reghospital` (`id`, `name`, `licence`) VALUES
(1, 'National Hospital of Sri Lanka', '123'),
(3, 'Avissawella Base Hospital', '124'),
(4, 'Homagama Base Hospital', '224'),
(5, 'Piliyandala Divisional Hospital', '224'),
(6, 'Koswatta Divisional Hospital', '224'),
(2, 'Infectious Disease Hospital', '224'),
(7, 'Hemas Hospital', '224'),
(8, 'Asiri Hospital', '224'),
(9, 'Lanka Hospitals', '224');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
