-- phpMyAdmin SQL Dump
-- version 3.3.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 19, 2012 at 08:31 PM
-- Server version: 5.5.9
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `questions`
--
CREATE DATABASE `smartest` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `smartest`;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `text` varchar(255) NOT NULL,
  `answersTexts` varchar(255) NOT NULL,
  `answersPoints` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `questions`
--

