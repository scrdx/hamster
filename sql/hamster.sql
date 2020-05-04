/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50149
 Source Host           : localhost:3306
 Source Schema         : hamster

 Target Server Type    : MySQL
 Target Server Version : 50149
 File Encoding         : 65001

 Date: 20/04/2020 01:44:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_bm_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bm_bookmark`;
CREATE TABLE `tbl_bm_bookmark`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标URL',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `parents` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类与上层分类路径',
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签ID列表',
  `created` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(255) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tbl_bm_bookmark
-- ----------------------------
INSERT INTO `tbl_bm_bookmark` VALUES (17, 'dfawdfasf', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', '', '153c1b4d9e744839851ecc08062e221c.jpeg', 2, '1/2/', 'inory', '', '2020-03-28 19:03:11', '2020-04-06 03:58:13', 0);
INSERT INTO `tbl_bm_bookmark` VALUES (18, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3b88b7fad2', '', '9686e132ef69490f935a6ce4ea2bf957.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:06:29', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (19, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3b88b7fad2', '', '573947de663b491f80aa1bd28ab9729e.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:08:10', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (20, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', 'dsfagasdfsa', 'c59111ea088c42dcaab65d43bd20eb7e.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:19:27', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (21, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', 'dsfagasdfsa', '32fd4d0fc1654b879591e98805417766.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:21:24', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (22, '啊哈哈哈', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3b88b7fad2', 'dasgdf', 'f6f098c9034e4f74b563c988c23e4229.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:22:52', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (23, 'PORNHUB', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', '', 'aa4d9bcb2e2c4d7a9a08e6c4942516e0.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:24:32', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (24, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'dfasgdsf', 'b6c2269c72ee4fefb33b27bf25cfc3ce.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:31:46', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (25, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'esdfgasdf', 'b4953bd0bb474e31b67e37a60a2e07db.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:36:23', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (26, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'esdfgasdf', 'd74f42ad9454417b9e207544c84b6215.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:36:35', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (27, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'esdfgasdf', '70716fca62154da1bf2558cabc8de17d.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:36:41', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (28, 'test', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', '', '63a5d92c06b147f09ab7b56ae2f3abb3.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:43:07', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (29, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', '', '15d943ea181f4be9a841f8d0dc1734cb.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:45:36', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (33, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', '', '0ec48de4654c4a3da41132f6fbd12380.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:47:18', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (34, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', '', '3a3d43e3476745e5b08485e9b542a484.jpeg', 1, '1/', 'inory', NULL, '2020-03-28 19:47:44', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (35, '啊哈哈哈', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', '', '03a9206cbc324a61a89dd595109cd00b.jpeg', 1, '1/', 'inory', NULL, '2020-03-29 17:32:44', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (36, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', '', '60f3d985a22541e8887da62a525e7aed.jpeg', 2, '1/2/', 'inory', '', '2020-03-29 17:35:18', '2020-04-06 03:57:56', 0);
INSERT INTO `tbl_bm_bookmark` VALUES (37, 'TEST', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', '', 'c2a4c1ed74e8406b93b209bf6de25e81.jpeg', 1, '1/', 'inory', NULL, '2020-03-29 17:36:57', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (38, '', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3b88b7fad2', '', 'deb76261877f4a59979142b55814a0a0.jpeg', 1, '1/', 'inory', NULL, '2020-03-29 17:38:28', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (39, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', '', '8571fff8969147549efae9ea75e19b08.jpeg', 4, '1/4/', 'inory', NULL, '2020-03-29 22:52:03', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (40, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', '', '27b8c2985e0f475dbdc4c646689428a5.jpeg', 2, '1/2/', 'inory', NULL, '2020-03-29 22:53:12', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (41, 'PORNHUB', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', '', 'cc38fed3a5a34e6c806741ae59188082.jpeg', 21, '1/4/21/', 'inory', NULL, '2020-03-30 05:43:35', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (42, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'sdagfsdafasgasdfas', '750791488bcc4317ac2740c1a971d3da.jpeg', 2, '1/2/', 'inory', '30,31,26,32', '2020-04-04 01:31:02', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (43, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'tesgdasfasdfdsgasfasdgsdfswafds', '3265166e991c450ebdac04179c8ceaa8.jpeg', 2, '1/2/', 'inory', '33,26,34,35,36,37,38', '2020-04-04 22:23:57', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (44, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'trwsafsdgasdfsdaf', '8786879711a543c4a2e3eaecff17a037.jpeg', 2, '1/2/', 'inory', '39,26,40', '2020-04-05 17:34:47', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (45, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3b88b7fad2', 'vasdfgsdaasgvasdfAFS', 'ed1ab51ed48341a2b2f4e918a2793b29.jpeg', 2, '1/2/', 'inory', '26,41,42,43', '2020-04-05 17:43:21', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (46, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'asdfdasfasdf', 'a60650463d934156ae2e1408ce121f1f.jpeg', 2, '1/2/', 'inory', '26,41,42,43', '2020-04-05 17:45:41', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (47, 'tasdfagasedf', 'https://www.youtube.com/watch?v=KYV8Ccbx_JQ', 'rweafasdgawfdawsdfaes', '45e073efb83e4d15a1a8a45b2afe033b.jpeg', 2, '1/2/', 'inory', '30,26,44', '2020-04-05 17:59:42', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (48, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'dgasdfdasfdas', '337f3b521cd549b19f533f59356952d2.jpeg', 2, '1/2/', 'inory', '26,45', '2020-04-05 18:01:18', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (49, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'dsafgsdafasf', '4738ea424563424187df50eb8b62b2e6.jpeg', 2, '1/2/', 'inory', '46,47,48,26,49', '2020-04-05 18:02:22', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (50, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'dfasgdasfas', '00603f97296b45e492330fe00fa8b85e.jpeg', 32, '1/2932/', 'inory', '50,26', '2020-04-05 18:03:31', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (51, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', 'ateawsfdasfdas', '0574315e6d9e4bac982680d3d0d44c85.jpeg', 35, '1/3135/', 'inory', '51,52,26,53,54,55,56', '2020-04-05 18:06:02', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (52, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'dsfasfdasfasfsa', 'c2c85b08863644aa89bf3851e617ccb9.jpeg', 3, '1/3/', 'inory', '30,57,26,58,59,60', '2020-04-05 18:56:26', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (53, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'dfas', 'ab276b73e2004b80af522f5c64493dd6.jpeg', 2, '1/2/', 'inory', '26,61', '2020-04-05 18:58:35', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (54, 'ACFUN', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df3920ee4bd7', '我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。我是一串很长的描述。', '3a294ff8ffcd43b3b546dc1f4bcf1511.jpeg', 22, '1/4/2122/', 'inory', '30,57,26,62,63,27', '2020-04-05 20:27:25', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (55, 'LOLI', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'what is the world if compare at least visual star in the firmement.', 'bfe23af6f79344a19df6a3bf3894eaa6.jpeg', 2, '1/2/', 'inory', '64,31,26,65,66,67,68', '2020-04-06 02:34:05', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (56, 'TEST', 'https://cn.pornhub.com/view_video.php?viewkey=ph5df785c8c8ba6', 'just for fun.', '44751561d60f48d8bf932f0e4e4f2ab9.jpeg', 2, '1/2/', 'inory', '69', '2020-04-06 02:47:51', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (57, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'dfsf', 'ee64574ce9054fd4b60dd1af09cec871.jpeg', 2, '1/2/', 'inory', '70,26', '2020-04-06 03:04:16', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (58, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'ABABA', '8e13588561374320bd223d751bb7ac6b.jpeg', 22, '1/4/2122/', 'inory', '70,26', '2020-04-06 03:04:39', '2020-04-06 04:14:59', 0);
INSERT INTO `tbl_bm_bookmark` VALUES (59, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', NULL, 2, '1/2/', 'inory', NULL, '2020-04-06 03:22:29', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (60, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '1006493ef8e54919bb0971b5b859d952.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:24:40', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (61, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '9367339015a54953ade03367601b742e.jpeg', 19, '1/3/1819/', 'inory', '', '2020-04-06 03:26:32', '2020-04-06 04:18:32', 0);
INSERT INTO `tbl_bm_bookmark` VALUES (62, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', 'ff91d85eddea4be8bb7826afbf3fbbd1.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:27:14', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (63, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '2c5a55ef13334a52ab164f3be65cb5f6.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:27:58', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (64, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '3b1228a07d2241de86b8076a7213f1b8.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:28:53', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (65, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', 'f97fdc100f414f7cbbab8f465b076898.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:30:22', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (66, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '2f9521fc28eb4eeb98ddf3163aab4a7a.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:30:57', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (67, 'dfdsfdsafsa', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', 'a83f68554781425985bdb55846c6a119.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:31:40', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (68, 'PORNHUB', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '19c3d8ebf73b40d7ba4a92aa041615ca.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:32:34', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (69, 'hijghijgilh', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '56acbaae8ad4410e8713c0d3b665c0de.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:33:42', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (70, 'stststs', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '561002ea29b24eb18e7238dc0193c08d.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:36:38', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (71, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', '3f630681f6164517b8982ace06737d6a.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-06 03:50:00', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (72, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', '', 'a7933af203a44a429bd7123cc91b2f97.jpeg', 2, '1/2/', 'inory', '71', '2020-04-06 16:45:57', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (73, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', 'edfsdafsdf', 'f0843017204844bf8d021366d3a9903b.jpeg', 2, '1/2/', 'inory', '26,38', '2020-04-07 19:05:03', '2020-04-07 19:05:25', 0);
INSERT INTO `tbl_bm_bookmark` VALUES (74, '', '', '', '0d7a43f6b6da4a16992c3b003b937aeb.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-07 19:13:29', NULL, 0);
INSERT INTO `tbl_bm_bookmark` VALUES (75, 'test', 'https://cn.pornhub.com/view_video.php?viewkey=ph5d52a2c698a20', ']\\;kiohkjhbuo', '66d7467d8dfa48858523c181d70dae8b.jpeg', 2, '1/2/', 'inory', NULL, '2020-04-07 19:14:47', NULL, 0);

-- ----------------------------
-- Table structure for tbl_bm_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bm_category`;
CREATE TABLE `tbl_bm_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `parents` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` datetime NULL DEFAULT NULL,
  `modified` datetime NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tbl_bm_category
-- ----------------------------
INSERT INTO `tbl_bm_category` VALUES (1, 'root', NULL, NULL, '', 'inory', '2020-03-22 18:35:19', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (2, 'ACG', NULL, 1, '1/', 'inory', '2020-03-24 22:32:47', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (3, 'ACG', NULL, 1, '1/', 'inory', '2020-03-24 22:34:03', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (4, 'ACG', NULL, 1, '1/', 'inory', '2020-03-24 22:35:23', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (5, 'ACG', NULL, 1, '1/', 'inory', '2020-03-24 22:36:25', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (6, 'ACG', NULL, 1, '1/', 'inory', '2020-03-24 22:36:42', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (18, 'dfagdasfa', NULL, 3, '1/3', 'inory', '2020-03-25 22:47:07', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (19, 'dsfasdgdsaf', NULL, 18, '1/3/18', 'inory', '2020-03-25 22:47:14', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (21, '动漫', NULL, 4, '1/4', 'inory', '2020-03-26 19:08:11', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (22, '资源', NULL, 21, '1/4/21', 'inory', '2020-03-26 19:08:21', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (25, '我真是天才!', NULL, 1, '1/', 'inory', '2020-03-26 20:11:56', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (26, '啊哈哈哈哈', NULL, 1, '1/', 'inory', '2020-03-26 20:12:37', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (27, '星空', NULL, 1, '1/', 'inory', '2020-03-26 20:12:56', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (28, 'AAAAAAAAAA', NULL, 1, '1/', 'inory', '2020-03-26 20:17:47', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (29, 'WHY', NULL, 1, '1/', 'inory', '2020-03-26 20:18:57', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (30, 'NO！', NULL, 1, '1/', 'inory', '2020-03-26 20:20:04', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (31, 'AGAIN', NULL, 1, '1/', 'inory', '2020-03-26 20:28:09', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (32, 'TEST', NULL, 29, '1/29', 'inory', '2020-03-29 21:54:03', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (33, 'dsfawsf', NULL, 1, '1/', 'inory', '2020-03-29 21:55:44', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (34, '000', NULL, 33, '1/33', 'inory', '2020-03-29 21:58:26', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (35, '321', NULL, 31, '1/31', 'inory', '2020-03-29 21:59:29', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (36, '456', NULL, 34, '1/33/34', 'inory', '2020-03-29 21:59:37', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (37, 'test', NULL, 25, '1/25/', 'inory', '2020-04-05 18:03:47', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (38, 'aaaa', NULL, 25, '1/25/', 'inory', '2020-04-05 18:05:04', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (39, '1111', NULL, 33, '1/33/', 'inory', '2020-04-06 04:17:21', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (40, 'dfasdfsdaf', NULL, 1, '1/', 'inory', '2020-04-07 19:04:03', NULL, NULL);
INSERT INTO `tbl_bm_category` VALUES (41, 'dfdfdf', NULL, 40, '1/40/', 'inory', '2020-04-07 19:04:09', NULL, NULL);

-- ----------------------------
-- Table structure for tbl_bm_tag
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bm_tag`;
CREATE TABLE `tbl_bm_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tbl_bm_tag
-- ----------------------------
INSERT INTO `tbl_bm_tag` VALUES (1, 'A站', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (2, 'ACFUN', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (3, 'ACG', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (4, '弹幕', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (5, '视频', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (6, '动画', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (7, '漫画', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (8, '游戏', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (9, '新番', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (10, '鬼畜', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (11, '东方', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (12, '初音', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (13, 'DOTA', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (14, 'MUGEN', 'inory', '2020-03-22 00:02:45');
INSERT INTO `tbl_bm_tag` VALUES (15, 'TEST', 'inory', '2020-03-22 00:11:45');
INSERT INTO `tbl_bm_tag` VALUES (19, 'dfdsagdsfas', 'inory', '2020-03-26 22:20:17');
INSERT INTO `tbl_bm_tag` VALUES (20, 'tests', 'inory', '2020-03-26 22:20:17');
INSERT INTO `tbl_bm_tag` VALUES (21, 'aaaaaaaaaaa', 'inory', '2020-03-26 22:20:17');
INSERT INTO `tbl_bm_tag` VALUES (22, 'ffffffffff', 'inory', '2020-03-26 22:22:54');
INSERT INTO `tbl_bm_tag` VALUES (23, '090p', 'inory', '2020-03-27 21:17:23');
INSERT INTO `tbl_bm_tag` VALUES (26, 'test', 'inory', '2020-03-27 21:51:21');
INSERT INTO `tbl_bm_tag` VALUES (27, 'tag', 'inory', '2020-03-27 21:51:21');
INSERT INTO `tbl_bm_tag` VALUES (28, '动漫', 'inory', '2020-03-28 19:02:13');
INSERT INTO `tbl_bm_tag` VALUES (29, '女朋友', 'inory', '2020-03-28 19:02:13');
INSERT INTO `tbl_bm_tag` VALUES (30, 'aaa', 'inory', '2020-04-04 01:31:02');
INSERT INTO `tbl_bm_tag` VALUES (31, 'abc', 'inory', '2020-04-04 01:31:02');
INSERT INTO `tbl_bm_tag` VALUES (32, 'ggg', 'inory', '2020-04-04 01:31:02');
INSERT INTO `tbl_bm_tag` VALUES (33, 'jk', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (34, '水手服', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (35, '粉色', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (36, '大渔铁板烧', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (37, '美女', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (38, 'aaaa', 'inory', '2020-04-04 22:23:57');
INSERT INTO `tbl_bm_tag` VALUES (39, '粉头发的妹妹', 'inory', '2020-04-05 17:34:47');
INSERT INTO `tbl_bm_tag` VALUES (40, '烤肉拌饭', 'inory', '2020-04-05 17:34:47');
INSERT INTO `tbl_bm_tag` VALUES (41, '/etc/centos-release', 'inory', '2020-04-05 17:43:21');
INSERT INTO `tbl_bm_tag` VALUES (42, 'eeeee', 'inory', '2020-04-05 17:43:21');
INSERT INTO `tbl_bm_tag` VALUES (43, 'redhat', 'inory', '2020-04-05 17:43:21');
INSERT INTO `tbl_bm_tag` VALUES (44, 'ddddd', 'inory', '2020-04-05 17:59:42');
INSERT INTO `tbl_bm_tag` VALUES (45, 'dddddddddddd', 'inory', '2020-04-05 18:01:18');
INSERT INTO `tbl_bm_tag` VALUES (46, 'djkalsf', 'inory', '2020-04-05 18:02:22');
INSERT INTO `tbl_bm_tag` VALUES (47, 'sss', 'inory', '2020-04-05 18:02:22');
INSERT INTO `tbl_bm_tag` VALUES (48, 's', 'inory', '2020-04-05 18:02:22');
INSERT INTO `tbl_bm_tag` VALUES (49, 'dshgaiofjadosfgjas', 'inory', '2020-04-05 18:02:22');
INSERT INTO `tbl_bm_tag` VALUES (50, '123', 'inory', '2020-04-05 18:03:31');
INSERT INTO `tbl_bm_tag` VALUES (51, 'aaaaaaaaaaaaaa', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (52, '中文', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (53, 'ffffff', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (54, 'gggggg', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (55, 'ddddddddddddfd', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (56, 'ds', 'inory', '2020-04-05 18:06:02');
INSERT INTO `tbl_bm_tag` VALUES (57, 'bbb', 'inory', '2020-04-05 18:56:26');
INSERT INTO `tbl_bm_tag` VALUES (58, 'cccc', 'inory', '2020-04-05 18:56:26');
INSERT INTO `tbl_bm_tag` VALUES (59, 'dddd', 'inory', '2020-04-05 18:56:26');
INSERT INTO `tbl_bm_tag` VALUES (60, 'eeee', 'inory', '2020-04-05 18:56:26');
INSERT INTO `tbl_bm_tag` VALUES (61, 'bbbb', 'inory', '2020-04-05 18:58:35');
INSERT INTO `tbl_bm_tag` VALUES (62, 'chrome', 'inory', '2020-04-05 20:27:25');
INSERT INTO `tbl_bm_tag` VALUES (63, '丝袜', 'inory', '2020-04-05 20:27:25');
INSERT INTO `tbl_bm_tag` VALUES (64, 'df', 'inory', '2020-04-06 02:34:05');
INSERT INTO `tbl_bm_tag` VALUES (65, 'ddd', 'inory', '2020-04-06 02:34:05');
INSERT INTO `tbl_bm_tag` VALUES (66, 'tageditor', 'inory', '2020-04-06 02:34:05');
INSERT INTO `tbl_bm_tag` VALUES (67, 'information', 'inory', '2020-04-06 02:34:05');
INSERT INTO `tbl_bm_tag` VALUES (68, 'life', 'inory', '2020-04-06 02:34:05');
INSERT INTO `tbl_bm_tag` VALUES (69, 'cbb', 'inory', '2020-04-06 02:47:51');
INSERT INTO `tbl_bm_tag` VALUES (70, 'bb', 'inory', '2020-04-06 03:04:16');
INSERT INTO `tbl_bm_tag` VALUES (71, 'fxk', 'inory', '2020-04-06 16:45:57');
INSERT INTO `tbl_bm_tag` VALUES (72, 'dfddf', 'inory', '2020-04-07 19:05:03');
INSERT INTO `tbl_bm_tag` VALUES (73, 'ateafdfds', 'inory', '2020-04-07 19:05:03');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `user_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` datetime NULL DEFAULT NULL,
  `modified` datetime NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('inory', '蝶祈', 'e10adc3949ba59abbe56e057f20f883e', 'fc4bb44fabb847eca6a1048aaf4c1ef2.jpeg', '2020-03-22 23:20:00', '2020-04-07 19:09:47', NULL);

SET FOREIGN_KEY_CHECKS = 1;
