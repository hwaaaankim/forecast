-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.1.46-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- forecast 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `forecast` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `forecast`;

-- 테이블 forecast.TB_ADMINISTRATION 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_ADMINISTRATION` (
  `ADMINISTRATION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADMINISTRATION_USERNAME` varchar(50) NOT NULL DEFAULT '0',
  `ADMINISTRATION_PASSWORD` varchar(100) NOT NULL DEFAULT '0',
  `ADMINISTRATION_NICKNAME` varchar(50) NOT NULL DEFAULT '0',
  `ADMINISTRATION_ACTIVATED` bit(1) NOT NULL DEFAULT b'0',
  `ADMINISTRATION_ROLE` varchar(50) NOT NULL DEFAULT '0',
  `ADMINISTRATION_DATE` date NOT NULL,
  PRIMARY KEY (`ADMINISTRATION_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_ADMINISTRATION:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_ADMINISTRATION` DISABLE KEYS */;
INSERT INTO `TB_ADMINISTRATION` (`ADMINISTRATION_ID`, `ADMINISTRATION_USERNAME`, `ADMINISTRATION_PASSWORD`, `ADMINISTRATION_NICKNAME`, `ADMINISTRATION_ACTIVATED`, `ADMINISTRATION_ROLE`, `ADMINISTRATION_DATE`) VALUES
	(1, 'admin', '$2a$10$Uima3p/m/w7zxkCid6S.0uFjSerhv6M.W0OlCXgBSX6XsMQUgaTJm', 'hwan', b'1', 'admin', '0000-00-00');
/*!40000 ALTER TABLE `TB_ADMINISTRATION` ENABLE KEYS */;

-- 테이블 forecast.TB_MEMBER 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_MEMBER` (
  `MEMBER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MEMBER_WALLET` varchar(200) NOT NULL DEFAULT '',
  `MEMBER_JOINDATE` date NOT NULL,
  `MEMBER_AUTHORIZATION` bit(1) NOT NULL,
  PRIMARY KEY (`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_MEMBER:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_MEMBER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_MEMBER` ENABLE KEYS */;

-- 테이블 forecast.TB_MEMBER_ACCESS_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_MEMBER_ACCESS_LOG` (
  `ACCESS_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCESS_LOG_TEXT` varchar(100) NOT NULL DEFAULT '0',
  `ACCESS_LOG_DATE` date NOT NULL,
  `ACCESS_LOG_SIGN` bigint(20) NOT NULL,
  `ACCESS_LOG_MEMBER_ID` bigint(20) NOT NULL DEFAULT '0',
  `ACCESS_LOG_IP` varchar(50) NOT NULL,
  `ACCESS_LOG_JWT` varchar(50) NOT NULL,
  PRIMARY KEY (`ACCESS_LOG_ID`),
  KEY `FK_TB_MEMBER_ACCESS_LOG_TB_MEMBER` (`ACCESS_LOG_MEMBER_ID`),
  CONSTRAINT `FK_TB_MEMBER_ACCESS_LOG_TB_MEMBER` FOREIGN KEY (`ACCESS_LOG_MEMBER_ID`) REFERENCES `TB_MEMBER` (`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_MEMBER_ACCESS_LOG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_MEMBER_ACCESS_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_MEMBER_ACCESS_LOG` ENABLE KEYS */;

-- 테이블 forecast.TB_MEMBER_ACTION_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_MEMBER_ACTION_LOG` (
  `ACTION_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTION_LOG_SIGN` bit(1) NOT NULL DEFAULT b'0',
  `ACTION_LOG_TEXT` varchar(100) DEFAULT NULL,
  `ACTION_LOG_DATE` date NOT NULL,
  `ACTION_LOG_QUEST_ID` bigint(20) NOT NULL DEFAULT '0',
  `ACTION_LOG_MEMBER_ID` bigint(20) NOT NULL,
  `ACTION_LOG_TOKEN_AMOUNT` bigint(20) NOT NULL,
  PRIMARY KEY (`ACTION_LOG_ID`),
  KEY `FK_TB_MEMBER_ACTION_LOG_TB_MEMBER` (`ACTION_LOG_MEMBER_ID`),
  KEY `FK_TB_MEMBER_ACTION_LOG_TB_QUEST` (`ACTION_LOG_QUEST_ID`),
  CONSTRAINT `FK_TB_MEMBER_ACTION_LOG_TB_MEMBER` FOREIGN KEY (`ACTION_LOG_MEMBER_ID`) REFERENCES `TB_MEMBER` (`MEMBER_ID`),
  CONSTRAINT `FK_TB_MEMBER_ACTION_LOG_TB_QUEST` FOREIGN KEY (`ACTION_LOG_QUEST_ID`) REFERENCES `TB_QUEST` (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_MEMBER_ACTION_LOG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_MEMBER_ACTION_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_MEMBER_ACTION_LOG` ENABLE KEYS */;

-- 테이블 forecast.TB_MEMBER_TRANSFERING_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_MEMBER_TRANSFERING_LOG` (
  `TRANSFERING_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TRANSFERING_LOG_SUBJECT` varchar(100) NOT NULL DEFAULT '0',
  `TRANSFERING_LOG_CONTENT` varchar(200) DEFAULT '0',
  `TRANSFERING_LOG_DATE` date NOT NULL,
  `TRANSFERING_LOG_TOKEN_AMOUNT` bigint(20) NOT NULL,
  `TRANSFERING_LOG_SIGN` bit(1) NOT NULL DEFAULT b'0',
  `TRANSFERING_LOG_MEMBER_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TRANSFERING_LOG_ID`),
  KEY `FK_TB_MEMBER_TRANSFERING_LOG_TB_MEMBER` (`TRANSFERING_LOG_MEMBER_ID`),
  CONSTRAINT `FK_TB_MEMBER_TRANSFERING_LOG_TB_MEMBER` FOREIGN KEY (`TRANSFERING_LOG_MEMBER_ID`) REFERENCES `TB_MEMBER` (`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_MEMBER_TRANSFERING_LOG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_MEMBER_TRANSFERING_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_MEMBER_TRANSFERING_LOG` ENABLE KEYS */;

-- 테이블 forecast.TB_MEMBER_VOTING_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_MEMBER_VOTING_LOG` (
  `VOTING_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VOTING_LOG_DATE` date NOT NULL,
  `VOTING_LOG_TEXT` varchar(100) DEFAULT '0',
  `VOTING_LOG_QUEST_ID` bigint(20) NOT NULL,
  `VOTING_LOG_MEMBER_ID` bigint(20) NOT NULL,
  `VOTING_LOG_TOKEN_AMOUNT` bigint(20) NOT NULL,
  `VOTING_LOG_SIGN` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`VOTING_LOG_ID`),
  KEY `FK_TB_MEMBER_VOTING_LOG_TB_MEMBER` (`VOTING_LOG_MEMBER_ID`),
  KEY `FK_TB_MEMBER_VOTING_LOG_TB_QUEST` (`VOTING_LOG_QUEST_ID`),
  CONSTRAINT `FK_TB_MEMBER_VOTING_LOG_TB_MEMBER` FOREIGN KEY (`VOTING_LOG_MEMBER_ID`) REFERENCES `TB_MEMBER` (`MEMBER_ID`),
  CONSTRAINT `FK_TB_MEMBER_VOTING_LOG_TB_QUEST` FOREIGN KEY (`VOTING_LOG_QUEST_ID`) REFERENCES `TB_QUEST` (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_MEMBER_VOTING_LOG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_MEMBER_VOTING_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_MEMBER_VOTING_LOG` ENABLE KEYS */;

-- 테이블 forecast.TB_POPUP 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_POPUP` (
  `POPUP_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POPUP_SUBJECT` varchar(100) NOT NULL DEFAULT '0',
  `POPUP_CONTENT` varchar(200) NOT NULL DEFAULT '0',
  `POPUP_IMAGE_PATH` varchar(200) NOT NULL DEFAULT '0',
  `POPUP_IMAGE_ROAD` varchar(200) NOT NULL DEFAULT '0',
  `POPUP_IMAGE_NAME` varchar(200) NOT NULL DEFAULT '0',
  `POPUP_TITLE` varchar(100) NOT NULL DEFAULT '0',
  `POPUP_DATE` date NOT NULL,
  `POPUP_SIGN` bit(1) NOT NULL DEFAULT b'0',
  `POPUP_HASHTAG` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`POPUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_POPUP:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_POPUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_POPUP` ENABLE KEYS */;

-- 테이블 forecast.TB_QUEST 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_QUEST` (
  `QUEST_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `QUEST_TITLE` varchar(100) NOT NULL,
  `QUEST_POINT` mediumint(9) NOT NULL DEFAULT '0',
  `QUEST_SUBJECT` varchar(200) NOT NULL,
  `QUEST_CONTENT` varchar(500) NOT NULL,
  `QUEST_UPLOAD_DATE` date NOT NULL,
  `QUEST_END_DATE` date NOT NULL,
  `QUEST_START_DATE` date NOT NULL,
  `QUEST_ACTION_START` date DEFAULT NULL,
  `QUEST_ACTION_END` date DEFAULT NULL,
  `QUEST_IMAGE_PATH` varchar(200) NOT NULL,
  `QUEST_IMAGE_ROAD` varchar(200) NOT NULL,
  `QUEST_TOKEN_AMOUNT` bigint(20) DEFAULT '0',
  `QUEST_SIGN` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_QUEST:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_QUEST` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_QUEST` ENABLE KEYS */;

-- 테이블 forecast.TB_QUEST_HASHTAG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_QUEST_HASHTAG` (
  `HASHTAG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `HASHTAG_NAME` varchar(50) NOT NULL,
  `HASHTAG_QUEST_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`HASHTAG_ID`) USING BTREE,
  UNIQUE KEY `HASHTAG_NAME` (`HASHTAG_NAME`),
  KEY `FK_TB_QUEST_HASHTAG_TB_QUEST` (`HASHTAG_QUEST_ID`),
  CONSTRAINT `FK_TB_QUEST_HASHTAG_TB_QUEST` FOREIGN KEY (`HASHTAG_QUEST_ID`) REFERENCES `TB_QUEST` (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_QUEST_HASHTAG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_QUEST_HASHTAG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_QUEST_HASHTAG` ENABLE KEYS */;

-- 테이블 forecast.TB_QUEST_IMAGES 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_QUEST_IMAGES` (
  `QUEST_IMAGES_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `QUEST_IMAGES_PATH` varchar(200) NOT NULL,
  `QUEST_IMAGES_ROAD` varchar(200) NOT NULL,
  `QUEST_IMAGES_NAME` varchar(50) NOT NULL,
  `QUEST_IMAGES_QUEST_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`QUEST_IMAGES_ID`),
  KEY `FK_TB_QUEST_IMAGES_TB_QUEST` (`QUEST_IMAGES_QUEST_ID`),
  CONSTRAINT `FK_TB_QUEST_IMAGES_TB_QUEST` FOREIGN KEY (`QUEST_IMAGES_QUEST_ID`) REFERENCES `TB_QUEST` (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_QUEST_IMAGES:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_QUEST_IMAGES` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_QUEST_IMAGES` ENABLE KEYS */;

-- 테이블 forecast.TB_QUEST_MANAGE_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_QUEST_MANAGE_LOG` (
  `MANAGE_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MANAGE_LOG_SUBJECT` varchar(100) DEFAULT '',
  `MANAGE_LOG_CONTENT` varchar(100) DEFAULT '',
  `MANAGE_LOG_DATE` date NOT NULL,
  `MANAGE_LOG_QUEST_ID` bigint(20) NOT NULL,
  `MANAGE_LOG_MEMBER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`MANAGE_LOG_ID`) USING BTREE,
  KEY `FK_TB_QUEST_MANAGE_LOG_TB_QUEST` (`MANAGE_LOG_QUEST_ID`),
  KEY `FK_TB_QUEST_MANAGE_LOG_TB_MEMBER` (`MANAGE_LOG_MEMBER_ID`),
  CONSTRAINT `FK_TB_QUEST_MANAGE_LOG_TB_MEMBER` FOREIGN KEY (`MANAGE_LOG_MEMBER_ID`) REFERENCES `TB_MEMBER` (`MEMBER_ID`),
  CONSTRAINT `FK_TB_QUEST_MANAGE_LOG_TB_QUEST` FOREIGN KEY (`MANAGE_LOG_QUEST_ID`) REFERENCES `TB_QUEST` (`QUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_QUEST_MANAGE_LOG:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_QUEST_MANAGE_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_QUEST_MANAGE_LOG` ENABLE KEYS */;

-- 테이블 forecast.TB_QUEST_PARTICIPANTS 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_QUEST_PARTICIPANTS` (
  `QUEST_PARTICIPANTS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `QUEST_PARTICIPANTS_MEMBER_ID` bigint(20) NOT NULL DEFAULT '0',
  `QUEST_PARTICIPANTS_QUEST_ID` bigint(20) NOT NULL DEFAULT '0',
  `QUEST_PARTICIPANTS_TOKEN_AMOUNT` bigint(20) NOT NULL,
  `QUEST_PARTICIPANTS_DATE` date NOT NULL,
  `QUEST_PARTICIPANTS_SIGN` bit(1) NOT NULL DEFAULT b'0',
  `QUEST_PARTICIPANTS_TEXT` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`QUEST_PARTICIPANTS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_QUEST_PARTICIPANTS:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_QUEST_PARTICIPANTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_QUEST_PARTICIPANTS` ENABLE KEYS */;

-- 테이블 forecast.TB_SETTING 구조 내보내기
CREATE TABLE IF NOT EXISTS `TB_SETTING` (
  `VOTING_REWARD_RATE` float NOT NULL DEFAULT '0',
  `ACTION_REWARD_RATE` float NOT NULL,
  `ADMINISTRATION_REWARD_RATE` float NOT NULL,
  `TRANSFERRING_RATE` float NOT NULL,
  `QUEST_REGISTRATION_REWARD_RATE` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 forecast.TB_SETTING:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `TB_SETTING` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_SETTING` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;