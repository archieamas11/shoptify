-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2024 at 07:48 AM
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
(2, 'buyer', 'buyer', 'buyer, tuyan, cebu2', '12345678900', '2', '2', '$2a$10$SC4i5tqUFzVXjRn41weoi.7rVRmkbwwIrCQQhRbRYD979cQoi7xQe', 'Buyer', '', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active');

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
  `message_category` varchar(100) NOT NULL,
  `message_title` varchar(100) NOT NULL,
  `message_description` varchar(2000) NOT NULL,
  `date_sent` date NOT NULL,
  `message_status` varchar(100) NOT NULL DEFAULT 'Under Review'
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
  `notes` varchar(100) NOT NULL,
  `date_purchase` date NOT NULL,
  `order_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_orders`
--

INSERT INTO `tbl_orders` (`order_id`, `buyer_id`, `seller_id`, `product_id`, `total_quantity`, `total_price`, `payment_method`, `notes`, `date_purchase`, `order_status`) VALUES
(21, 2, 1, 14, 3, 615, 'COD', '', '2024-05-25', 'Accepted'),
(22, 2, 1, 12, 7, 1230, 'COD', '', '2024-05-25', 'Pending'),
(24, 2, 1, 13, 1, 369, 'COD', '', '2024-05-25', 'Pending');

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
  `total_sold` int(11) NOT NULL DEFAULT 0,
  `product_image` varchar(600) NOT NULL,
  `date_created` date NOT NULL,
  `product_status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_products`
--

INSERT INTO `tbl_products` (`product_id`, `seller_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_category`, `total_sold`, `product_image`, `date_created`, `product_status`) VALUES
(17, 1, '123', 123, 3, '12311', 'Electronics', 100, 'src/ProductsImages/p_mouse.png', '2024-05-26', 'Available');

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
(254, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 05:56:03'),
(255, 1, 'Accept Order', 'Seller 1 successfully accepted order 17!', '2024-05-25 05:56:36'),
(256, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 05:58:23'),
(257, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 05:59:51'),
(258, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 06:00:29'),
(259, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 06:01:35'),
(260, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 06:01:54'),
(261, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 06:04:09'),
(262, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 07:22:58'),
(263, 1, 'Accept Order', 'Seller 1 successfully accepted order 21!', '2024-05-25 07:23:50'),
(264, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 07:32:29'),
(265, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 07:43:37'),
(266, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-25 07:44:22'),
(267, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-25 17:03:36'),
(268, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-25 17:03:54'),
(269, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 04:27:40'),
(270, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 04:28:51'),
(271, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-26 04:30:22'),
(272, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-26 04:30:30'),
(273, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 04:34:52'),
(274, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-05-26 04:35:16'),
(275, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-26 04:35:18'),
(276, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 04:35:21'),
(277, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-26 04:35:23'),
(278, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 05:31:45'),
(279, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-26 05:31:47'),
(280, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-26 05:31:50'),
(281, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-26 05:31:51');

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
(5, 2, 1, 17, 2, '2024-05-25'),
(6, 2, 1, 12, 1, '2024-05-25'),
(7, 2, 1, 15, 1, '2024-05-25');

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
  ADD KEY `product_id` (`product_id`),
  ADD KEY `seller_id` (`seller_id`);

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  MODIFY `adminlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_buyerlogs`
--
ALTER TABLE `tbl_buyerlogs`
  MODIFY `buyerlogs_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_message4admin`
--
ALTER TABLE `tbl_message4admin`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_messages4seller`
--
ALTER TABLE `tbl_messages4seller`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
  MODIFY `sellerlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=282;

--
-- AUTO_INCREMENT for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  MODIFY `wishlist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  ADD CONSTRAINT `tbl_adminlogs_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `tbl_accounts` (`account_id`);

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
  ADD CONSTRAINT `tbl_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`),
  ADD CONSTRAINT `tbl_cart_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`);

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
  ADD CONSTRAINT `tbl_wishlist_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`product_id`),
  ADD CONSTRAINT `tbl_wishlist_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `tbl_accounts` (`account_id`),
  ADD CONSTRAINT `tbl_wishlist_ibfk_4` FOREIGN KEY (`buyer_id`) REFERENCES `tbl_accounts` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
