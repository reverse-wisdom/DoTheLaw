-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: law
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT COMMENT '글번호',
  `uuid` int NOT NULL COMMENT '작성자uuid',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '제목',
  `content` text COLLATE utf8_unicode_ci COMMENT '내용',
  `hit` int NOT NULL DEFAULT '0' COMMENT '조회수',
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  `category` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '일반' COMMENT '카테고리명',
  PRIMARY KEY (`board_id`),
  KEY `FK_board_uuid_member_uuid` (`uuid`),
  CONSTRAINT `FK_board_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='커뮤니티(QnA, 공지사항, 일반)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `board_id` int NOT NULL COMMENT '글번호',
  `uuid` int NOT NULL COMMENT '작성자uuid',
  `content` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '내용',
  PRIMARY KEY (`comment_id`),
  KEY `FK_comment_board_id_board_board_id` (`board_id`),
  KEY `FK_comment_uuid_member_uuid` (`uuid`),
  CONSTRAINT `FK_comment_board_id_board_board_id` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_comment_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='댓글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controversy`
--

DROP TABLE IF EXISTS `controversy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `controversy` (
  `controversy_id` int NOT NULL AUTO_INCREMENT COMMENT '글번호',
  `uuid` int NOT NULL COMMENT '작성자uuid',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '시작날짜',
  `end_date` timestamp NOT NULL COMMENT '종료날짜',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '제목',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '내용',
  PRIMARY KEY (`controversy_id`),
  KEY `FK_controversy_uuid_member_uuid` (`uuid`),
  CONSTRAINT `FK_controversy_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='찬반논쟁';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controversy`
--

LOCK TABLES `controversy` WRITE;
/*!40000 ALTER TABLE `controversy` DISABLE KEYS */;
/*!40000 ALTER TABLE `controversy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lawyer`
--

DROP TABLE IF EXISTS `lawyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lawyer` (
  `uuid` int NOT NULL COMMENT 'uuid',
  `career` text COLLATE utf8_unicode_ci COMMENT '경력',
  `introduction` text COLLATE utf8_unicode_ci COMMENT '자기소개',
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '주소',
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'default.jpg' COMMENT '사진',
  `dept` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '소속',
  `check` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N' COMMENT '변호사합격확인',
  PRIMARY KEY (`uuid`),
  CONSTRAINT `FK_lawyer_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='변호사';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lawyer`
--

LOCK TABLES `lawyer` WRITE;
/*!40000 ALTER TABLE `lawyer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lawyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matching`
--

DROP TABLE IF EXISTS `matching`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matching` (
  `matching_id` int NOT NULL AUTO_INCREMENT COMMENT '자문번호',
  `uuid` int NOT NULL COMMENT '신청자uuid',
  `lawyer_uuid` int NOT NULL COMMENT '변호사uuid',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '내용',
  `state` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '진행상태',
  `reservation_date` timestamp NULL DEFAULT NULL COMMENT '예약시간',
  PRIMARY KEY (`matching_id`),
  KEY `FK_matching_uuid_member_uuid` (`uuid`),
  KEY `FK_matching_lawyer_uuid_member_uuid` (`lawyer_uuid`),
  CONSTRAINT `FK_matching_lawyer_uuid_member_uuid` FOREIGN KEY (`lawyer_uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_matching_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='변호사자문';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matching`
--

LOCK TABLES `matching` WRITE;
/*!40000 ALTER TABLE `matching` DISABLE KEYS */;
/*!40000 ALTER TABLE `matching` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `uuid` int NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '이메일',
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '비번',
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '이름',
  `phone` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '연락처',
  `role` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '역할',
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='사용자';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proposal`
--

DROP TABLE IF EXISTS `proposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proposal` (
  `opinion_id` int NOT NULL AUTO_INCREMENT COMMENT '의견번호',
  `uuid` int NOT NULL COMMENT '작성자uuid',
  `content` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '내용',
  `agree` char(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '찬성,반대',
  PRIMARY KEY (`opinion_id`),
  KEY `FK_proposal_uuid_member_uuid` (`uuid`),
  CONSTRAINT `FK_proposal_uuid_member_uuid` FOREIGN KEY (`uuid`) REFERENCES `member` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='의견(찬반논쟁댓글)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposal`
--

LOCK TABLES `proposal` WRITE;
/*!40000 ALTER TABLE `proposal` DISABLE KEYS */;
/*!40000 ALTER TABLE `proposal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-26 10:14:54
