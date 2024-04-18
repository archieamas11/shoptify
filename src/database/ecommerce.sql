-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2024 at 12:15 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts_table`
--

CREATE TABLE `accounts_table` (
  `account_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fname` varchar(24) NOT NULL,
  `lname` varchar(24) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `username` varchar(24) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(24) NOT NULL,
  `profile_picture` varchar(600) DEFAULT NULL,
  `date joined` date DEFAULT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts_table`
--

INSERT INTO `accounts_table` (`account_id`, `email`, `fname`, `lname`, `address`, `gender`, `username`, `password`, `role`, `profile_picture`, `date joined`, `status`) VALUES
(1021, '1', '1', '1', NULL, NULL, '1', '$2a$10$IuaBakuMCeHBEdPaJIoaL.7T66rpjvKW3LnPSwqI29NTP28Rx0Qou', 'Admin', NULL, '2024-04-11', 'Activated'),
(1022, '2', '2', '2', NULL, NULL, '2', '$2a$10$NOxD84Sv8QpWql02gEoGI.Df6f07g39wWNi3LpbSmAY.5uDRUdjZW', 'Admin', NULL, '2024-04-11', 'Activated'),
(1024, '3', '3', '3', NULL, NULL, '3', '$2a$10$ZFdsOK5sQCKxsBUo9uhFcefNJmmP4TjdfehpYi.sLHZXgST9m7B26', 'Buyer', NULL, '2024-04-12', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `add2cart`
--

CREATE TABLE `add2cart` (
  `cart_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_price` int(50) NOT NULL,
  `product_quantity` int(100) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `add2cart`
--

INSERT INTO `add2cart` (`cart_id`, `account_id`, `product_id`, `product_name`, `product_price`, `product_quantity`, `timestamp`) VALUES
(37, 1024, 1024, 'Archie ALbarico', 4543, 2, '2024-04-17 06:49:38'),
(38, 1022, 1024, 'Archie ALbarico', 4543, 1, '2024-04-17 06:50:53'),
(39, 1022, 1001, 'Samsung Galaxy s20', 35000, 1, '2024-04-17 06:56:27'),
(40, 1024, 1027, 'Gaming Headset', 1299, 1, '2024-04-17 08:14:03');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `Product Name` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL,
  `Stock` int(11) NOT NULL,
  `Description` varchar(300) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `ImagePath` varchar(600) NOT NULL,
  `Date Created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `account_id`, `Product Name`, `Price`, `Stock`, `Description`, `Status`, `ImagePath`, `Date Created`) VALUES
(1001, 1021, 'Samsung Galaxy s20', 35000, 3, 'Introducing the latest new Samsung Galaxy smartphone, designed to revolutionize your mobile experience. With its sleek design and cutting-edge features.', 'Available', 'src/images/phone.png', '2024-04-01'),
(1024, 1021, 'Archie ALbarico', 4543, 3, '\"Archie Albarico\" sounds like a unique and memorable name. \"Archie\" is a friendly and familiar first name, often associated with comic book characters like Archie Andrews. \"Albarico\" is less common and could be of Italian or Spanish origin, adding a touch of exoticism to the name.', 'Available', 'src/images/2024 Yearly Calendar.png', '2024-04-03'),
(1027, 1021, 'Gaming Headset', 1299, 3, 'Immerse yourself in the game with our high-quality gaming headset! Experience crystal-clear audio and deep bass for an unparalleled gaming experience. Designed for comfort during long gaming sessions, our headset features adjustable headbands and plush ear cushions. ', 'Available', 'src/images/headset.png', '2024-04-17'),
(1028, 1021, '123', 123, 123, '123', 'Available', 'src/images/headset.png', '2024-04-18'),
(1029, 1021, '1234', 1234, 1233, '1234', 'Available', 'src/images/2.png', '2024-04-18');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `transaction_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_price` int(11) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `date_purchase` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`transaction_id`, `account_id`, `product_id`, `product_name`, `product_price`, `total_quantity`, `total_price`, `date_purchase`) VALUES
(36, 1024, 1001, 'Samsung Galaxy s20', 35000, 3, 105000, '2024-04-17 06:43:15'),
(37, 1022, 1029, '1234', 1234, 1, 1234, '2024-04-18 09:00:52');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts_table`
--
ALTER TABLE `accounts_table`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `add2cart`
--
ALTER TABLE `add2cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `product_id` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts_table`
--
ALTER TABLE `accounts_table`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1027;

--
-- AUTO_INCREMENT for table `add2cart`
--
ALTER TABLE `add2cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1030;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `add2cart`
--
ALTER TABLE `add2cart`
  ADD CONSTRAINT `add2cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`account_id`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`account_id`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
