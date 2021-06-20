-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2021 at 03:10 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iug`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(9) NOT NULL,
  `name` varchar(50) NOT NULL,
  `room` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `name`, `room`) VALUES
(1, 'java', 'k101'),
(2, 'sql', 'k102'),
(3, 'web', 'k103'),
(4, 'E', 'k106'),
(5, 'diesin', 'k105');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `studentid` int(9) NOT NULL,
  `courseid` int(9) NOT NULL,
  `semester` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`studentid`, `courseid`, `semester`) VALUES
(120185070, 2, 'semester 1'),
(120191212, 2, 'semester 1'),
(120191212, 3, 'semester 1'),
(120197042, 1, 'semester 1'),
(120197045, 3, 'semester 1'),
(120197045, 5, 'semester 1'),
(120197050, 5, 'semester 1'),
(120197080, 4, 'semester 1'),
(120197088, 3, 'semester 1'),
(120197090, 3, 'semester 1'),
(120198888, 2, 'semester 1'),
(120198888, 5, 'semester 1');

-- --------------------------------------------------------

--
-- Table structure for table `std`
--

CREATE TABLE `std` (
  `id` int(9) NOT NULL,
  `name` varchar(100) NOT NULL,
  `age` int(2) NOT NULL,
  `college` varchar(50) NOT NULL,
  `gpa` double NOT NULL,
  `graduationDate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `std`
--

INSERT INTO `std` (`id`, `name`, `age`, `college`, `gpa`, `graduationDate`) VALUES
(120185070, 'yousef', 19, 'Sceince', 72, '17/6/2025'),
(120191212, 'abd qudra', 21, 'IT', 95, '18/8/2020'),
(120197042, 'taj aldeen', 21, 'Sceince', 71.25, '17/6/2025'),
(120197045, 'test', 20, 'Medical', 81.25, '11/6/2024'),
(120197050, 'jameel al qudsy', 19, 'Software Engineering', 81.25, '12/6/2024'),
(120197080, 'ali ali', 22, 'Nursing', 87.5, '10/6/2024'),
(120197088, 'aaa', 21, 'Sceince', 87.5, '20/6/2023'),
(120197090, 'omar ', 21, 'Software Engineering', 85, '18/6/2024'),
(120198888, 'ali baba', 20, 'Nursing', 81.25, '14/6/2023');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`studentid`,`courseid`),
  ADD KEY `courseid` (`courseid`);

--
-- Indexes for table `std`
--
ALTER TABLE `std`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `registration_ibfk_2` FOREIGN KEY (`studentid`) REFERENCES `std` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
