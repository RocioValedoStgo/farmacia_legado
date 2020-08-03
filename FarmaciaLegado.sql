CREATE DATABASE IF NOT EXISTS `farmaciaLegado`;
USE `farmaciaLegado`;

DROP TABLE IF EXISTS `Users`;
CREATE TABLE IF NOT EXISTS `Users` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20) NOT NULL,
`last_name` varchar(30) NOT NULL,
`username` varchar(20) NOT NULL,
`phone` bigint(10) NOT NULL,
`turn` varchar(15) NOT NULL,
`rol` tinyint(4) NOT NULL,
`email` varchar(50) NOT NULL,
`password` varchar(20) NOT NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Providers`;
CREATE TABLE IF NOT EXISTS `Providers` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20) NOT NULL,
`address` varchar(30) NOT NULL,
`phone` bigint(10) NOT NULL,
`email` varchar(50) NOT NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Categories`;
CREATE TABLE IF NOT EXISTS `Categories` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20) NOT NULL,
`father_id` int(11) NULL,
`image` varchar(30) NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Products`;
CREATE TABLE IF NOT EXISTS `Products` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20) NOT NULL,
`description` varchar(30) NOT NULL,
`image` varchar(30) NULL,
`price` float(6,2) NOT NULL,
`quantity` int(10) NOT NULL,
`provider_id` int(11) NOT NULL,
`category_id` int(11) NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Sell_Details`;
CREATE TABLE IF NOT EXISTS `Sell_Details` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`product_id` int(11) NOT NULL,
`quantity` int(5) NOT NULL,
`subtotal` float(5,2) NOT NULL,
`sell_id` int(11) NOT NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Sells`;
CREATE TABLE IF NOT EXISTS `Sells` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`IVA` float(5,2) NOT NULL,
`total` float(10,2) NOT NULL,
`incoming` float(5,2) NOT NULL,
`output` float(5,2) NOT NULL,
`cash_register_id` int(11) NOT NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `Cash_Register`;
CREATE TABLE IF NOT EXISTS `Cash_Register` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`total` float(11,2) NOT NULL,
`status` binary(4) NOT NULL,
`close` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE `Products` ADD CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;
ALTER TABLE `Products` ADD CONSTRAINT `provider_id` FOREIGN KEY (`provider_id`) REFERENCES `Providers` (`id`);
ALTER TABLE `Sell_Details` ADD CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `Products` (`id`);
ALTER TABLE `Sell_Details` ADD CONSTRAINT `sell_id` FOREIGN KEY (`sell_id`) REFERENCES `Sells` (`id`);
ALTER TABLE `Sells` ADD CONSTRAINT `cash_register_id` FOREIGN KEY (`cash_register_id`) REFERENCES `Cash_Register` (`id`);

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'Salim', 'Vazquez Solis', 'svs21', 1234567982, 'Matutino', 1, 'svs@proton-devs.com', 'MTIzNDU=', '2020-07-31 17:21:38');
INSERT INTO `users` VALUES (2, 'test edit', 'user', 'test', 12345, 'Vespertino', 3, 'test@example.com', 'MTIzNDU=', '2020-07-31 20:03:16');

SET GLOBAL sql_mode = '';