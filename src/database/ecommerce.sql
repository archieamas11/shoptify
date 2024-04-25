-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2024 at 09:51 AM
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
  `phone number` varchar(11) DEFAULT NULL,
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

INSERT INTO `accounts_table` (`account_id`, `email`, `fname`, `lname`, `address`, `phone number`, `username`, `password`, `role`, `profile_picture`, `date joined`, `status`) VALUES
(1037, '1', '1', '1', 'Tunghaan, Minglanilla, Cebu', '09231226478', '1', '$2a$10$pILBGF5OtaHEnqLgKOoM7.fj3ouigQXNyFoK64pMkYmBK7Mwm/p6i', 'Admin', 'src/sampleProfiles/default profile 100x100.png', '2024-04-24', 'Active'),
(1040, '2', '2', '2', 'Ward III, Minglanilla, Cebu', '09231226478', '2', '$2a$10$88HZhTvIKqmk/Kr3dAaGcOx2Q7ZzeATeQSRm1E0ThP4XNgBWF6lBi', 'Seller', 'src/sampleProfiles/default profile 100x100.png', '2024-04-24', 'Inactive'),
(1041, '3', '3', '3', '3', '33333333333', '3', '$2a$10$K2DfKxrsdwvbRj2RqxvMUODLGmDgYCNhBPEuTH00YUF8FovxfxxXW', 'Buyer', 'src/sampleProfiles/default profile 100x100.png', '2024-04-25', 'Active');

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1042;

--
-- AUTO_INCREMENT for table `add2cart`
--
ALTER TABLE `add2cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1032;

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
