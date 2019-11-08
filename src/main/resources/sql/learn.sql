/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : learn

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 08/11/2019 13:36:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `article_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章类型id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` longblob NULL COMMENT '文档内容',
  `text` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '文档文本',
  `html` longblob NULL COMMENT 'html文档',
  `modifytime` datetime(0) NULL DEFAULT NULL COMMENT '文档修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文档作者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('001', '8cc5a681e8a94e138b791d6d8ea413cc', 'bootstrap', 0x626F6F747374726170, 'bootstrap', 0x626F6F747374726170, '2019-07-22 14:16:44', NULL);
INSERT INTO `article` VALUES ('552eace4d4ea480ba1c478c84f3f0755', '5631bceab07748edb81bd980e135ca29', '测试新增文章', 0x23E7ACACE4B880E6ACA1E6ADA3E5BC8FE6B58BE8AF95E696B0E5A29EE69687E6A1A3, '第一次正式测试新增文档', 0x3C68312069643D2268312D75374232437534453030753642323175364236337535463046753644344275384244357536354230753538394575363538377536383633223E3C61206E616D653D22E7ACACE4B880E6ACA1E6ADA3E5BC8FE6B58BE8AF95E696B0E5A29EE69687E6A1A32220636C6173733D227265666572656E63652D6C696E6B223E3C2F613E3C7370616E20636C6173733D226865616465722D6C696E6B206F637469636F6E206F637469636F6E2D6C696E6B223E3C2F7370616E3EE7ACACE4B880E6ACA1E6ADA3E5BC8FE6B58BE8AF95E696B0E5A29EE69687E6A1A33C2F68313E, '2019-11-01 09:18:33', NULL);

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章类型',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章类型描述',
  `category_pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父类型id',
  `flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES ('5631bceab07748edb81bd980e135ca29', '后端', '后端', '86913445cf984bb2aed8bee9549eb218', '1');
INSERT INTO `article_category` VALUES ('86913445cf984bb2aed8bee9549eb218', 'java', 'java', 'ROOT', '1');
INSERT INTO `article_category` VALUES ('8cc5a681e8a94e138b791d6d8ea413cc', '前端', '前端', '86913445cf984bb2aed8bee9549eb218', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `menu_pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单描述',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'url地址',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标class',
  `menu_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否启用：1是，0否',
  `menu_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('04b4d3873fb64bb58676af1ed8084b17', '个人信息', '96d5ebddc89d4ea4ad6d546f4852fcce', '维护当前登录人的个人基本信息', '', '', '1', '1');
INSERT INTO `menu` VALUES ('0bcdfcf7f04d4788b200dc68774845e2', '菜单管理', '15d93311e5c6457d8db381770efa7a54', '', '/menu/showMenuPage', '', '1', '1');
INSERT INTO `menu` VALUES ('15d93311e5c6457d8db381770efa7a54', '菜单管理', 'ROOT', '菜单管理', '', '', '1', '1');
INSERT INTO `menu` VALUES ('1e688dc5203945e2b569fca7889db48c', '网站用户', '96d5ebddc89d4ea4ad6d546f4852fcce', '展示网站所有的用户', '', '', '1', '2');
INSERT INTO `menu` VALUES ('372501ef70ce444097f613ecfca4947f', 'swaager接口监控', '6b40f38966bd48e78625529804191c23', 'swaager接口监控', '/swagger-ui.html', '', '1', '2');
INSERT INTO `menu` VALUES ('400c8573614047669964eb60d63de5e3', '权限管理', '96d5ebddc89d4ea4ad6d546f4852fcce', '权限管理', '', '', '1', '4');
INSERT INTO `menu` VALUES ('4ea5d5a720454a6db8b5f6efdd1038fc', 'icon图标管理', '15d93311e5c6457d8db381770efa7a54', '', '', '', '1', '2');
INSERT INTO `menu` VALUES ('5118f2fe03a84c7391d5c64dcc8edfee', '数据库连接监控', '6b40f38966bd48e78625529804191c23', '数据库连接监控', '/druid', '', '1', '1');
INSERT INTO `menu` VALUES ('6b40f38966bd48e78625529804191c23', '安全监控', 'ROOT', '安全监控', '', '', '1', '10');
INSERT INTO `menu` VALUES ('7dccb7673176444ab2d6cb6d379b3972', '文章管理', 'ROOT', '文章管理', '', '', '1', '2');
INSERT INTO `menu` VALUES ('894c9f86e57b4016bd967e0c1b521e13', '系统参数配置', 'c1bab0a2fb364985968cd375e9739149', '系统参数配置', '', '', '1', '1');
INSERT INTO `menu` VALUES ('95460e713c4246e79a1ee6a971b2d872', '文章分类', '7dccb7673176444ab2d6cb6d379b3972', '文章类型', '/article/category/showCategoryPage', '', '1', '1');
INSERT INTO `menu` VALUES ('96d5ebddc89d4ea4ad6d546f4852fcce', '用户中心', 'ROOT', '维护人员信息', '', '', '1', '3');
INSERT INTO `menu` VALUES ('a98c7a9911ae4169ba8e445f68becf9d', '首页', 'ROOT', '首页', '/index/showHomePage', '', '1', '0');
INSERT INTO `menu` VALUES ('c1bab0a2fb364985968cd375e9739149', '系统设置', 'ROOT', '系统设置', '', '', '1', '4');
INSERT INTO `menu` VALUES ('c7045dfe631a455c85aaca0db9f6ae9e', '角色管理', '96d5ebddc89d4ea4ad6d546f4852fcce', '角色管理', '', '', '1', '3');
INSERT INTO `menu` VALUES ('c79ad83beb744f169a950a824fee1944', '文章维护', '7dccb7673176444ab2d6cb6d379b3972', '文章维护', '/article/showArticlePage', '', '1', '2');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人ID',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('192.168.60.21', 'mrbird', '执行方法一23123', 3, 'com.learn.TestController.methodOne()', 'name: null', '192.168.60.21', '2019-07-17 11:51:29');

-- ----------------------------
-- Table structure for sys_logininfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfo`;
CREATE TABLE `sys_logininfo`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '人员id',
  `operatetype` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '1:登陆，0：登出',
  `operatetime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `loginname` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '加密盐值',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
