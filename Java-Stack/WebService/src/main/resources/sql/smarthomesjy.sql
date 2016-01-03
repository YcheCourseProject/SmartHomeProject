/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : smarthome

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-08-12 00:02:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity_type
-- ----------------------------
DROP TABLE IF EXISTS `activity_type`;
CREATE TABLE `activity_type` (
  `activity_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_type` varchar(15) NOT NULL,
  PRIMARY KEY (`activity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_type
-- ----------------------------
INSERT INTO `activity_type` VALUES ('1', '运动');
INSERT INTO `activity_type` VALUES ('2', '吃饭');
INSERT INTO `activity_type` VALUES ('3', '延迟');

-- ----------------------------
-- Table structure for air_condition
-- ----------------------------
DROP TABLE IF EXISTS `air_condition`;
CREATE TABLE `air_condition` (
  `air_condition_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  `air_condition_ip` varchar(15) DEFAULT NULL,
  `air_condition_rated_power` float DEFAULT NULL,
  PRIMARY KEY (`air_condition_id`),
  KEY `FK_control_air_condition` (`box_id`),
  CONSTRAINT `FK_control_air_condition` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_condition
-- ----------------------------
INSERT INTO `air_condition` VALUES ('1', null, 'localhost', '2000');
INSERT INTO `air_condition` VALUES ('2', null, 'localhost', '2000');

-- ----------------------------
-- Table structure for air_condition_control_detail
-- ----------------------------
DROP TABLE IF EXISTS `air_condition_control_detail`;
CREATE TABLE `air_condition_control_detail` (
  `ari_condition_control_id` int(11) NOT NULL AUTO_INCREMENT,
  `air_condition_real_time_decision_id` int(11) DEFAULT NULL,
  `air_condition_id` int(11) DEFAULT NULL,
  `air_condition_control_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `air_condition_control_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `air_condition_temperature` float NOT NULL,
  `air_condition_mode` int(11) NOT NULL,
  `air_condition_control_record_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ari_condition_control_id`),
  KEY `FK_air_condtion_has_control_detail` (`air_condition_id`),
  KEY `FK_transfer_to_air_condition_control_detail` (`air_condition_real_time_decision_id`),
  CONSTRAINT `FK_air_condtion_has_control_detail` FOREIGN KEY (`air_condition_id`) REFERENCES `air_condition` (`air_condition_id`),
  CONSTRAINT `FK_transfer_to_air_condition_control_detail` FOREIGN KEY (`air_condition_real_time_decision_id`) REFERENCES `air_condition_real_time_decision` (`air_condition_real_time_decision_id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_condition_control_detail
-- ----------------------------
INSERT INTO `air_condition_control_detail` VALUES ('1', '41', '1', '2015-08-08 09:30:00', '2015-08-10 11:00:00', '25', '1', '2015-08-08 02:43:33');
INSERT INTO `air_condition_control_detail` VALUES ('2', '42', '1', '2015-08-08 05:30:00', '2015-08-10 07:00:01', '31.6713', '1', '2015-08-08 02:43:33');
INSERT INTO `air_condition_control_detail` VALUES ('3', '43', '1', '2015-08-08 11:00:00', '2015-08-08 11:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('4', '44', '1', '2015-08-08 05:00:00', '2015-08-08 05:00:00', '33.0658', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('5', '45', '1', '2015-08-08 09:00:00', '2015-08-08 09:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('6', '46', '1', '2015-08-08 08:00:00', '2015-08-08 08:00:01', '29.1111', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('7', '47', '1', '2015-08-08 08:30:00', '2015-08-10 10:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('8', '48', '1', '2015-08-08 11:30:00', '2015-08-10 13:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('9', '49', '1', '2015-08-08 10:00:00', '2015-08-08 10:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('10', '50', '1', '2015-08-08 10:30:00', '2015-08-10 12:00:00', '25', '1', '2015-08-08 02:43:34');
INSERT INTO `air_condition_control_detail` VALUES ('11', '51', '1', '2015-08-09 00:00:00', '2015-08-09 00:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('12', '52', '1', '2015-08-08 23:00:00', '2015-08-08 11:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('13', '53', '1', '2015-08-08 22:00:00', '2015-08-08 10:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('14', '54', '1', '2015-08-09 00:30:00', '2015-08-11 02:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('15', '55', '1', '2015-08-08 21:30:00', '2015-08-10 11:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('16', '56', '1', '2015-08-08 22:30:00', '2015-08-10 12:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('17', '57', '1', '2015-08-08 18:30:00', '2015-08-10 08:00:01', '31.6713', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('18', '58', '1', '2015-08-08 18:00:00', '2015-08-08 06:00:00', '33.0658', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('19', '59', '1', '2015-08-08 23:30:00', '2015-08-10 13:00:00', '25', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('20', '60', '1', '2015-08-08 21:00:00', '2015-08-08 09:00:01', '29.1111', '1', '2015-08-08 15:46:20');
INSERT INTO `air_condition_control_detail` VALUES ('21', '61', '1', '2015-08-08 18:30:00', '2015-08-10 08:00:01', '31.6713', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('22', '62', '1', '2015-08-08 23:30:00', '2015-08-10 13:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('23', '63', '1', '2015-08-08 23:00:00', '2015-08-08 11:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('24', '64', '1', '2015-08-08 18:00:00', '2015-08-08 06:00:00', '33.0658', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('25', '65', '1', '2015-08-08 21:30:00', '2015-08-10 11:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('26', '66', '1', '2015-08-08 21:00:00', '2015-08-08 09:00:01', '29.1111', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('27', '67', '1', '2015-08-08 22:30:00', '2015-08-10 12:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('28', '68', '1', '2015-08-08 22:00:00', '2015-08-08 10:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('29', '69', '1', '2015-08-09 00:30:00', '2015-08-11 02:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('30', '70', '1', '2015-08-09 00:00:00', '2015-08-09 00:00:00', '25', '1', '2015-08-08 15:59:16');
INSERT INTO `air_condition_control_detail` VALUES ('31', '71', '1', '2015-08-08 22:00:00', '2015-08-08 10:00:01', '29.1111', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('32', '72', '1', '2015-08-09 01:30:00', '2015-08-11 03:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('33', '73', '1', '2015-08-09 01:00:00', '2015-08-09 01:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('34', '74', '1', '2015-08-08 23:30:00', '2015-08-10 13:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('35', '75', '1', '2015-08-09 00:30:00', '2015-08-11 02:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('36', '76', '1', '2015-08-09 00:00:00', '2015-08-09 00:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('37', '77', '1', '2015-08-08 19:30:00', '2015-08-10 09:00:01', '31.6713', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('38', '78', '1', '2015-08-08 19:00:00', '2015-08-08 07:00:00', '33.0658', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('39', '79', '1', '2015-08-08 23:00:00', '2015-08-08 11:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('40', '80', '1', '2015-08-08 22:30:00', '2015-08-10 12:00:00', '25', '1', '2015-08-08 16:01:20');
INSERT INTO `air_condition_control_detail` VALUES ('41', '81', '1', '2015-08-09 04:30:00', '2015-08-11 06:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('42', '82', '1', '2015-08-09 06:00:00', '2015-08-09 06:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('43', '83', '1', '2015-08-09 05:30:00', '2015-08-11 07:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('44', '84', '1', '2015-08-09 05:00:00', '2015-08-09 05:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('45', '85', '1', '2015-08-09 06:30:00', '2015-08-11 08:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('46', '86', '1', '2015-08-09 01:30:00', '2015-08-11 03:00:01', '31.6713', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('47', '87', '1', '2015-08-09 01:00:00', '2015-08-09 01:00:00', '33.0658', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('48', '88', '1', '2015-08-09 07:00:00', '2015-08-09 07:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('49', '89', '1', '2015-08-09 07:30:00', '2015-08-11 09:00:00', '25', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('50', '90', '1', '2015-08-09 04:00:00', '2015-08-09 04:00:01', '29.1111', '1', '2015-08-08 22:24:10');
INSERT INTO `air_condition_control_detail` VALUES ('51', '91', '1', '2015-08-09 08:30:00', '2015-08-11 10:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('52', '92', '1', '2015-08-09 06:30:00', '2015-08-11 08:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('53', '93', '1', '2015-08-09 05:30:00', '2015-08-11 07:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('54', '94', '1', '2015-08-09 02:00:00', '2015-08-09 02:00:00', '33.0658', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('55', '95', '1', '2015-08-09 08:00:00', '2015-08-09 08:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('56', '96', '1', '2015-08-09 05:00:00', '2015-08-09 05:00:01', '29.1111', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('57', '97', '1', '2015-08-09 07:00:00', '2015-08-09 07:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('58', '98', '1', '2015-08-09 02:30:00', '2015-08-11 04:00:01', '31.6713', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('59', '99', '1', '2015-08-09 07:30:00', '2015-08-11 09:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('60', '100', '1', '2015-08-09 06:00:00', '2015-08-09 06:00:00', '25', '1', '2015-08-08 23:12:20');
INSERT INTO `air_condition_control_detail` VALUES ('61', '101', '1', '2015-08-09 07:00:00', '2015-08-09 07:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('62', '102', '1', '2015-08-09 06:00:00', '2015-08-09 06:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('63', '103', '1', '2015-08-09 02:00:00', '2015-08-09 02:00:00', '33.0658', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('64', '104', '1', '2015-08-09 05:30:00', '2015-08-11 07:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('65', '105', '1', '2015-08-09 05:00:00', '2015-08-09 05:00:01', '29.1111', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('66', '106', '1', '2015-08-09 06:30:00', '2015-08-11 08:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('67', '107', '1', '2015-08-09 08:30:00', '2015-08-11 10:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('68', '108', '1', '2015-08-09 07:30:00', '2015-08-11 09:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('69', '109', '1', '2015-08-09 08:00:00', '2015-08-09 08:00:00', '25', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('70', '110', '1', '2015-08-09 02:30:00', '2015-08-11 04:00:01', '31.6713', '1', '2015-08-08 23:14:32');
INSERT INTO `air_condition_control_detail` VALUES ('71', '111', '1', '2015-08-10 04:30:00', '2015-08-12 06:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('72', '112', '1', '2015-08-10 01:30:00', '2015-08-12 03:00:01', '31.6713', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('73', '113', '1', '2015-08-10 06:00:00', '2015-08-10 06:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('74', '114', '1', '2015-08-10 05:00:00', '2015-08-10 05:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('75', '115', '1', '2015-08-10 05:30:00', '2015-08-12 07:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('76', '116', '1', '2015-08-10 07:30:00', '2015-08-12 09:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('77', '117', '1', '2015-08-10 06:30:00', '2015-08-12 08:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('78', '118', '1', '2015-08-10 01:00:00', '2015-08-10 01:00:00', '33.0658', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('79', '119', '1', '2015-08-10 07:00:00', '2015-08-10 07:00:00', '25', '1', '2015-08-09 00:07:06');
INSERT INTO `air_condition_control_detail` VALUES ('80', '120', '1', '2015-08-10 04:00:00', '2015-08-10 04:00:01', '29.1111', '1', '2015-08-09 00:07:07');
INSERT INTO `air_condition_control_detail` VALUES ('81', '121', '1', '2015-08-10 06:00:00', '2015-08-10 06:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('82', '122', '1', '2015-08-10 07:30:00', '2015-08-12 09:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('83', '123', '1', '2015-08-10 07:00:00', '2015-08-10 07:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('84', '124', '1', '2015-08-10 01:00:00', '2015-08-10 01:00:00', '33.0658', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('85', '125', '1', '2015-08-10 05:30:00', '2015-08-12 07:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('86', '126', '1', '2015-08-10 06:30:00', '2015-08-12 08:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('87', '127', '1', '2015-08-10 05:00:00', '2015-08-10 05:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('88', '128', '1', '2015-08-10 04:30:00', '2015-08-12 06:00:00', '25', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('89', '129', '1', '2015-08-10 01:30:00', '2015-08-12 03:00:01', '31.6713', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('90', '130', '1', '2015-08-10 04:00:00', '2015-08-10 04:00:01', '29.1111', '1', '2015-08-09 00:29:05');
INSERT INTO `air_condition_control_detail` VALUES ('91', '131', '1', '2015-08-10 01:30:00', '2015-08-12 03:00:01', '31.6713', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('92', '132', '1', '2015-08-10 04:00:00', '2015-08-10 04:00:01', '29.1111', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('93', '133', '1', '2015-08-10 05:30:00', '2015-08-12 07:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('94', '134', '1', '2015-08-10 07:00:00', '2015-08-10 07:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('95', '135', '1', '2015-08-10 07:30:00', '2015-08-12 09:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('96', '136', '1', '2015-08-10 04:30:00', '2015-08-12 06:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('97', '137', '1', '2015-08-10 06:30:00', '2015-08-12 08:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('98', null, null, '2015-08-10 06:00:00', '2015-08-10 06:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('99', null, null, '2015-08-10 05:00:00', '2015-08-10 05:00:00', '25', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('100', null, null, '2015-08-10 01:00:00', '2015-08-10 01:00:00', '33.0658', '1', '2015-08-09 00:30:33');
INSERT INTO `air_condition_control_detail` VALUES ('101', '141', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('102', '142', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('103', '143', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('104', '144', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('105', '145', '1', '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('106', '146', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('107', '147', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('108', '148', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('109', '149', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('110', '150', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:23:37');
INSERT INTO `air_condition_control_detail` VALUES ('111', '151', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('112', '152', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('113', '153', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('114', '154', '1', '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('115', '155', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('116', '156', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('117', '157', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('118', '158', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('119', '159', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('120', '160', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:26:37');
INSERT INTO `air_condition_control_detail` VALUES ('121', '161', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('122', '162', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('123', '163', '1', '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('124', '164', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('125', '165', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('126', '166', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('127', '167', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('128', '168', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('129', '169', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('130', '170', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:28:19');
INSERT INTO `air_condition_control_detail` VALUES ('131', '171', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('132', '172', '1', '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('133', '173', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('134', '174', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('135', '175', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('136', '176', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('137', '177', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('138', '178', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('139', '179', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('140', '180', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:50:26');
INSERT INTO `air_condition_control_detail` VALUES ('141', '181', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('142', '182', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('143', '183', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('144', '184', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('145', '185', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('146', '186', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('147', '187', '1', '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('148', '188', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('149', '189', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('150', '190', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:51:51');
INSERT INTO `air_condition_control_detail` VALUES ('151', '191', '1', '2015-08-12 00:00:00', '2015-08-12 00:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('152', '192', '1', '2015-08-11 19:30:00', '2015-08-13 09:00:01', '31.6713', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('153', '193', '1', '2015-08-11 22:30:00', '2015-08-13 12:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('154', '194', '1', '2015-08-12 01:00:00', '2015-08-12 01:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('155', '195', '1', '2015-08-11 23:30:00', '2015-08-13 13:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('156', '196', '1', '2015-08-11 23:00:00', '2015-08-11 11:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('157', '197', '1', '2015-08-12 00:30:00', '2015-08-14 02:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('158', '198', '1', '2015-08-12 01:30:00', '2015-08-14 03:00:00', '25', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('159', null, null, '2015-08-11 19:00:00', '2015-08-11 07:00:00', '33.0658', '1', '2015-08-11 15:56:35');
INSERT INTO `air_condition_control_detail` VALUES ('160', '200', '1', '2015-08-11 22:00:00', '2015-08-11 10:00:01', '29.1111', '1', '2015-08-11 15:56:35');

-- ----------------------------
-- Table structure for air_condition_demand
-- ----------------------------
DROP TABLE IF EXISTS `air_condition_demand`;
CREATE TABLE `air_condition_demand` (
  `air_condition_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `social_activity_to_demand_id` int(11) DEFAULT NULL,
  `gps_info_to_demand_id` int(11) DEFAULT NULL,
  `wearable_info_to_demand_id` int(11) DEFAULT NULL,
  `air_condition_run_time` time DEFAULT NULL,
  `air_condtion_stop_time` time DEFAULT NULL,
  `air_condition_temperature` float NOT NULL,
  `air_condition_temperature_delta` float DEFAULT NULL,
  `air_condition_demand_internal` float DEFAULT NULL,
  PRIMARY KEY (`air_condition_demand_id`),
  KEY `FK_gps_info_has_air_condition_demand_rule` (`gps_info_to_demand_id`),
  KEY `FK_social_activity_has_air_condition_demand_rule` (`social_activity_to_demand_id`),
  CONSTRAINT `FK_gps_info_has_air_condition_demand_rule` FOREIGN KEY (`gps_info_to_demand_id`) REFERENCES `gps_info_to_demand` (`gps_info_to_demand_id`),
  CONSTRAINT `FK_social_activity_has_air_condition_demand_rule` FOREIGN KEY (`social_activity_to_demand_id`) REFERENCES `social_activity_to_demand` (`social_activity_to_demand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_condition_demand
-- ----------------------------
INSERT INTO `air_condition_demand` VALUES ('1', '2', null, null, '18:00:00', '19:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('2', '2', null, null, '19:00:00', '24:00:00', '26', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('3', '3', null, null, '19:00:00', '20:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('4', '3', null, null, '20:00:00', '24:00:00', '26', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('5', '1', null, null, '20:00:00', '21:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('6', '1', null, null, '18:00:00', '24:00:00', '26', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('7', '5', null, null, '19:00:00', '20:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('8', '6', null, null, '20:00:00', '21:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('9', '4', null, null, '20:00:00', '21:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('10', '4', null, null, '21:00:00', '24:00:00', '26', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('11', '7', null, null, '12:00:00', '14:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('12', '8', null, null, '14:00:00', '15:00:00', '25', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('13', '9', null, null, '14:00:00', '16:00:00', '24', '1', '0.5');
INSERT INTO `air_condition_demand` VALUES ('14', '10', null, null, '15:00:00', '16:00:00', '25', '1', '0.5');

-- ----------------------------
-- Table structure for air_condition_real_time_decision
-- ----------------------------
DROP TABLE IF EXISTS `air_condition_real_time_decision`;
CREATE TABLE `air_condition_real_time_decision` (
  `air_condition_real_time_decision_id` int(11) NOT NULL AUTO_INCREMENT,
  `real_time_decision_id` int(11) DEFAULT NULL,
  `ari_condition_control_id` int(11) DEFAULT NULL,
  `air_condition_id` int(11) DEFAULT NULL,
  `air_condition_decide_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `air_condition_decide_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `air_condition_decide_average_energy` float NOT NULL,
  `air_condition_decision_record_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`air_condition_real_time_decision_id`),
  KEY `FK_air_condition_has_decision_detail` (`air_condition_id`),
  KEY `FK_has_air_condition_real_time_desicion` (`real_time_decision_id`),
  KEY `FK_transfer_to_air_condition_control_detail2` (`ari_condition_control_id`),
  CONSTRAINT `FK_air_condition_has_decision_detail` FOREIGN KEY (`air_condition_id`) REFERENCES `air_condition` (`air_condition_id`),
  CONSTRAINT `FK_has_air_condition_real_time_desicion` FOREIGN KEY (`real_time_decision_id`) REFERENCES `real_time_decision` (`real_time_decision_id`),
  CONSTRAINT `FK_transfer_to_air_condition_control_detail2` FOREIGN KEY (`ari_condition_control_id`) REFERENCES `air_condition_control_detail` (`ari_condition_control_id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_condition_real_time_decision
-- ----------------------------
INSERT INTO `air_condition_real_time_decision` VALUES ('41', null, '1', '1', '2015-08-08 09:30:00', '2015-08-08 10:00:00', '0.188889', '2015-08-08 02:42:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('42', null, '2', '1', '2015-08-08 05:30:00', '2015-08-08 06:00:00', '0.6', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('43', null, '3', '1', '2015-08-08 11:00:00', '2015-08-08 11:30:00', '0.122222', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('44', null, '4', '1', '2015-08-08 05:00:00', '2015-08-08 05:30:00', '0.0764346', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('45', null, '5', '1', '2015-08-08 09:00:00', '2015-08-08 09:30:00', '0.177778', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('46', null, '6', '1', '2015-08-08 08:00:00', '2015-08-08 08:30:00', '0.6', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('47', null, '7', '1', '2015-08-08 08:30:00', '2015-08-08 09:00:00', '0.2', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('48', null, '8', '1', '2015-08-08 11:30:00', '2015-08-08 12:00:00', '0.296193', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('49', null, '9', '1', '2015-08-08 10:00:00', '2015-08-08 10:30:00', '0.166667', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('50', null, '10', '1', '2015-08-08 10:30:00', '2015-08-08 11:00:00', '0.144444', '2015-08-08 02:42:50');
INSERT INTO `air_condition_real_time_decision` VALUES ('51', null, '11', '1', '2015-08-09 00:00:00', '2015-08-09 00:30:00', '0.122222', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('52', null, '12', '1', '2015-08-08 23:00:00', '2015-08-08 23:30:00', '0.166667', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('53', null, '13', '1', '2015-08-08 22:00:00', '2015-08-08 22:30:00', '0.177778', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('54', null, '14', '1', '2015-08-09 00:30:00', '2015-08-09 01:00:00', '0.296193', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('55', null, '15', '1', '2015-08-08 21:30:00', '2015-08-08 22:00:00', '0.2', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('56', null, '16', '1', '2015-08-08 22:30:00', '2015-08-08 23:00:00', '0.188889', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('57', null, '17', '1', '2015-08-08 18:30:00', '2015-08-08 19:00:00', '0.6', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('58', null, '18', '1', '2015-08-08 18:00:00', '2015-08-08 18:30:00', '0.0764346', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('59', null, '19', '1', '2015-08-08 23:30:00', '2015-08-09 00:00:00', '0.144444', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('60', null, '20', '1', '2015-08-08 21:00:00', '2015-08-08 21:30:00', '0.6', '2015-08-08 15:46:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('61', null, '21', '1', '2015-08-08 18:30:00', '2015-08-08 19:00:00', '0.6', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('62', '6', '22', '1', '2015-08-09 17:10:13', '2015-08-09 00:00:00', '0.144444', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('63', null, '23', '1', '2015-08-08 23:00:00', '2015-08-08 23:30:00', '0.166667', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('64', '6', '24', '1', '2015-08-09 17:10:12', '2015-08-08 18:30:00', '0.0764346', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('65', '6', '25', '1', '2015-08-09 17:10:11', '2015-08-08 22:00:00', '0.2', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('66', '5', '26', '1', '2015-08-09 17:10:08', '2015-08-08 21:30:00', '0.6', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('67', '5', '27', '1', '2015-08-09 17:10:09', '2015-08-08 23:00:00', '0.188889', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('68', '5', '28', '1', '2015-08-09 17:10:07', '2015-08-08 22:30:00', '0.177778', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('69', null, '29', '1', '2015-08-09 00:30:00', '2015-08-09 01:00:00', '0.296193', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('70', null, '30', '1', '2015-08-09 00:00:00', '2015-08-09 00:30:00', '0.122222', '2015-08-08 15:59:16');
INSERT INTO `air_condition_real_time_decision` VALUES ('71', null, '31', '1', '2015-08-08 22:00:00', '2015-08-08 22:30:00', '0.6', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('72', null, '32', '1', '2015-08-09 01:30:00', '2015-08-09 02:00:00', '0.296193', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('73', null, '33', '1', '2015-08-09 01:00:00', '2015-08-09 01:30:00', '0.122222', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('74', null, '34', '1', '2015-08-08 23:30:00', '2015-08-09 00:00:00', '0.188889', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('75', null, '35', '1', '2015-08-09 00:30:00', '2015-08-09 01:00:00', '0.144444', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('76', null, '36', '1', '2015-08-09 00:00:00', '2015-08-09 00:30:00', '0.166667', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('77', null, '37', '1', '2015-08-08 19:30:00', '2015-08-08 20:00:00', '0.6', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('78', null, '38', '1', '2015-08-08 19:00:00', '2015-08-08 19:30:00', '0.0764346', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('79', null, '39', '1', '2015-08-08 23:00:00', '2015-08-08 23:30:00', '0.177778', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('80', null, '40', '1', '2015-08-08 22:30:00', '2015-08-08 23:00:00', '0.2', '2015-08-08 16:01:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('81', null, '41', '1', '2015-08-09 04:30:00', '2015-08-09 05:00:00', '0.2', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('82', null, '42', '1', '2015-08-09 06:00:00', '2015-08-09 06:30:00', '0.166667', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('83', null, '43', '1', '2015-08-09 05:30:00', '2015-08-09 06:00:00', '0.188889', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('84', null, '44', '1', '2015-08-09 05:00:00', '2015-08-09 05:30:00', '0.177778', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('85', null, '45', '1', '2015-08-09 06:30:00', '2015-08-09 07:00:00', '0.144444', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('86', null, '46', '1', '2015-08-09 01:30:00', '2015-08-09 02:00:00', '0.6', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('87', null, '47', '1', '2015-08-09 01:00:00', '2015-08-09 01:30:00', '0.0764346', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('88', null, '48', '1', '2015-08-09 07:00:00', '2015-08-09 07:30:00', '0.122222', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('89', null, '49', '1', '2015-08-09 07:30:00', '2015-08-09 08:00:00', '0.296193', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('90', null, '50', '1', '2015-08-09 04:00:00', '2015-08-09 04:30:00', '0.6', '2015-08-08 22:24:10');
INSERT INTO `air_condition_real_time_decision` VALUES ('91', null, '51', '1', '2015-08-09 08:30:00', '2015-08-09 09:00:00', '0.296193', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('92', null, '52', '1', '2015-08-09 06:30:00', '2015-08-09 07:00:00', '0.188889', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('93', null, '53', '1', '2015-08-09 05:30:00', '2015-08-09 06:00:00', '0.2', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('94', null, '54', '1', '2015-08-09 02:00:00', '2015-08-09 02:30:00', '0.0764346', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('95', null, '55', '1', '2015-08-09 08:00:00', '2015-08-09 08:30:00', '0.122222', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('96', null, '56', '1', '2015-08-09 05:00:00', '2015-08-09 05:30:00', '0.6', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('97', null, '57', '1', '2015-08-09 07:00:00', '2015-08-09 07:30:00', '0.166667', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('98', null, '58', '1', '2015-08-09 02:30:00', '2015-08-09 03:00:00', '0.6', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('99', null, '59', '1', '2015-08-09 07:30:00', '2015-08-09 08:00:00', '0.144444', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('100', null, '60', '1', '2015-08-09 06:00:00', '2015-08-09 06:30:00', '0.177778', '2015-08-08 23:12:20');
INSERT INTO `air_condition_real_time_decision` VALUES ('101', null, '61', '1', '2015-08-09 07:00:00', '2015-08-09 07:30:00', '0.166667', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('102', null, '62', '1', '2015-08-09 06:00:00', '2015-08-09 06:30:00', '0.177778', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('103', null, '63', '1', '2015-08-09 02:00:00', '2015-08-09 02:30:00', '0.0764346', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('104', null, '64', '1', '2015-08-09 05:30:00', '2015-08-09 06:00:00', '0.2', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('105', null, '65', '1', '2015-08-09 05:00:00', '2015-08-09 05:30:00', '0.6', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('106', null, '66', '1', '2015-08-09 06:30:00', '2015-08-09 07:00:00', '0.188889', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('107', null, '67', '1', '2015-08-09 08:30:00', '2015-08-09 09:00:00', '0.296193', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('108', null, '68', '1', '2015-08-09 07:30:00', '2015-08-09 08:00:00', '0.144444', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('109', null, '69', '1', '2015-08-09 08:00:00', '2015-08-09 08:30:00', '0.122222', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('110', null, '70', '1', '2015-08-09 02:30:00', '2015-08-09 03:00:00', '0.6', '2015-08-08 23:14:32');
INSERT INTO `air_condition_real_time_decision` VALUES ('111', null, '71', '1', '2015-08-10 04:30:00', '2015-08-10 05:00:00', '0.2', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('112', null, '72', '1', '2015-08-10 01:30:00', '2015-08-10 02:00:00', '0.6', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('113', null, '73', '1', '2015-08-10 06:00:00', '2015-08-10 06:30:00', '0.166667', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('114', null, '74', '1', '2015-08-10 05:00:00', '2015-08-10 05:30:00', '0.177778', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('115', null, '75', '1', '2015-08-10 05:30:00', '2015-08-10 06:00:00', '0.188889', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('116', null, '76', '1', '2015-08-10 07:30:00', '2015-08-10 08:00:00', '0.296193', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('117', null, '77', '1', '2015-08-10 06:30:00', '2015-08-10 07:00:00', '0.144444', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('118', null, '78', '1', '2015-08-10 01:00:00', '2015-08-10 01:30:00', '0.0764346', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('119', null, '79', '1', '2015-08-10 07:00:00', '2015-08-10 07:30:00', '0.122222', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('120', null, '80', '1', '2015-08-10 04:00:00', '2015-08-10 04:30:00', '0.6', '2015-08-09 00:07:02');
INSERT INTO `air_condition_real_time_decision` VALUES ('121', null, '81', '1', '2015-08-10 06:00:00', '2015-08-10 06:30:00', '0.166667', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('122', null, '82', '1', '2015-08-10 07:30:00', '2015-08-10 08:00:00', '0.296193', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('123', null, '83', '1', '2015-08-10 07:00:00', '2015-08-10 07:30:00', '0.122222', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('124', null, '84', '1', '2015-08-10 01:00:00', '2015-08-10 01:30:00', '0.0764346', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('125', null, '85', '1', '2015-08-10 05:30:00', '2015-08-10 06:00:00', '0.188889', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('126', null, '86', '1', '2015-08-10 06:30:00', '2015-08-10 07:00:00', '0.144444', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('127', null, '87', '1', '2015-08-10 05:00:00', '2015-08-10 05:30:00', '0.177778', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('128', null, '88', '1', '2015-08-10 04:30:00', '2015-08-10 05:00:00', '0.2', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('129', null, '89', '1', '2015-08-10 01:30:00', '2015-08-10 02:00:00', '0.6', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('130', null, '90', '1', '2015-08-10 04:00:00', '2015-08-10 04:30:00', '0.6', '2015-08-09 00:28:49');
INSERT INTO `air_condition_real_time_decision` VALUES ('131', null, '91', '1', '2015-08-10 01:30:00', '2015-08-10 02:00:00', '0.6', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('132', null, '92', '1', '2015-08-10 04:00:00', '2015-08-10 04:30:00', '0.6', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('133', null, '93', '1', '2015-08-10 05:30:00', '2015-08-10 06:00:00', '0.188889', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('134', null, '94', '1', '2015-08-10 07:00:00', '2015-08-10 07:30:00', '0.122222', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('135', null, '95', '1', '2015-08-10 07:30:00', '2015-08-10 08:00:00', '0.296193', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('136', '13', '96', '1', '2015-08-09 17:18:19', '2015-08-10 05:00:00', '0.2', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('137', '13', '97', '1', '2015-08-09 17:18:15', '2015-08-10 07:00:00', '0.144444', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('138', '14', '98', '1', '2015-08-09 17:18:14', '2015-08-10 06:30:00', '0.166667', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('139', '14', '99', '1', '2015-08-09 17:18:13', '2015-08-10 05:30:00', '0.177778', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('140', '14', '100', '1', '2015-08-09 17:18:11', '2015-08-10 01:30:00', '0.0764346', '2015-08-09 00:30:33');
INSERT INTO `air_condition_real_time_decision` VALUES ('141', null, '101', '1', '2015-08-11 22:00:00', '2015-08-11 22:30:00', '0.6', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('142', null, '102', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('143', null, '103', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('144', null, '104', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('145', null, '105', '1', '2015-08-11 19:00:00', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('146', null, '106', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('147', null, '107', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('148', null, '108', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('149', null, '109', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('150', null, '110', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:22:45');
INSERT INTO `air_condition_real_time_decision` VALUES ('151', null, '111', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('152', null, '112', '1', '2015-08-11 22:00:00', '2015-08-11 22:30:00', '0.6', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('153', null, '113', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('154', null, '114', '1', '2015-08-11 19:00:00', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('155', null, '115', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('156', null, '116', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('157', null, '117', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('158', null, '118', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('159', null, '119', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('160', null, '120', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:26:15');
INSERT INTO `air_condition_real_time_decision` VALUES ('161', null, '121', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('162', null, '122', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('163', null, '123', '1', '2015-08-11 19:00:00', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('164', null, '124', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('165', null, '125', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('166', null, '126', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('167', null, '127', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('168', null, '128', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('169', null, '129', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('170', null, '130', '1', '2015-08-11 22:00:00', '2015-08-11 22:30:00', '0.6', '2015-08-11 15:28:19');
INSERT INTO `air_condition_real_time_decision` VALUES ('171', null, '131', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('172', null, '132', '1', '2015-08-11 19:00:00', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('173', null, '133', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('174', null, '134', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('175', null, '135', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('176', null, '136', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:50:25');
INSERT INTO `air_condition_real_time_decision` VALUES ('177', null, '137', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:50:26');
INSERT INTO `air_condition_real_time_decision` VALUES ('178', null, '138', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:50:26');
INSERT INTO `air_condition_real_time_decision` VALUES ('179', null, '139', '1', '2015-08-11 22:00:00', '2015-08-11 22:30:00', '0.6', '2015-08-11 15:50:26');
INSERT INTO `air_condition_real_time_decision` VALUES ('180', null, '140', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:50:26');
INSERT INTO `air_condition_real_time_decision` VALUES ('181', null, '141', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('182', null, '142', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('183', null, '143', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('184', null, '144', '1', '2015-08-11 22:00:00', '2015-08-11 22:30:00', '0.6', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('185', null, '145', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('186', null, '146', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('187', null, '147', '1', '2015-08-11 19:00:00', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('188', null, '148', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('189', null, '149', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('190', null, '150', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:51:51');
INSERT INTO `air_condition_real_time_decision` VALUES ('191', null, '151', '1', '2015-08-12 00:00:00', '2015-08-12 00:30:00', '0.166667', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('192', null, '152', '1', '2015-08-11 19:30:00', '2015-08-11 20:00:00', '0.6', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('193', null, '153', '1', '2015-08-11 22:30:00', '2015-08-11 23:00:00', '0.2', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('194', null, '154', '1', '2015-08-12 01:00:00', '2015-08-12 01:30:00', '0.122222', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('195', null, '155', '1', '2015-08-11 23:30:00', '2015-08-12 00:00:00', '0.188889', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('196', null, '156', '1', '2015-08-11 23:00:00', '2015-08-11 23:30:00', '0.177778', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('197', null, '157', '1', '2015-08-12 00:30:00', '2015-08-12 01:00:00', '0.144444', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('198', null, '158', '1', '2015-08-12 01:30:00', '2015-08-12 02:00:00', '0.296193', '2015-08-11 15:56:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('199', null, '159', '1', '2015-08-11 22:35:12', '2015-08-11 19:30:00', '0.0764346', '2015-08-11 22:35:35');
INSERT INTO `air_condition_real_time_decision` VALUES ('200', null, '160', '1', '2015-08-11 23:22:52', '2015-08-11 22:30:00', '0.6', '2015-08-11 22:35:35');

-- ----------------------------
-- Table structure for air_condition_status
-- ----------------------------
DROP TABLE IF EXISTS `air_condition_status`;
CREATE TABLE `air_condition_status` (
  `air_condition_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `air_condition_id` int(11) DEFAULT NULL,
  `air_condition_temperature` float NOT NULL,
  `air_condition_mode` int(11) NOT NULL,
  `is_controlled_by_user` tinyint(1) NOT NULL,
  `air_condition_status_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`air_condition_status_id`),
  KEY `FK_record_air_condition_status` (`air_condition_id`),
  CONSTRAINT `FK_record_air_condition_status` FOREIGN KEY (`air_condition_id`) REFERENCES `air_condition` (`air_condition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_condition_status
-- ----------------------------
INSERT INTO `air_condition_status` VALUES ('1', '1', '35', '1', '0', '2015-08-03 21:06:43', null);
INSERT INTO `air_condition_status` VALUES ('2', '1', '35', '1', '0', '2015-08-03 21:12:58', null);
INSERT INTO `air_condition_status` VALUES ('3', '1', '33.0658', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('4', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('5', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('6', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('7', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('8', '1', '29.1111', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('9', '1', '31.6713', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('10', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('11', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('12', '1', '25', '3', '0', '2015-08-08 02:44:13', null);
INSERT INTO `air_condition_status` VALUES ('13', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('14', '1', '33.0658', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('15', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('16', '1', '31.6713', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('17', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('18', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('19', '1', '29.1111', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('20', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('21', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('22', '1', '25', '3', '0', '2015-08-08 15:46:44', null);
INSERT INTO `air_condition_status` VALUES ('23', '1', '31.6713', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('24', '1', '33.0658', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('25', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('26', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('27', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('28', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('29', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('30', '1', '29.1111', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('31', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('32', '1', '25', '3', '0', '2015-08-08 15:59:40', null);
INSERT INTO `air_condition_status` VALUES ('33', '1', '29.1111', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('34', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('35', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('36', '1', '33.0658', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('37', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('38', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('39', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('40', '1', '31.6713', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('41', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('42', '1', '25', '3', '0', '2015-08-08 16:01:44', '0');
INSERT INTO `air_condition_status` VALUES ('43', '1', '35', '1', '0', '2015-08-08 16:28:01', null);
INSERT INTO `air_condition_status` VALUES ('44', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('45', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('46', '1', '29.1111', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('47', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('48', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('49', '1', '31.6713', '3', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('50', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('51', '1', '33.0658', '3', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('52', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('53', '1', '25', '1', '0', '2015-08-08 22:24:34', '0');
INSERT INTO `air_condition_status` VALUES ('54', '1', '35', '1', '0', '2015-08-08 22:27:18', '0');
INSERT INTO `air_condition_status` VALUES ('55', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('56', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('57', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('58', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('59', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('60', '1', '29.1111', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('61', '1', '33.0658', '3', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('62', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('63', '1', '25', '1', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('64', '1', '31.6713', '3', '0', '2015-08-08 23:12:44', '0');
INSERT INTO `air_condition_status` VALUES ('65', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('66', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('67', '1', '29.1111', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('68', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('69', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('70', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('71', '1', '31.6713', '3', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('72', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('73', '1', '33.0658', '3', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('74', '1', '25', '1', '0', '2015-08-08 23:14:56', '0');
INSERT INTO `air_condition_status` VALUES ('75', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('76', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('77', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('78', '1', '29.1111', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('79', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('80', '1', '31.6713', '3', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('81', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('82', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('83', '1', '25', '1', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('84', '1', '33.0658', '3', '0', '2015-08-09 00:07:46', '0');
INSERT INTO `air_condition_status` VALUES ('85', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('86', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('87', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('88', '1', '33.0658', '3', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('89', '1', '29.1111', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('90', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('91', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('92', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('93', '1', '31.6713', '3', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('94', '1', '25', '1', '0', '2015-08-09 00:29:47', '0');
INSERT INTO `air_condition_status` VALUES ('95', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('96', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('97', '1', '31.6713', '3', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('98', '1', '29.1111', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('99', '1', '33.0658', '3', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('100', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('101', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('102', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('103', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('104', '1', '25', '1', '0', '2015-08-09 00:30:57', '0');
INSERT INTO `air_condition_status` VALUES ('105', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('106', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('107', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('108', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('109', '1', '31.6713', '3', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('110', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('111', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('112', '1', '29.1111', '1', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('113', '1', '33.0658', '3', '0', '2015-08-11 15:57:06', '0');
INSERT INTO `air_condition_status` VALUES ('114', '1', '25', '1', '0', '2015-08-11 15:57:06', '0');

-- ----------------------------
-- Table structure for box
-- ----------------------------
DROP TABLE IF EXISTS `box`;
CREATE TABLE `box` (
  `box_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL,
  `development_board_ip` text NOT NULL,
  `control_model_ip` text NOT NULL,
  PRIMARY KEY (`box_id`),
  KEY `FK_belong_to` (`room_id`),
  CONSTRAINT `FK_belong_to` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of box
-- ----------------------------
INSERT INTO `box` VALUES ('1', '1', 'localhost', 'localhost');

-- ----------------------------
-- Table structure for circuit_line
-- ----------------------------
DROP TABLE IF EXISTS `circuit_line`;
CREATE TABLE `circuit_line` (
  `circuit_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `cir_circuit_line_id` int(11) DEFAULT NULL,
  `electricity_meter_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `circuit_line_description` text,
  PRIMARY KEY (`circuit_line_id`),
  KEY `FK_has_circuit_line` (`room_id`),
  KEY `FK_has_electricity_meter` (`electricity_meter_id`),
  KEY `FK_has_father_circuit_line` (`cir_circuit_line_id`),
  CONSTRAINT `FK_has_circuit_line` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FK_has_electricity_meter` FOREIGN KEY (`electricity_meter_id`) REFERENCES `electricity_meter` (`electricity_meter_id`),
  CONSTRAINT `FK_has_father_circuit_line` FOREIGN KEY (`cir_circuit_line_id`) REFERENCES `circuit_line` (`circuit_line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circuit_line
-- ----------------------------
INSERT INTO `circuit_line` VALUES ('1', null, null, null, '1');

-- ----------------------------
-- Table structure for curtain
-- ----------------------------
DROP TABLE IF EXISTS `curtain`;
CREATE TABLE `curtain` (
  `curtain_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  `curtain_ip` varchar(15) DEFAULT NULL,
  `curtain_size` float DEFAULT NULL,
  `curtain_rated_power` float DEFAULT NULL,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`curtain_id`),
  KEY `FK_control_curtain` (`box_id`),
  CONSTRAINT `FK_control_curtain` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of curtain
-- ----------------------------
INSERT INTO `curtain` VALUES ('1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for curtain_status
-- ----------------------------
DROP TABLE IF EXISTS `curtain_status`;
CREATE TABLE `curtain_status` (
  `curtain_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `curtain_id` int(11) DEFAULT NULL,
  `curtain_status` float NOT NULL,
  `is_controlled_by_user` tinyint(1) NOT NULL,
  `curtain_status_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`curtain_status_id`),
  KEY `FK_record_curtain_status` (`curtain_id`),
  CONSTRAINT `FK_record_curtain_status` FOREIGN KEY (`curtain_id`) REFERENCES `curtain` (`curtain_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of curtain_status
-- ----------------------------
INSERT INTO `curtain_status` VALUES ('1', '1', '10', '0', '2015-08-03 21:06:43', null);
INSERT INTO `curtain_status` VALUES ('2', '1', '10', '0', '2015-08-03 21:12:58', null);

-- ----------------------------
-- Table structure for daily_electricity_price
-- ----------------------------
DROP TABLE IF EXISTS `daily_electricity_price`;
CREATE TABLE `daily_electricity_price` (
  `daily_electricity_price_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `price_period_0` float DEFAULT NULL,
  `price_period_1` float DEFAULT NULL,
  `price_period_2` float DEFAULT NULL,
  `price_period_3` float DEFAULT NULL,
  `price_period_4` float DEFAULT NULL,
  `price_period_5` float DEFAULT NULL,
  `price_period_6` float DEFAULT NULL,
  `price_period_7` float DEFAULT NULL,
  `price_period_8` float DEFAULT NULL,
  `price_period_9` float DEFAULT NULL,
  `price_period_10` float DEFAULT NULL,
  `price_period_11` float DEFAULT NULL,
  `price_period_12` float DEFAULT NULL,
  `price_period_13` float DEFAULT NULL,
  `pirce_period_14` float DEFAULT NULL,
  `price_period_15` float DEFAULT NULL,
  `price_period_16` float DEFAULT NULL,
  `price_period_17` float DEFAULT NULL,
  `price_period_18` float DEFAULT NULL,
  `price_period_19` float DEFAULT NULL,
  `price_period_20` float DEFAULT NULL,
  `price_period_21` float DEFAULT NULL,
  `price_period_22` float DEFAULT NULL,
  `price_period_23` float DEFAULT NULL,
  PRIMARY KEY (`daily_electricity_price_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily_electricity_price
-- ----------------------------
INSERT INTO `daily_electricity_price` VALUES ('1', null, '0.391', '0.391', '0.391', '0.793', '1.194', '0.988', '0.781', '0.781', '0.781', '0.988', '1.194', '0.793', '0.391', '0.391', '0.391', '0.793', '1.194', '0.988', '0.781', '0.781', '0.781', '0.988', '1.194', '0.793');

-- ----------------------------
-- Table structure for electricity_info
-- ----------------------------
DROP TABLE IF EXISTS `electricity_info`;
CREATE TABLE `electricity_info` (
  `electricity_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `electricity_meter_id` int(11) DEFAULT NULL,
  `active_power` float NOT NULL,
  `reactive_power` float NOT NULL,
  `total_consume_energy` float NOT NULL,
  `electricity_info_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`electricity_info_id`),
  KEY `FK_collect_electricity_meter_data` (`electricity_meter_id`),
  CONSTRAINT `FK_collect_electricity_meter_data` FOREIGN KEY (`electricity_meter_id`) REFERENCES `electricity_meter` (`electricity_meter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of electricity_info
-- ----------------------------
INSERT INTO `electricity_info` VALUES ('3', '1', '12', '13', '12134', '2015-08-07 12:00:52');
INSERT INTO `electricity_info` VALUES ('4', '1', '2', '23', '124', '2015-08-07 01:00:54');
INSERT INTO `electricity_info` VALUES ('5', '1', '132', '24', '134', '2015-08-07 12:56:02');

-- ----------------------------
-- Table structure for electricity_meter
-- ----------------------------
DROP TABLE IF EXISTS `electricity_meter`;
CREATE TABLE `electricity_meter` (
  `electricity_meter_id` int(11) NOT NULL AUTO_INCREMENT,
  `electricity_type_id` int(11) DEFAULT NULL,
  `circuit_line_id` int(11) DEFAULT NULL,
  `electricity_meter_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`electricity_meter_id`),
  KEY `FK_has_electricity_meter2` (`circuit_line_id`),
  KEY `FK_refer` (`electricity_type_id`),
  CONSTRAINT `FK_has_electricity_meter2` FOREIGN KEY (`circuit_line_id`) REFERENCES `circuit_line` (`circuit_line_id`),
  CONSTRAINT `FK_refer` FOREIGN KEY (`electricity_type_id`) REFERENCES `electricity_type` (`electricity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of electricity_meter
-- ----------------------------
INSERT INTO `electricity_meter` VALUES ('1', null, '1', '1');

-- ----------------------------
-- Table structure for electricity_type
-- ----------------------------
DROP TABLE IF EXISTS `electricity_type`;
CREATE TABLE `electricity_type` (
  `electricity_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `electricity_type` text NOT NULL,
  PRIMARY KEY (`electricity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of electricity_type
-- ----------------------------
INSERT INTO `electricity_type` VALUES ('1', '1');

-- ----------------------------
-- Table structure for flame_sensor
-- ----------------------------
DROP TABLE IF EXISTS `flame_sensor`;
CREATE TABLE `flame_sensor` (
  `flame_sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`flame_sensor_id`),
  KEY `FK_has_flame_sensor` (`box_id`),
  CONSTRAINT `FK_has_flame_sensor` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flame_sensor
-- ----------------------------
INSERT INTO `flame_sensor` VALUES ('1', '1');
INSERT INTO `flame_sensor` VALUES ('2', '1');

-- ----------------------------
-- Table structure for flame_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `flame_sensor_data`;
CREATE TABLE `flame_sensor_data` (
  `flame_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `flame_sensor_id` int(11) DEFAULT NULL,
  `flame_data` float NOT NULL,
  `flame_data_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`flame_data_id`),
  KEY `FK_collect_flame_data` (`flame_sensor_id`),
  CONSTRAINT `FK_collect_flame_data` FOREIGN KEY (`flame_sensor_id`) REFERENCES `flame_sensor` (`flame_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flame_sensor_data
-- ----------------------------
INSERT INTO `flame_sensor_data` VALUES ('1', '1', '10', '2015-07-31 18:04:08');
INSERT INTO `flame_sensor_data` VALUES ('2', '1', '10', '2015-08-03 14:34:33');
INSERT INTO `flame_sensor_data` VALUES ('3', '1', '10', '2015-08-03 15:56:55');
INSERT INTO `flame_sensor_data` VALUES ('4', '1', '10', '2015-08-03 15:57:33');
INSERT INTO `flame_sensor_data` VALUES ('5', '2', '10', '2015-08-03 16:06:55');
INSERT INTO `flame_sensor_data` VALUES ('6', '2', '10', '2015-08-03 16:13:29');
INSERT INTO `flame_sensor_data` VALUES ('7', '2', '10', '2015-08-03 16:22:40');
INSERT INTO `flame_sensor_data` VALUES ('8', '2', '10', '2015-08-03 16:22:48');

-- ----------------------------
-- Table structure for gas_sensor
-- ----------------------------
DROP TABLE IF EXISTS `gas_sensor`;
CREATE TABLE `gas_sensor` (
  `gas_sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`gas_sensor_id`),
  KEY `FK_has_gas_sensor` (`box_id`),
  CONSTRAINT `FK_has_gas_sensor` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gas_sensor
-- ----------------------------
INSERT INTO `gas_sensor` VALUES ('1', '1');
INSERT INTO `gas_sensor` VALUES ('2', '1');

-- ----------------------------
-- Table structure for gas_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `gas_sensor_data`;
CREATE TABLE `gas_sensor_data` (
  `gas_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `gas_sensor_id` int(11) DEFAULT NULL,
  `gas_data` float NOT NULL,
  `gas_data_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gas_data_id`),
  KEY `FK_collect_gas_data` (`gas_sensor_id`),
  CONSTRAINT `FK_collect_gas_data` FOREIGN KEY (`gas_sensor_id`) REFERENCES `gas_sensor` (`gas_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gas_sensor_data
-- ----------------------------
INSERT INTO `gas_sensor_data` VALUES ('1', '1', '10', '2015-07-31 18:04:07');
INSERT INTO `gas_sensor_data` VALUES ('2', '1', '10', '2015-08-03 14:34:29');
INSERT INTO `gas_sensor_data` VALUES ('3', '1', '10', '2015-08-03 15:56:54');
INSERT INTO `gas_sensor_data` VALUES ('4', '1', '10', '2015-08-03 15:57:32');
INSERT INTO `gas_sensor_data` VALUES ('5', '2', '10', '2015-08-03 16:06:54');
INSERT INTO `gas_sensor_data` VALUES ('6', '2', '10', '2015-08-03 16:13:28');

-- ----------------------------
-- Table structure for gps_info
-- ----------------------------
DROP TABLE IF EXISTS `gps_info`;
CREATE TABLE `gps_info` (
  `gps_id` int(11) NOT NULL AUTO_INCREMENT,
  `loc_type_id` int(11) DEFAULT NULL,
  `gps_longitude` double NOT NULL,
  `gps_latitude` double NOT NULL,
  `distance_from_home` float NOT NULL,
  `gps_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gps_id`),
  KEY `FK_reflect_a_location_type` (`loc_type_id`),
  CONSTRAINT `FK_reflect_a_location_type` FOREIGN KEY (`loc_type_id`) REFERENCES `location_type` (`loc_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gps_info
-- ----------------------------
INSERT INTO `gps_info` VALUES ('1', '1', '200.947538566871', '101.312456205435', '100.65', '2015-08-05 15:36:56');
INSERT INTO `gps_info` VALUES ('2', '1', '200.52820098621', '101.227076998905', '100.628', '2015-08-05 15:41:16');
INSERT INTO `gps_info` VALUES ('3', '1', '200.56038655354', '101.80241173912', '100.663', '2015-08-05 15:42:42');
INSERT INTO `gps_info` VALUES ('4', '1', '200.087789765629', '101.964962057395', '100.026', '2015-08-05 15:43:32');
INSERT INTO `gps_info` VALUES ('5', '1', '200.35009942673', '101.035660378667', '100.058', '2015-08-05 15:43:56');

-- ----------------------------
-- Table structure for gps_info_to_demand
-- ----------------------------
DROP TABLE IF EXISTS `gps_info_to_demand`;
CREATE TABLE `gps_info_to_demand` (
  `gps_info_to_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `loc_type_id` int(11) DEFAULT NULL,
  `distance_from_home` float DEFAULT NULL,
  `gps_info_begin_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gps_info_to_demand_id`),
  KEY `FK_location_type_has_demand_rule` (`loc_type_id`),
  CONSTRAINT `FK_location_type_has_demand_rule` FOREIGN KEY (`loc_type_id`) REFERENCES `location_type` (`loc_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gps_info_to_demand
-- ----------------------------

-- ----------------------------
-- Table structure for has_air_condition_demand
-- ----------------------------
DROP TABLE IF EXISTS `has_air_condition_demand`;
CREATE TABLE `has_air_condition_demand` (
  `air_condition_demand_id` int(11) NOT NULL,
  `real_time_demand_id` int(11) NOT NULL,
  PRIMARY KEY (`air_condition_demand_id`,`real_time_demand_id`),
  KEY `FK_has_air_condition_demand2` (`real_time_demand_id`),
  CONSTRAINT `FK_has_air_condition_demand` FOREIGN KEY (`air_condition_demand_id`) REFERENCES `air_condition_demand` (`air_condition_demand_id`),
  CONSTRAINT `FK_has_air_condition_demand2` FOREIGN KEY (`real_time_demand_id`) REFERENCES `real_time_demand` (`real_time_demand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of has_air_condition_demand
-- ----------------------------
INSERT INTO `has_air_condition_demand` VALUES ('13', '5');
INSERT INTO `has_air_condition_demand` VALUES ('13', '6');
INSERT INTO `has_air_condition_demand` VALUES ('13', '7');
INSERT INTO `has_air_condition_demand` VALUES ('13', '8');
INSERT INTO `has_air_condition_demand` VALUES ('13', '9');
INSERT INTO `has_air_condition_demand` VALUES ('1', '10');
INSERT INTO `has_air_condition_demand` VALUES ('2', '10');
INSERT INTO `has_air_condition_demand` VALUES ('1', '11');
INSERT INTO `has_air_condition_demand` VALUES ('2', '11');
INSERT INTO `has_air_condition_demand` VALUES ('3', '12');
INSERT INTO `has_air_condition_demand` VALUES ('4', '12');
INSERT INTO `has_air_condition_demand` VALUES ('11', '13');
INSERT INTO `has_air_condition_demand` VALUES ('11', '14');
INSERT INTO `has_air_condition_demand` VALUES ('11', '15');
INSERT INTO `has_air_condition_demand` VALUES ('11', '16');
INSERT INTO `has_air_condition_demand` VALUES ('11', '17');
INSERT INTO `has_air_condition_demand` VALUES ('11', '18');
INSERT INTO `has_air_condition_demand` VALUES ('11', '19');
INSERT INTO `has_air_condition_demand` VALUES ('11', '20');
INSERT INTO `has_air_condition_demand` VALUES ('11', '21');
INSERT INTO `has_air_condition_demand` VALUES ('11', '22');
INSERT INTO `has_air_condition_demand` VALUES ('11', '23');
INSERT INTO `has_air_condition_demand` VALUES ('13', '24');
INSERT INTO `has_air_condition_demand` VALUES ('13', '25');
INSERT INTO `has_air_condition_demand` VALUES ('13', '26');
INSERT INTO `has_air_condition_demand` VALUES ('13', '27');
INSERT INTO `has_air_condition_demand` VALUES ('3', '28');
INSERT INTO `has_air_condition_demand` VALUES ('4', '28');
INSERT INTO `has_air_condition_demand` VALUES ('3', '29');
INSERT INTO `has_air_condition_demand` VALUES ('4', '29');
INSERT INTO `has_air_condition_demand` VALUES ('3', '30');
INSERT INTO `has_air_condition_demand` VALUES ('4', '30');
INSERT INTO `has_air_condition_demand` VALUES ('3', '31');
INSERT INTO `has_air_condition_demand` VALUES ('4', '31');
INSERT INTO `has_air_condition_demand` VALUES ('3', '32');
INSERT INTO `has_air_condition_demand` VALUES ('4', '32');
INSERT INTO `has_air_condition_demand` VALUES ('3', '33');
INSERT INTO `has_air_condition_demand` VALUES ('4', '33');
INSERT INTO `has_air_condition_demand` VALUES ('3', '34');
INSERT INTO `has_air_condition_demand` VALUES ('4', '34');
INSERT INTO `has_air_condition_demand` VALUES ('3', '35');
INSERT INTO `has_air_condition_demand` VALUES ('4', '35');
INSERT INTO `has_air_condition_demand` VALUES ('3', '36');
INSERT INTO `has_air_condition_demand` VALUES ('4', '36');
INSERT INTO `has_air_condition_demand` VALUES ('3', '37');
INSERT INTO `has_air_condition_demand` VALUES ('4', '37');

-- ----------------------------
-- Table structure for has_water_heater_demand
-- ----------------------------
DROP TABLE IF EXISTS `has_water_heater_demand`;
CREATE TABLE `has_water_heater_demand` (
  `water_heater_demand_id` int(11) NOT NULL,
  `real_time_demand_id` int(11) NOT NULL,
  PRIMARY KEY (`water_heater_demand_id`,`real_time_demand_id`),
  KEY `FK_has_water_heater_demand2` (`real_time_demand_id`),
  CONSTRAINT `FK_has_water_heater_demand` FOREIGN KEY (`water_heater_demand_id`) REFERENCES `water_heater_demand` (`water_heater_demand_id`),
  CONSTRAINT `FK_has_water_heater_demand2` FOREIGN KEY (`real_time_demand_id`) REFERENCES `real_time_demand` (`real_time_demand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of has_water_heater_demand
-- ----------------------------

-- ----------------------------
-- Table structure for humidity_data
-- ----------------------------
DROP TABLE IF EXISTS `humidity_data`;
CREATE TABLE `humidity_data` (
  `humidity_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature_sensor_id` int(11) DEFAULT NULL,
  `humidity_data` float NOT NULL,
  `humidity_data_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`humidity_data_id`),
  KEY `FK_collect_humidity_data` (`temperature_sensor_id`),
  CONSTRAINT `FK_collect_humidity_data` FOREIGN KEY (`temperature_sensor_id`) REFERENCES `temperature_sensor` (`temperature_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of humidity_data
-- ----------------------------
INSERT INTO `humidity_data` VALUES ('1', '1', '10', '2015-08-03 18:05:05');
INSERT INTO `humidity_data` VALUES ('2', '1', '10', '2015-08-03 18:22:53');
INSERT INTO `humidity_data` VALUES ('3', '1', '10', '2015-08-03 18:24:01');
INSERT INTO `humidity_data` VALUES ('4', '1', '10', '2015-08-04 14:13:33');
INSERT INTO `humidity_data` VALUES ('5', '2', '10', '2015-08-06 19:28:28');

-- ----------------------------
-- Table structure for lamp
-- ----------------------------
DROP TABLE IF EXISTS `lamp`;
CREATE TABLE `lamp` (
  `lamp_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  `lamp_type` int(11) DEFAULT NULL,
  `lamp_rated_power` float DEFAULT NULL,
  `lamp_location` int(11) DEFAULT NULL,
  PRIMARY KEY (`lamp_id`),
  KEY `FK_control_lamp` (`box_id`),
  CONSTRAINT `FK_control_lamp` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lamp
-- ----------------------------
INSERT INTO `lamp` VALUES ('1', '1', null, null, null);
INSERT INTO `lamp` VALUES ('2', '1', null, null, null);
INSERT INTO `lamp` VALUES ('3', '1', null, null, null);

-- ----------------------------
-- Table structure for lamp_status
-- ----------------------------
DROP TABLE IF EXISTS `lamp_status`;
CREATE TABLE `lamp_status` (
  `lamp_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `lamp_id` int(11) DEFAULT NULL,
  `lamp_status` int(11) NOT NULL,
  `is_controlled_by_user` tinyint(1) NOT NULL,
  `lamp_status_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_already_controlled` tinyint(1) NOT NULL,
  PRIMARY KEY (`lamp_status_id`),
  KEY `FK_record_lamp_status` (`lamp_id`),
  CONSTRAINT `FK_record_lamp_status` FOREIGN KEY (`lamp_id`) REFERENCES `lamp` (`lamp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lamp_status
-- ----------------------------
INSERT INTO `lamp_status` VALUES ('5', '1', '2', '1', '2015-07-31 18:23:31', '1');
INSERT INTO `lamp_status` VALUES ('6', '1', '2', '1', '2015-08-03 18:27:21', '1');
INSERT INTO `lamp_status` VALUES ('7', '1', '2', '1', '2015-08-03 21:06:43', '1');
INSERT INTO `lamp_status` VALUES ('8', '1', '2', '1', '2015-08-03 21:12:58', '1');

-- ----------------------------
-- Table structure for light_sensor
-- ----------------------------
DROP TABLE IF EXISTS `light_sensor`;
CREATE TABLE `light_sensor` (
  `box_id` int(11) DEFAULT NULL,
  `light_sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`light_sensor_id`),
  KEY `FK_has_light_sensor` (`box_id`),
  CONSTRAINT `FK_has_light_sensor` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of light_sensor
-- ----------------------------
INSERT INTO `light_sensor` VALUES (null, '1');
INSERT INTO `light_sensor` VALUES ('1', '2');

-- ----------------------------
-- Table structure for light_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `light_sensor_data`;
CREATE TABLE `light_sensor_data` (
  `light_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `light_sensor_id` int(11) DEFAULT NULL,
  `light_data` float NOT NULL,
  `light_data_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`light_data_id`),
  KEY `FK_collect_light_data` (`light_sensor_id`),
  CONSTRAINT `FK_collect_light_data` FOREIGN KEY (`light_sensor_id`) REFERENCES `light_sensor` (`light_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of light_sensor_data
-- ----------------------------
INSERT INTO `light_sensor_data` VALUES ('1', '1', '10', '2015-08-03 15:00:55');
INSERT INTO `light_sensor_data` VALUES ('2', '1', '10', '2015-08-03 15:40:33');
INSERT INTO `light_sensor_data` VALUES ('3', '2', '10', '2015-08-03 16:00:55');
INSERT INTO `light_sensor_data` VALUES ('4', '2', '10', '2015-08-03 16:13:29');
INSERT INTO `light_sensor_data` VALUES ('5', '1', '10', '2015-08-06 18:00:25');
INSERT INTO `light_sensor_data` VALUES ('6', '1', '10', '2015-08-06 18:20:41');

-- ----------------------------
-- Table structure for location_type
-- ----------------------------
DROP TABLE IF EXISTS `location_type`;
CREATE TABLE `location_type` (
  `loc_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `loc_type` varchar(15) NOT NULL,
  PRIMARY KEY (`loc_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location_type
-- ----------------------------
INSERT INTO `location_type` VALUES ('1', '学校');
INSERT INTO `location_type` VALUES ('2', '体育场所');
INSERT INTO `location_type` VALUES ('3', '医院');
INSERT INTO `location_type` VALUES ('4', '商店');
INSERT INTO `location_type` VALUES ('5', '饭店');

-- ----------------------------
-- Table structure for moving_status
-- ----------------------------
DROP TABLE IF EXISTS `moving_status`;
CREATE TABLE `moving_status` (
  `moving_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `moving_type` varchar(15) NOT NULL,
  PRIMARY KEY (`moving_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moving_status
-- ----------------------------
INSERT INTO `moving_status` VALUES ('1', 'walking');
INSERT INTO `moving_status` VALUES ('2', 'jogging');
INSERT INTO `moving_status` VALUES ('3', 'running');
INSERT INTO `moving_status` VALUES ('4', 'idle');
INSERT INTO `moving_status` VALUES ('5', 'unknown');

-- ----------------------------
-- Table structure for outdoor_weather
-- ----------------------------
DROP TABLE IF EXISTS `outdoor_weather`;
CREATE TABLE `outdoor_weather` (
  `outdoor_weather_id` int(11) NOT NULL AUTO_INCREMENT,
  `outdoor_weather_tem` float DEFAULT NULL,
  `outdoor_weather_hum` float DEFAULT NULL,
  `outdoor_weather_pres` float DEFAULT NULL,
  `outdoor_weather_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`outdoor_weather_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outdoor_weather
-- ----------------------------

-- ----------------------------
-- Table structure for plr_sensor
-- ----------------------------
DROP TABLE IF EXISTS `plr_sensor`;
CREATE TABLE `plr_sensor` (
  `plr_sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`plr_sensor_id`),
  KEY `FK_has_plr_sensor` (`box_id`),
  CONSTRAINT `FK_has_plr_sensor` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plr_sensor
-- ----------------------------
INSERT INTO `plr_sensor` VALUES ('1', null);
INSERT INTO `plr_sensor` VALUES ('2', '1');

-- ----------------------------
-- Table structure for plr_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `plr_sensor_data`;
CREATE TABLE `plr_sensor_data` (
  `plr_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `plr_sensor_id` int(11) DEFAULT NULL,
  `plr_data` tinyint(1) NOT NULL,
  `plr_data_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`plr_data_id`),
  KEY `FK_collect_plr_data` (`plr_sensor_id`),
  CONSTRAINT `FK_collect_plr_data` FOREIGN KEY (`plr_sensor_id`) REFERENCES `plr_sensor` (`plr_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plr_sensor_data
-- ----------------------------
INSERT INTO `plr_sensor_data` VALUES ('1', '1', '1', '2015-07-31 18:00:07');
INSERT INTO `plr_sensor_data` VALUES ('2', '1', '1', '2015-08-06 14:20:33');
INSERT INTO `plr_sensor_data` VALUES ('3', '1', '1', '2015-08-03 15:56:54');
INSERT INTO `plr_sensor_data` VALUES ('4', '1', '1', '2015-08-03 15:57:33');
INSERT INTO `plr_sensor_data` VALUES ('5', '2', '1', '2015-08-03 16:40:55');
INSERT INTO `plr_sensor_data` VALUES ('6', '1', '1', '2015-08-06 21:00:01');

-- ----------------------------
-- Table structure for real_time_decision
-- ----------------------------
DROP TABLE IF EXISTS `real_time_decision`;
CREATE TABLE `real_time_decision` (
  `real_time_decision_id` int(11) NOT NULL AUTO_INCREMENT,
  `real_time_demand_id` int(11) DEFAULT NULL,
  `real_time_decision_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`real_time_decision_id`),
  KEY `FK_operate` (`real_time_demand_id`),
  CONSTRAINT `FK_operate` FOREIGN KEY (`real_time_demand_id`) REFERENCES `real_time_demand` (`real_time_demand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of real_time_decision
-- ----------------------------
INSERT INTO `real_time_decision` VALUES ('5', null, '2015-08-08 02:42:49');
INSERT INTO `real_time_decision` VALUES ('6', null, '2015-08-08 15:46:20');
INSERT INTO `real_time_decision` VALUES ('7', null, '2015-08-08 15:59:16');
INSERT INTO `real_time_decision` VALUES ('8', null, '2015-08-08 16:01:20');
INSERT INTO `real_time_decision` VALUES ('9', null, '2015-08-08 22:24:10');
INSERT INTO `real_time_decision` VALUES ('10', null, '2015-08-08 23:12:20');
INSERT INTO `real_time_decision` VALUES ('11', null, '2015-08-08 23:14:32');
INSERT INTO `real_time_decision` VALUES ('12', null, '2015-08-09 00:07:02');
INSERT INTO `real_time_decision` VALUES ('13', null, '2015-08-09 00:28:49');
INSERT INTO `real_time_decision` VALUES ('14', null, '2015-08-09 00:30:33');
INSERT INTO `real_time_decision` VALUES ('15', null, '2015-08-11 15:22:44');
INSERT INTO `real_time_decision` VALUES ('16', null, '2015-08-11 15:26:15');
INSERT INTO `real_time_decision` VALUES ('17', null, '2015-08-11 15:28:19');
INSERT INTO `real_time_decision` VALUES ('18', null, '2015-08-11 15:50:25');
INSERT INTO `real_time_decision` VALUES ('19', null, '2015-08-11 15:51:51');
INSERT INTO `real_time_decision` VALUES ('20', null, '2015-08-11 22:00:35');

-- ----------------------------
-- Table structure for real_time_demand
-- ----------------------------
DROP TABLE IF EXISTS `real_time_demand`;
CREATE TABLE `real_time_demand` (
  `real_time_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `real_time_demand_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`real_time_demand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of real_time_demand
-- ----------------------------
INSERT INTO `real_time_demand` VALUES ('1', '2015-08-07 23:54:55');
INSERT INTO `real_time_demand` VALUES ('2', '2015-08-08 01:25:55');
INSERT INTO `real_time_demand` VALUES ('3', '2015-08-08 01:41:51');
INSERT INTO `real_time_demand` VALUES ('4', '2015-08-08 01:52:31');
INSERT INTO `real_time_demand` VALUES ('5', '2015-08-08 02:03:24');
INSERT INTO `real_time_demand` VALUES ('6', '2015-08-08 02:15:49');
INSERT INTO `real_time_demand` VALUES ('7', '2015-08-08 02:39:19');
INSERT INTO `real_time_demand` VALUES ('8', '2015-08-08 02:42:38');
INSERT INTO `real_time_demand` VALUES ('9', '2015-08-08 02:50:30');
INSERT INTO `real_time_demand` VALUES ('10', '2015-08-08 15:46:19');
INSERT INTO `real_time_demand` VALUES ('11', '2015-08-08 15:59:16');
INSERT INTO `real_time_demand` VALUES ('12', '2015-08-08 16:01:20');
INSERT INTO `real_time_demand` VALUES ('13', '2015-08-08 22:19:47');
INSERT INTO `real_time_demand` VALUES ('14', '2015-08-08 22:22:44');
INSERT INTO `real_time_demand` VALUES ('15', '2015-08-08 22:24:09');
INSERT INTO `real_time_demand` VALUES ('16', '2015-08-08 22:27:17');
INSERT INTO `real_time_demand` VALUES ('17', '2015-08-08 23:12:19');
INSERT INTO `real_time_demand` VALUES ('18', '2015-08-08 23:14:32');
INSERT INTO `real_time_demand` VALUES ('19', '2015-08-09 00:06:51');
INSERT INTO `real_time_demand` VALUES ('20', '2015-08-09 00:28:18');
INSERT INTO `real_time_demand` VALUES ('21', '2015-08-09 00:30:29');
INSERT INTO `real_time_demand` VALUES ('22', '2015-08-09 00:32:02');
INSERT INTO `real_time_demand` VALUES ('23', '2015-08-09 00:32:03');
INSERT INTO `real_time_demand` VALUES ('24', '2015-08-09 01:25:47');
INSERT INTO `real_time_demand` VALUES ('25', '2015-08-09 01:30:35');
INSERT INTO `real_time_demand` VALUES ('26', '2015-08-09 01:56:22');
INSERT INTO `real_time_demand` VALUES ('27', '2015-08-09 01:58:27');
INSERT INTO `real_time_demand` VALUES ('28', '2015-08-11 13:48:22');
INSERT INTO `real_time_demand` VALUES ('29', '2015-08-11 13:49:01');
INSERT INTO `real_time_demand` VALUES ('30', '2015-08-11 13:49:57');
INSERT INTO `real_time_demand` VALUES ('31', '2015-08-11 15:21:11');
INSERT INTO `real_time_demand` VALUES ('32', '2015-08-11 15:26:04');
INSERT INTO `real_time_demand` VALUES ('33', '2015-08-11 15:28:14');
INSERT INTO `real_time_demand` VALUES ('34', '2015-08-11 15:50:23');
INSERT INTO `real_time_demand` VALUES ('35', '2015-08-11 15:51:44');
INSERT INTO `real_time_demand` VALUES ('36', '2015-08-11 15:56:28');
INSERT INTO `real_time_demand` VALUES ('37', '2015-08-11 16:42:06');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `room_size` float NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FK_owned_room` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '0', '35');

-- ----------------------------
-- Table structure for she_switch
-- ----------------------------
DROP TABLE IF EXISTS `she_switch`;
CREATE TABLE `she_switch` (
  `she_switch_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`she_switch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of she_switch
-- ----------------------------
INSERT INTO `she_switch` VALUES ('1', null);
INSERT INTO `she_switch` VALUES ('2', null);

-- ----------------------------
-- Table structure for she_switch_status
-- ----------------------------
DROP TABLE IF EXISTS `she_switch_status`;
CREATE TABLE `she_switch_status` (
  `she_switch_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `she_switch_id` int(11) DEFAULT NULL,
  `she_switch_status` tinyint(1) NOT NULL,
  `is_controlled_by_user` tinyint(1) DEFAULT NULL,
  `she_switch_status_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`she_switch_status_id`),
  KEY `FK_record_switch_hstatus` (`she_switch_id`),
  CONSTRAINT `FK_record_switch_hstatus` FOREIGN KEY (`she_switch_id`) REFERENCES `she_switch` (`she_switch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of she_switch_status
-- ----------------------------
INSERT INTO `she_switch_status` VALUES ('1', '1', '1', '1', '2015-08-03 21:06:43', null);
INSERT INTO `she_switch_status` VALUES ('2', '1', '1', '1', '2015-08-03 21:12:58', null);
INSERT INTO `she_switch_status` VALUES ('3', '1', '1', '1', '2015-08-03 22:13:16', null);

-- ----------------------------
-- Table structure for social_activity_to_demand
-- ----------------------------
DROP TABLE IF EXISTS `social_activity_to_demand`;
CREATE TABLE `social_activity_to_demand` (
  `social_activity_to_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_type_id` int(11) DEFAULT NULL,
  `social_activity_begin_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`social_activity_to_demand_id`),
  KEY `FK_activity_type_has_demand_rule` (`activity_type_id`),
  CONSTRAINT `FK_activity_type_has_demand_rule` FOREIGN KEY (`activity_type_id`) REFERENCES `activity_type` (`activity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of social_activity_to_demand
-- ----------------------------
INSERT INTO `social_activity_to_demand` VALUES ('1', '1', '14');
INSERT INTO `social_activity_to_demand` VALUES ('2', '1', '15');
INSERT INTO `social_activity_to_demand` VALUES ('3', '1', '16');
INSERT INTO `social_activity_to_demand` VALUES ('4', '1', '17');
INSERT INTO `social_activity_to_demand` VALUES ('5', '1', '18');
INSERT INTO `social_activity_to_demand` VALUES ('6', '1', '19');
INSERT INTO `social_activity_to_demand` VALUES ('7', '1', '10');
INSERT INTO `social_activity_to_demand` VALUES ('8', '1', '11');
INSERT INTO `social_activity_to_demand` VALUES ('9', '1', '12');
INSERT INTO `social_activity_to_demand` VALUES ('10', '1', '13');

-- ----------------------------
-- Table structure for social_info
-- ----------------------------
DROP TABLE IF EXISTS `social_info`;
CREATE TABLE `social_info` (
  `social_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_type_id` int(11) DEFAULT NULL,
  `source_type_id` int(11) DEFAULT NULL,
  `start_time` varchar(30) DEFAULT NULL,
  `end_time` varchar(30) DEFAULT NULL,
  `location` varchar(10) DEFAULT NULL,
  `activity_sent_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `activity_record_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`social_info_id`),
  KEY `FK_collected_from` (`source_type_id`),
  KEY `FK_reflect_activities` (`activity_type_id`),
  CONSTRAINT `FK_collected_from` FOREIGN KEY (`source_type_id`) REFERENCES `social_source` (`source_type_id`),
  CONSTRAINT `FK_reflect_activities` FOREIGN KEY (`activity_type_id`) REFERENCES `activity_type` (`activity_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of social_info
-- ----------------------------
INSERT INTO `social_info` VALUES ('30', '2', null, null, null, null, '2015-08-05 15:43:32', '2015-08-05 15:43:32');
INSERT INTO `social_info` VALUES ('31', '2', null, null, null, null, '2015-08-05 15:43:56', '2015-08-05 15:43:56');
INSERT INTO `social_info` VALUES ('32', '2', null, null, null, null, '2015-08-05 15:43:56', '2015-08-05 15:43:56');
INSERT INTO `social_info` VALUES ('33', '2', null, null, null, null, '2015-08-05 15:43:56', '2015-08-05 15:43:56');
INSERT INTO `social_info` VALUES ('34', '2', '1', null, null, null, '2015-08-05 16:59:58', '2015-08-05 16:59:59');
INSERT INTO `social_info` VALUES ('35', '2', '1', null, null, null, '2015-08-05 17:00:54', '2015-08-05 17:00:55');
INSERT INTO `social_info` VALUES ('36', '1', '1', '2015-08-07 23:45:34.397', null, null, '2015-08-07 23:45:34', '2015-08-07 23:46:02');
INSERT INTO `social_info` VALUES ('37', '1', '1', '2015-08-07 23:54:53.439', null, null, '2015-08-07 23:54:53', '2015-08-07 23:54:54');
INSERT INTO `social_info` VALUES ('38', '1', '1', '2015-08-08 00:26:48.239', null, null, '2015-08-08 00:26:48', '2015-08-08 00:27:15');
INSERT INTO `social_info` VALUES ('39', '1', '1', '2015-08-08 01:25:54.2', null, null, '2015-08-08 01:25:54', '2015-08-08 01:25:55');
INSERT INTO `social_info` VALUES ('40', '1', '1', '2015-08-08 01:38:14.614', null, null, '2015-08-08 01:38:14', '2015-08-08 01:38:27');
INSERT INTO `social_info` VALUES ('41', '1', '1', '2015-08-08 01:51:35.673', null, null, '2015-08-08 01:51:35', '2015-08-08 01:51:52');
INSERT INTO `social_info` VALUES ('42', '1', '1', '2015-08-08 02:01:40.556', null, null, '2015-08-08 02:01:40', '2015-08-08 02:01:54');
INSERT INTO `social_info` VALUES ('43', '1', '1', '2015-08-08 02:02:44.129', null, null, '2015-08-08 02:02:44', '2015-08-08 02:02:51');
INSERT INTO `social_info` VALUES ('44', '1', '1', '2015-08-08 02:14:25.725', null, null, '2015-08-08 02:14:25', '2015-08-08 02:15:22');
INSERT INTO `social_info` VALUES ('45', '1', '1', '2015-08-08 02:39:13.418', null, null, '2015-08-08 02:39:13', '2015-08-08 02:39:18');
INSERT INTO `social_info` VALUES ('46', '1', '1', '2015-08-08 02:42:20.315', null, null, '2015-08-08 02:42:20', '2015-08-08 02:42:32');
INSERT INTO `social_info` VALUES ('47', '1', '1', '2015-08-08 02:43:24.315', null, null, '2015-08-08 02:43:24', '2015-08-08 02:44:08');
INSERT INTO `social_info` VALUES ('48', '1', '1', '2015-08-08 15:46:18.346', null, null, '2015-08-08 15:46:18', '2015-08-08 15:46:19');
INSERT INTO `social_info` VALUES ('49', '1', '1', '2015-08-08 15:59:15.787', null, null, '2015-08-08 15:59:15', '2015-08-08 15:59:16');
INSERT INTO `social_info` VALUES ('50', '1', '1', '2015-08-08 16:01:19.38', null, null, '2015-08-08 16:01:19', '2015-08-08 16:01:20');
INSERT INTO `social_info` VALUES ('51', '1', '1', '2015-08-08 20:46:37.097', null, null, '2015-08-08 20:46:37', '2015-08-08 20:46:38');
INSERT INTO `social_info` VALUES ('52', '1', '1', '2015-08-08 22:19:46.022', null, null, '2015-08-08 22:19:46', '2015-08-08 22:19:46');
INSERT INTO `social_info` VALUES ('53', '1', '1', '2015-08-08 22:21:17.618', null, null, '2015-08-08 22:21:17', '2015-08-08 22:21:39');
INSERT INTO `social_info` VALUES ('54', '1', '1', '2015-08-08 22:24:05.01', null, null, '2015-08-08 22:24:05', '2015-08-08 22:24:09');
INSERT INTO `social_info` VALUES ('55', '1', '1', '2015-08-08 22:24:24.482', null, null, '2015-08-08 22:24:24', '2015-08-08 22:24:40');
INSERT INTO `social_info` VALUES ('56', '1', '1', '2015-08-08 23:12:18.668', null, null, '2015-08-08 23:12:18', '2015-08-08 23:12:19');
INSERT INTO `social_info` VALUES ('57', '1', '1', '2015-08-08 23:14:32.138', '2015-08-08 23:14:32.144', null, '2015-08-08 23:14:32', '2015-08-08 23:14:32');
INSERT INTO `social_info` VALUES ('58', '1', '1', '2015-08-08 22:00:00', null, null, '2015-08-08 23:17:50', '2015-08-08 23:17:51');
INSERT INTO `social_info` VALUES ('59', '1', '1', '2015-08-08 22:00:00', null, null, '2015-08-08 23:23:26', '2015-08-08 23:23:35');
INSERT INTO `social_info` VALUES ('60', '1', '1', '2015-08-08 22:00:00', null, null, '2015-08-09 00:06:10', '2015-08-09 00:06:22');
INSERT INTO `social_info` VALUES ('61', '1', '1', '2015-08-08 22:05:00', null, null, '2015-08-09 00:27:00', '2015-08-09 00:27:17');
INSERT INTO `social_info` VALUES ('62', '1', '1', '2015-08-08 22:05:00', null, null, '2015-08-09 00:27:00', '2015-08-09 00:27:26');
INSERT INTO `social_info` VALUES ('63', '1', '1', '2015-08-08 22:05:00', null, null, '2015-08-09 00:31:58', '2015-08-09 00:32:00');
INSERT INTO `social_info` VALUES ('64', '1', '1', '2015-08-08 22:05:00', null, null, '2015-08-09 00:31:58', '2015-08-09 00:32:01');
INSERT INTO `social_info` VALUES ('65', '1', '1', '2015-08-09 01:25:46.421', '2015-08-09 01:25:46.428', null, '2015-08-09 01:25:46', '2015-08-09 01:25:47');
INSERT INTO `social_info` VALUES ('66', '1', '1', '2015-08-09 01:29:10.084', '2015-08-09 01:29:10.091', null, '2015-08-09 01:29:10', '2015-08-09 01:29:16');
INSERT INTO `social_info` VALUES ('67', '1', '1', '2015-08-09 01:56:11.2', '2015-08-09 01:56:11.206', null, '2015-08-09 01:56:11', '2015-08-09 01:56:20');
INSERT INTO `social_info` VALUES ('68', '1', '1', '2015-08-09 01:57:31.364', '2015-08-09 01:57:31.37', null, '2015-08-09 01:57:31', '2015-08-09 01:57:42');
INSERT INTO `social_info` VALUES ('69', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 13:48:21', '2015-08-11 13:48:22');
INSERT INTO `social_info` VALUES ('70', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 13:49:01', '2015-08-11 13:49:01');
INSERT INTO `social_info` VALUES ('71', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 13:49:55', '2015-08-11 13:49:56');
INSERT INTO `social_info` VALUES ('72', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:20:07', '2015-08-11 15:20:28');
INSERT INTO `social_info` VALUES ('73', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:25:53', '2015-08-11 15:26:02');
INSERT INTO `social_info` VALUES ('74', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:28:03', '2015-08-11 15:28:10');
INSERT INTO `social_info` VALUES ('75', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:50:17', '2015-08-11 15:50:21');
INSERT INTO `social_info` VALUES ('76', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:51:23', '2015-08-11 15:51:28');
INSERT INTO `social_info` VALUES ('77', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 15:56:21', '2015-08-11 15:56:26');
INSERT INTO `social_info` VALUES ('78', '1', '1', '2015-08-09 16:00:00', null, null, '2015-08-11 16:41:56', '2015-08-11 16:42:03');

-- ----------------------------
-- Table structure for social_source
-- ----------------------------
DROP TABLE IF EXISTS `social_source`;
CREATE TABLE `social_source` (
  `source_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `source_type` text NOT NULL,
  PRIMARY KEY (`source_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of social_source
-- ----------------------------
INSERT INTO `social_source` VALUES ('1', '日历');
INSERT INTO `social_source` VALUES ('2', '短信');
INSERT INTO `social_source` VALUES ('3', '新浪微博');

-- ----------------------------
-- Table structure for temperature_sensor
-- ----------------------------
DROP TABLE IF EXISTS `temperature_sensor`;
CREATE TABLE `temperature_sensor` (
  `temperature_sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`temperature_sensor_id`),
  KEY `FK_has_temperature_sensor` (`box_id`),
  CONSTRAINT `FK_has_temperature_sensor` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temperature_sensor
-- ----------------------------
INSERT INTO `temperature_sensor` VALUES ('1', null);
INSERT INTO `temperature_sensor` VALUES ('2', '1');

-- ----------------------------
-- Table structure for temperature_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `temperature_sensor_data`;
CREATE TABLE `temperature_sensor_data` (
  `temperature_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature_sensor_id` int(11) DEFAULT NULL,
  `temperature_data` float NOT NULL,
  `temperature_data_collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`temperature_data_id`),
  KEY `FK_collect_temperature_data` (`temperature_sensor_id`),
  CONSTRAINT `FK_collect_temperature_data` FOREIGN KEY (`temperature_sensor_id`) REFERENCES `temperature_sensor` (`temperature_sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temperature_sensor_data
-- ----------------------------
INSERT INTO `temperature_sensor_data` VALUES ('1', '1', '32', '2015-08-06 17:00:38');
INSERT INTO `temperature_sensor_data` VALUES ('2', '1', '32', '2015-08-06 18:04:07');
INSERT INTO `temperature_sensor_data` VALUES ('3', '1', '32', '2015-08-03 14:20:33');
INSERT INTO `temperature_sensor_data` VALUES ('4', '1', '32', '2015-08-06 15:40:55');
INSERT INTO `temperature_sensor_data` VALUES ('5', '1', '32', '2015-08-03 15:20:33');
INSERT INTO `temperature_sensor_data` VALUES ('6', '2', '32', '2015-08-03 16:00:55');
INSERT INTO `temperature_sensor_data` VALUES ('7', '2', '32', '2015-08-03 16:13:29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_salt` varchar(30) NOT NULL,
  `user_hash` varchar(30) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `user_address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_address_latitude` float NOT NULL,
  `user_address_longitude` float NOT NULL,
  `user_address_detail` text NOT NULL,
  PRIMARY KEY (`user_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for water_heater
-- ----------------------------
DROP TABLE IF EXISTS `water_heater`;
CREATE TABLE `water_heater` (
  `water_heater_id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  `water_heater_rated_power` float DEFAULT NULL,
  PRIMARY KEY (`water_heater_id`),
  KEY `FK_contro_water_heater` (`box_id`),
  CONSTRAINT `FK_contro_water_heater` FOREIGN KEY (`box_id`) REFERENCES `box` (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_heater
-- ----------------------------
INSERT INTO `water_heater` VALUES ('1', '1', null);

-- ----------------------------
-- Table structure for water_heater_control_detail
-- ----------------------------
DROP TABLE IF EXISTS `water_heater_control_detail`;
CREATE TABLE `water_heater_control_detail` (
  `water_heater_control_id` int(11) NOT NULL AUTO_INCREMENT,
  `water_heater_id` int(11) DEFAULT NULL,
  `water_heater_real_time_decision_id` int(11) DEFAULT NULL,
  `water_heater_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `water_heater_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `water_temperature` float NOT NULL,
  `water_temperature_decision_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`water_heater_control_id`),
  KEY `FK_transfer_to_water_heater_control_detail2` (`water_heater_real_time_decision_id`),
  KEY `FK_water_heater_has_control_detail` (`water_heater_id`),
  CONSTRAINT `FK_transfer_to_water_heater_control_detail2` FOREIGN KEY (`water_heater_real_time_decision_id`) REFERENCES `water_heater_real_time_decision` (`water_heater_real_time_decision_id`),
  CONSTRAINT `FK_water_heater_has_control_detail` FOREIGN KEY (`water_heater_id`) REFERENCES `water_heater` (`water_heater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_heater_control_detail
-- ----------------------------

-- ----------------------------
-- Table structure for water_heater_demand
-- ----------------------------
DROP TABLE IF EXISTS `water_heater_demand`;
CREATE TABLE `water_heater_demand` (
  `water_heater_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `wearable_info_to_demand_id` int(11) DEFAULT NULL,
  `gps_info_to_demand_id` int(11) DEFAULT NULL,
  `social_activity_to_demand_id` int(11) DEFAULT NULL,
  `water_heater_run_time` time DEFAULT NULL,
  `water_heater_stop_time` time DEFAULT NULL,
  `water_heater_temperature` float DEFAULT NULL,
  `water_heater_temperature_delta` float DEFAULT NULL,
  `water_heater_demand_internal` float DEFAULT NULL,
  PRIMARY KEY (`water_heater_demand_id`),
  KEY `FK_gps_info_has_water_heater_demand_rule` (`gps_info_to_demand_id`),
  KEY `FK_social_activity_has_water_heater_demand_rule` (`social_activity_to_demand_id`),
  KEY `FK_wearable_info_has_water_heater_demand_rule` (`wearable_info_to_demand_id`),
  CONSTRAINT `FK_gps_info_has_water_heater_demand_rule` FOREIGN KEY (`gps_info_to_demand_id`) REFERENCES `gps_info_to_demand` (`gps_info_to_demand_id`),
  CONSTRAINT `FK_social_activity_has_water_heater_demand_rule` FOREIGN KEY (`social_activity_to_demand_id`) REFERENCES `social_activity_to_demand` (`social_activity_to_demand_id`),
  CONSTRAINT `FK_wearable_info_has_water_heater_demand_rule` FOREIGN KEY (`wearable_info_to_demand_id`) REFERENCES `wearable_info_to_demand` (`wearable_info_to_demand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_heater_demand
-- ----------------------------

-- ----------------------------
-- Table structure for water_heater_real_time_decision
-- ----------------------------
DROP TABLE IF EXISTS `water_heater_real_time_decision`;
CREATE TABLE `water_heater_real_time_decision` (
  `water_heater_real_time_decision_id` int(11) NOT NULL AUTO_INCREMENT,
  `water_heater_id` int(11) DEFAULT NULL,
  `real_time_decision_id` int(11) DEFAULT NULL,
  `water_heater_control_id` int(11) DEFAULT NULL,
  `water_heater_real_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `water_heater_real_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `water_heater_consume_average_energy` float DEFAULT NULL,
  `water_heater_decision_record_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`water_heater_real_time_decision_id`),
  KEY `FK_has_water_heater_real_time_decision` (`real_time_decision_id`),
  KEY `FK_transfer_to_water_heater_control_detail` (`water_heater_control_id`),
  KEY `FK_water_heater_has_decision_detail` (`water_heater_id`),
  CONSTRAINT `FK_has_water_heater_real_time_decision` FOREIGN KEY (`real_time_decision_id`) REFERENCES `real_time_decision` (`real_time_decision_id`),
  CONSTRAINT `FK_transfer_to_water_heater_control_detail` FOREIGN KEY (`water_heater_control_id`) REFERENCES `water_heater_control_detail` (`water_heater_control_id`),
  CONSTRAINT `FK_water_heater_has_decision_detail` FOREIGN KEY (`water_heater_id`) REFERENCES `water_heater` (`water_heater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_heater_real_time_decision
-- ----------------------------

-- ----------------------------
-- Table structure for water_heater_status
-- ----------------------------
DROP TABLE IF EXISTS `water_heater_status`;
CREATE TABLE `water_heater_status` (
  `water_heater_stauts_id` int(11) NOT NULL AUTO_INCREMENT,
  `water_heater_id` int(11) DEFAULT NULL,
  `water_heater_temperature` float NOT NULL,
  `is_controlled_by_user` tinyint(1) DEFAULT NULL,
  `water_heater_status_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_already_controlled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`water_heater_stauts_id`),
  KEY `FK_record_water_heater_status` (`water_heater_id`),
  CONSTRAINT `FK_record_water_heater_status` FOREIGN KEY (`water_heater_id`) REFERENCES `water_heater` (`water_heater_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_heater_status
-- ----------------------------
INSERT INTO `water_heater_status` VALUES ('1', '1', '37', '1', '2015-08-03 21:06:43', null);
INSERT INTO `water_heater_status` VALUES ('2', '1', '37', '1', '2015-08-03 21:12:58', null);
INSERT INTO `water_heater_status` VALUES ('3', '1', '37', '1', '2015-08-03 22:12:16', null);
INSERT INTO `water_heater_status` VALUES ('4', '1', '37', '1', '2015-08-03 22:12:32', null);
INSERT INTO `water_heater_status` VALUES ('5', '1', '37', '1', '2015-08-03 22:13:17', null);

-- ----------------------------
-- Table structure for wearable_device_info
-- ----------------------------
DROP TABLE IF EXISTS `wearable_device_info`;
CREATE TABLE `wearable_device_info` (
  `wearable_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `moving_type_id` int(11) DEFAULT NULL,
  `accelerated_speed_x` float DEFAULT NULL,
  `accelerated_speed_y` float DEFAULT NULL,
  `accelerated_speed_z` float DEFAULT NULL,
  `gyroscope_x` float DEFAULT NULL,
  `gyroscope_y` float DEFAULT NULL,
  `gyroscope_z` float DEFAULT NULL,
  `body_temperature` float NOT NULL,
  `heart_rate` float DEFAULT NULL,
  `speed` float DEFAULT NULL,
  `wearable_info_record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wearable_info_id`),
  KEY `FK_reflect_a_moving_status` (`moving_type_id`),
  CONSTRAINT `FK_reflect_a_moving_status` FOREIGN KEY (`moving_type_id`) REFERENCES `moving_status` (`moving_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wearable_device_info
-- ----------------------------
INSERT INTO `wearable_device_info` VALUES ('1', '1', '100', '100', '100', '200', '200', '200', '10', '100', '100', '2015-08-05 16:28:26');
INSERT INTO `wearable_device_info` VALUES ('2', '1', '100', '100', '100', '200', '200', '200', '10', '100', '100', '2015-08-05 16:28:27');

-- ----------------------------
-- Table structure for wearable_info_to_demand
-- ----------------------------
DROP TABLE IF EXISTS `wearable_info_to_demand`;
CREATE TABLE `wearable_info_to_demand` (
  `wearable_info_to_demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `moving_type_id` int(11) DEFAULT NULL,
  `heart_rate` float NOT NULL,
  `body_temperature` float NOT NULL,
  `wearable_info_begin_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`wearable_info_to_demand_id`),
  KEY `FK_moving_status_has_demand_rule` (`moving_type_id`),
  CONSTRAINT `FK_moving_status_has_demand_rule` FOREIGN KEY (`moving_type_id`) REFERENCES `moving_status` (`moving_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wearable_info_to_demand
-- ----------------------------
