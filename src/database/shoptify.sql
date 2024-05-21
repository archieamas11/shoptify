-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2024 at 02:35 PM
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
-- Database: `shoptify`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_accounts`
--

CREATE TABLE `tbl_accounts` (
  `account_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone_number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(100) NOT NULL,
  `shop_name` varchar(100) NOT NULL,
  `profile_picture` varchar(600) NOT NULL,
  `date_joined` date NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_accounts`
--

INSERT INTO `tbl_accounts` (`account_id`, `first_name`, `last_name`, `address`, `phone_number`, `email`, `username`, `password`, `role`, `shop_name`, `profile_picture`, `date_joined`, `status`) VALUES
(1, 'Archie', 'Albarico', 'Tunghaan, Minglanilla, Cebu', '12345678900', '1', '1', '$2a$10$WcnKFLLy7oWBzaKim/dtB.6o5LVbtRSylzysmnka1Y.cghiIfCXLC', 'Seller', 'Archie Shop', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active'),
(2, 'buyer', 'buyer', 'buyer, tuyan, cebu', '12345678900', '2', '2', '$2a$10$SC4i5tqUFzVXjRn41weoi.7rVRmkbwwIrCQQhRbRYD979cQoi7xQe', 'Buyer', '', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_adminlogs`
--

CREATE TABLE `tbl_adminlogs` (
  `adminlogs_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `adminlogs_action` varchar(100) NOT NULL,
  `adminlogs_details` varchar(600) NOT NULL,
  `adminlogs_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_adminlogs`
--

INSERT INTO `tbl_adminlogs` (`adminlogs_id`, `admin_id`, `adminlogs_action`, `adminlogs_details`, `adminlogs_timestamp`) VALUES
(1, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-17 14:51:38'),
(2, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:20:16'),
(3, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:30:10'),
(4, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:31:25'),
(6, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:35:18'),
(7, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:37:21'),
(8, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-18 07:39:59'),
(10, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-19 07:34:17'),
(11, 1, 'Logged in', 'Admin 1 successfully logged in!', '2024-05-20 05:17:45');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bestproducts`
--

CREATE TABLE `tbl_bestproducts` (
  `bestproducts_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_buyerlogs`
--

CREATE TABLE `tbl_buyerlogs` (
  `buyerlogs_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `buyerlogs_action` varchar(100) NOT NULL,
  `buyerlogs_details` varchar(600) NOT NULL,
  `buyerlogs_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cart`
--

CREATE TABLE `tbl_cart` (
  `cart_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `date_added` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_invoice`
--

CREATE TABLE `tbl_invoice` (
  `invoice_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `invoice_date` date NOT NULL,
  `invoice_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_message4admin`
--

CREATE TABLE `tbl_message4admin` (
  `message_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `message` int(11) NOT NULL,
  `date_sent` date NOT NULL,
  `message_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_messages4seller`
--

CREATE TABLE `tbl_messages4seller` (
  `message_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `message` varchar(600) NOT NULL,
  `date_sent` date NOT NULL,
  `message_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orders`
--

CREATE TABLE `tbl_orders` (
  `order_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `payment_method` varchar(100) NOT NULL,
  `notes` varchar(100) NOT NULL,
  `date_purchase` date NOT NULL,
  `order_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_products`
--

CREATE TABLE `tbl_products` (
  `product_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_stock` int(11) NOT NULL,
  `product_description` varchar(900) NOT NULL,
  `product_category` varchar(100) NOT NULL,
  `total_sold` int(11) DEFAULT 0,
  `product_image` varchar(600) NOT NULL,
  `date_created` date NOT NULL,
  `product_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_products`
--

INSERT INTO `tbl_products` (`product_id`, `seller_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_category`, `total_sold`, `product_image`, `date_created`, `product_status`) VALUES
(10, 1, '123', 123, 123, '123', 'Electronics', 0, 'src/ProductsImages/3.png', '2024-05-21', 'Archived'),
(11, 1, 'qwe', 123, 123, '123', 'Electronics', 0, 'src/ProductsImages/4.png', '2024-05-21', 'Available'),
(12, 1, 'sdf', 123, 0, '', 'Electronics', 0, 'src/ProductsImages/5.png', '2024-05-21', 'Available'),
(13, 1, 'cv', 123, 123, 'qwe', 'Electronics', 0, 'src/ProductsImages/5.png', '2024-05-21', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating4products`
--

CREATE TABLE `tbl_rating4products` (
  `rate_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_star` int(11) NOT NULL,
  `date_rated` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating4seller`
--

CREATE TABLE `tbl_rating4seller` (
  `rate_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `total_star` int(11) NOT NULL,
  `date_rated` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sellerlogs`
--

CREATE TABLE `tbl_sellerlogs` (
  `sellerlogs_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `sellerlogs_action` varchar(100) NOT NULL,
  `sellerlogs_details` varchar(600) NOT NULL,
  `sellerlogs_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_sellerlogs`
--

INSERT INTO `tbl_sellerlogs` (`sellerlogs_id`, `seller_id`, `sellerlogs_action`, `sellerlogs_details`, `sellerlogs_timestamp`) VALUES
(1, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:20:13'),
(2, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:31:56'),
(3, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:32:33'),
(4, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:34:32'),
(5, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:37:57'),
(6, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:39:17'),
(7, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 06:43:02'),
(8, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 07:19:53'),
(9, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-20 07:21:18'),
(10, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 00:55:16'),
(12, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 00:57:59'),
(13, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 00:58:36'),
(14, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 00:59:19'),
(15, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:08:57'),
(16, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:12:04'),
(17, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:13:00'),
(18, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:13:50'),
(19, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:14:30'),
(20, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:16:57'),
(21, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:17:27'),
(22, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:28:10'),
(23, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:30:03'),
(24, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:30:37'),
(25, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:31:04'),
(26, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:32:00'),
(27, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:33:55'),
(28, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:34:52'),
(29, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:35:28'),
(30, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:53:44'),
(31, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 01:58:40'),
(32, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 09:34:55'),
(33, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 09:36:13'),
(34, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 09:44:19'),
(35, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 09:45:18'),
(36, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:46:55'),
(37, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:48:09'),
(38, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:48:39'),
(39, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:50:20'),
(40, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:52:55'),
(41, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:54:28'),
(42, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 10:55:44'),
(43, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 10:56:05'),
(44, 1, 'Delete product', 'User 1 Successfully deleted the product 1!', '2024-05-21 10:56:34'),
(45, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:05:21'),
(46, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:10:41'),
(47, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:11:03'),
(48, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:11:24'),
(49, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:12:23'),
(50, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:16:21'),
(51, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:16:49'),
(52, 1, 'Delete product', 'User 1 Successfully deleted the product 0!', '2024-05-21 11:17:14'),
(53, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:19:41'),
(54, 1, 'Delete product', 'User 1 Successfully deleted the product 4!', '2024-05-21 11:19:48'),
(55, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:20:21'),
(56, 1, 'Delete product', 'User 1 Successfully deleted the product 0!', '2024-05-21 11:20:24'),
(57, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:21:45'),
(58, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:24:45'),
(59, 1, 'Delete product', 'User 1 Successfully deleted the product 4!', '2024-05-21 11:24:49'),
(60, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:26:28'),
(61, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:29:54'),
(62, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:30:06'),
(63, 1, 'Delete product', 'User 1 Successfully deleted the product 0!', '2024-05-21 11:30:10'),
(64, 1, 'Delete product', 'User 1 Successfully deleted the product 5!', '2024-05-21 11:30:14'),
(65, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:32:52'),
(66, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:33:00'),
(67, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:33:51'),
(68, 1, 'Delete product', 'User 1 successfully deleted the product 6!', '2024-05-21 11:34:00'),
(69, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:36:22'),
(70, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:36:38'),
(71, 1, 'Delete product', 'User 1 successfully deleted the product 7!', '2024-05-21 11:36:43'),
(72, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:40:57'),
(73, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:41:04'),
(74, 1, 'Delete product', 'User 1 successfully deleted the product 8!', '2024-05-21 11:41:07'),
(75, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:43:48'),
(76, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:45:50'),
(77, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 11:45:58'),
(78, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:48:20'),
(79, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:48:53'),
(80, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 11:50:21'),
(81, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:00:27'),
(82, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:00:53'),
(83, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:03:46'),
(84, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:06:14'),
(85, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 12:06:26'),
(86, 1, 'Archive', 'Seller 1 Successfully put product 10 to archive!', '2024-05-21 12:06:44'),
(87, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:08:17'),
(88, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:13:01'),
(89, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:13:27'),
(90, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 12:13:39'),
(91, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:24:52'),
(92, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 12:25:11'),
(93, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:28:38'),
(94, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:33:06'),
(95, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-21 12:33:50'),
(96, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-21 12:34:10');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_wishlist`
--

CREATE TABLE `tbl_wishlist` (
  `wishlist_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `date_added` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD PRIMARY KEY (`adminlogs_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Indexes for table `tbl_bestproducts`
--
ALTER TABLE `tbl_bestproducts`
  ADD PRIMARY KEY (`bestproducts_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_buyerlogs`
--
ALTER TABLE `tbl_buyerlogs`
  ADD PRIMARY KEY (`buyerlogs_id`),
  ADD KEY `buyer_id` (`buyer_id`);

--
-- Indexes for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `buyer_id` (`buyer_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `buyer_id` (`buyer_id`,`seller_id`,`product_id`),
  ADD KEY `seller_id` (`seller_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_rating4products`
--
ALTER TABLE `tbl_rating4products`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `seller_id` (`seller_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  ADD PRIMARY KEY (`sellerlogs_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  ADD PRIMARY KEY (`wishlist_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `product_id` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  MODIFY `adminlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_bestproducts`
--
ALTER TABLE `tbl_bestproducts`
  MODIFY `bestproducts_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_buyerlogs`
--
ALTER TABLE `tbl_buyerlogs`
  MODIFY `buyerlogs_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_rating4products`
--
ALTER TABLE `tbl_rating4products`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  MODIFY `sellerlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  MODIFY `wishlist_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD CONSTRAINT `tbl_adminlogs_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_bestproducts`
--
ALTER TABLE `tbl_bestproducts`
  ADD CONSTRAINT `tbl_bestproducts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_buyerlogs`
--
ALTER TABLE `tbl_buyerlogs`
  ADD CONSTRAINT `tbl_buyerlogs_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD CONSTRAINT `tbl_cart_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  ADD CONSTRAINT `tbl_invoice_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_invoice_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `tbl_orders` (`order_id`),
  ADD CONSTRAINT `tbl_invoice_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  ADD CONSTRAINT `tbl_message4admin_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  ADD CONSTRAINT `tbl_messages4seller_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_messages4seller_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD CONSTRAINT `tbl_orders_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_orders_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_orders_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD CONSTRAINT `tbl_products_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_rating4products`
--
ALTER TABLE `tbl_rating4products`
  ADD CONSTRAINT `tbl_rating4products_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_rating4products_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_rating4products_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  ADD CONSTRAINT `tbl_rating4seller_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_rating4seller_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  ADD CONSTRAINT `tbl_sellerlogs_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  ADD CONSTRAINT `tbl_wishlist_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_wishlist_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
