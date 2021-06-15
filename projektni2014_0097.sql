/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 10.1.30-MariaDB : Database - projektni2014_0097
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projektni2014_0097` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `projektni2014_0097`;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sifra` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `imePrezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`korisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikID`,`korisnickoIme`,`sifra`,`imePrezime`) values 
(1,'raco','raco','Rastko Petrovic'),
(2,'pera','pera','Petar Petrovic'),
(3,'mica','mica','Milica Milic');

/*Table structure for table `plivac` */

DROP TABLE IF EXISTS `plivac`;

CREATE TABLE `plivac` (
  `plivacID` int(11) NOT NULL AUTO_INCREMENT,
  `imePrezimePlivaca` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `drzava` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datumRodjenja` date DEFAULT NULL,
  `brojMedaljaEvropskaPrvenstva` int(11) DEFAULT '0',
  `brojMedaljaSvetskaPrvenstva` int(11) DEFAULT '0',
  `brojMedaljaOlimpijskeIgre` int(11) DEFAULT '0',
  PRIMARY KEY (`plivacID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `plivac` */

insert  into `plivac`(`plivacID`,`imePrezimePlivaca`,`drzava`,`datumRodjenja`,`brojMedaljaEvropskaPrvenstva`,`brojMedaljaSvetskaPrvenstva`,`brojMedaljaOlimpijskeIgre`) values 
(1,'Milorad Cavic','Srbija','1984-05-24',10,2,1),
(2,'Michael Phelps','USA','1985-06-30',0,33,28),
(3,'Laslo Ceh','Madjarska','1985-12-03',48,18,6),
(4,'Adam Peaty','Velika Britanija','1994-12-28',6,5,4),
(6,'Yannick Agnel','Francuska','1992-06-02',7,6,3),
(7,'Cesar Cielo','Brazil','1987-01-10',0,10,5),
(8,'Caba Siladji','Srbija','1986-06-23',4,1,1),
(10,'Ivan Lendjer','Srbija','1987-05-23',2,1,1),
(11,'Tamash Kendershi','Madjarska','1998-05-23',3,3,2);

/*Table structure for table `trka` */

DROP TABLE IF EXISTS `trka`;

CREATE TABLE `trka` (
  `trkaID` int(11) NOT NULL AUTO_INCREMENT,
  `duzina` int(11) DEFAULT '0',
  `drzava` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datumOdrzavanja` date DEFAULT NULL,
  `vremeOdrzavanja` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nazivPlivalista` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `brojUcesnika` int(11) DEFAULT '0',
  `fazaTakmicenja` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `evropskiRekord` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svetskiRekord` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `olimpijskiRekord` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vrstaID` int(11) NOT NULL,
  PRIMARY KEY (`trkaID`),
  KEY `vrstaID` (`vrstaID`),
  CONSTRAINT `trka_ibfk_1` FOREIGN KEY (`vrstaID`) REFERENCES `vrstatrke` (`vrstaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `trka` */

insert  into `trka`(`trkaID`,`duzina`,`drzava`,`datumOdrzavanja`,`vremeOdrzavanja`,`nazivPlivalista`,`brojUcesnika`,`fazaTakmicenja`,`evropskiRekord`,`svetskiRekord`,`olimpijskiRekord`,`vrstaID`) values 
(3,100,'Brazil','2016-07-21','18:50','Marija Lenk',3,'Polufinale','50.23','49.89','49.98',4);

/*Table structure for table `ucesce` */

DROP TABLE IF EXISTS `ucesce`;

CREATE TABLE `ucesce` (
  `trkaID` int(11) NOT NULL,
  `plivacID` int(11) NOT NULL,
  `brojStaze` int(11) DEFAULT '0',
  `vreme` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `plasman` int(11) DEFAULT '0',
  `korisnikID` int(11) NOT NULL,
  PRIMARY KEY (`trkaID`,`plivacID`),
  KEY `ucesceplivac_fk` (`plivacID`),
  KEY `ucescekorisnik_fk` (`korisnikID`),
  CONSTRAINT `ucescekorisnik_fk` FOREIGN KEY (`korisnikID`) REFERENCES `korisnik` (`korisnikID`) ON UPDATE CASCADE,
  CONSTRAINT `ucesceplivac_fk` FOREIGN KEY (`plivacID`) REFERENCES `plivac` (`plivacID`) ON UPDATE CASCADE,
  CONSTRAINT `ucescetrka_fk` FOREIGN KEY (`trkaID`) REFERENCES `trka` (`trkaID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `ucesce` */

insert  into `ucesce`(`trkaID`,`plivacID`,`brojStaze`,`vreme`,`plasman`,`korisnikID`) values 
(3,2,2,'50.45',3,1),
(3,7,3,'50.42',2,1),
(3,10,4,'50.35',1,1);

/*Table structure for table `vrstatrke` */

DROP TABLE IF EXISTS `vrstatrke`;

CREATE TABLE `vrstatrke` (
  `vrstaID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivStila` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vrstaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `vrstatrke` */

insert  into `vrstatrke`(`vrstaID`,`nazivStila`,`opis`) values 
(1,'Kraul','Naizmenicni zamasi levom i desnom rukom, dok je plivac okrenut licem prema dole'),
(2,'Ledjno','Naizmenicni zamasi levom i desnom rukom, dok je plivac okrenut licem prema gore'),
(3,'Prsno','Pokreti obema rukama prema napred uz ogranicnje da laktovi moraju biti pod vodom'),
(4,'Delfin',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
