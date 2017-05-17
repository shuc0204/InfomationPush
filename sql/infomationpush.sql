/*
Navicat MySQL Data Transfer

Source Server         : .
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : infomationpush

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-05-15 17:48:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `updatetime` bigint(20) DEFAULT NULL,
  `keyword` varchar(255) NOT NULL,
  `keycount` int(11) unsigned NOT NULL DEFAULT '1',
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`keyword`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'aa', 'aa', '教师'); 
INSERT INTO `user` VALUES ('3', 'dd', 'dd', 'dd');
INSERT INTO `user` VALUES ('4', 'bb', 'bb', '123');
INSERT INTO `user` VALUES ('5', '11', '11', '11');
