-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2024 at 03:16 AM
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
(1, 'Archie', 'Albarico', 'Tunghaan, Minglanilla, Cebu', '12345678900', 'archieamas@gmail.com', '1', '$2a$10$WcnKFLLy7oWBzaKim/dtB.6o5LVbtRSylzysmnka1Y.cghiIfCXLC', 'Seller', 'Archie Shop', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active'),
(2, 'buyer', 'buyer', 'buyer, tuyan, cebu2', '12345678900', '2', '2', '$2a$10$SC4i5tqUFzVXjRn41weoi.7rVRmkbwwIrCQQhRbRYD979cQoi7xQe', 'Buyer', '', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active'),
(3, 'Archie', 'Albarico', 'Tunghaan, Minglanilla, Cebu', '12345678900', '1', 'seller', '$2a$10$zPZ6fC4O1b9/WrfjtChF.eXm8dLCvMKTPrsFgJ4MwGliJVJ5ublei', 'Seller', 'Xtronics', 'src/sampleProfiles/default_user_profile.png', '2015-05-01', 'Active'),
(4, 'archie', 'albarico', 'tunghaan, minglanilla, cebu', '09231226478', 'archiealbarico69@gmail.com', 'admin', '$2a$10$b3Z5LyUH/HqNxCo.CcnZYuS4yQ1FjD9PNg21N.Ycsy9TzfqAe1lX.', 'Admin', 'None', 'src/sampleProfiles/profile.png', '2024-05-28', 'Active');

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
(12, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 13:58:33'),
(13, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 14:47:31'),
(14, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 14:53:57'),
(15, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 14:55:10'),
(16, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 14:56:14'),
(17, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 16:05:11'),
(18, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 16:07:31'),
(19, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 16:09:07'),
(20, 4, 'Logged out', 'User 4 Successfully logged out!', '2024-05-28 16:09:25'),
(21, 1, 'Edit profile', 'User 1 Successfully change profile info!', '2024-05-28 16:26:40'),
(22, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 17:24:46'),
(23, 4, 'Logged out', 'User 4 Successfully logged out!', '2024-05-28 17:25:45'),
(24, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-28 17:26:55'),
(25, 1, 'Edit profile', 'User 1 Successfully change profile info!', '2024-05-28 18:55:12'),
(26, 1, 'Edit profile', 'User 1 Successfully change profile info!', '2024-05-28 18:55:33');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cart`
--

CREATE TABLE `tbl_cart` (
  `cart_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
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
  `product_id` int(11) NOT NULL,
  `invoice_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_invoice`
--

INSERT INTO `tbl_invoice` (`invoice_id`, `buyer_id`, `seller_id`, `order_id`, `product_id`, `invoice_date`) VALUES
(1, 2, 1, 26, 20, '2024-05-28'),
(2, 2, 1, 26, 20, '2024-05-28');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_message4admin`
--

CREATE TABLE `tbl_message4admin` (
  `message_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `message_category` varchar(100) NOT NULL,
  `message_title` varchar(100) NOT NULL,
  `message_description` varchar(2000) NOT NULL,
  `date_sent` date NOT NULL,
  `message_status` varchar(100) NOT NULL DEFAULT 'Under Review'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_message4admin`
--

INSERT INTO `tbl_message4admin` (`message_id`, `account_id`, `message_category`, `message_title`, `message_description`, `date_sent`, `message_status`) VALUES
(2, 1, 'Report a issue', 'asd', 'asd', '2024-05-29', 'Under Review'),
(3, 2, 'Delete a product', 'erdged', 'gdfg', '2024-05-29', 'Under Review');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_messages4seller`
--

CREATE TABLE `tbl_messages4seller` (
  `message_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `message` varchar(600) NOT NULL,
  `date_sent` date NOT NULL,
  `message_status` varchar(100) NOT NULL DEFAULT 'Under Review'
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
  `notes` varchar(500) NOT NULL,
  `date_purchase` date NOT NULL,
  `order_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_orders`
--

INSERT INTO `tbl_orders` (`order_id`, `buyer_id`, `seller_id`, `product_id`, `total_quantity`, `total_price`, `payment_method`, `notes`, `date_purchase`, `order_status`) VALUES
(26, 2, 1, 20, 1, 15000, 'Cash on delivery', '', '2024-05-28', 'Accepted');

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
  `product_description` varchar(1000) NOT NULL,
  `product_category` varchar(100) NOT NULL,
  `total_sold` int(11) NOT NULL DEFAULT 0,
  `product_image` varchar(600) NOT NULL,
  `date_created` date NOT NULL,
  `product_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_products`
--

INSERT INTO `tbl_products` (`product_id`, `seller_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_category`, `total_sold`, `product_image`, `date_created`, `product_status`) VALUES
(20, 1, 'GTX 1650 Super OC', 15000, 8, 'Unleash your gaming potential with the UltraBoost GTX 1650 Super OC Graphics Card. Equipped with 6GB of VRAM, this overclocked powerhouse ensures smooth and immersive gameplay. The GTX 1650 Super offers exceptional performance and efficiency, making it perfect for both casual and competitive gamers. Experience high frame rates and stunning visuals with advanced Turing architecture, delivering real-time ray tracing and AI enhanced graphics. With robust cooling technology, your system stays cool even during intense gaming sessions. Elevate your gaming setup with the UltraBoost GTX 1650 Super and enjoy superior performance at an unbeatable value.', 'Electronics', 2, 'src/ProductsImages/6.png', '2024-05-27', 'Available'),
(21, 3, 'SwiftGlide Pro Mouse', 2500, 3, 'Experience precision and speed with the SwiftGlide Pro Mouse. Designed for gamers who demand the best, this ultra-lightweight mouse weighs only 49 grams and features a high-performance PAW 3395 sensor for pinpoint accuracy. With a 1000Hz polling rate, enjoy seamless and responsive gameplay that keeps you ahead of the competition. Ergonomically designed for comfort during extended gaming sessions, the SwiftGlide Pro Mouse ensures you can play longer without fatigue. Upgrade your gaming setup with a mouse that combines cutting-edge technology and superior design. Dominate every game with SwiftGlide Pro!', 'Electronics', 0, 'src/ProductsImages/p_mouse.png', '2024-05-27', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating4products`
--

CREATE TABLE `tbl_rating4products` (
  `rate_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_star` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_rating4products`
--

INSERT INTO `tbl_rating4products` (`rate_id`, `product_id`, `total_star`) VALUES
(3, 20, 5);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating4seller`
--

CREATE TABLE `tbl_rating4seller` (
  `rate_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `total_star` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_rating4seller`
--

INSERT INTO `tbl_rating4seller` (`rate_id`, `seller_id`, `total_star`) VALUES
(5, 1, 5);

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
(1, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 15:03:51'),
(2, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-27 15:05:21'),
(3, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-27 15:05:28'),
(4, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 15:36:50'),
(5, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-27 15:37:09'),
(6, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-05-27 15:38:52'),
(7, 3, 'Add Product', 'Seller 3 Successfully added a new product!', '2024-05-27 15:39:38'),
(8, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-05-27 15:39:51'),
(9, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 15:50:02'),
(10, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 15:55:59'),
(11, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:23:51'),
(12, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-27 16:24:00'),
(13, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:25:07'),
(14, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-05-27 16:26:35'),
(15, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:39:40'),
(16, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:43:28'),
(17, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:44:32'),
(18, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:45:30'),
(19, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:46:26'),
(20, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:49:26'),
(21, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-27 16:52:16'),
(22, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 11:10:56'),
(23, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 11:49:41'),
(24, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:07:06'),
(25, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-28 13:07:17'),
(26, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:07:43'),
(27, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:13:08'),
(28, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:14:11'),
(29, 1, 'Accept Order', 'Seller 1 successfully accepted order 26!', '2024-05-28 13:15:02'),
(30, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:16:43'),
(31, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:20:00'),
(32, 1, 'Accept Order', 'Seller 1 successfully accepted order 26!', '2024-05-28 13:20:11'),
(33, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:20:45'),
(34, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:29:03'),
(35, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:30:42'),
(36, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:33:42'),
(37, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 13:34:43'),
(38, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 15:02:44'),
(39, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 15:10:04'),
(40, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 15:13:51'),
(41, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 15:17:25'),
(42, 1, 'Edit Product', 'Seller 1 successfully edited product 20!', '2024-05-28 15:17:35'),
(43, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:09:29'),
(44, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:10:54'),
(45, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:11:59'),
(46, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:14:37'),
(47, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:20:30'),
(48, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 16:24:01'),
(49, 1, 'Archive', 'Seller 1 Successfully put product 20 to archive!', '2024-05-28 16:24:34'),
(50, 1, 'Restore', 'Seller 1 Successfully restore product 20!', '2024-05-28 16:24:53'),
(51, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 17:11:42'),
(52, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 17:14:49'),
(53, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-28 17:14:56'),
(54, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 18:46:14'),
(55, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 18:55:02');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_wishlist`
--

CREATE TABLE `tbl_wishlist` (
  `wishlist_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_favorites` int(11) NOT NULL,
  `date_added` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_wishlist`
--

INSERT INTO `tbl_wishlist` (`wishlist_id`, `buyer_id`, `seller_id`, `product_id`, `total_favorites`, `date_added`) VALUES
(8, 2, 1, 20, 1, '2024-05-27'),
(9, 2, 3, 21, 1, '2024-05-27');

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
-- Indexes for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `buyer_id` (`buyer_id`,`product_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `buyer_id` (`buyer_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `seller_id` (`seller_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `seller_id` (`account_id`);

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
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  ADD PRIMARY KEY (`rate_id`),
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
  ADD KEY `product_id` (`product_id`),
  ADD KEY `seller_id` (`seller_id`),
  ADD KEY `buyer_id` (`buyer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  MODIFY `adminlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tbl_rating4products`
--
ALTER TABLE `tbl_rating4products`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  MODIFY `sellerlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  MODIFY `wishlist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD CONSTRAINT `tbl_adminlogs_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD CONSTRAINT `tbl_cart_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`),
  ADD CONSTRAINT `tbl_cart_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  ADD CONSTRAINT `tbl_invoice_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_invoice_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `tbl_orders` (`order_id`),
  ADD CONSTRAINT `tbl_invoice_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_invoice_ibfk_4` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  ADD CONSTRAINT `tbl_message4admin_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `tbl_accounts` (`account_id`);

--
-- Constraints for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  ADD CONSTRAINT `tbl_messages4seller_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `tbl_accounts` (`account_id`);

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
  ADD CONSTRAINT `tbl_rating4products_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);

--
-- Constraints for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
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
  ADD CONSTRAINT `tbl_wishlist_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_wishlist_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
