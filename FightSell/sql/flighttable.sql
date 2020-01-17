/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : wbhz

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 12/01/2020 22:33:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flighttable
-- ----------------------------
DROP TABLE IF EXISTS `flighttable`;
CREATE TABLE `flighttable`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `flight_number` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `take_off_time` datetime(0) NOT NULL,
  `flying_time` int(10) NOT NULL,
  `start_place` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `end_place` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tickets` int(20) NOT NULL,
  `price` float NOT NULL,
  `alltickets` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
