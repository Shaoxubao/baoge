# Spring 事务传播行为测试初始化sql

CREATE TABLE `t_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(10) DEFAULT '' COMMENT '姓名',
  `balance` double(10,2) unsigned DEFAULT '0.00' COMMENT '账户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户信息';

CREATE TABLE `t_book` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `isbn` varchar(255) NOT NULL DEFAULT '' COMMENT '图书编码',
  `name` varchar(255) DEFAULT '' COMMENT '书名',
  `price` double(10,2) DEFAULT '0.00' COMMENT '书价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='图书信息';

CREATE TABLE `t_book_stock` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `isbn` varchar(255) NOT NULL DEFAULT '' COMMENT '图书编码',
  `stock` int(10) DEFAULT '0' COMMENT '图书库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='图书库存';

INSERT INTO `test`.`t_account` (`id`, `user_name`, `balance`) VALUES ('1', 'baoge', '100.00');

INSERT INTO `test`.`t_book_stock` (`id`, `isbn`, `stock`) VALUES ('1', '1001', '100');
INSERT INTO `test`.`t_book_stock` (`id`, `isbn`, `stock`) VALUES ('2', '1002', '100');

INSERT INTO `test`.`t_book` (`id`, `isbn`, `name`, `price`) VALUES ('1', '1001', 'spring技术内幕', '60.00');
INSERT INTO `test`.`t_book` (`id`, `isbn`, `name`, `price`) VALUES ('2', '1002', '高性能MySql', '50.00');
