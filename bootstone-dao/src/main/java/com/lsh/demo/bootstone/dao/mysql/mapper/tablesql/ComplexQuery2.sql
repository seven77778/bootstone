DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(16) DEFAULT NULL COMMENT '酒店名称',
  `hotel_id` int(16) DEFAULT NULL COMMENT '酒店ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1', '范县大酒店', '1001');
INSERT INTO `hotel` VALUES ('2', '张庄大酒店', '1002');
INSERT INTO `hotel` VALUES ('3', '李庄大酒店', '1003');

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `room_type` varchar(16) DEFAULT NULL COMMENT '房型code',
  `hotel_id` int(16) DEFAULT NULL COMMENT '酒店ID',
  `date` date DEFAULT NULL COMMENT '入住日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', 'type123', '1001', '2020-12-22');
INSERT INTO `orders` VALUES ('2', 'type123', '1001', '2020-12-22');
INSERT INTO `orders` VALUES ('3', 'type123', '1001', null);
INSERT INTO `orders` VALUES ('4', 'type124', '1001', null);
INSERT INTO `orders` VALUES ('5', 'type123', '1002', null);
INSERT INTO `orders` VALUES ('6', 'type124', '1002', null);
INSERT INTO `orders` VALUES ('7', 'type124', '1002', null);
INSERT INTO `orders` VALUES ('8', 'type124', '1002', null);
INSERT INTO `orders` VALUES ('9', 'type125', '1003', null);
INSERT INTO `orders` VALUES ('10', 'type127', '1003', null);
INSERT INTO `orders` VALUES ('11', 'type127', '1003', null);

DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `room_type` varchar(16) DEFAULT NULL COMMENT '房型code',
  `type_name` varchar(16) DEFAULT NULL COMMENT '房型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES ('1', 'type123', '大床房');
INSERT INTO `room_type` VALUES ('2', 'type124', '双床房');
INSERT INTO `room_type` VALUES ('3', 'type125', '三人房');
INSERT INTO `room_type` VALUES ('4', 'type126', '无敌海景');
INSERT INTO `room_type` VALUES ('5', 'type127', '总统套房');