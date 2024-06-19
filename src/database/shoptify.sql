-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2024 at 04:57 PM
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
(3, 'Kyrie', 'Irving', 'Tunghaan, Minglanilla, Cebu', '12345678900', 'kyrie666@gmail.com', 'kyrie', '$2a$10$zPZ6fC4O1b9/WrfjtChF.eXm8dLCvMKTPrsFgJ4MwGliJVJ5ublei', 'Seller', 'Xtronics', 'src/sampleProfiles/default_user_profile.png', '2015-05-01', 'Active'),
(4, 'archie', 'albarico', 'tunghaan, minglanilla, cebu', '09231226478', 'archiealbarico69@gmail.com', 'admin', '$2a$10$b3Z5LyUH/HqNxCo.CcnZYuS4yQ1FjD9PNg21N.Ycsy9TzfqAe1lX.', 'Admin', 'None', 'src/sampleProfiles/profile.png', '2024-05-28', 'Active'),
(7, 'Luka', 'Doncic', '3', '33333333333', 'lukadoncic123@gmail.com', 'luka', '$2a$10$ByOEEcnYH7mj7gw0aCVk1.WlxMlHTJIGGfA64RIXonpOZrvdcRQ46', 'Seller', 'Petty', 'src/sampleProfiles/default_user_profile.png', '2024-05-29', 'Active'),
(8, 'Lebron', 'James', 'Purok tambis, national high schoool', '11111111111', 'lebronjames@gmail.com', 'lebron', '$2a$10$awp6gtYURKNs7NFgVE45UeOZ1vD4mgC7bgsuROkZF8ZxUUxDb6uGy', 'Seller', 'Trendy Thrift', 'src/sampleProfiles/default_user_profile.png', '2024-05-29', 'Active'),
(9, 'Archie', 'Albarico', 'buyer, tuyan, cebu2', '12345678900', 'archiemas@gmail.com', '24', '$2a$10$SC4i5tqUFzVXjRn41weoi.7rVRmkbwwIrCQQhRbRYD979cQoi7xQe', 'Buyer', '', 'src/sampleProfiles/default_user_profile.png', '2024-05-17', 'Active');

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
(26, 1, 'Edit profile', 'User 1 Successfully change profile info!', '2024-05-28 18:55:33'),
(27, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-29 15:16:59'),
(28, 4, 'Change Status', 'User 4 Successfully changed the status of 8 to Active', '2024-05-29 15:17:05'),
(29, 4, 'Logged out', 'User 4 Successfully logged out!', '2024-05-29 15:17:38'),
(30, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-30 02:20:28'),
(31, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-30 03:33:45'),
(32, 4, 'Change Status', 'User 4 Successfully changed the role of 8 to Admin', '2024-05-30 03:33:55'),
(33, 4, 'Change Status', 'User 4 Successfully changed the role of 8 to Seller', '2024-05-30 03:33:59'),
(34, 4, 'Edit Product', 'Admin 4 successfully edited product 20!', '2024-05-30 03:34:15'),
(35, 4, 'Edit product', 'Admin 4 add product 20!', '2024-05-30 03:34:15'),
(36, 4, 'Archive', 'Admin 4 Successfully put product 27 to archive!', '2024-05-30 03:34:28'),
(37, 4, 'Restore', 'Admin 4 Successfully restore product 27!', '2024-05-30 03:34:32'),
(38, 4, 'Archive', 'Admin 4 Successfully put product 21 to archive!', '2024-05-30 03:34:37'),
(39, 4, 'Archive', 'Admin 4 Successfully put product 22 to archive!', '2024-05-30 03:34:39'),
(40, 4, 'Archive', 'Admin 4 Successfully put product 27 to archive!', '2024-05-30 03:34:41'),
(41, 4, 'Archive', 'Admin 4 Successfully put product 23 to archive!', '2024-05-30 03:34:43'),
(42, 4, 'Restore', 'Admin 4 Successfully restore product 21!', '2024-05-30 03:34:53'),
(43, 4, 'Restore', 'Admin 4 Successfully restore product 23!', '2024-05-30 03:34:54'),
(44, 4, 'Restore', 'Admin 4 Successfully restore product 22!', '2024-05-30 03:34:56'),
(45, 4, 'Restore', 'Admin 4 Successfully restore product 22!', '2024-05-30 03:34:57'),
(46, 4, 'Restore', 'Admin 4 Successfully restore product 27!', '2024-05-30 03:34:59'),
(47, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-30 03:40:38'),
(48, 4, 'Archive', 'Admin 4 Successfully put product 20 to archive!', '2024-05-30 03:40:42'),
(49, 4, 'Restore', 'Admin 4 Successfully restore product 20!', '2024-05-30 03:40:45'),
(50, 4, 'Archive', 'Admin 4 Successfully put product 20 to archive!', '2024-05-30 03:43:44'),
(51, 4, 'Restore', 'Admin 4 Successfully restore product 20!', '2024-05-30 03:43:48'),
(52, 4, 'Archive', 'Admin 4 Successfully put product 20 to archive!', '2024-05-30 03:44:34'),
(53, 4, 'Restore', 'Admin 4 Successfully restore product 20!', '2024-05-30 03:44:43'),
(54, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-30 03:51:29'),
(55, 4, 'Logged in', 'Admin 4 successfully logged in!', '2024-05-30 04:20:48');

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
(3, 2, 1, 27, 20, '2024-06-09'),
(4, 2, 1, 28, 20, '2024-06-09'),
(5, 2, 1, 29, 20, '2024-06-09'),
(6, 2, 3, 30, 21, '2024-06-10'),
(7, 9, 3, 31, 21, '2024-06-10'),
(8, 2, 3, 32, 23, '2024-06-10'),
(9, 2, 1, 33, 20, '2024-06-16'),
(10, 2, 1, 34, 20, '2024-06-16'),
(11, 2, 3, 35, 22, '2024-06-16');

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
(27, 2, 1, 20, 2, 30000, 'Cash on delivery', '', '2024-06-09', 'Accepted'),
(28, 2, 1, 20, 2, 30000, 'Cash on delivery', '', '2024-06-09', 'Accepted'),
(29, 2, 1, 20, 1, 15000, 'Cash on delivery', '', '2024-06-09', 'Accepted'),
(30, 2, 3, 21, 3, 7500, 'Cash on delivery', '', '2024-06-10', 'Accepted'),
(31, 9, 3, 21, 1, 2500, 'Cash on delivery', '', '2024-06-10', 'Accepted'),
(32, 2, 3, 23, 1, 2500, 'Cash on delivery', '', '2024-06-10', 'Accepted'),
(33, 2, 1, 20, 1, 15000, 'Cash on delivery', '', '2024-06-16', 'Accepted'),
(34, 2, 1, 20, 5, 75000, 'Cash on delivery', '', '2024-06-16', 'Accepted'),
(35, 2, 3, 22, 1, 88000, 'Cash on delivery', '', '2024-06-16', 'Accepted');

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
(20, 1, 'GTX 1650 Super OC', 15000, 1, 'Unleash your gaming potential with the UltraBoost GTX 1650 Super OC Graphics Card. Equipped with 6GB of VRAM, this overclocked powerhouse ensures smooth and immersive gameplay. The GTX 1650 Super offers exceptional performance and efficiency, making it perfect for both casual and competitive gamers. Experience high frame rates and stunning visuals with advanced Turing architecture, delivering real-time ray tracing and AI enhanced graphics. With robust cooling technology, your system stays cool even during intense gaming sessions. Elevate your gaming setup with the UltraBoost GTX 1650 Super and enjoy superior performance at an unbeatable value.', 'Electronics', 10, 'src/ProductsImages/6.png', '2024-05-27', 'Available'),
(21, 3, 'SwiftGlide Pro Mouse', 2500, 2, 'Experience precision and speed with the SwiftGlide Pro Mouse. Designed for gamers who demand the best, this ultra-lightweight mouse weighs only 49 grams and features a high-performance PAW 3395 sensor for pinpoint accuracy. With a 1000Hz polling rate, enjoy seamless and responsive gameplay that keeps you ahead of the competition. Ergonomically designed for comfort during extended gaming sessions, the SwiftGlide Pro Mouse ensures you can play longer without fatigue. Upgrade your gaming setup with a mouse that combines cutting-edge technology and superior design. Dominate every game with SwiftGlide Pro!', 'Electronics', 4, 'src/ProductsImages/p_mouse.png', '2024-05-27', 'Available'),
(22, 3, 'MacBook Pro 2021', 88000, 1, 'Discover the ultimate in performance and style with this Sleek MacBook Pro 2021. This powerful laptop features Apple\'s M1 chip, offering unprecedented speed and efficiency for all your computing needs. With its stunning 13.3-inch Retina display, you\'ll experience vivid colors and incredible detail, making it perfect for both work and entertainment. The 8GB of RAM ensures smooth multitasking, while the 256GB SSD provides ample storage space for your files and applications.\r\n\r\nThis MacBook Pro also boasts a remarkable battery life of up to 20 hours, ensuring you stay productive throughout the day. The sleek aluminum design is not only durable but also aesthetically pleasing, making it a standout device wherever you go. Additionally, the advanced macOS Monterey enhances your user experience with intuitive features and robust security.', 'Electronics', 1, 'src/ProductsImages/4.png', '2024-05-30', 'Available'),
(23, 3, 'RGB Gaming Keyboard', 2500, 7, 'Elevate your gaming experience with the RGB Gaming Keyboard Pro. This high-performance keyboard is designed for gamers who demand precision, speed, and style. Featuring customizable RGB backlighting, you can choose from a spectrum of colors and lighting effects to match your setup and create an immersive gaming atmosphere.\r\n\r\nThe keyboard is equipped with mechanical switches, delivering a satisfying tactile feedback and rapid response times for every keystroke. Anti-ghosting technology ensures that every keypress is registered accurately, even during intense gaming sessions. The durable construction and ergonomic design provide comfort and reliability, allowing you to game for hours without fatigue.\r\n\r\nWith programmable macro keys, you can assign complex commands and execute them with a single press, giving you a competitive edge in your favorite games. The dedicated media controls allow you to easily manage your audio settings without interrupting your gameplay.', 'Electronics', 1, 'src/ProductsImages/5.png', '2024-05-30', 'Available'),
(24, 3, 'iPhone 14 Pro MAX', 69000, 2, 'Experience innovation at its finest with the iPhone 14 Pro MAX. This flagship smartphone from Apple redefines excellence with its advanced features and stunning design. Boasting a 6.7-inch Super Retina XDR display, the iPhone 14 Pro MAX offers an unparalleled visual experience with vibrant colors, deep blacks, and impressive brightness, perfect for everything from browsing to streaming.\r\n\r\nPowered by the A16 Bionic chip, the iPhone 14 Pro MAX delivers lightning-fast performance, enabling seamless multitasking, gaming, and AR experiences. The iOS 16 operating system provides a smooth, intuitive user interface with enhanced privacy and security features.\r\n\r\nCapture your world in extraordinary detail with the Pro camera system, featuring a 48MP main sensor, ultra-wide lens, and telephoto lens. Whether it\'s low-light photography or 4K video recording, the iPhone 14 Pro MAX ensures stunning clarity and color accuracy.', 'Electronics', 0, 'src/ProductsImages/7.png', '2024-05-30', 'Available'),
(25, 7, 'Premium Cat Food', 199, 10, 'Nourish your feline friend with our Premium Cat Food Mix, a carefully crafted blend designed to meet all your cat’s nutritional needs. Made with high-quality, natural ingredients, this cat food offers a balanced diet that promotes overall health and vitality. Each serving is packed with essential proteins, vitamins, and minerals to support strong muscles, healthy skin, and a shiny coat.\r\n\r\nOur formula includes real chicken and fish, providing a delicious and protein-rich meal that cats crave. The added omega-3 and omega-6 fatty acids contribute to optimal brain function and a glossy coat, while taurine supports heart health and vision. We also include a blend of vegetables and grains to provide fiber for healthy digestion.', 'Pet Supplies', 0, 'src/ProductsImages/8.png', '2024-05-30', 'Available'),
(26, 8, 'Luxury Cotton Tshirt', 499, 5, 'Experience unmatched comfort and style with our Luxury Cotton T-Shirt. Crafted from the finest quality, 100% organic cotton, this t-shirt offers a soft, breathable fabric that feels great against your skin. Designed for both men and women, it features a classic fit that suits any body type, ensuring a perfect blend of comfort and elegance.\r\n\r\nThe Luxury Cotton T-Shirt is not only stylish but also incredibly durable, thanks to its high-quality stitching and fabric that withstands regular wear and washing. The versatile design makes it an ideal choice for any occasion, whether you’re dressing up for a casual day out, a night on the town, or lounging at home.', 'Fashion', 0, 'src/ProductsImages/10.png', '2024-05-30', 'Available'),
(27, 8, 'Stylish Jackets', 399, 5, 'Our jackets are crafted with the utmost attention to detail, offering a perfect blend of style, comfort, and functionality. Made from premium materials, they provide warmth and protection against the elements while ensuring breathability and freedom of movement. With a variety of designs and colors to choose from, there\'s a jacket to suit every taste and occasion. Whether you\'re braving the cold outdoors or adding a fashionable layer to your outfit, our jackets are the perfect choice for staying cozy and chic.', 'Fashion', 0, 'src/ProductsImages/15.png', '2024-05-30', 'Available');

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
(6, 20, 2),
(7, 20, 5),
(8, 22, 5);

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
(10, 1, 2),
(11, 1, 5),
(12, 3, 5);

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
(55, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-28 18:55:02'),
(56, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 14:48:14'),
(57, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 14:59:57'),
(58, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 15:01:47'),
(59, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 15:02:48'),
(60, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 15:05:38'),
(61, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 15:07:41'),
(62, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-05-29 15:17:44'),
(63, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-05-29 15:17:51'),
(64, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-05-29 15:17:53'),
(65, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-05-29 15:18:16'),
(66, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-05-29 21:22:49'),
(67, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-05-29 21:25:19'),
(68, 3, 'Add Product', 'Seller 3 Successfully added a new product!', '2024-05-29 21:26:48'),
(69, 3, 'Add Product', 'Seller 3 Successfully added a new product!', '2024-05-29 21:27:50'),
(70, 3, 'Add Product', 'Seller 3 Successfully added a new product!', '2024-05-29 21:28:44'),
(71, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-05-29 21:28:55'),
(72, 7, 'Logged in', 'Seller 7 successfully logged in!', '2024-05-29 21:29:20'),
(73, 7, 'Add Product', 'Seller 7 Successfully added a new product!', '2024-05-29 21:30:20'),
(74, 7, 'Logged out', 'Seller 7 Successfully logged out!', '2024-05-29 21:30:22'),
(75, 8, 'Logged in', 'Seller 8 successfully logged in!', '2024-05-29 21:30:35'),
(76, 8, 'Add Product', 'Seller 8 Successfully added a new product!', '2024-05-29 21:31:28'),
(77, 8, 'Add Product', 'Seller 8 Successfully added a new product!', '2024-05-29 21:33:35'),
(78, 8, 'Logged out', 'Seller 8 Successfully logged out!', '2024-05-29 21:33:40'),
(79, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:37:01'),
(80, 1, 'Add Product', 'Seller 1 Successfully added a new product!', '2024-06-02 10:37:14'),
(81, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-02 10:37:16'),
(82, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:40:36'),
(83, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:41:16'),
(84, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:42:34'),
(85, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:43:55'),
(86, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:44:47'),
(87, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:45:13'),
(88, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:46:38'),
(89, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:48:32'),
(90, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:50:03'),
(91, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:53:21'),
(92, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:55:20'),
(93, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 10:56:06'),
(94, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-02 14:54:45'),
(95, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:19:41'),
(96, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-09 05:19:50'),
(97, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:21:38'),
(98, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:31:19'),
(99, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:33:27'),
(100, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:40:37'),
(101, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:42:06'),
(102, 1, 'Accept Order', 'Seller 1 successfully accepted order 27!', '2024-06-09 05:42:27'),
(103, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:45:11'),
(104, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:46:49'),
(105, 1, 'Accept Order', 'Seller 1 successfully accepted order 28!', '2024-06-09 05:46:57'),
(106, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-09 05:47:19'),
(107, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:47:53'),
(108, 1, 'Accept Order', 'Seller 1 successfully accepted order 29!', '2024-06-09 05:48:04'),
(109, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:50:18'),
(110, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 05:59:43'),
(111, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-09 06:03:45'),
(112, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 10:25:15'),
(113, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 10:33:06'),
(114, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 10:34:27'),
(115, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 10:40:06'),
(116, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 12:16:09'),
(117, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-10 12:21:59'),
(118, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-06-10 13:01:42'),
(119, 3, 'Accept Order', 'Seller 3 successfully accepted order 30!', '2024-06-10 13:01:59'),
(120, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-06-10 13:02:18'),
(121, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-10 13:02:21'),
(122, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-10 13:02:23'),
(123, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-06-10 13:02:29'),
(124, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-06-10 13:02:44'),
(125, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-06-10 13:06:48'),
(126, 3, 'Accept Order', 'Seller 3 successfully accepted order 31!', '2024-06-10 13:07:16'),
(127, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-06-10 13:19:04'),
(128, 3, 'Accept Order', 'Seller 3 successfully accepted order 32!', '2024-06-10 13:19:08'),
(129, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-06-10 13:19:09'),
(130, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-12 01:39:05'),
(131, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-12 01:40:11'),
(132, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-12 01:43:25'),
(133, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-12 01:44:04'),
(134, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-12 01:44:42'),
(135, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-16 12:27:51'),
(136, 1, 'Accept Order', 'Seller 1 successfully accepted order 33!', '2024-06-16 12:28:19'),
(137, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-16 12:28:23'),
(138, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-16 12:30:05'),
(139, 1, 'Accept Order', 'Seller 1 successfully accepted order 34!', '2024-06-16 12:30:12'),
(140, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-16 12:30:13'),
(141, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-16 12:30:49'),
(142, 1, 'Logged out', 'Seller 1 Successfully logged out!', '2024-06-16 12:31:40'),
(143, 1, 'Logged in', 'Seller 1 successfully logged in!', '2024-06-16 12:32:19'),
(144, 3, 'Logged in', 'Seller 3 successfully logged in!', '2024-06-16 13:12:25'),
(145, 3, 'Accept Order', 'Seller 3 successfully accepted order 35!', '2024-06-16 13:12:33'),
(146, 3, 'Logged out', 'Seller 3 Successfully logged out!', '2024-06-16 13:12:37');

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
(10, 2, 1, 20, 1, '2024-06-10'),
(11, 9, 3, 21, 1, '2024-06-10');

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_adminlogs`
--
ALTER TABLE `tbl_adminlogs`
  MODIFY `adminlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tbl_invoice`
--
ALTER TABLE `tbl_invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `tbl_rating4products`
--
ALTER TABLE `tbl_rating4products`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_rating4seller`
--
ALTER TABLE `tbl_rating4seller`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_sellerlogs`
--
ALTER TABLE `tbl_sellerlogs`
  MODIFY `sellerlogs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;

--
-- AUTO_INCREMENT for table `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  MODIFY `wishlist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
