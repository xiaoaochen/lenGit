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

 Date: 12/01/2020 22:34:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `order_number` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `start_place` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `end_place` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `price` float NOT NULL,
  `buy_time` datetime(0) NOT NULL,
  `take_off_time` datetime(0) NOT NULL,
  `return_ticket_time` datetime(0) NOT NULL,
  `user_name` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `flight_number` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `seat` int(10) NOT NULL DEFAULT 0,
  `can_used` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未使用',
  PRIMARY KEY (`id`, `flight_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
