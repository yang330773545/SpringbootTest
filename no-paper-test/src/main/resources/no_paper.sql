/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : no_paper

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-24 15:30:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) DEFAULT NULL,
  `anno_title` varchar(255) DEFAULT NULL,
  `anno_content` longtext,
  `logo_file_id` int(11) DEFAULT NULL,
  `background_file_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '1', '狗子', '狗子很可爱', '1', '1');
INSERT INTO `announcement` VALUES ('2', '1', '小猫', '小猫很可爱', '2', '3');
INSERT INTO `announcement` VALUES ('3', '3', '小鹿', '小鹿被大灰狼吃了', '4', '5');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '设备类型，1代表终端，2代表流媒体，3代表投影仪',
  `meeting_room_id` int(11) DEFAULT NULL COMMENT '会议室id',
  `device_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备名称',
  `device_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', '1', '1', '爆山大爷', '192.168.0.1');
INSERT INTO `device` VALUES ('2', '1', '1', '终端设备2', '192.168.0.2');
INSERT INTO `device` VALUES ('3', '1', '1', '终端设备3', '192.168.0.3');
INSERT INTO `device` VALUES ('4', '1', '1', '终端设备4', '192.168.0.4');
INSERT INTO `device` VALUES ('5', '1', '1', '终端设备5', '192.168.0.5');
INSERT INTO `device` VALUES ('6', '1', '1', '终端设备6', '192.168.0.6');
INSERT INTO `device` VALUES ('7', '1', '1', '终端设备7', '192.168.0.7');
INSERT INTO `device` VALUES ('8', '1', '1', '终端设备8', '192.168.0.8');
INSERT INTO `device` VALUES ('9', '1', '1', '终端设备9', '192.168.0.9');
INSERT INTO `device` VALUES ('10', '1', '1', '终端设备10', '192.168.0.10');
INSERT INTO `device` VALUES ('11', '2', '1', '流媒体1', '192.168.0.11');
INSERT INTO `device` VALUES ('12', '2', '1', '流媒体2', '192.168.0.12');
INSERT INTO `device` VALUES ('13', '2', '1', '流媒体3', '192.168.0.13');
INSERT INTO `device` VALUES ('14', '2', '1', '流媒体4', '192.168.0.14');
INSERT INTO `device` VALUES ('15', '3', '1', '投影仪1', '192.168.0.15');

-- ----------------------------
-- Table structure for election_question
-- ----------------------------
DROP TABLE IF EXISTS `election_question`;
CREATE TABLE `election_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1代表选举 2代表问卷',
  `election_content` varchar(255) DEFAULT '' COMMENT '内容',
  `option_1` varchar(255) DEFAULT '' COMMENT '选项1',
  `option_2` varchar(255) DEFAULT NULL,
  `option_3` varchar(255) DEFAULT NULL,
  `option_4` varchar(255) DEFAULT NULL,
  `option_5` varchar(255) DEFAULT NULL,
  `kind` int(1) DEFAULT NULL COMMENT '1多选 2单选 3五选四 4五选三 5五选二 6三选二',
  `status` int(1) DEFAULT '1' COMMENT '1未发起 2进行中 3已结束',
  `register` int(1) DEFAULT '1' COMMENT '1非记名 2记名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of election_question
-- ----------------------------
INSERT INTO `election_question` VALUES ('1', '1', '1', '我是谁', '张三', '李四', '王五', '不知道', '狗子', '1', '1', '1');
INSERT INTO `election_question` VALUES ('2', '1', '2', '问卷啊', '哈', '哈哈', '哈哈哈', '哈哈哈哈', '哈哈哈哈哈', '1', '1', '1');
INSERT INTO `election_question` VALUES ('3', '1', '1', '狗子去哪儿', '不', '值', '到', '啊', '哈', '1', '2', '2');

-- ----------------------------
-- Table structure for ele_que_vote
-- ----------------------------
DROP TABLE IF EXISTS `ele_que_vote`;
CREATE TABLE `ele_que_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ele_que_id` int(11) DEFAULT NULL COMMENT '问卷or选举id',
  `participant_id` int(11) DEFAULT NULL COMMENT '参会人id',
  `choose_1` int(1) DEFAULT '0' COMMENT '选项1   1选',
  `choose_2` int(1) DEFAULT '0',
  `choose_3` int(1) DEFAULT '0',
  `choose_4` int(1) DEFAULT '0',
  `choose_5` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ele_que_vote
-- ----------------------------
INSERT INTO `ele_que_vote` VALUES ('1', '1', '1', '1', '1', '0', '0', '0');
INSERT INTO `ele_que_vote` VALUES ('2', '1', '2', '1', '0', '1', '1', '0');
INSERT INTO `ele_que_vote` VALUES ('3', '1', '3', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `path` varchar(255) DEFAULT NULL COMMENT '存储地址',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '1系统背景文件 2系统logo文件 3会议资料 4共享资料 5批注资料',
  `meeting_id` int(11) DEFAULT NULL,
  `upload_participant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name_INDEX` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '云晫背景', 'E:\\no_paper_file/xxx.jpg', '1', null, null);
INSERT INTO `file` VALUES ('2', '云晫logo', 'E:\\no_paper_file/xxx.jpg', '2', null, null);
INSERT INTO `file` VALUES ('3', '会议文件1', 'E:\\no_paper_file/xxx.jpg', '3', '1', null);
INSERT INTO `file` VALUES ('4', '会议文件2', 'E:\\no_paper_file/xxx.jpg', '3', '1', null);
INSERT INTO `file` VALUES ('5', '共享文件1', 'E:\\no_paper_file/xxx.jpg', '4', '1', '1');
INSERT INTO `file` VALUES ('6', '共享文件2', 'E:\\no_paper_file/xxx.jpg', '4', '1', '2');
INSERT INTO `file` VALUES ('8', 'config.ini', 'E://no_paper_fileb7285de0-1326-458b-9392-184ed4c48317config.ini', '1', null, null);
INSERT INTO `file` VALUES ('10', 'xxx.py', 'E://no_paper_filec852b018-af5f-47b8-93aa-e01d194487a7xxx.py', '3', '1', null);
INSERT INTO `file` VALUES ('11', 'wifiadb.txt', 'E://no_paper_filec69ba31a-484c-473b-998e-379a72830be1wifiadb.txt', '3', '1', null);
INSERT INTO `file` VALUES ('12', '阿斯顿.txt', 'E://no_paper_filee8e97cc6-d9c2-4e5a-a9bb-feb87bd60b0b阿斯顿.txt', '1', null, null);
INSERT INTO `file` VALUES ('13', '猴儿.txt', 'E://no_paper_file/764c99cc-3c2a-4804-b550-8f5290639ec3猴儿.txt', '1', null, null);

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `appointment_persion` varchar(255) DEFAULT NULL COMMENT '会议预约人',
  `sign_back_id` int(11) DEFAULT NULL COMMENT '签到页背景文件id',
  `projection_back_id` int(11) DEFAULT NULL COMMENT '投影页背景文件id',
  `table_back_id` int(11) DEFAULT NULL COMMENT '桌牌背景文件id',
  `sign_logo_id` int(11) DEFAULT NULL COMMENT '签到页面logo文件id',
  `projection_logo_id` int(11) DEFAULT NULL COMMENT '投影页背景图id',
  `meeting_room_id` int(11) DEFAULT NULL COMMENT '会议室id',
  `way_of_sign` int(1) DEFAULT NULL COMMENT '签到方式 0 直接签到 1扫码签到',
  `confidentiality` int(1) DEFAULT NULL COMMENT '该会议是否保密 0为不保密 1为保密',
  `theme` int(1) DEFAULT NULL COMMENT '内置风格',
  `schema` longtext COMMENT '议程',
  `url` varchar(255) DEFAULT NULL COMMENT '默认url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES ('1', '2019-05-27 18:08:00', '2019-05-27 22:08:13', '张三', '1', '2', '3', '4', '5', '6', '0', '0', '1', '① 注重挖掘背景信息。提及相关项目时，必要时增加一两句话语交代背景信息，让未参会人员、上级领导能够理解事情的来龙去脉。    ② 注重领悟领导意图。会议中务必记录领导说话的关键词，纪要最关键的是传递大领导下达了什么信息，但又不能直白的说了XXX领导说了什么。一般以会议强调、会议认为、会议指出代替，同时要善于利用、换用领导原话，善于拓展领导原话。    ③ 注重行文逻辑主次。沥青会议思路，有会议议程按议程展开。但有些领导可能思路比较跳跃，不一定会按照节奏出来，中间还包括插科打诨、训话、打断等等，因此纪要务必不能按时间先后，不能记流水账，切记会议纪要不是记录，要注重语言的提炼和逻辑的重组。    ④ 注重语言风格。会议纪要一般不能过于直白，但也不宜虚话连篇。同时注意用词严谨，官方话要有，有时候还需无中生有；领导原话、关键词句要兼顾，如何把两者结合好，非常考验技巧。', 'www.baidu.com');

-- ----------------------------
-- Table structure for meeting_participant_device
-- ----------------------------
DROP TABLE IF EXISTS `meeting_participant_device`;
CREATE TABLE `meeting_participant_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议id',
  `participant_id` int(11) DEFAULT NULL COMMENT '参会人员id',
  `device_id` int(11) DEFAULT NULL COMMENT '设备id',
  `role` int(1) DEFAULT NULL COMMENT '角色1代表主讲人，2代表参会人，3代表服务人员',
  `sign` int(1) DEFAULT '1' COMMENT '是否签到 1未签到',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of meeting_participant_device
-- ----------------------------
INSERT INTO `meeting_participant_device` VALUES ('1', '1', '1', '1', '1', '1');
INSERT INTO `meeting_participant_device` VALUES ('2', '1', '2', '2', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('3', '1', '3', '3', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('4', '1', '4', '4', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('5', '1', '5', '5', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('6', '1', '6', '6', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('7', '1', '7', '7', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('8', '1', '8', '8', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('9', '1', '9', '9', '2', '1');
INSERT INTO `meeting_participant_device` VALUES ('10', '1', '10', '10', '2', '2');

-- ----------------------------
-- Table structure for meeting_room
-- ----------------------------
DROP TABLE IF EXISTS `meeting_room`;
CREATE TABLE `meeting_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会议室名称',
  `cen_ip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of meeting_room
-- ----------------------------
INSERT INTO `meeting_room` VALUES ('1', '云晫会议室1', '192.168.0.1', '3301');
INSERT INTO `meeting_room` VALUES ('2', '山顶洞会议室', '192.168.0.2', '3302');
INSERT INTO `meeting_room` VALUES ('4', '爆山大爷1', '123456', '3303');
INSERT INTO `meeting_room` VALUES ('5', '爆山', '1234', '狗楼啊');

-- ----------------------------
-- Table structure for participant
-- ----------------------------
DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '参会人的姓名',
  `sex` int(1) DEFAULT NULL COMMENT '参会人性别，2男，1女',
  `position` varchar(255) DEFAULT NULL COMMENT '参会人职位',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '电话号码',
  `wx_code` varchar(255) DEFAULT NULL COMMENT '微信号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of participant
-- ----------------------------
INSERT INTO `participant` VALUES ('1', '张三', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('2', '李四', '1', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('3', '王五', '1', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('4', '赵六', '1', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('5', '钱七', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('6', '孙八', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('7', '大娃', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('8', '二娃', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('9', '三娃', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('10', '四娃', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('11', '五娃', '2', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('12', '六娃', '1', '职员', '12345678912', null);
INSERT INTO `participant` VALUES ('13', '七娃', '1', '职员', '12345678913', null);

-- ----------------------------
-- Table structure for participant_vote
-- ----------------------------
DROP TABLE IF EXISTS `participant_vote`;
CREATE TABLE `participant_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `vote_opinion` int(1) DEFAULT NULL COMMENT '1为同意 2为不同意 3为弃权',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of participant_vote
-- ----------------------------
INSERT INTO `participant_vote` VALUES ('1', '1', '1', '1');
INSERT INTO `participant_vote` VALUES ('2', '1', '2', '1');
INSERT INTO `participant_vote` VALUES ('3', '1', '3', '2');
INSERT INTO `participant_vote` VALUES ('4', '1', '4', '2');
INSERT INTO `participant_vote` VALUES ('5', '1', '5', '3');
INSERT INTO `participant_vote` VALUES ('6', '2', '2', '2');
INSERT INTO `participant_vote` VALUES ('7', '1', '2', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0超管 1秘书',
  `name` varchar(255) DEFAULT NULL COMMENT '所属人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'root', '0', '爆山大爷');
INSERT INTO `user` VALUES ('2', 'admin', 'admin123', '1', '李四');
INSERT INTO `user` VALUES ('4', 'admin1', 'admin1', '1', '张三');

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) DEFAULT NULL,
  `vote_type` int(1) DEFAULT NULL COMMENT '1代表记名 2非记名',
  `vote_information` varchar(255) DEFAULT NULL,
  `vote_status` int(1) DEFAULT '1' COMMENT '投票状态 1 未发起 2进行中 3已结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '1', '1', '你大爷', '1');
INSERT INTO `vote` VALUES ('2', '1', '1', '扎杂活、', '1');
INSERT INTO `vote` VALUES ('3', '1', '2', '哈哈哈', '1');
