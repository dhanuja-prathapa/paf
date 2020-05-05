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
-- Database: `paf`
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
(1, 'National Hospital of Sri Lanka', 'National Hospital', 'General Hospital', 'Colombo 07', '0112691111'),
(2, 'Infectious Disease Hospital', 'Base Hospital Type A', 'IDH', 'Mandavila Rd, Kotikawatta', '0112411224'),
(3, 'Avissawella Base Hospital', 'Base Hospital Type A', 'Government hospital in Avissawella', 'Avissawella Rd, Avissawella', '0362222261'),
(4, 'Homagama Base Hospital', 'Base Hospital Type A', 'Government hospital in Homagama', 'Hospital Rd, Homagama 10200', '0112855200'),
(5, 'Piliyandala Divisional Hospital', 'Divisional Hospital Type A', 'Divisional Hospital - Piliyandala', 'No. 100 Colombo Rd, Piliyandala 10300', '0112614264'),
(6, 'Koswatta Divisional Hospital', 'Divisional Hospital Type B', 'Government hospital in Battaramulla', 'B240, Sri Jayawardenepura Kotte 10120', '0112862313'),
(7, 'Lanka Hospitals', 'Private Hospital', 'Lanka Hospital,     formerly known as Apollo', '578/ Elvitigala Mawatha,     Colombo 00500', '0115430000');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
