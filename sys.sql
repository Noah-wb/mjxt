/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : sys

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2018-07-23 10:52:56
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_device`
-- ----------------------------
DROP TABLE IF EXISTS `tb_device`;
CREATE TABLE `tb_device` (
  `dev_num` varchar(50) DEFAULT NULL,
  `dev_name` varchar(50) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_device
-- ----------------------------
INSERT INTO tb_device VALUES ('1000', '设备一', '555');

-- ----------------------------
-- Table structure for `tb_emp`
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp`;
CREATE TABLE `tb_emp` (
  `emp_num` varchar(30) DEFAULT NULL,
  `emp_name` varchar(30) DEFAULT NULL,
  `emp_sex` varchar(6) DEFAULT NULL,
  `emp_age` int(11) DEFAULT NULL,
  `emp_dept` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_emp
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_emp_dev`
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp_dev`;
CREATE TABLE `tb_emp_dev` (
  `dev_num` varchar(50) DEFAULT NULL,
  `emp_num` varchar(50) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_emp_dev
-- ----------------------------
INSERT INTO tb_emp_dev VALUES ('1000', '1000', '事实上');
