-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2019 at 01:14 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `videgrenier`
--

-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authority`
--

INSERT INTO `authority` (`id`, `authority`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `bidder`
--

CREATE TABLE `bidder` (
  `id` bigint(20) NOT NULL,
  `insertionDate` datetime NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bidder`
--

INSERT INTO `bidder` (`id`, `insertionDate`, `price`, `product_id`, `user_id`) VALUES
(1, '2019-05-27 16:19:07', '301.00', 5, 1),
(2, '2019-05-27 16:20:28', '251.00', 4, 1),
(3, '2019-05-27 16:31:21', '252.00', 4, 1),
(4, '2019-05-27 16:20:28', '251.00', 11, 1),
(5, '2019-05-27 16:31:21', '252.00', 11, 1),
(6, '2019-08-14 17:05:55', '11.00', 13, 2);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category`) VALUES
(1, 0),
(2, 7),
(3, 3),
(4, 1),
(5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `category_product`
--

CREATE TABLE `category_product` (
  `Category_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL,
  `country` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `country`, `name`) VALUES
(1, 'Belgique', 'Namur'),
(2, 'Belgique', 'Gand'),
(3, 'Belgique', 'Soignies'),
(4, 'Belgique', 'Spa'),
(5, 'Belgique', 'Arlon'),
(6, 'Belgique', 'Bruxelles'),
(7, 'États-Unis', 'Albany'),
(8, 'Pays-Bas', 'Zandvoort');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `note` double NOT NULL,
  `given_id` bigint(20) DEFAULT NULL,
  `received_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `comment`, `date`, `note`, `given_id`, `received_id`) VALUES
(11, 'Serieux\r<br>Ponctuel\r<br>Un peu difficile à contacter', '2019-06-28 11:01:35', 4, 1, 2),
(14, 'Acheteur super sympathique\r<br>Se déplace dans toute la Belgique\r<br>Ponctuel', '2019-06-28 11:53:38', 4, 2, 1),
(15, 'Serieux \r<br>Ponctuel \r<br>Un peu difficile à contacter', '2019-08-12 09:10:18', 4, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `comment_buyer`
--

CREATE TABLE `comment_buyer` (
  `User_id` bigint(20) NOT NULL,
  `commentByBuyer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment_buyer`
--

INSERT INTO `comment_buyer` (`User_id`, `commentByBuyer_id`) VALUES
(2, 11);

-- --------------------------------------------------------

--
-- Table structure for table `comment_seller`
--

CREATE TABLE `comment_seller` (
  `User_id` bigint(20) NOT NULL,
  `commentBySeller_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment_seller`
--

INSERT INTO `comment_seller` (`User_id`, `commentBySeller_id`) VALUES
(1, 14);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `date`, `user_id`) VALUES
(6, '2019-06-05 13:01:53', 1),
(7, '2019-08-05 09:05:00', 1),
(8, '2019-08-05 09:05:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders_product`
--

CREATE TABLE `orders_product` (
  `Orders_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders_product`
--

INSERT INTO `orders_product` (`Orders_id`, `products_id`) VALUES
(6, 6);

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `panier`
--

INSERT INTO `panier` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8);

-- --------------------------------------------------------

--
-- Table structure for table `panier_product`
--

CREATE TABLE `panier_product` (
  `Panier_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `panier_product`
--

INSERT INTO `panier_product` (`Panier_id`, `products_id`) VALUES
(2, 2),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime NOT NULL,
  `description` varchar(10000) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `isAuction` tinyint(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `view` bigint(20) DEFAULT NULL,
  `isSell` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `creationDate`, `description`, `endDate`, `isAuction`, `name`, `picture`, `price`, `category_id`, `seller_id`, `view`, `isSell`) VALUES
(1, '2019-05-07 11:41:45', 'Windows 10 Famille 64\r\n<br>\r\n<br>Processeur Intel® Core™ i7-8750H + Carte graphique NVIDIA® GeForce® GTX 1070 (8 Go)\r\n<br>\r\n<br>16 Go de mémoire\r\n<br>\r\n<br>1 To de stockage + 256 Go de SSD\r\n<br>\r\n<br>Écran Full HD 144 Hz IPS antireflet de 39,6 cm (15,6 pouces)\r\n<br>\r\n<br>Technologie NVIDIA G-SYNC™. Chassis aluminium. Clavier rétroéclairé. Qualité audio Bang & Olufsen.', '2019-08-22 00:00:00', 0, 'OMEN by HP 15-dc0002nf - i7, 16Go, 1To + 256SSD, NVIDIA® GeForce® GTX 1070', 'D:\\tmp\\img\\test\\alienware.jpg', '1200.00', 1, 1, 1, 0),
(2, '2019-05-08 13:17:15', '1. Public Enemy\r<br>2. End Transmission\r<br>3. Excuse Me\r<br>4. Composition\r<br>5. Evolve\r<br>6. Pretenders\r<br>7. Diversion\r<br>8. In Spite Of Doubt\r<br>9. Lifeline\r<br>10. Into The Blue', NULL, 0, 'Album fire from the gods - narrative', 'D:\\tmp\\img\\test\\fire.jpg', '10.00', 2, 1, 10, 0),
(3, '2019-05-08 13:18:39', '1. Oh, Catastrophe\r<br>2. The Fallout\r<br>3. Memories of a Broken Heart\r<br>4. Makeshift Chemistry\r<br>5. The One You Feed\r<br>6. Menace\r<br>7. Journals	\r<br>8. Two\'s Too Many\r<br>9. Evidence\r<br>10. Children of Love\r<br>11. Johnny\'s Revenge', '2019-12-25 00:00:00', 1, 'Crown the empire The Fallout', 'D:\\tmp\\img\\test\\R-4839261-1377109261-7581.jpeg.jpg', '3.00', 2, 1, 9, 0),
(4, '2019-05-08 13:20:24', 'PS4 PRO EDITION LIMITEE GOD OF WAR Ce pack spécial comprend une console PS4 Pro 1 To Leviathan Grey entièrement personnalisée et inspirée par la hache de Kratos, une manette sans fil DUALSHOCK 4 assortie ainsi qu\'un exemplaire de God of War Day One Edition sur Blu-ray disc.', '2019-11-06 00:00:00', 1, 'Ps4  spécial édition GOW', 'D:\\tmp\\img\\dorian\\ps4-god-of-war.jpg', '252.00', 1, 3, 11, 0),
(5, '2019-05-09 09:53:22', 'Le reflex numérique le plus léger au monde avec écran orientable : l\'EOS 200D est un excellent choix pour les paysages, les portraits et les voyages. Il vous permet de réaliser des photos et vidéos de haute qualité grâce à son capteur nouvelle génération de 24,2 millions de pixels. Sa conception le rend compatible avec les smartphones et tablettes. Grâce à sa polyvalence, l\'objectif zoom EF-S 18-55mm f/4-5.6 IS STM inclus s\'adapte à vos séances photo quotidiennes de personnes et de lieux. La mise au point STM embellit également vos vidéos.\r\n<br>Découvrez le EOS 200D\r\n<br>Prenez de magnifiques photos et réalisez des vidéos sublimes en toute facilité\r\n<br>Une conception intelligente\r\n<br>Avec ses commandes instinctives et son écran tactile de type smartphone, le EOS 200D est extrêmement facile à utiliser. Prenez vos photos à l\'aide d\'un viseur optique lumineux pour observer la scène telle qu\'elle est réellement, ou à l\'aide de l\'écran orientable qui pivote pour vous aider à trouver des angles originaux.\r\n<br>Des photos que vous ne cesserez d\'admirer\r\n<br>Prenez directement des photos de 24,2 millions de pixels riches en détails et aux couleurs éblouissantes. Vous obtiendrez des clichés qui racontent une histoire, parfaits pour les agrandissements, les albums photo et les galeries en ligne. Nous avons équipé le EOS 200D d\'un processeur DIGIC 7 qui lui permet de capturer des photos en basse lumière. Le mode Autofocus CMOS Dual Pixel garantit quant à lui la mise au point avec visée par l\'écran la plus rapide au monde.\r\n<br>Faible profondeur de champ\r\n<br>Le grand capteur APS-C du EOS 200D permet de réaliser facilement des clichés avec une faible profondeur de champ, idéale pour les portraits. Vous obtiendrez ainsi des visages extrêmement nets sur un fond joliment flouté. Les photographes professionnels du monde entier optent pour cette approche.\r\n<br>Plus léger. Plus de photos. Plus polyvalent.\r\n<br>Le EOS 200D est l\'appareil photo reflex numérique à écran orientable le plus léger au monde¹. Conçu pour être emporté partout, il vous permet de photographier davantage de scènes. Vacances, soirées, cérémonies ou simples promenades : laissez libre cours à votre créativité.\r\n<br>Quel type de prise de vue ?\r\n<br>Quelle que soit votre utilisation de l\'appareil photo, le EOS 200D garantit une mise au point et une réactivité ultra-rapides. Prenez vos photos à l\'aide du viseur optique sans décalage pour observer la scène telle qu\'elle est réellement, ou composez vos photos à l\'aide de l\'écran orientable comme celui d\'un smartphone en profitant de l\'AF avec visée par l\'écran le plus rapide au monde.\r\n<br>Des vidéos en toute simplicité\r\n<br>Enregistrez des vidéos Full HD de haute qualité en toute simplicité grâce au mode de mise au point continue Autofocus CMOS Dual Pixel.\r\n<br>Cours de photo\r\n<br>L\'interface utilisateur du EOS 200D fournit des indications pour vous aider à choisir vos paramètres en fonction de l\'effet recherché. C\'est comme si vous étiez accompagné d\'un professeur de photo attitré.\r\n<br>Compatibilité avec smartphones et tablettes\r\n<br>Parcourez, modifiez et partagez en toute simplicité les photos de votre EOS 200D sur votre smartphone ou votre tablette². La technologie Bluetooth® Low Energy vous permet de rester connecté et d\'accéder à votre appareil photo en permanence, même lorsqu\'il se trouve dans votre sac.', '2019-12-22 10:00:00', 1, 'CANON Appareil photo reflex EOS 200D + 18-55mm + 75-300mm ', 'D:\\tmp\\img\\test2\\CANON-Appareil-photo-reflex-EOS-200D---18-55mm---75-300mm-(2250C025AA).png', '301.00', 3, 2, 6, 0),
(6, '2019-05-09 17:23:27', 'Stan smith jamais portées et vendu dans leur boite en taille 40.', NULL, 0, 'Stan smith rouge homme taille 40', 'D:\\tmp\\img\\test2\\basket-adidas-stan-smith-age-adulte-couleur.jpg', '30.00', 4, 2, 14, 1),
(7, '2019-05-09 17:28:45', 'Fontaine à eau pour chien et chat Drinkwell Platinum, incite votre animal à boire, avec réservoir intégré de 5 L et pré-filtre supplémentaire. Fontaine silencieuse.\r<br>Dimensions : L 40 x l 27 x H 26 cm', NULL, 0, 'Fontaine à eau Drinkwell Platinum 5 L', 'D:\\tmp\\img\\test2\\63245_PLA_Drinkwell_Platinum_Trinkbrunnen__5_Liter_136298_6.jpg', '20.00', 5, 2, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product_bidder`
--

CREATE TABLE `product_bidder` (
  `Product_id` bigint(20) NOT NULL,
  `bidders_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_bidder`
--

INSERT INTO `product_bidder` (`Product_id`, `bidders_id`) VALUES
(4, 2),
(4, 3),
(5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `averageRatingBuyer` double NOT NULL,
  `averageRatingSeller` double NOT NULL,
  `creationDate` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `panier_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `averageRatingBuyer`, `averageRatingSeller`, `creationDate`, `email`, `firstName`, `lastName`, `password`, `picture`, `username`, `city_id`, `panier_id`) VALUES
(1, 4, 0, '2019-05-07 11:16:58', 'dorian.s1080@gmail.com', 'test', 'test', '$2a$10$qCMQPoAbfLnbWs4o94.GMOsGOZybTVe39XuH0asc8b76YC4UxKlx6', 'D:\\tmp\\img\\test\\person1.jpg', 'test', 1, 1),
(2, 0, 4, '2019-05-07 11:25:13', 'dorian_1080@hotmail.com', 'test2', 'test2', '$2a$10$f8kDfDysL/HGl7VFvyPgweJSqlQgGB4vvVw9YWm88ohRyQqLv.mVq', 'D:\\tmp\\img\\test2\\person3.jpg', 'test2', 2, 2),
(3, 0, 0, '2019-05-07 11:47:37', 'dorian.scohier@gmail.com', 'dorian2', 'Scohier2', '$2a$10$pvW5qjKCKfmLsTNZXBn2CO2k1z7dSCFh3p30uMT6B6LQtapsqp5/W', 'D:\\tmp\\img\\dorian\\15826241_10211998188094885_695441678685516480_n.jpg', 'dorian', 3, 3),
(4, 0, 0, '2019-05-10 13:04:16', 'test3@gmail.com', 'test3', 'test3', '$2a$10$HhLyGCPYZFpqgV7SWWWu0uNqdRI6MjP7yDELr7KZHnRNnD5JqdHJa', 'D:\\tmp\\img\\test3\\person2.jpg', 'test3', 4, 4),
(5, 0, 0, '2019-05-10 13:05:05', 'test4@gmail.com', 'test4', 'test4', '$2a$10$XU7ZfPSzE7iEqfy0xuWa..xzs7MY0HoAAHHDIIEzq9MtGNBWGzjcW', 'D:\\tmp\\img\\test4\\img_bg_5.jpg', 'test4', 5, 5),
(6, 0, 0, '2019-07-30 16:46:24', 'admin@gmail.com', 'Admin', 'Admin', '$2a$10$SWR5BZHDXiBQF8E94R9aueRb8deWhR3p99iTmtnjcCMhS0efjECNa', 'D:\\tmp\\img\\admin\\15826241_10211998188094885_695441678685516480_n.jpg', 'admin', 6, 6),
(8, 0, 0, '2019-08-09 14:45:30', 'admin4@gmail.com', 'test5', 'test5', '$2a$10$21Eno7itTOmPMvH9lQmWTeNG486VvvJFooblxi7ydr8/ESJx4vvQW', 'D:\\tmp\\img\\test5\\ballon.jpg', 'test5', 8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `user_authority`
--

CREATE TABLE `user_authority` (
  `User_id` bigint(20) NOT NULL,
  `authorities_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_authority`
--

INSERT INTO `user_authority` (`User_id`, `authorities_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(6, 2),
(8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_bidder`
--

CREATE TABLE `user_bidder` (
  `User_id` bigint(20) NOT NULL,
  `bidders_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_product`
--

CREATE TABLE `user_product` (
  `User_id` bigint(20) NOT NULL,
  `productToSell_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bidder`
--
ALTER TABLE `bidder`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK76967614AE4D6595` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_product`
--
ALTER TABLE `category_product`
  ADD PRIMARY KEY (`Category_id`,`products_id`),
  ADD UNIQUE KEY `products_id` (`products_id`),
  ADD KEY `FKE9872EAEEB7CD92A` (`products_id`),
  ADD KEY `FKE9872EAEE1149F35` (`Category_id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9BDE863F43864A3` (`given_id`),
  ADD KEY `FK9BDE863F39B9A7BF` (`received_id`);

--
-- Indexes for table `comment_buyer`
--
ALTER TABLE `comment_buyer`
  ADD PRIMARY KEY (`User_id`,`commentByBuyer_id`),
  ADD UNIQUE KEY `commentByBuyer_id` (`commentByBuyer_id`),
  ADD KEY `FKBD23CFD3AE4D6595` (`User_id`),
  ADD KEY `FKBD23CFD33B033CE1` (`commentByBuyer_id`);

--
-- Indexes for table `comment_seller`
--
ALTER TABLE `comment_seller`
  ADD PRIMARY KEY (`User_id`,`commentBySeller_id`),
  ADD UNIQUE KEY `commentBySeller_id` (`commentBySeller_id`),
  ADD KEY `FK371455FAE4D6595` (`User_id`),
  ADD KEY `FK371455FC5D9869` (`commentBySeller_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8D444F05AE4D6595` (`user_id`);

--
-- Indexes for table `orders_product`
--
ALTER TABLE `orders_product`
  ADD PRIMARY KEY (`Orders_id`,`products_id`),
  ADD KEY `FKEA318595EB7CD92A` (`products_id`),
  ADD KEY `FKEA3185951ABDD495` (`Orders_id`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `panier_product`
--
ALTER TABLE `panier_product`
  ADD PRIMARY KEY (`Panier_id`,`products_id`),
  ADD KEY `FK99DFF3C9E0F23815` (`Panier_id`),
  ADD KEY `FK99DFF3C9EB7CD92A` (`products_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK50C664CFE1149F35` (`category_id`),
  ADD KEY `FK50C664CF5CD7F561` (`seller_id`);

--
-- Indexes for table `product_bidder`
--
ALTER TABLE `product_bidder`
  ADD PRIMARY KEY (`Product_id`,`bidders_id`),
  ADD UNIQUE KEY `bidders_id` (`bidders_id`),
  ADD KEY `FK711AD78460423B4A` (`bidders_id`),
  ADD KEY `FK711AD784ACD42DBF` (`Product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `FK285FEBE0F23815` (`panier_id`),
  ADD KEY `FK285FEBE5DB0195` (`city_id`);

--
-- Indexes for table `user_authority`
--
ALTER TABLE `user_authority`
  ADD PRIMARY KEY (`User_id`,`authorities_id`),
  ADD KEY `FKF114D7CFAE4D6595` (`User_id`),
  ADD KEY `FKF114D7CFF1F61761` (`authorities_id`);

--
-- Indexes for table `user_bidder`
--
ALTER TABLE `user_bidder`
  ADD PRIMARY KEY (`User_id`,`bidders_id`),
  ADD UNIQUE KEY `bidders_id` (`bidders_id`),
  ADD KEY `FK63964E860423B4A` (`bidders_id`),
  ADD KEY `FK63964E8AE4D6595` (`User_id`);

--
-- Indexes for table `user_product`
--
ALTER TABLE `user_product`
  ADD PRIMARY KEY (`User_id`,`productToSell_id`),
  ADD UNIQUE KEY `productToSell_id` (`productToSell_id`),
  ADD KEY `FKB581507BAE4D6595` (`User_id`),
  ADD KEY `FKB581507B8BAEFD92` (`productToSell_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authority`
--
ALTER TABLE `authority`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `bidder`
--
ALTER TABLE `bidder`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bidder`
--
ALTER TABLE `bidder`
  ADD CONSTRAINT `FK76967614AE4D6595` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `category_product`
--
ALTER TABLE `category_product`
  ADD CONSTRAINT `FKE9872EAEE1149F35` FOREIGN KEY (`Category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKE9872EAEEB7CD92A` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK9BDE863F39B9A7BF` FOREIGN KEY (`received_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK9BDE863F43864A3` FOREIGN KEY (`given_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment_buyer`
--
ALTER TABLE `comment_buyer`
  ADD CONSTRAINT `FKBD23CFD33B033CE1` FOREIGN KEY (`commentByBuyer_id`) REFERENCES `comment` (`id`),
  ADD CONSTRAINT `FKBD23CFD3AE4D6595` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment_seller`
--
ALTER TABLE `comment_seller`
  ADD CONSTRAINT `FK371455FAE4D6595` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK371455FC5D9869` FOREIGN KEY (`commentBySeller_id`) REFERENCES `comment` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK8D444F05AE4D6595` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `orders_product`
--
ALTER TABLE `orders_product`
  ADD CONSTRAINT `FKEA3185951ABDD495` FOREIGN KEY (`Orders_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKEA318595EB7CD92A` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `panier_product`
--
ALTER TABLE `panier_product`
  ADD CONSTRAINT `FK99DFF3C9E0F23815` FOREIGN KEY (`Panier_id`) REFERENCES `panier` (`id`),
  ADD CONSTRAINT `FK99DFF3C9EB7CD92A` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK50C664CF5CD7F561` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK50C664CFE1149F35` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `product_bidder`
--
ALTER TABLE `product_bidder`
  ADD CONSTRAINT `FK711AD78460423B4A` FOREIGN KEY (`bidders_id`) REFERENCES `bidder` (`id`),
  ADD CONSTRAINT `FK711AD784ACD42DBF` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK285FEBE0F23815` FOREIGN KEY (`panier_id`) REFERENCES `panier` (`id`),
  ADD CONSTRAINT `FK285FEBE5DB0195` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);

--
-- Constraints for table `user_authority`
--
ALTER TABLE `user_authority`
  ADD CONSTRAINT `FKF114D7CFAE4D6595` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKF114D7CFF1F61761` FOREIGN KEY (`authorities_id`) REFERENCES `authority` (`id`);

--
-- Constraints for table `user_bidder`
--
ALTER TABLE `user_bidder`
  ADD CONSTRAINT `FK63964E860423B4A` FOREIGN KEY (`bidders_id`) REFERENCES `bidder` (`id`),
  ADD CONSTRAINT `FK63964E8AE4D6595` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_product`
--
ALTER TABLE `user_product`
  ADD CONSTRAINT `FKB581507B8BAEFD92` FOREIGN KEY (`productToSell_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKB581507BAE4D6595` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
