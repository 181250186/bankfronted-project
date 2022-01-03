SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
                        `pid` int(10) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `biography` varchar(255) DEFAULT NULL,
                        `birthplace` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`pid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `node`;
CREATE TABLE  `node`(
                      `nid` int(10) NOT NULL AUTO_INCREMENT,
                      `cid` int(10) not null,
                      `name` varchar(100) not null,
                      `classes` varchar(255) not null,
                      `positionX` float,
                      `positionY` float,
                      PRIMARY KEY(`nid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `edge`;
CREATE TABLE  `edge`(
                      `eid` int(10) NOT NULL AUTO_INCREMENT,
                      `cid` int(10) not null,
                      `name` varchar(255) not null,
                      `source`  varchar(100) not null,
                      `target` varchar(100) not null,
                      `classes` varchar (100) not null,
                      PRIMARY KEY(`eid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `chart`;
CREATE TABLE `chart`(
                      `cid` int(10) Not null AUTO_INCREMENT,
                      `name` varchar(255) not null,
                      primary key(`cid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
                      `uid` int(10) Not null AUTO_INCREMENT,
                      `userName` varchar(255) not null,
                      `password` varchar(255) not null,
                      primary key(`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
