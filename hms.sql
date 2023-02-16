/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.26 : Database - hms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hms`;

/*Table structure for table `appointments` */

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appointment_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `reason` varchar(255) NOT NULL,
  `status` enum('Scheduled','In Progress','Completed','Cancelled') NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `appointments` */

/*Table structure for table `doctors` */

DROP TABLE IF EXISTS `doctors`;

CREATE TABLE `doctors` (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `specialty` varchar(100) NOT NULL,
  `years_of_experience` int NOT NULL,
  `degree` varchar(100) NOT NULL,
  `hospital_affiliation` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `doctors` */

/*Table structure for table `medications` */

DROP TABLE IF EXISTS `medications`;

CREATE TABLE `medications` (
  `medication_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `treatment_id` int NOT NULL,
  `medication_name` varchar(255) NOT NULL,
  `dosage` varchar(100) NOT NULL,
  `frequency` enum('Once daily','Twice daily','Three times daily','Four times daily') NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('Active','Completed','Cancelled') NOT NULL,
  PRIMARY KEY (`medication_id`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `treatment_id` (`treatment_id`),
  CONSTRAINT `medications_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  CONSTRAINT `medications_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`),
  CONSTRAINT `medications_ibfk_3` FOREIGN KEY (`treatment_id`) REFERENCES `treatments` (`treatment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `medications` */

/*Table structure for table `patients` */

DROP TABLE IF EXISTS `patients`;

CREATE TABLE `patients` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `emergency_contact_name` varchar(100) NOT NULL,
  `emergency_contact_number` varchar(20) NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `patients` */

/*Table structure for table `treatments` */

DROP TABLE IF EXISTS `treatments`;

CREATE TABLE `treatments` (
  `treatment_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `appointment_id` int NOT NULL,
  `treatment_name` varchar(255) NOT NULL,
  `treatment_description` varchar(1000) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('In Progress','Completed','Cancelled') NOT NULL,
  PRIMARY KEY (`treatment_id`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `appointment_id` (`appointment_id`),
  CONSTRAINT `treatments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  CONSTRAINT `treatments_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`),
  CONSTRAINT `treatments_ibfk_3` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `treatments` */

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mobileNo` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_login` */

insert  into `user_login`(`id`,`username`,`mobileNo`,`password`) values 
(5,'pravesh','7007340557','$2a$10$NatONQS6ukBnK/wdMxcTzub6mFkMtppQHwe2vMOjEmqG2HzJElwzu');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(30) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_roles` */

insert  into `user_roles`(`id`,`authority`,`username`) values 
(2,'ADMIN','pravesh');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;








CREATE TABLE `sms_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `http_response_code` varchar(100) NOT NULL,
  `message` text,
  `phone` varchar(100) DEFAULT NULL,
  `response_code` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `delivery_time` varchar(100) DEFAULT NULL,
  `sms_properties_id` int DEFAULT NULL,
  `created_by` varchar(150) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(150) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) 


insert  into `sms_log`(`id`,`http_response_code`,`message`,`phone`,`response_code`,`status`,`delivery_time`,`sms_properties_id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values 
(4,'200','Success','7007340557','17','INPROGRESS',NULL,2,'pravesh','2023-02-16 12:49:24','pravesh','2023-02-16 12:49:24');




CREATE TABLE `sms_properties` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `base_url` text,
  `ukey` varchar(255) NOT NULL,
  `credittype` varchar(100) NOT NULL,
  `language` varchar(50) NOT NULL,
  `senderid` varchar(25) NOT NULL,
  `templateid` varchar(20) NOT NULL,
  `filetype` varchar(100) NOT NULL,
  `isactive` bit(1) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(150) DEFAULT NULL,
  `last_modified_by` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sms_properties` */

insert  into `sms_properties`(`id`,`base_url`,`ukey`,`credittype`,`language`,`senderid`,`templateid`,`filetype`,`isactive`,`created_date`,`last_modified_date`,`created_by`,`last_modified_by`) values 
(1,'http://125.16.147.178/VoicenSMS/webresources/CreateSMSCampaignGet?','W5VGUYO5ruBartWeOKJoDDqDk','2','0','GOMRKT','0','2','','2023-02-15 13:18:49','2023-02-15 13:19:09','pravesh','pravesh'),
(2,'https://test.payumoney.com/Asynctaskprocessor/int/sendSms','vpp_otp_login','otp','0','PAYUMN','2','0','','2023-02-16 12:44:37','2023-02-16 12:44:40',NULL,NULL);