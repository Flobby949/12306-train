/*
 Navicat Premium Data Transfer

 Source Server         : train
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : rm-cn-4xl3e1zjr00016fo.rwlb.rds.aliyuncs.com:3306
 Source Schema         : train_member

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/12/2023 17:06:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint NOT NULL COMMENT 'id',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobile_unique`(`mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (2, '13951901489');
INSERT INTO `member` VALUES (1, '13951905171');
INSERT INTO `member` VALUES (1700467606065, '18962521753');
INSERT INTO `member` VALUES (1700467814047, '18962521754');

-- ----------------------------
-- Table structure for passenger
-- ----------------------------
DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger`  (
  `id` bigint NOT NULL COMMENT 'id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '旅客类型|枚举[PassengerTypeEnum]',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_id_index`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '乘车人' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of passenger
-- ----------------------------
INSERT INTO `passenger` VALUES (1726849324245192704, 1, 'flobby', '320506200108282222', '1', '2023-11-21 14:25:36.820', '2023-11-21 14:25:36.820');
INSERT INTO `passenger` VALUES (1726850585032003584, 1, 'flobby', '320506200108282222', '1', '2023-11-21 14:30:37.460', '2023-11-21 14:30:37.460');
INSERT INTO `passenger` VALUES (1726853328748548096, 1700467606065, 'flobby', '320506200108282222', '1', '2023-11-21 14:41:31.613', '2023-11-21 14:41:31.613');
INSERT INTO `passenger` VALUES (1726888915601854464, 1700467606065, 'ddd', '123', '1', '2023-11-21 17:02:56.179', '2023-11-21 17:02:56.179');

-- ----------------------------
-- Table structure for programmer
-- ----------------------------
DROP TABLE IF EXISTS `programmer`;
CREATE TABLE `programmer`  (
  `id` bigint NOT NULL COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `homepage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主页',
  `skill` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技能',
  `create_time` date NULL DEFAULT NULL,
  `update_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of programmer
-- ----------------------------
INSERT INTO `programmer` VALUES (1, '16422802@qq.com', '13951905171', 'mqxu', 'https://i2.100024.xyz/2023/11/24/i88h1t.webp', '开到荼蘼', 'https://mqxu.top', 'Java,Spring,Vue', '2018-10-10', '2023-11-24');
INSERT INTO `programmer` VALUES (2, '1943138087@qq.com', '17379724500', 'ysh', 'https://i2.100024.xyz/2023/11/24/igqykb.webp', 'handsome-whf', 'https://gitee.com/yuanshenghao', 'Java,springcloud,Vue,kyrie Irving,pual ', '2018-10-10', '2023-11-24');
INSERT INTO `programmer` VALUES (3, '2283582431@qq.com', '15729611641', 'ahang', 'https://i2.100024.xyz/2023/11/24/i87yod.webp', '阿航', 'https://github.com/sunyuhang233', 'Java,SpringBoot,React,Vue', '2019-10-09', '2023-11-24');
INSERT INTO `programmer` VALUES (4, '2975800991@qq.com', '18951307053', 'hjh', 'https://w.wallhaven.cc/full/x6/wallhaven-x6k9j3.jpg', '[]', 'https://github.com/legalHighest', 'Java,Spring,Vue', '2023-11-24', '2023-11-24');
INSERT INTO `programmer` VALUES (5, '2541226493@qq.com', '18962521753', 'flobby', 'https://flobby.oss-cn-shenzhen.aliyuncs.com/avatar1/zJR8wh8mKE.jpg', '万事胜意', 'https://github.com/Flobby949', 'Java,Vue', '2023-01-01', '2023-01-01');
INSERT INTO `programmer` VALUES (6, '1332869575@qq.com', '15995376581', 'whf', 'https://king-hf-bucket.oss-cn-shanghai.aliyuncs.com/avatar/me.jpg', '饮茶先', 'https://github.com/Hqk-rgb', 'LOL', '2016-10-20', '2023-11-24');
INSERT INTO `programmer` VALUES (7, '1716186890@qq.com', '18151683757', 'lxyi', 'https://f.afbkw.cn/mdRm2Y', 'lxy', 'https://github.com/Lxinyi667', 'Java,Vue', '2016-10-20', '2023-11-24');
INSERT INTO `programmer` VALUES (8, '1729298243@qq.com', '18120190176', 'kjwang', 'https://emos-1317822465.cos.ap-nanjing.myqcloud.com/photo%2Fboy001.png', '是肥猫猫啊', 'https://www.yuque.com/kjwang', 'Java,Vue,SpringBoot,Uniapp', '2023-11-24', '2023-11-24');
INSERT INTO `programmer` VALUES (9, '2532566074@qq.com', '17751111829', 'litao', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/55bb985e-37bc-4c9d-8e56-033992f67172.jpg', 'wuli涛涛', 'https://github.com/litao1829', 'Java,SpringBoot,Vue,Uniapp', '2023-11-20', '2023-11-24');
INSERT INTO `programmer` VALUES (10, 'cabudon@qq.com', '17354702432', 'th', 'https://z1.ax1x.com/2023/11/24/piw3A76.jpg', '逍遥游南浦', 'https://github.com/cabudon', 'java,vue', '2019-05-05', '2023-06-06');
INSERT INTO `programmer` VALUES (12, '656482519@qq.com', '18652487935', 'wml', 'https://pic.imgdb.cn/item/6523f17dc458853aef61feba.png', 'wml', 'https://www.yuque.com/summeray-cm2lu', 'Java,SpringBoot,Vue,Uniapp', '2023-11-24', '2023-11-24');
INSERT INTO `programmer` VALUES (13, '1090060206@qq.com', '15351958202', 'cjq', 'https://pic.imgdb.cn/item/65600566c458853aef16e9bd.jpg', 'C77', 'https://github.com/ChenJiaQi0', 'Java,SpringBoot,Vue', '2023-11-24', '2023-11-24');
INSERT INTO `programmer` VALUES (14, '3179716692@qq.com', '13270251015', 'xl', 'https://w.wallhaven.cc/full/2y/wallhaven-2y6rgy.png', 'xl', 'https://blog.csdn.net/qq_45902401', 'Java,Vue', '2022-11-11', '2023-11-24');
INSERT INTO `programmer` VALUES (15, '1760349255@qq.com', '15952201947', 'swback', 'https://public-cdn-oss.mosoteach.cn/avatar/2023/43/7fe99803c9eb5be9762025514be2501e.jpg', '1', 'https://www.swback.cn/', 'CTF', '2023-11-24', '2023-11-24');
INSERT INTO `programmer` VALUES (16, '1071852424@qq.com', '18852277447', 'stx', 'https://public-cdn-oss.mosoteach.cn/avatar/2023/17/e0f53ecd0e954e493f76bcf05c3d2741.jpg', '怀抱', 'https://stx.swback.cn', 'Java,SpringBoot,Vue', '2022-11-22', '2023-11-24');
INSERT INTO `programmer` VALUES (17, '1216634507@qq.com', '18761886023', 'lsc', 'https://public-cdn-oss.mosoteach.cn/avatar/2023/03/21a14d3038130cdc475660aec09be3f2.jpg?v=1677922967&x-oss-process=style/s300x300', 'lsc', 'http://lscwebsite.top', 'Java,Vue', '2021-10-20', '2023-11-14');

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `id` bigint NOT NULL COMMENT 'id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `passenger_id` bigint NOT NULL COMMENT '乘客id',
  `passenger_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘客姓名',
  `date` date NOT NULL COMMENT '日期',
  `train_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车次编号',
  `carriage_index` int NOT NULL COMMENT '厢序',
  `row` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '排号|01, 02',
  `col` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '列号|枚举[SeatColEnum]',
  `start` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出发站',
  `start_time` time(0) NOT NULL COMMENT '出发时间',
  `end` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '到达站',
  `end_time` time(0) NOT NULL COMMENT '到站时间',
  `seat_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '座位类型|枚举[SeatTypeEnum]',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_id_index`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车票' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (1729398025681506304, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '01', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:14.639', '2023-11-28 15:13:14.639');
INSERT INTO `ticket` VALUES (1729398028168728576, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '01', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:15.234', '2023-11-28 15:13:15.234');
INSERT INTO `ticket` VALUES (1729398031759052800, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '01', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:16.089', '2023-11-28 15:13:16.089');
INSERT INTO `ticket` VALUES (1729398033524854784, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '01', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:16.511', '2023-11-28 15:13:16.511');
INSERT INTO `ticket` VALUES (1729398034762174464, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '01', 'F', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:16.806', '2023-11-28 15:13:16.806');
INSERT INTO `ticket` VALUES (1729398036049825792, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '02', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:17.113', '2023-11-28 15:13:17.113');
INSERT INTO `ticket` VALUES (1729398037282951168, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '02', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:17.407', '2023-11-28 15:13:17.407');
INSERT INTO `ticket` VALUES (1729398038386053120, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '02', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:17.670', '2023-11-28 15:13:17.670');
INSERT INTO `ticket` VALUES (1729398039682093056, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '02', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:17.979', '2023-11-28 15:13:17.979');
INSERT INTO `ticket` VALUES (1729398042056069120, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '02', 'F', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:18.545', '2023-11-28 15:13:18.545');
INSERT INTO `ticket` VALUES (1729398043272417280, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '03', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:18.835', '2023-11-28 15:13:18.835');
INSERT INTO `ticket` VALUES (1729398044627177472, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '03', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:19.158', '2023-11-28 15:13:19.158');
INSERT INTO `ticket` VALUES (1729398046204235776, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '03', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:19.534', '2023-11-28 15:13:19.534');
INSERT INTO `ticket` VALUES (1729398047382835200, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '03', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 15:13:19.814', '2023-11-28 15:13:19.814');
INSERT INTO `ticket` VALUES (1729425108319211520, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'G1', 1, '01', 'A', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-11-28 17:00:51.605', '2023-11-28 17:00:51.605');
INSERT INTO `ticket` VALUES (1729425684759187456, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'G1', 1, '01', 'C', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-11-28 17:03:09.078', '2023-11-28 17:03:09.078');
INSERT INTO `ticket` VALUES (1729425688190128128, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'G1', 1, '01', 'D', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-11-28 17:03:09.896', '2023-11-28 17:03:09.896');
INSERT INTO `ticket` VALUES (1729426482926850048, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'G1', 1, '01', 'F', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-11-28 17:06:19.334', '2023-11-28 17:06:19.334');
INSERT INTO `ticket` VALUES (1729427051145990144, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'G1', 1, '02', 'A', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-11-28 17:08:34.850', '2023-11-28 17:08:34.850');
INSERT INTO `ticket` VALUES (1729427644988133376, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '03', 'F', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:56.434', '2023-11-28 17:10:56.434');
INSERT INTO `ticket` VALUES (1729427647915757568, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '04', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:57.132', '2023-11-28 17:10:57.132');
INSERT INTO `ticket` VALUES (1729427649467650048, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '04', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:57.502', '2023-11-28 17:10:57.502');
INSERT INTO `ticket` VALUES (1729427650923073536, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '04', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:57.848', '2023-11-28 17:10:57.848');
INSERT INTO `ticket` VALUES (1729427652881813504, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '04', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:58.316', '2023-11-28 17:10:58.316');
INSERT INTO `ticket` VALUES (1729427653997498368, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '04', 'F', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:58.582', '2023-11-28 17:10:58.582');
INSERT INTO `ticket` VALUES (1729427655155126272, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '05', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:58.858', '2023-11-28 17:10:58.858');
INSERT INTO `ticket` VALUES (1729427656329531392, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '05', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:10:59.138', '2023-11-28 17:10:59.138');
INSERT INTO `ticket` VALUES (1729427685005987840, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '05', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:11:05.975', '2023-11-28 17:11:05.975');
INSERT INTO `ticket` VALUES (1729427686591434752, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '05', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:11:06.352', '2023-11-28 17:11:06.352');
INSERT INTO `ticket` VALUES (1729427687795200000, 1700467606065, 1726853328748548096, 'flobby', '2023-11-29', 'D2', 2, '05', 'F', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-11-28 17:11:06.639', '2023-11-28 17:11:06.639');
INSERT INTO `ticket` VALUES (1730400629760856064, 1700467606065, 1726888915601854464, 'ddd', '2023-12-02', 'D2', 1, '01', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '1', '2023-12-01 09:37:14.074', '2023-12-01 09:37:14.074');
INSERT INTO `ticket` VALUES (1730400630503247872, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 1, '01', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '1', '2023-12-01 09:37:14.258', '2023-12-01 09:37:14.258');
INSERT INTO `ticket` VALUES (1730402018117423104, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 1, '01', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '1', '2023-12-01 09:42:45.090', '2023-12-01 09:42:45.090');
INSERT INTO `ticket` VALUES (1730402943640932352, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 1, '01', 'A', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-12-01 09:46:25.753', '2023-12-01 09:46:25.753');
INSERT INTO `ticket` VALUES (1730403093209812992, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 2, '01', 'A', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-12-01 09:47:01.412', '2023-12-01 09:47:01.412');
INSERT INTO `ticket` VALUES (1730404333436145664, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 2, '01', 'B', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-12-01 09:51:57.106', '2023-12-01 09:51:57.106');
INSERT INTO `ticket` VALUES (1730405071134527488, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 2, '01', 'C', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-12-01 09:54:52.986', '2023-12-01 09:54:52.986');
INSERT INTO `ticket` VALUES (1730405476992159744, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'D2', 2, '01', 'D', '宜兴站', '16:00:00', '湖州站', '17:00:00', '2', '2023-12-01 09:56:29.750', '2023-12-01 09:56:29.750');
INSERT INTO `ticket` VALUES (1730408431183073280, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 2, '01', 'C', '南京站', '12:20:00', '徐州站', '14:00:00', '2', '2023-12-01 10:08:14.084', '2023-12-01 10:08:14.084');
INSERT INTO `ticket` VALUES (1730409800019349504, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 1, '01', 'D', '南京站', '12:20:00', '徐州站', '14:00:00', '1', '2023-12-01 10:13:40.441', '2023-12-01 10:13:40.441');
INSERT INTO `ticket` VALUES (1730409955737079808, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 2, '01', 'F', '南京站', '12:20:00', '徐州站', '14:00:00', '2', '2023-12-01 10:14:17.567', '2023-12-01 10:14:17.567');
INSERT INTO `ticket` VALUES (1730414495664312320, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 2, '01', 'A', '徐州站', '14:10:00', '天津站', '18:00:00', '2', '2023-12-01 10:32:19.970', '2023-12-01 10:32:19.970');
INSERT INTO `ticket` VALUES (1730414871180349440, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 1, '01', 'C', '南京站', '12:20:00', '天津站', '18:00:00', '1', '2023-12-01 10:33:49.499', '2023-12-01 10:33:49.499');
INSERT INTO `ticket` VALUES (1730415652449161216, 1700467606065, 1726888915601854464, 'ddd', '2023-12-02', 'G1', 1, '01', 'D', '徐州站', '14:10:00', '天津站', '18:00:00', '1', '2023-12-01 10:36:55.769', '2023-12-01 10:36:55.769');
INSERT INTO `ticket` VALUES (1730452592351383552, 1700467606065, 1726888915601854464, 'ddd', '2023-12-02', 'G1', 1, '01', 'A', '南京站', '12:20:00', '徐州站', '14:00:00', '1', '2023-12-01 13:03:42.923', '2023-12-01 13:03:42.923');
INSERT INTO `ticket` VALUES (1730452593118941184, 1700467606065, 1726853328748548096, 'flobby', '2023-12-02', 'G1', 1, '02', 'A', '南京站', '12:20:00', '徐州站', '14:00:00', '1', '2023-12-01 13:03:43.111', '2023-12-01 13:03:43.111');

SET FOREIGN_KEY_CHECKS = 1;
