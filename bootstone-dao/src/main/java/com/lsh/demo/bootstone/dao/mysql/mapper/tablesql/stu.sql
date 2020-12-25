CREATE TABLE `stu` (
  `name` char(255) NOT NULL,
  `id` char(255) NOT NULL,
  `age` char(255) DEFAULT NULL COMMENT '年龄',
  `grade` varchar(16) DEFAULT NULL COMMENT '年级',
  `address` varchar(16) DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;