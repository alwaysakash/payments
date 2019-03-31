-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 31, 2019 at 04:14 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `monese`
--

-- --------------------------------------------------------

--
-- Table structure for table `acc_details`
--

CREATE TABLE `acc_details` (
  `acc_nm` bigint(20) NOT NULL,
  `acc_bal` bigint(20) NOT NULL,
  `acc_cre_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `acc_cre_id` varchar(255) NOT NULL,
  `acc_mnt_id` varchar(255) NOT NULL,
  `acc_mnt_ts` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acc_details`
--

INSERT INTO `acc_details` (`acc_nm`, `acc_bal`, `acc_cre_ts`, `acc_cre_id`, `acc_mnt_id`, `acc_mnt_ts`) VALUES
(9638527410, 1504, '2019-03-30 16:47:05', 'A123456', '', '2019-03-31 01:52:21'),
(9638527411, 3996, '2019-03-30 19:26:54', 'A123456', '', '2019-03-31 01:52:20');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tx_log`
--

CREATE TABLE `tx_log` (
  `tx_id` bigint(20) NOT NULL,
  `tx_to_amt` bigint(20) NOT NULL,
  `tx_from_acc_nm` varchar(255) NOT NULL,
  `tx_to_acc_nm` varchar(255) NOT NULL,
  `tx_og_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tx_st_cd` varchar(255) NOT NULL,
  `tx_st_mg` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tx_log`
--

INSERT INTO `tx_log` (`tx_id`, `tx_to_amt`, `tx_from_acc_nm`, `tx_to_acc_nm`, `tx_og_ts`, `tx_st_cd`, `tx_st_mg`) VALUES
(1, 500, '9638527410', '9638527411', '2019-03-30 19:33:53', 'S', 'SUCCESS'),
(2, 500, '9638527410', '9638527411', '2019-03-30 19:35:18', 'S', 'SUCCESS'),
(3, 500, '9638527410', '9638527411', '2019-03-30 19:38:09', 'S', 'SUCCESS'),
(4, 500, '9638527410', '9638527411', '2019-03-30 19:41:46', 'S', 'SUCCESS'),
(5, 500, '9638527410', '9638527411', '2019-03-30 19:44:19', 'S', 'SUCCESS'),
(6, 500, '9638527410', '9638527411', '2019-03-30 19:48:13', 'S', 'SUCCESS'),
(7, 50000, '9638527411', '9638527410', '2019-03-30 19:49:09', 'I', 'INITIATED'),
(8, 50000, '9638527411', '9638527410', '2019-03-30 19:51:40', 'F', 'FAILED'),
(9, 500, '9638527411', '9638527410', '2019-03-30 19:51:57', 'S', 'SUCCESS'),
(10, 500, '9638527411', '9638527410', '2019-03-30 19:52:59', 'S', 'SUCCESS'),
(11, 2, '9638527411', '9638527410', '2019-03-30 20:13:04', 'S', 'SUCCESS'),
(12, 2, '9638527411', '9638527410', '2019-03-30 20:22:20', 'S', 'SUCCESS');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `enabled`, `password`, `username`) VALUES
(1, b'1', '$2a$10$xy8.sZ3nG67FYWr96r.VcuS6qGQicsadeuCG4HOFu9dgiw..Lm47G', 'monese');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acc_details`
--
ALTER TABLE `acc_details`
  ADD PRIMARY KEY (`acc_nm`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tx_log`
--
ALTER TABLE `tx_log`
  ADD PRIMARY KEY (`tx_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD UNIQUE KEY `UK_60loxav507l5mreo05v0im1lq` (`roles_id`),
  ADD KEY `FK27iuqlfirca39l6y61p4p4qo2` (`user_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acc_details`
--
ALTER TABLE `acc_details`
  MODIFY `acc_nm` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9638527412;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tx_log`
--
ALTER TABLE `tx_log`
  MODIFY `tx_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK15d410tj6juko0sq9k4km60xq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FK27iuqlfirca39l6y61p4p4qo2` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
