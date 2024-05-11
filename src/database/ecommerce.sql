-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2024 at 05:51 PM
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
(1037, 'archieamas@gmail.com', 'archie', 'albarico', 'Sitio Tabay, Minglanilla, Cebu', '09231226477', '1', '$2a$10$PcpYRhrxFcMuokyYZMo2wuvo.v7RyaEdpJFzyaNLL0EPamDyI8tsm', 'Admin', 'src/sampleProfiles/5.png', '2024-04-24', 'Active'),
(1040, '2', '2', '2', 'Ward III, Minglanilla, Cebu', '09491853866', '2', '$2a$10$88HZhTvIKqmk/Kr3dAaGcOx2Q7ZzeATeQSRm1E0ThP4XNgBWF6lBi', 'Buyer', 'src/sampleProfiles/default profile 100x100.png', '2024-04-24', 'Active'),
(1041, '3', '3', '3', 'Tuyan, Naga, Cebu', '09231226478', '3', '$2a$10$W1StfWG4D8nifoWGrymFGutQvrlkD6oTPaKAtX3v1qn54UmqWwsyS', 'Seller', 'src/sampleProfiles/default profile 100x100.png', '2024-04-25', 'Active'),
(1043, '4', '4', '4', '4', '09491853866', '4', '$2a$10$zVjHhur/mSpPSEPNasJlJOcMStb1ec/Bo0Jr.fvGPW6d1Vxhy9GVm', 'Admin', 'src/sampleProfiles/default profile 100x100.png', '2024-04-26', 'Active');

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

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `account_id`, `Product Name`, `Price`, `Stock`, `Description`, `Status`, `ImagePath`, `Date Created`) VALUES
(1032, 1041, '2', 2, 0, '2', 'archived', 'src/ProductsImages/4.png', '2024-05-09');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_adminlogs`
--

CREATE TABLE `tbl_adminlogs` (
  `adminlogs_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `adminlogs_action` varchar(100) NOT NULL,
  `adminlogs_details` varchar(600) NOT NULL,
  `adminlogs_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_adminlogs`
--

INSERT INTO `tbl_adminlogs` (`adminlogs_id`, `account_id`, `adminlogs_action`, `adminlogs_details`, `adminlogs_timestamp`) VALUES
(73, 1037, 'Logged in', 'User 1037 successfully logged in!', '2024-05-09 01:58:06'),
(74, 1037, 'Change Status', 'User 1037 Successfully changed the status of 1041 to Active', '2024-05-09 01:58:13'),
(75, 1037, 'Logged out', 'User 1037 Successfully logged out!', '2024-05-09 01:58:15'),
(76, 1037, 'Logged in', 'User 1037 successfully logged in!', '2024-05-09 02:34:39'),
(77, 1037, 'Logged in', 'User 1037 successfully logged in!', '2024-05-09 02:35:52');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sales`
--

CREATE TABLE `tbl_sales` (
  `transaction_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_price` int(11) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `payment_method` varchar(100) NOT NULL DEFAULT 'COD',
  `order_status` varchar(100) NOT NULL DEFAULT 'Pending',
  `address` varchar(100) NOT NULL,
  `date_purchase` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_sales`
--

INSERT INTO `tbl_sales` (`transaction_id`, `account_id`, `product_id`, `product_name`, `product_price`, `total_quantity`, `total_price`, `payment_method`, `order_status`, `address`, `date_purchase`) VALUES
(38, 1040, 1032, '2', 2, 2, 4, 'COD', 'Accepted', 'Ward III, Minglanilla, Cebu', '2024-05-11 15:49:01');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sellerlogs`
--

CREATE TABLE `tbl_sellerlogs` (
  `s_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `s_action` varchar(100) NOT NULL,
  `s_details` varchar(600) NOT NULL,
  `s_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
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
-- Indexes for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD PRIMARY KEY (`adminlogs_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `tbl_sales`
--
ALTER TABLE `tbl_sales`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  ADD PRIMARY KEY (`s_id`),
  ADD KEY `a_id` (`a_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts_table`
--
ALTER TABLE `accounts_table`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1044;

--
-- AUTO_INCREMENT for table `add2cart`
--
ALTER TABLE `add2cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1033;

--
-- AUTO_INCREMENT for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  MODIFY `adminlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `tbl_sales`
--
ALTER TABLE `tbl_sales`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  MODIFY `s_id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Constraints for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD CONSTRAINT `tbl_adminlogs_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`account_id`);

--
-- Constraints for table `tbl_sales`
--
ALTER TABLE `tbl_sales`
  ADD CONSTRAINT `tbl_sales_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`account_id`),
  ADD CONSTRAINT `tbl_sales_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  ADD CONSTRAINT `tbl_sellerlogs_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `accounts_table` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
