/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : erha-admin

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 10/12/2022 15:50:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config`  (
                                       `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `form_show` bit(1) NULL DEFAULT NULL,
                                       `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `list_show` bit(1) NULL DEFAULT NULL,
                                       `not_null` bit(1) NULL DEFAULT NULL,
                                       `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       PRIMARY KEY (`column_id`) USING BTREE,
                                       INDEX `idx_table_name`(`table_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 667 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of code_column_config
-- ----------------------------
INSERT INTO `code_column_config` VALUES (233, 'sys_users_jobs', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (234, 'sys_users_jobs', 'job_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (235, 'sys_users_jobs', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (236, 'tool_qiniu_content', 'content_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (237, 'tool_qiniu_content', 'bucket', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'Bucket ?????????', NULL);
INSERT INTO `code_column_config` VALUES (238, 'tool_qiniu_content', 'name', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (239, 'tool_qiniu_content', 'size', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (240, 'tool_qiniu_content', 'type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????????????????????????????', NULL);
INSERT INTO `code_column_config` VALUES (241, 'tool_qiniu_content', 'url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????url', NULL);
INSERT INTO `code_column_config` VALUES (242, 'tool_qiniu_content', 'suffix', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (243, 'tool_qiniu_content', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????????????????', NULL);
INSERT INTO `code_column_config` VALUES (442, 'sys_quartz_log', 'log_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', '!=', 'ID', NULL);
INSERT INTO `code_column_config` VALUES (443, 'sys_quartz_log', 'bean_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', '=', '', NULL);
INSERT INTO `code_column_config` VALUES (444, 'sys_quartz_log', 'cron_expression', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (445, 'sys_quartz_log', 'exception_detail', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (446, 'sys_quartz_log', 'is_success', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (447, 'sys_quartz_log', 'job_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (448, 'sys_quartz_log', 'method_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (449, 'sys_quartz_log', 'params', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (450, 'sys_quartz_log', 'time', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (451, 'sys_quartz_log', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (452, 'sys_quartz_log', 'exec_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (453, 'sys_role', 'role_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (454, 'sys_role', 'name', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'1', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (455, 'sys_role', 'level', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (456, 'sys_role', 'description', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (457, 'sys_role', 'data_scope', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (458, 'sys_role', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (459, 'sys_role', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (460, 'sys_role', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (461, 'sys_role', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (462, 'sys_user', 'user_id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', '', 'ID', NULL);
INSERT INTO `code_column_config` VALUES (463, 'sys_user', 'dept_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'0', '', '????????????', '');
INSERT INTO `code_column_config` VALUES (464, 'sys_user', 'username', 'varchar', NULL, '', b'1', '?????????', 'UNI', b'1', b'1', 'Like', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (465, 'sys_user', 'nick_name', 'varchar', NULL, '', b'1', '?????????', '', b'1', b'1', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (466, 'sys_user', 'gender', 'varchar', 'gender', '', b'1', '?????????[??????]', '', b'1', b'1', '=', '??????', NULL);
INSERT INTO `code_column_config` VALUES (467, 'sys_user', 'phone', 'varchar', NULL, '', b'1', '?????????', '', b'1', b'1', '=', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (468, 'sys_user', 'email', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'1', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (469, 'sys_user', 'avatar_name', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (470, 'sys_user', 'avatar_path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????????????????', NULL);
INSERT INTO `code_column_config` VALUES (471, 'sys_user', 'password', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (472, 'sys_user', 'is_admin', 'bit', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '?????????admin??????', NULL);
INSERT INTO `code_column_config` VALUES (473, 'sys_user', 'enabled', 'bit', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '?????????1?????????0??????', NULL);
INSERT INTO `code_column_config` VALUES (474, 'sys_user', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (475, 'sys_user', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (476, 'sys_user', 'pwd_reset_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????????????????', NULL);
INSERT INTO `code_column_config` VALUES (477, 'sys_user', 'create_time', 'datetime', NULL, '', b'1', '?????????', '', b'1', b'0', 'Between', '????????????', '??????????????????');
INSERT INTO `code_column_config` VALUES (478, 'sys_user', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', '??????????????????');
INSERT INTO `code_column_config` VALUES (479, 'sys_user', 'now_role_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????????????????????????????????????????????????????', NULL);
INSERT INTO `code_column_config` VALUES (480, 'sys_roles_menus', 'menu_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (481, 'sys_roles_menus', 'role_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (482, 'sys_roles_menus', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (483, 'sys_users_roles', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (484, 'sys_users_roles', 'role_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '??????ID', NULL);
INSERT INTO `code_column_config` VALUES (485, 'sys_users_roles', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (521, 'code_gen_config', 'config_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (522, 'code_gen_config', 'table_name', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (523, 'code_gen_config', 'author', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (524, 'code_gen_config', 'cover', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (525, 'code_gen_config', 'module_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (526, 'code_gen_config', 'pack', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????????????????', NULL);
INSERT INTO `code_column_config` VALUES (527, 'code_gen_config', 'path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '???????????????????????????', NULL);
INSERT INTO `code_column_config` VALUES (528, 'code_gen_config', 'api_path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????Api', NULL);
INSERT INTO `code_column_config` VALUES (529, 'code_gen_config', 'prefix', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (530, 'code_gen_config', 'api_alias', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (540, 'sys_menu', 'menu_id', 'bigint', NULL, 'auto_increment', b'1', '', 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (541, 'sys_menu', 'pid', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '????????????ID', NULL);
INSERT INTO `code_column_config` VALUES (542, 'sys_menu', 'sub_count', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '???????????????', NULL);
INSERT INTO `code_column_config` VALUES (543, 'sys_menu', 'type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (544, 'sys_menu', 'title', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (545, 'sys_menu', 'name', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (546, 'sys_menu', 'locale', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '???????????????', NULL);
INSERT INTO `code_column_config` VALUES (547, 'sys_menu', 'order', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (548, 'sys_menu', 'icon', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (549, 'sys_menu', 'path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (550, 'sys_menu', 'i_frame', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (551, 'sys_menu', 'cache', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (552, 'sys_menu', 'hidden', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (553, 'sys_menu', 'permission', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (554, 'sys_menu', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (555, 'sys_menu', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (556, 'sys_menu', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (557, 'sys_menu', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (629, 'test_student', 'student_id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'0', b'0', NULL, 'id', NULL);
INSERT INTO `code_column_config` VALUES (630, 'test_student', 'name', 'varchar', NULL, '', b'1', '?????????', 'UNI', b'1', b'1', 'Like', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (631, 'test_student', 'like_food', 'varchar', 'fruit', '', b'1', '?????????[??????]', '', b'1', b'1', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (632, 'test_student', 'age', 'int', NULL, '', b'1', '?????????', '', b'1', b'1', '=', '??????', NULL);
INSERT INTO `code_column_config` VALUES (633, 'test_student', 'clazz_name', 'varchar', 'test_clazz', '', b'1', '?????????[??????]', '', b'1', b'1', '=', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (634, 'test_student', 'gender', 'bit', 'gender', '', b'1', '?????????[??????]', '', b'1', b'1', '', '??????', NULL);
INSERT INTO `code_column_config` VALUES (635, 'test_student', 'avg_score', 'decimal', NULL, '', b'1', '?????????', '', b'1', b'1', 'Between', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (636, 'test_student', 'create_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'1', NULL, '????????????', '??????????????????');
INSERT INTO `code_column_config` VALUES (637, 'test_student', 'update_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', 'Between', '????????????', '??????????????????');
INSERT INTO `code_column_config` VALUES (638, 'test_student', 'enabled', 'bit', 'user_status', '', b'1', '??????[????????????]', '', b'1', b'1', '=', '??????', NULL);
INSERT INTO `code_column_config` VALUES (639, 'test_student', 'expire_time', 'datetime', NULL, '', b'1', '?????????', '', b'1', b'1', '>=', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (640, 'online_user', 'user_name', 'varchar', NULL, '', b'0', NULL, 'PRI', b'1', b'0', 'Like', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (641, 'online_user', 'nickName', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (642, 'online_user', 'dept', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (643, 'online_user', 'browser', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (644, 'online_user', 'ip', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', '=', 'ip', NULL);
INSERT INTO `code_column_config` VALUES (645, 'online_user', 'address', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (646, 'online_user', 'login_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', 'Between', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (647, 'sys_log', 'log_id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'0', b'0', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (648, 'sys_log', 'description', 'varchar', NULL, '', b'0', '', '', b'1', b'0', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (649, 'sys_log', 'log_type', 'varchar', 'tools_log_type', '', b'0', '?????????[??????]', 'MUL', b'1', b'0', '=', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (650, 'sys_log', 'method', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (651, 'sys_log', 'params', 'text', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '??????', NULL);
INSERT INTO `code_column_config` VALUES (652, 'sys_log', 'request_ip', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', '=', 'IP??????', NULL);
INSERT INTO `code_column_config` VALUES (653, 'sys_log', 'time', 'bigint', NULL, '', b'0', NULL, '', b'1', b'0', 'Between', '??????', NULL);
INSERT INTO `code_column_config` VALUES (654, 'sys_log', 'username', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (655, 'sys_log', 'address', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', 'IP?????????', NULL);
INSERT INTO `code_column_config` VALUES (656, 'sys_log', 'browser', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '?????????', NULL);
INSERT INTO `code_column_config` VALUES (657, 'sys_log', 'exception_detail', 'text', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (658, 'sys_log', 'create_time', 'datetime', NULL, '', b'0', NULL, 'MUL', b'1', b'0', 'Between', '????????????', NULL);
INSERT INTO `code_column_config` VALUES (659, 'sys_job', 'job_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (660, 'sys_job', 'name', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'1', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (661, 'sys_job', 'enabled', 'bit', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (662, 'sys_job', 'job_sort', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '??????', NULL);
INSERT INTO `code_column_config` VALUES (663, 'sys_job', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (664, 'sys_job', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '?????????', NULL);
INSERT INTO `code_column_config` VALUES (665, 'sys_job', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);
INSERT INTO `code_column_config` VALUES (666, 'sys_job', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '????????????', NULL);

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config`  (
                                    `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                    `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                    `cover` bit(1) NULL DEFAULT NULL COMMENT '????????????',
                                    `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                    `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????????????????',
                                    `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???????????????????????????',
                                    `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????Api',
                                    `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                    `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                    PRIMARY KEY (`config_id`) USING BTREE,
                                    INDEX `idx_table_name`(`table_name`(100)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '?????????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of code_gen_config
-- ----------------------------
INSERT INTO `code_gen_config` VALUES (9, 'test_student', '?????????', b'1', 'erha-admin-tools', 'fun.yizhierha.test', 'test/student', 'test/student', NULL, '???????????????????????????');
INSERT INTO `code_gen_config` VALUES (10, 'online_user', '??????', b'1', 'erha-admin-monitor', 'fun.yizhierha.monitor', 'monitor/onlineuser', 'monitor/onlineuser', NULL, '???????????????????????????');
INSERT INTO `code_gen_config` VALUES (12, 'sys_log', '??????', b'1', 'erha-admin-monitor', 'fun.yizhierha.monitor', 'monitor/log', 'monitor/log', NULL, '???????????????????????????');

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app`  (
                            `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `upload_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `deploy_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `backup_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `port` int(255) NULL DEFAULT NULL COMMENT '????????????',
                            `start_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `deploy_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                            `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                            `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                            `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                            `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                            PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_app
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database`  (
                                 `db_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
                                 `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????',
                                 `jdbc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc??????',
                                 `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????',
                                 `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????',
                                 `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                 `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                 PRIMARY KEY (`db_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '???????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_database
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy`  (
                               `deploy_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `app_id` bigint(20) NULL DEFAULT NULL COMMENT '????????????',
                               `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                               `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                               `create_time` datetime(0) NULL DEFAULT NULL,
                               `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                               PRIMARY KEY (`deploy_id`) USING BTREE,
                               INDEX `FK6sy157pseoxx4fmcqr1vnvvhy`(`app_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_deploy
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history`  (
                                       `history_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
                                       `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                                       `deploy_date` datetime(0) NOT NULL COMMENT '????????????',
                                       `deploy_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                                       `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '?????????IP',
                                       `deploy_id` bigint(20) NULL DEFAULT NULL COMMENT '????????????',
                                       PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_deploy_history
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server`  (
                                      `deploy_id` bigint(20) NOT NULL COMMENT '??????ID',
                                      `server_id` bigint(20) NOT NULL COMMENT '??????ID',
                                      PRIMARY KEY (`deploy_id`, `server_id`) USING BTREE,
                                      INDEX `FKeaaha7jew9a02b3bk9ghols53`(`server_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_deploy_server
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server`  (
                               `server_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                               `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP??????',
                               `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                               `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                               `port` int(11) NULL DEFAULT NULL COMMENT '??????',
                               `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                               `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                               `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                               `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                               PRIMARY KEY (`server_id`) USING BTREE,
                               INDEX `idx_ip`(`ip`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '???????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mnt_server
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `pid` bigint(20) NULL DEFAULT NULL COMMENT '????????????',
                             `sub_count` int(5) NULL DEFAULT 0 COMMENT '???????????????',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????',
                             `dept_sort` int(5) NULL DEFAULT 999 COMMENT '??????',
                             `enabled` bit(1) NOT NULL COMMENT '??????',
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             PRIMARY KEY (`dept_id`) USING BTREE,
                             INDEX `inx_pid`(`pid`) USING BTREE,
                             INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (2, 7, 1, '?????????', 3, b'1', 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '?????????', 4, b'1', 'admin', 'admin', '2019-03-25 09:20:44', '2020-05-17 14:27:27');
INSERT INTO `sys_dept` VALUES (6, 8, 2, '?????????', 6, b'1', 'admin', 'admin2', '2019-03-25 09:52:18', '2022-09-02 16:28:30');
INSERT INTO `sys_dept` VALUES (7, NULL, 2, '????????????', 2, b'1', 'admin', 'admin2', '2019-03-25 11:04:50', '2022-11-10 16:52:22');
INSERT INTO `sys_dept` VALUES (8, NULL, 2, '????????????', 1, b'1', 'admin', 'admin2', '2019-03-25 11:04:53', '2022-12-09 22:18:57');
INSERT INTO `sys_dept` VALUES (15, 8, 1, 'UI??????', 7, b'1', 'admin', 'admin2', '2020-05-13 22:56:53', '2022-09-02 16:43:20');
INSERT INTO `sys_dept` VALUES (17, 2, 0, '????????????', 999, b'1', 'admin', 'admin', '2020-08-02 14:49:07', '2020-08-02 14:49:07');
INSERT INTO `sys_dept` VALUES (18, 6, 1, '????????????', 999, b'1', 'admin2', 'admin', '2022-09-02 21:18:11', '2022-09-02 21:18:14');
INSERT INTO `sys_dept` VALUES (20, 6, 0, '????????????', 999, b'1', 'admin2', NULL, '2022-08-31 14:46:26', NULL);
INSERT INTO `sys_dept` VALUES (22, NULL, 0, '????????????', 999, b'1', 'admin2', 'admin2', '2022-08-31 14:54:10', '2022-11-10 19:59:48');
INSERT INTO `sys_dept` VALUES (24, 15, 0, 'UI??????', 999, b'1', 'admin2', NULL, '2022-08-31 14:57:40', NULL);
INSERT INTO `sys_dept` VALUES (25, 18, 0, '??????A', 999, b'1', 'admin2', NULL, '2022-08-31 14:58:09', NULL);
INSERT INTO `sys_dept` VALUES (27, NULL, 0, '????????????', 9, b'1', 'admin2', 'admin2', '2022-08-31 15:00:53', '2022-09-02 17:20:49');
INSERT INTO `sys_dept` VALUES (28, NULL, 0, '????????????', 9, b'1', 'admin2', 'admin2', '2022-08-31 15:01:10', '2022-09-02 17:20:50');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
                             `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                             `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user_status', '????????????', NULL, 'admin2', '2019-10-27 20:31:36', '2022-09-10 23:46:22');
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '????????????', NULL, 'admin2', '2019-10-27 20:31:36', '2022-09-10 23:46:35');
INSERT INTO `sys_dict` VALUES (5, 'job_status', '????????????', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (7, 'fruit', '????????????222', 'admin2', 'admin2', '2022-09-10 23:44:26', '2022-12-04 15:48:47');
INSERT INTO `sys_dict` VALUES (8, 'gender', '??????', 'admin2', NULL, '2022-11-22 15:39:38', NULL);
INSERT INTO `sys_dict` VALUES (9, 'test_clazz', '???????????????', 'admin2', NULL, '2022-11-27 13:34:00', NULL);
INSERT INTO `sys_dict` VALUES (10, 'tools_log_type', '????????????', 'admin2', NULL, '2022-12-09 20:40:28', NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
                                    `detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `dict_id` bigint(11) NULL DEFAULT NULL COMMENT '??????id',
                                    `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                                    `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '?????????',
                                    `dict_sort` int(5) NULL DEFAULT NULL COMMENT '??????',
                                    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                    PRIMARY KEY (`detail_id`) USING BTREE,
                                    INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, '??????', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '??????', 'false', 2, NULL, NULL, '2022-07-26 18:50:02', NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '??????', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '??????', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '??????', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '??????', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (7, 7, '??????', 'apple', 11, 'admin2', 'admin2', '2022-09-10 23:46:08', '2022-09-10 23:47:35');
INSERT INTO `sys_dict_detail` VALUES (8, 7, '??????', 'banana', 2, 'admin2', 'admin2', '2022-09-10 23:46:57', '2022-09-13 21:51:02');
INSERT INTO `sys_dict_detail` VALUES (9, 7, '??????', 'pear', 13, 'admin2', 'admin2', '2022-09-10 23:47:13', '2022-09-10 23:47:35');
INSERT INTO `sys_dict_detail` VALUES (10, 8, '???', '1', 1, 'admin2', 'admin2', '2022-11-22 15:40:10', '2022-11-26 18:15:11');
INSERT INTO `sys_dict_detail` VALUES (11, 8, '???', '0', 1, 'admin2', NULL, '2022-11-22 15:40:19', NULL);
INSERT INTO `sys_dict_detail` VALUES (13, 9, '??????', '1', 0, 'admin2', NULL, '2022-11-27 13:34:28', NULL);
INSERT INTO `sys_dict_detail` VALUES (14, 9, '??????', '2', 0, 'admin2', 'admin2', '2022-11-27 13:34:34', '2022-11-27 13:34:41');
INSERT INTO `sys_dict_detail` VALUES (15, 9, '??????', '3', 0, 'admin2', NULL, '2022-11-27 13:34:56', NULL);
INSERT INTO `sys_dict_detail` VALUES (16, 9, '??????', '4', 0, 'admin2', NULL, '2022-11-27 13:35:11', NULL);
INSERT INTO `sys_dict_detail` VALUES (17, 10, '??????', 'INFO', 0, 'admin2', NULL, '2022-12-09 20:40:47', NULL);
INSERT INTO `sys_dict_detail` VALUES (18, 10, '??????', 'ERROR', 0, 'admin2', NULL, '2022-12-09 20:40:55', NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
                            `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                            `enabled` bit(1) NOT NULL COMMENT '????????????',
                            `job_sort` int(5) NULL DEFAULT NULL COMMENT '??????',
                            `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                            `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                            `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                            `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                            PRIMARY KEY (`job_id`) USING BTREE,
                            UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
                            INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (8, '????????????', b'1', 31, NULL, 'admin2', '2019-03-29 14:52:28', '2022-09-03 15:33:15');
INSERT INTO `sys_job` VALUES (10, '????????????', b'1', 0, NULL, 'admin2', '2019-03-29 14:55:51', '2022-11-24 21:49:28');
INSERT INTO `sys_job` VALUES (11, '????????????', b'1', 21, NULL, 'admin2', '2019-03-31 13:39:30', '2022-09-03 15:33:15');
INSERT INTO `sys_job` VALUES (12, '????????????', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');
INSERT INTO `sys_job` VALUES (13, '????????????23', b'1', 0, 'admin2', 'admin2', '2022-09-03 14:54:06', '2022-09-03 22:26:15');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
                            `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                            `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `time` bigint(20) NULL DEFAULT NULL,
                            `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                            `create_time` datetime(0) NULL DEFAULT NULL,
                            PRIMARY KEY (`log_id`) USING BTREE,
                            INDEX `log_create_time_index`(`create_time`) USING BTREE,
                            INDEX `inx_log_type`(`log_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 399 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (44, '??????????????????', 'INFO', 'fun.yizhierha.monitor.controller.SysLogController.del()', '[32,43,30,31]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:17:21');
INSERT INTO `sys_log` VALUES (55, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:16');
INSERT INTO `sys_log` VALUES (66, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"dept_status\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:23');
INSERT INTO `sys_log` VALUES (67, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.getDept()', '[{},{\"pageSize\":999,\"currentPage\":1}]', '192.168.56.1', 18, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:23');
INSERT INTO `sys_log` VALUES (68, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"user_status\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:24');
INSERT INTO `sys_log` VALUES (69, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:24');
INSERT INTO `sys_log` VALUES (70, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 23, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:24');
INSERT INTO `sys_log` VALUES (71, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 11, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:26');
INSERT INTO `sys_log` VALUES (72, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:26');
INSERT INTO `sys_log` VALUES (73, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"user_status\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:27');
INSERT INTO `sys_log` VALUES (74, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:27');
INSERT INTO `sys_log` VALUES (75, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 21, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:27');
INSERT INTO `sys_log` VALUES (76, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:30');
INSERT INTO `sys_log` VALUES (77, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 40, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:47');
INSERT INTO `sys_log` VALUES (78, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"user_status\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:47');
INSERT INTO `sys_log` VALUES (79, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 81, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:47');
INSERT INTO `sys_log` VALUES (80, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"dept_status\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:55');
INSERT INTO `sys_log` VALUES (81, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.getDept()', '[{},{\"pageSize\":999,\"currentPage\":1}]', '192.168.56.1', 11, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:55');
INSERT INTO `sys_log` VALUES (82, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.editDept()', '[{\"enabled\":true,\"id\":8}]', '192.168.56.1', 31, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:57');
INSERT INTO `sys_log` VALUES (83, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"user_status\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:59');
INSERT INTO `sys_log` VALUES (84, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:59');
INSERT INTO `sys_log` VALUES (85, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 18, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:18:59');
INSERT INTO `sys_log` VALUES (86, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{\"deptIds\":[8,18,20,6,24,25,15]},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 20, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:00');
INSERT INTO `sys_log` VALUES (87, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{\"deptIds\":[7,17,2,5]},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 18, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:01');
INSERT INTO `sys_log` VALUES (88, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{\"deptIds\":[8,18,20,6,24,25,15]},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 20, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:02');
INSERT INTO `sys_log` VALUES (89, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{\"deptIds\":[7,17,2,5]},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 15, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:03');
INSERT INTO `sys_log` VALUES (90, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 16, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:04');
INSERT INTO `sys_log` VALUES (91, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:19:05');
INSERT INTO `sys_log` VALUES (92, '?????????', 'INFO', 'fun.yizhierha.modules.system.controller.DeptController.list()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:22:54');
INSERT INTO `sys_log` VALUES (93, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"user_status\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:22:54');
INSERT INTO `sys_log` VALUES (94, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.list()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 16, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:22:54');
INSERT INTO `sys_log` VALUES (95, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"job_status\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:22:57');
INSERT INTO `sys_log` VALUES (96, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.JobController.getJob()', '[{},{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 12, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:22:57');
INSERT INTO `sys_log` VALUES (97, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:23:04');
INSERT INTO `sys_log` VALUES (98, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:33:42');
INSERT INTO `sys_log` VALUES (99, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:33:43');
INSERT INTO `sys_log` VALUES (100, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:33:43');
INSERT INTO `sys_log` VALUES (101, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:34:40');
INSERT INTO `sys_log` VALUES (102, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:34:40');
INSERT INTO `sys_log` VALUES (103, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:34:41');
INSERT INTO `sys_log` VALUES (104, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:36:02');
INSERT INTO `sys_log` VALUES (105, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:36:42');
INSERT INTO `sys_log` VALUES (106, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:36:42');
INSERT INTO `sys_log` VALUES (107, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:36:43');
INSERT INTO `sys_log` VALUES (108, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:37:05');
INSERT INTO `sys_log` VALUES (109, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:37:05');
INSERT INTO `sys_log` VALUES (110, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:37:06');
INSERT INTO `sys_log` VALUES (111, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:39:39');
INSERT INTO `sys_log` VALUES (112, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:39:39');
INSERT INTO `sys_log` VALUES (113, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:39:40');
INSERT INTO `sys_log` VALUES (114, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:41:52');
INSERT INTO `sys_log` VALUES (115, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:41:52');
INSERT INTO `sys_log` VALUES (116, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:41:52');
INSERT INTO `sys_log` VALUES (117, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:42:32');
INSERT INTO `sys_log` VALUES (118, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:42:32');
INSERT INTO `sys_log` VALUES (119, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:42:32');
INSERT INTO `sys_log` VALUES (120, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:12');
INSERT INTO `sys_log` VALUES (121, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:12');
INSERT INTO `sys_log` VALUES (122, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:12');
INSERT INTO `sys_log` VALUES (123, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:38');
INSERT INTO `sys_log` VALUES (124, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:38');
INSERT INTO `sys_log` VALUES (125, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:38');
INSERT INTO `sys_log` VALUES (126, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:49');
INSERT INTO `sys_log` VALUES (127, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:49');
INSERT INTO `sys_log` VALUES (128, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:43:49');
INSERT INTO `sys_log` VALUES (129, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:01');
INSERT INTO `sys_log` VALUES (130, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:01');
INSERT INTO `sys_log` VALUES (131, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:01');
INSERT INTO `sys_log` VALUES (132, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:24');
INSERT INTO `sys_log` VALUES (133, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:24');
INSERT INTO `sys_log` VALUES (134, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:25');
INSERT INTO `sys_log` VALUES (135, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:40');
INSERT INTO `sys_log` VALUES (136, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:40');
INSERT INTO `sys_log` VALUES (137, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:40');
INSERT INTO `sys_log` VALUES (138, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:51');
INSERT INTO `sys_log` VALUES (139, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:51');
INSERT INTO `sys_log` VALUES (140, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:44:52');
INSERT INTO `sys_log` VALUES (141, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:06');
INSERT INTO `sys_log` VALUES (142, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:06');
INSERT INTO `sys_log` VALUES (143, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:06');
INSERT INTO `sys_log` VALUES (144, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:20');
INSERT INTO `sys_log` VALUES (145, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:20');
INSERT INTO `sys_log` VALUES (146, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:45:20');
INSERT INTO `sys_log` VALUES (147, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:49:00');
INSERT INTO `sys_log` VALUES (148, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:49:14');
INSERT INTO `sys_log` VALUES (149, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:49:14');
INSERT INTO `sys_log` VALUES (150, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:49:14');
INSERT INTO `sys_log` VALUES (151, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:50:37');
INSERT INTO `sys_log` VALUES (152, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:50:41');
INSERT INTO `sys_log` VALUES (153, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:50:41');
INSERT INTO `sys_log` VALUES (154, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:50:42');
INSERT INTO `sys_log` VALUES (155, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:51:19');
INSERT INTO `sys_log` VALUES (156, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:51:25');
INSERT INTO `sys_log` VALUES (157, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:51:25');
INSERT INTO `sys_log` VALUES (158, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:51:26');
INSERT INTO `sys_log` VALUES (159, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:56:59');
INSERT INTO `sys_log` VALUES (160, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:03');
INSERT INTO `sys_log` VALUES (161, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:03');
INSERT INTO `sys_log` VALUES (162, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:04');
INSERT INTO `sys_log` VALUES (163, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:25');
INSERT INTO `sys_log` VALUES (164, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:41');
INSERT INTO `sys_log` VALUES (165, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:47');
INSERT INTO `sys_log` VALUES (166, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:47');
INSERT INTO `sys_log` VALUES (167, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:57:47');
INSERT INTO `sys_log` VALUES (168, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:58:04');
INSERT INTO `sys_log` VALUES (169, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:58:51');
INSERT INTO `sys_log` VALUES (170, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:59:26');
INSERT INTO `sys_log` VALUES (171, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:59:30');
INSERT INTO `sys_log` VALUES (172, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:59:30');
INSERT INTO `sys_log` VALUES (173, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:59:31');
INSERT INTO `sys_log` VALUES (174, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 22:59:45');
INSERT INTO `sys_log` VALUES (175, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-09 23:02:30');
INSERT INTO `sys_log` VALUES (176, '???????????????', 'INFO', 'fun.yizhierha.modules.security.controller.AuthorizationController.getCode()', '', '192.168.56.1', 302, '', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:37');
INSERT INTO `sys_log` VALUES (177, '???????????????', 'INFO', 'fun.yizhierha.modules.security.controller.AuthorizationController.getCode()', '', '192.168.56.1', 13, '', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:37');
INSERT INTO `sys_log` VALUES (178, '???????????????', 'INFO', 'fun.yizhierha.modules.security.controller.AuthorizationController.getCode()', '', '192.168.56.1', 25, '', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:40');
INSERT INTO `sys_log` VALUES (179, '??????', 'INFO', 'fun.yizhierha.modules.security.controller.AuthorizationController.login()', '{\"uuid\":\"eh-captcha-code-key-9f3cfa43f2ba4e2bb5b571e1ac8c320b\",\"password\":\"jKukRZwFbuuBm5TizOOx+61dr7TWzTmIRP5cP2sBiMr2PH8jHtILFWiMBUijyZzaOVxqChXu8f/TSTYJXBmr8EmEin9nKoNrp7NZ5U+lZ8isK+Y7zr+OfIJRjFSZCaC71vUR0vDGSo0NXgU50c7NtNVCpG6JkmiiujOdXpC2ff4=\",\"captcha\":\"11\",\"username\":\"admin2\"}', '192.168.56.1', 324, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:42');
INSERT INTO `sys_log` VALUES (180, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 35, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:42');
INSERT INTO `sys_log` VALUES (181, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 20, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:42:49');
INSERT INTO `sys_log` VALUES (182, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:51:09');
INSERT INTO `sys_log` VALUES (183, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 13, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:51:32');
INSERT INTO `sys_log` VALUES (184, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 13:54:11');
INSERT INTO `sys_log` VALUES (185, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:03:51');
INSERT INTO `sys_log` VALUES (186, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 14, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:06:24');
INSERT INTO `sys_log` VALUES (187, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:06:24');
INSERT INTO `sys_log` VALUES (188, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 10, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:07:53');
INSERT INTO `sys_log` VALUES (189, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:07:53');
INSERT INTO `sys_log` VALUES (190, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 14, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:08:06');
INSERT INTO `sys_log` VALUES (191, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:08:06');
INSERT INTO `sys_log` VALUES (192, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:08:06');
INSERT INTO `sys_log` VALUES (193, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 28, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:12:24');
INSERT INTO `sys_log` VALUES (194, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:12:24');
INSERT INTO `sys_log` VALUES (195, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:14:08');
INSERT INTO `sys_log` VALUES (196, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:14:09');
INSERT INTO `sys_log` VALUES (197, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:14:10');
INSERT INTO `sys_log` VALUES (198, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:14:24');
INSERT INTO `sys_log` VALUES (199, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:16:15');
INSERT INTO `sys_log` VALUES (200, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:23:37');
INSERT INTO `sys_log` VALUES (201, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:23:40');
INSERT INTO `sys_log` VALUES (202, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:23:40');
INSERT INTO `sys_log` VALUES (203, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:23:40');
INSERT INTO `sys_log` VALUES (204, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 10, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:28:35');
INSERT INTO `sys_log` VALUES (205, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:28:35');
INSERT INTO `sys_log` VALUES (206, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:28:36');
INSERT INTO `sys_log` VALUES (207, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:29:37');
INSERT INTO `sys_log` VALUES (208, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 15, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:29:48');
INSERT INTO `sys_log` VALUES (209, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:29:48');
INSERT INTO `sys_log` VALUES (210, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:29:49');
INSERT INTO `sys_log` VALUES (211, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:36:05');
INSERT INTO `sys_log` VALUES (212, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:36:15');
INSERT INTO `sys_log` VALUES (213, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 9, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:36:17');
INSERT INTO `sys_log` VALUES (214, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:36:17');
INSERT INTO `sys_log` VALUES (215, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:36:22');
INSERT INTO `sys_log` VALUES (216, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:37:06');
INSERT INTO `sys_log` VALUES (217, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:37:42');
INSERT INTO `sys_log` VALUES (218, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:37:42');
INSERT INTO `sys_log` VALUES (219, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:37:42');
INSERT INTO `sys_log` VALUES (220, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:39:21');
INSERT INTO `sys_log` VALUES (221, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:39:23');
INSERT INTO `sys_log` VALUES (222, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:39:23');
INSERT INTO `sys_log` VALUES (223, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:39:24');
INSERT INTO `sys_log` VALUES (224, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:09');
INSERT INTO `sys_log` VALUES (225, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:10');
INSERT INTO `sys_log` VALUES (226, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:11');
INSERT INTO `sys_log` VALUES (227, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:11');
INSERT INTO `sys_log` VALUES (228, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:12');
INSERT INTO `sys_log` VALUES (229, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:41');
INSERT INTO `sys_log` VALUES (230, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:48');
INSERT INTO `sys_log` VALUES (231, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:48');
INSERT INTO `sys_log` VALUES (232, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:42:48');
INSERT INTO `sys_log` VALUES (233, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:43:11');
INSERT INTO `sys_log` VALUES (234, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:43:16');
INSERT INTO `sys_log` VALUES (235, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:43:16');
INSERT INTO `sys_log` VALUES (236, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:43:17');
INSERT INTO `sys_log` VALUES (237, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:44:01');
INSERT INTO `sys_log` VALUES (238, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:44:09');
INSERT INTO `sys_log` VALUES (239, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 1, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:44:09');
INSERT INTO `sys_log` VALUES (240, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:44:09');
INSERT INTO `sys_log` VALUES (241, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:45:14');
INSERT INTO `sys_log` VALUES (242, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:45:16');
INSERT INTO `sys_log` VALUES (243, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:45:16');
INSERT INTO `sys_log` VALUES (244, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:45:16');
INSERT INTO `sys_log` VALUES (245, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:46:41');
INSERT INTO `sys_log` VALUES (246, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:20');
INSERT INTO `sys_log` VALUES (247, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:27');
INSERT INTO `sys_log` VALUES (248, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:29');
INSERT INTO `sys_log` VALUES (249, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:29');
INSERT INTO `sys_log` VALUES (250, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:30');
INSERT INTO `sys_log` VALUES (251, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:45');
INSERT INTO `sys_log` VALUES (252, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:46');
INSERT INTO `sys_log` VALUES (253, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:46');
INSERT INTO `sys_log` VALUES (254, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:47:47');
INSERT INTO `sys_log` VALUES (255, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:48:32');
INSERT INTO `sys_log` VALUES (256, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:48:47');
INSERT INTO `sys_log` VALUES (257, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:49:09');
INSERT INTO `sys_log` VALUES (258, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:49:14');
INSERT INTO `sys_log` VALUES (259, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:49:14');
INSERT INTO `sys_log` VALUES (260, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:49:14');
INSERT INTO `sys_log` VALUES (261, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:49:35');
INSERT INTO `sys_log` VALUES (262, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:50:06');
INSERT INTO `sys_log` VALUES (263, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:50:10');
INSERT INTO `sys_log` VALUES (264, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:50:10');
INSERT INTO `sys_log` VALUES (265, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:50:11');
INSERT INTO `sys_log` VALUES (266, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:50:41');
INSERT INTO `sys_log` VALUES (267, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:51:47');
INSERT INTO `sys_log` VALUES (268, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:51:53');
INSERT INTO `sys_log` VALUES (269, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:51:57');
INSERT INTO `sys_log` VALUES (270, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:51:57');
INSERT INTO `sys_log` VALUES (271, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:51:57');
INSERT INTO `sys_log` VALUES (272, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:52:12');
INSERT INTO `sys_log` VALUES (273, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:52:14');
INSERT INTO `sys_log` VALUES (274, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:52:14');
INSERT INTO `sys_log` VALUES (275, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:52:14');
INSERT INTO `sys_log` VALUES (276, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:53:22');
INSERT INTO `sys_log` VALUES (277, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:53:50');
INSERT INTO `sys_log` VALUES (278, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:53:54');
INSERT INTO `sys_log` VALUES (279, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:53:54');
INSERT INTO `sys_log` VALUES (280, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:53:55');
INSERT INTO `sys_log` VALUES (281, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:54:09');
INSERT INTO `sys_log` VALUES (282, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:54:09');
INSERT INTO `sys_log` VALUES (283, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:54:10');
INSERT INTO `sys_log` VALUES (284, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:54:59');
INSERT INTO `sys_log` VALUES (285, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:55:01');
INSERT INTO `sys_log` VALUES (286, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:55:01');
INSERT INTO `sys_log` VALUES (287, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:55:01');
INSERT INTO `sys_log` VALUES (288, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:55:59');
INSERT INTO `sys_log` VALUES (289, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:57:38');
INSERT INTO `sys_log` VALUES (290, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:57:55');
INSERT INTO `sys_log` VALUES (291, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:12');
INSERT INTO `sys_log` VALUES (292, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:14');
INSERT INTO `sys_log` VALUES (293, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:14');
INSERT INTO `sys_log` VALUES (294, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:14');
INSERT INTO `sys_log` VALUES (295, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:45');
INSERT INTO `sys_log` VALUES (296, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:49');
INSERT INTO `sys_log` VALUES (297, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:49');
INSERT INTO `sys_log` VALUES (298, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 14:59:49');
INSERT INTO `sys_log` VALUES (299, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:00:06');
INSERT INTO `sys_log` VALUES (300, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:00:27');
INSERT INTO `sys_log` VALUES (301, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:01:29');
INSERT INTO `sys_log` VALUES (302, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:01');
INSERT INTO `sys_log` VALUES (303, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:11');
INSERT INTO `sys_log` VALUES (304, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:34');
INSERT INTO `sys_log` VALUES (305, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:44');
INSERT INTO `sys_log` VALUES (306, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:44');
INSERT INTO `sys_log` VALUES (307, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:02:45');
INSERT INTO `sys_log` VALUES (308, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:08');
INSERT INTO `sys_log` VALUES (309, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:12');
INSERT INTO `sys_log` VALUES (310, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:12');
INSERT INTO `sys_log` VALUES (311, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:12');
INSERT INTO `sys_log` VALUES (312, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:48');
INSERT INTO `sys_log` VALUES (313, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:03:59');
INSERT INTO `sys_log` VALUES (314, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:04:28');
INSERT INTO `sys_log` VALUES (315, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:04:51');
INSERT INTO `sys_log` VALUES (316, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:05:35');
INSERT INTO `sys_log` VALUES (317, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:09:00');
INSERT INTO `sys_log` VALUES (318, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:09:55');
INSERT INTO `sys_log` VALUES (319, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:09:55');
INSERT INTO `sys_log` VALUES (320, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:06');
INSERT INTO `sys_log` VALUES (321, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:06');
INSERT INTO `sys_log` VALUES (322, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:07');
INSERT INTO `sys_log` VALUES (323, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:35');
INSERT INTO `sys_log` VALUES (324, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:40');
INSERT INTO `sys_log` VALUES (325, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:10:58');
INSERT INTO `sys_log` VALUES (326, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:11:22');
INSERT INTO `sys_log` VALUES (327, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:12:38');
INSERT INTO `sys_log` VALUES (328, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:12:43');
INSERT INTO `sys_log` VALUES (329, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:12:43');
INSERT INTO `sys_log` VALUES (330, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:12:44');
INSERT INTO `sys_log` VALUES (331, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:13:16');
INSERT INTO `sys_log` VALUES (332, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:13:31');
INSERT INTO `sys_log` VALUES (333, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:13:52');
INSERT INTO `sys_log` VALUES (334, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:13:57');
INSERT INTO `sys_log` VALUES (335, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:14:07');
INSERT INTO `sys_log` VALUES (336, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:14:11');
INSERT INTO `sys_log` VALUES (337, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:15:25');
INSERT INTO `sys_log` VALUES (338, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:15:31');
INSERT INTO `sys_log` VALUES (339, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 13, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:18:41');
INSERT INTO `sys_log` VALUES (340, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:19:02');
INSERT INTO `sys_log` VALUES (341, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:19:43');
INSERT INTO `sys_log` VALUES (342, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:20:04');
INSERT INTO `sys_log` VALUES (343, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:20:46');
INSERT INTO `sys_log` VALUES (344, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:20:58');
INSERT INTO `sys_log` VALUES (345, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:23:40');
INSERT INTO `sys_log` VALUES (346, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:23:44');
INSERT INTO `sys_log` VALUES (347, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:23:55');
INSERT INTO `sys_log` VALUES (348, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:24:02');
INSERT INTO `sys_log` VALUES (349, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:24:47');
INSERT INTO `sys_log` VALUES (350, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:25:02');
INSERT INTO `sys_log` VALUES (351, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:26:30');
INSERT INTO `sys_log` VALUES (352, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:26:34');
INSERT INTO `sys_log` VALUES (353, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 15, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:30:09');
INSERT INTO `sys_log` VALUES (354, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:30:09');
INSERT INTO `sys_log` VALUES (355, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:30:10');
INSERT INTO `sys_log` VALUES (356, '???????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTables()', '[{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 21, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:32:55');
INSERT INTO `sys_log` VALUES (357, '???????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTables()', '[{\"pageSize\":10,\"currentPage\":3}]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:32:56');
INSERT INTO `sys_log` VALUES (358, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', 'sys_job', '192.168.56.1', 17, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:04');
INSERT INTO `sys_log` VALUES (359, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', NULL, '192.168.56.1', 14, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:04');
INSERT INTO `sys_log` VALUES (360, '????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableColumns()', 'sys_job', '192.168.56.1', 76, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:04');
INSERT INTO `sys_log` VALUES (361, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictController.list()', '[{},{\"pageSize\":9999,\"currentPage\":1}]', '192.168.56.1', 19, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:05');
INSERT INTO `sys_log` VALUES (362, '???????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTables()', '[{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 14, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:13');
INSERT INTO `sys_log` VALUES (363, '???????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTables()', '[{\"pageSize\":10,\"currentPage\":3}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:17');
INSERT INTO `sys_log` VALUES (364, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', 'sys_log', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:20');
INSERT INTO `sys_log` VALUES (365, '????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableColumns()', 'sys_log', '192.168.56.1', 7, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:20');
INSERT INTO `sys_log` VALUES (366, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', NULL, '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:20');
INSERT INTO `sys_log` VALUES (367, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictController.list()', '[{},{\"pageSize\":9999,\"currentPage\":1}]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:20');
INSERT INTO `sys_log` VALUES (368, '????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.updateTableColumns()', '[{\"formType\":\"?????????[??????]\",\"id\":649}]', '192.168.56.1', 30, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:37');
INSERT INTO `sys_log` VALUES (369, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', 'sys_log', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:38');
INSERT INTO `sys_log` VALUES (370, '????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTableGenConfig()', NULL, '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:38');
INSERT INTO `sys_log` VALUES (371, '???????????????????????????????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.editOrSaveTableGenConfig()', '{\"author\":\"??????\",\"moduleName\":\"erha-admin-monitor\",\"apiAlias\":\"???????????????????????????\",\"pack\":\"fun.yizhierha.monitor\",\"cover\":true,\"path\":\"monitor/log\",\"configId\":12,\"apiPath\":\"monitor/log\"}', '192.168.56.1', 19, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:38');
INSERT INTO `sys_log` VALUES (372, '????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.GenerateController.preview()', 'sys_log', '192.168.56.1', 306, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:33:41');
INSERT INTO `sys_log` VALUES (373, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:34:09');
INSERT INTO `sys_log` VALUES (374, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:35:20');
INSERT INTO `sys_log` VALUES (375, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:35:20');
INSERT INTO `sys_log` VALUES (376, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:35:21');
INSERT INTO `sys_log` VALUES (377, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 90, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:36:59');
INSERT INTO `sys_log` VALUES (378, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 2, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:36:59');
INSERT INTO `sys_log` VALUES (379, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 18, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:36:59');
INSERT INTO `sys_log` VALUES (380, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"dept_status\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:37:19');
INSERT INTO `sys_log` VALUES (381, '????????????', 'ERROR', 'fun.yizhierha.modules.system.controller.DeptController.getDept()', '[{},{\"pageSize\":999,\"currentPage\":1}]', '192.168.56.1', 14, 'admin2', '??????IP', 'Chrome 108', 'java.lang.ArithmeticException: / by zero\r\n	at fun.yizhierha.modules.system.controller.DeptController.getDept(DeptController.java:53)\r\n	at fun.yizhierha.modules.system.controller.DeptController$$FastClassBySpringCGLIB$$db1d5a47.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\r\n	at fun.yizhierha.monitor.aspect.LogAspect.logAround(LogAspect.java:70)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:634)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:624)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:61)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\r\n	at fun.yizhierha.modules.system.controller.DeptController$$EnhancerBySpringCGLIB$$5960a0ba.getDept(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1067)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:111)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:121)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:115)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at fun.yizhierha.modules.security.security.TokenFilter.doFilter(TokenFilter.java:66)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:750)\r\n', '2022-12-10 15:37:19');
INSERT INTO `sys_log` VALUES (382, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:37:21');
INSERT INTO `sys_log` VALUES (383, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 17, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:39:21');
INSERT INTO `sys_log` VALUES (384, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:42:30');
INSERT INTO `sys_log` VALUES (385, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:43:18');
INSERT INTO `sys_log` VALUES (386, '???????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.retrieve()', '', '192.168.56.1', 19, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:43:28');
INSERT INTO `sys_log` VALUES (387, '????????????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.UserController.info()', '', '192.168.56.1', 0, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:43:28');
INSERT INTO `sys_log` VALUES (388, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 4, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:43:29');
INSERT INTO `sys_log` VALUES (389, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:44:08');
INSERT INTO `sys_log` VALUES (390, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 5, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:45:39');
INSERT INTO `sys_log` VALUES (391, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 3, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:46:59');
INSERT INTO `sys_log` VALUES (392, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 22, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:24');
INSERT INTO `sys_log` VALUES (393, '????????????', 'INFO', 'fun.yizhierha.modules.system.controller.MenuController.list()', '[{},{\"pageSize\":999,\"currentPage\":1}]', '192.168.56.1', 29, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:27');
INSERT INTO `sys_log` VALUES (394, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 6, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:30');
INSERT INTO `sys_log` VALUES (395, '??????????????????', 'INFO', 'fun.yizhierha.monitor.controller.SysLogController.del()', '[48,49,50,51,52,53,54,45,46,47]', '192.168.56.1', 17, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:38');
INSERT INTO `sys_log` VALUES (396, '??????????????????', 'INFO', 'fun.yizhierha.monitor.controller.SysLogController.del()', '[64,65,56,57,58,59,60,61,62,63]', '192.168.56.1', 8, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:44');
INSERT INTO `sys_log` VALUES (397, '???????????????', 'INFO', 'fun.yizhierha.tools.generate.controller.TableController.listTables()', '[{\"pageSize\":10,\"currentPage\":1}]', '192.168.56.1', 25, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:49:56');
INSERT INTO `sys_log` VALUES (398, '??????????????????', 'INFO', 'fun.yizhierha.modules.system.controller.DictDetailController.list()', '[\"tools_log_type\",{}]', '192.168.56.1', 12, 'admin2', '??????IP', 'Chrome 108', NULL, '2022-12-10 15:50:41');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `pid` bigint(20) NULL DEFAULT NULL COMMENT '????????????ID',
                             `sub_count` int(5) NULL DEFAULT 0 COMMENT '???????????????',
                             `type` int(11) NULL DEFAULT NULL COMMENT '????????????',
                             `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `locale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???????????????',
                             `order` int(5) NULL DEFAULT NULL COMMENT '??????',
                             `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `i_frame` bit(1) NULL DEFAULT NULL COMMENT '????????????',
                             `cache` bit(1) NULL DEFAULT b'0' COMMENT '??????',
                             `hidden` bit(1) NULL DEFAULT b'0' COMMENT '??????',
                             `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             PRIMARY KEY (`menu_id`) USING BTREE,
                             UNIQUE INDEX `uniq_title`(`title`) USING BTREE,
                             INDEX `inx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (117, NULL, 7, 0, '????????????', 'System', 'menu.system', 0, 'icon-settings', NULL, b'0', b'1', b'0', NULL, 'admin', 'admin2', '2022-09-11 22:08:30', '2022-09-20 19:50:48');
INSERT INTO `sys_menu` VALUES (118, 117, 3, 1, '????????????', 'User', 'menu.system.user', 0, 'icon-user', NULL, b'0', b'1', b'0', 'user:list', 'admin', 'admin', '2022-09-11 22:08:30', '2022-09-11 22:08:30');
INSERT INTO `sys_menu` VALUES (119, 117, 3, 1, '????????????', 'Role', 'menu.system.role', 1, 'icon-relation', NULL, b'0', b'1', b'0', 'role:list', 'admin', 'admin', '2022-09-11 22:08:30', '2022-09-11 22:08:30');
INSERT INTO `sys_menu` VALUES (120, 118, 0, 2, '????????????', NULL, NULL, 1, NULL, NULL, b'0', b'0', b'0', 'user:add', 'admin', 'admin2', '2022-09-14 19:59:36', '2022-09-20 19:51:22');
INSERT INTO `sys_menu` VALUES (121, 118, 0, 2, '????????????', NULL, NULL, 4, NULL, NULL, b'0', b'0', b'0', 'user:edit', 'admin2', 'admin2', '2022-09-18 23:12:17', '2022-09-20 19:57:03');
INSERT INTO `sys_menu` VALUES (122, 118, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'user:del', 'admin2', 'admin2', '2022-09-18 23:13:54', '2022-09-20 19:51:07');
INSERT INTO `sys_menu` VALUES (123, 119, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'role:add', 'admin2', NULL, '2022-09-18 23:15:10', NULL);
INSERT INTO `sys_menu` VALUES (124, 119, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'role:edit', 'admin2', NULL, '2022-09-18 23:15:43', NULL);
INSERT INTO `sys_menu` VALUES (125, 119, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'role:del', 'admin2', NULL, '2022-09-18 23:15:59', NULL);
INSERT INTO `sys_menu` VALUES (126, 117, 3, 1, '????????????', 'Dept', 'menu.system.dept', 3, 'icon-home', NULL, b'0', b'0', b'0', 'dept:list', 'admin2', 'admin2', '2022-09-18 23:22:59', '2022-09-25 23:07:49');
INSERT INTO `sys_menu` VALUES (128, 117, 3, 1, '????????????', 'Job', 'menu.system.job', 4, 'icon-bookmark', NULL, b'0', b'1', b'0', 'job:list', 'admin2', 'admin2', '2022-09-19 22:07:02', '2022-09-25 23:07:49');
INSERT INTO `sys_menu` VALUES (129, 117, 3, 1, '????????????', 'Dict', 'menu.system.dict', 5, 'icon-book', NULL, b'0', b'1', b'0', 'dict:list', 'admin2', 'admin2', '2022-09-19 22:08:11', '2022-09-25 23:07:49');
INSERT INTO `sys_menu` VALUES (130, 117, 3, 1, '????????????', 'Menu', 'menu.system.menu', 6, 'icon-menu', NULL, b'0', b'0', b'0', 'menu:list', 'admin2', 'admin2', '2022-09-19 22:09:18', '2022-09-25 23:07:49');
INSERT INTO `sys_menu` VALUES (132, 126, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dept:add', 'admin2', NULL, '2022-09-19 22:11:32', NULL);
INSERT INTO `sys_menu` VALUES (133, 126, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dept:edit', 'admin2', NULL, '2022-09-19 22:11:57', NULL);
INSERT INTO `sys_menu` VALUES (134, 126, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dept:del', 'admin2', NULL, '2022-09-19 22:12:17', NULL);
INSERT INTO `sys_menu` VALUES (135, 128, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'job:add', 'admin2', NULL, '2022-09-19 22:12:52', NULL);
INSERT INTO `sys_menu` VALUES (136, 128, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'job:edit', 'admin2', NULL, '2022-09-19 22:13:28', NULL);
INSERT INTO `sys_menu` VALUES (137, 128, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'job:del', 'admin2', NULL, '2022-09-19 22:13:52', NULL);
INSERT INTO `sys_menu` VALUES (138, 129, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dict:add', 'admin2', NULL, '2022-09-19 22:14:44', NULL);
INSERT INTO `sys_menu` VALUES (139, 129, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dict:edit', 'admin2', NULL, '2022-09-19 22:15:00', NULL);
INSERT INTO `sys_menu` VALUES (140, 129, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'dict:del', 'admin2', NULL, '2022-09-19 22:15:26', NULL);
INSERT INTO `sys_menu` VALUES (141, 130, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'menu:add', 'admin2', NULL, '2022-09-19 22:15:55', NULL);
INSERT INTO `sys_menu` VALUES (142, 130, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'menu:edit', 'admin2', NULL, '2022-09-19 22:16:32', NULL);
INSERT INTO `sys_menu` VALUES (143, 130, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'menu:del', 'admin2', NULL, '2022-09-19 22:16:46', NULL);
INSERT INTO `sys_menu` VALUES (146, 117, 0, 1, '????????????', 'Quartz', 'menu.system.quartz', 7, 'icon-schedule', NULL, b'0', b'0', b'0', 'quartz:list', 'admin2', 'admin2', '2022-10-13 22:47:44', '2022-10-13 22:52:21');
INSERT INTO `sys_menu` VALUES (147, NULL, 1, 0, '????????????', 'Tools', 'menu.tools', 5, 'icon-tool', NULL, b'0', b'0', b'0', NULL, 'admin2', 'admin2', '2022-11-05 14:24:10', '2022-12-02 11:59:44');
INSERT INTO `sys_menu` VALUES (148, 147, 0, 1, '????????????', 'Generate', 'menu.tools.generate', 0, 'icon-code', NULL, b'0', b'0', b'0', NULL, 'admin2', NULL, '2022-11-05 14:25:33', NULL);
INSERT INTO `sys_menu` VALUES (173, NULL, 2, 0, '????????????', 'Monitor', 'menu.monitor', 1, 'icon-computer', NULL, b'0', b'0', b'0', NULL, 'SYSTEM', 'admin2', '2022-12-02 10:41:50', '2022-12-02 11:59:44');
INSERT INTO `sys_menu` VALUES (174, 173, 3, 1, '????????????', 'Onlineuser', 'menu.monitor.onlineuser', 0, 'icon-user-group', NULL, b'0', b'0', b'0', 'onlineUser:list', 'SYSTEM', 'admin2', '2022-12-02 10:41:50', '2022-12-02 11:58:10');
INSERT INTO `sys_menu` VALUES (177, 174, 0, 2, '????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'onlineUser:del', 'SYSTEM', 'admin2', '2022-12-02 10:41:50', '2022-12-02 11:59:12');
INSERT INTO `sys_menu` VALUES (186, 173, 3, 1, '????????????', 'Log', 'menu.monitor.log', 0, 'icon-archive', NULL, b'0', b'0', b'0', 'sysLog:list', 'SYSTEM', 'admin2', '2022-12-09 20:47:13', '2022-12-09 20:55:27');
INSERT INTO `sys_menu` VALUES (189, 186, 0, 2, '??????????????????', NULL, NULL, 0, NULL, NULL, b'0', b'0', b'0', 'sysLog:del', 'SYSTEM', NULL, '2022-12-09 20:47:13', NULL);

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
                                   `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean??????',
                                   `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron ?????????',
                                   `is_pause` bit(1) NULL DEFAULT NULL COMMENT '?????????1?????????0??????',
                                   `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                   `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                   `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                   `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                   `person_in_charge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                   `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                   `sub_task` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????ID',
                                   `pause_after_failure` bit(1) NULL DEFAULT NULL COMMENT '???????????????????????????',
                                   `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                   `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                   `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                   `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                   PRIMARY KEY (`job_id`) USING BTREE,
                                   INDEX `inx_is_pause`(`is_pause`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '??????1??????', 'run1', 'apple,banana', '???????????????????????????json', '??????', NULL, NULL, b'0', NULL, 'admin2', '2019-08-22 14:08:29', '2022-10-31 23:01:08');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '??????', 'run', '', '???????????????', 'Zheng Jie', '', '5,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-24 14:48:12');
INSERT INTO `sys_quartz_job` VALUES (5, 'Test', '0/5 * * * * ?', b'1', '??????????????????', 'run', 'awdas', '??????', 'test', '', NULL, b'1', 'admin', 'admin2', '2020-05-05 20:32:41', '2022-10-31 22:09:11');
INSERT INTO `sys_quartz_job` VALUES (6, 'testTask', '0/5 * * * * ?', b'1', '??????3', 'run2', NULL, '??????3', 'Zheng Jie', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');
INSERT INTO `sys_quartz_job` VALUES (7, 'testTask', '0/10 * * * * ?', b'1', '??????????????????', 'print', 'abc', '????????????String???????????????', '?????????', NULL, NULL, b'1', 'admin2', 'admin2', '2022-11-04 10:44:52', '2022-11-04 10:46:36');

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log`  (
                                   `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                                   `is_success` bit(1) NULL DEFAULT NULL,
                                   `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `time` bigint(20) NULL DEFAULT NULL,
                                   `create_time` datetime(0) NULL DEFAULT NULL,
                                   `exec_time` datetime(0) NULL DEFAULT NULL,
                                   PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_quartz_log
-- ----------------------------
INSERT INTO `sys_quartz_log` VALUES (1, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (2, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (3, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (4, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (5, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (6, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (7, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (8, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (9, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (10, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (11, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 2, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (12, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (13, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (14, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (15, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (16, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (17, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (18, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (19, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (20, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (21, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (22, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (23, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (24, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (25, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (26, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 2, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (27, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'test', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (28, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (29, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (30, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (31, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (32, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (33, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (34, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (35, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (36, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (37, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (38, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (39, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (40, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (41, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (42, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (43, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (44, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (45, 'Test', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:49)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'run', NULL, 1, '2020-05-05 20:32:41', '2022-10-31 20:32:41');
INSERT INTO `sys_quartz_log` VALUES (46, 'Test', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:49)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'run', NULL, 0, '2020-05-05 20:32:41', '2022-10-31 22:03:01');
INSERT INTO `sys_quartz_log` VALUES (47, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 1, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (48, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 1, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (49, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (50, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (51, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (52, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (53, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (54, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (55, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (56, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 1, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (57, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (58, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (59, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 1, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (60, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (61, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (62, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (63, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (64, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 1, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (65, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 0, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (66, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 0, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (67, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (68, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????', 'run', '', 13, '2019-09-26 16:44:39', NULL);
INSERT INTO `sys_quartz_log` VALUES (69, 'Test', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:49)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'run', NULL, 4, '2020-05-05 20:32:41', NULL);
INSERT INTO `sys_quartz_log` VALUES (70, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 2, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (71, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (72, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 2, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (73, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (74, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', '??????????????????', 1, '2019-08-22 14:08:29', NULL);
INSERT INTO `sys_quartz_log` VALUES (75, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????3', 'run2', NULL, 1, '2020-05-05 20:35:41', '2022-10-31 22:08:50');
INSERT INTO `sys_quartz_log` VALUES (76, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????3', 'run2', NULL, 1, '2020-05-05 20:35:41', '2022-10-31 22:08:55');
INSERT INTO `sys_quartz_log` VALUES (77, 'Test', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:51)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'run', 'awdas', 2, '2020-05-05 20:32:41', '2022-10-31 22:09:18');
INSERT INTO `sys_quartz_log` VALUES (78, 'Test', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:51)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'run', 'awdas', 0, '2020-05-05 20:32:41', '2022-10-31 22:09:45');
INSERT INTO `sys_quartz_log` VALUES (79, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 2, '2019-08-22 14:08:29', '2022-10-31 23:01:10');
INSERT INTO `sys_quartz_log` VALUES (80, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 0, '2019-08-22 14:08:29', '2022-10-31 23:01:15');
INSERT INTO `sys_quartz_log` VALUES (81, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 0, '2019-08-22 14:08:29', '2022-10-31 23:01:20');
INSERT INTO `sys_quartz_log` VALUES (82, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 1, '2019-08-22 14:08:29', '2022-10-31 23:01:25');
INSERT INTO `sys_quartz_log` VALUES (83, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 1, '2019-08-22 14:08:29', '2022-10-31 23:01:30');
INSERT INTO `sys_quartz_log` VALUES (84, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 1, '2019-08-22 14:08:29', '2022-10-31 23:01:35');
INSERT INTO `sys_quartz_log` VALUES (85, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 1, '2019-08-22 14:08:29', '2022-10-31 23:01:40');
INSERT INTO `sys_quartz_log` VALUES (86, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????1??????', 'run1', 'apple,banana', 1, '2019-08-22 14:08:29', '2022-10-31 23:01:45');
INSERT INTO `sys_quartz_log` VALUES (87, 'testTask', '0/5 * * * * ?', NULL, b'1', '??????3', 'run2', NULL, 1, '2020-05-05 20:35:41', '2022-10-31 23:05:40');
INSERT INTO `sys_quartz_log` VALUES (88, 'TestTask', '0/10 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'TestTask\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:51)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'print', 'abc', 1, '2022-11-04 10:44:52', '2022-11-04 10:45:00');
INSERT INTO `sys_quartz_log` VALUES (89, 'TestTask', '0/10 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'TestTask\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)\r\n	at fun.yizhierha.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:51)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'print', 'abc', 0, '2022-11-04 10:44:52', '2022-11-04 10:45:40');
INSERT INTO `sys_quartz_log` VALUES (90, 'testTask', '0/10 * * * * ?', 'java.lang.NoSuchMethodException: fun.yizhierha.modules.system.quartz.task.TestTask.print(java.lang.String)\r\n	at java.lang.Class.getDeclaredMethod(Class.java:2130)\r\n	at fun.yizhierha.modules.system.quartz.QuartzRunnable.<init>(QuartzRunnable.java:42)\r\n	at fun.yizhierha.modules.system.quartz.ExecutionJob.executeInternal(ExecutionJob.java:51)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '??????????????????', 'print', 'abc', 0, '2022-11-04 10:44:52', '2022-11-04 10:46:40');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????',
                             `level` int(255) NULL DEFAULT NULL COMMENT '????????????',
                             `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             PRIMARY KEY (`role_id`) USING BTREE,
                             UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
                             INDEX `role_name_index`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '?????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '???????????????', 1, '-', '??????', NULL, 'admin2', '2018-11-23 11:04:37', '2022-08-31 16:05:13');
INSERT INTO `sys_role` VALUES (2, '????????????', 3, '????????????????????????', '?????????', NULL, 'admin2', '2018-11-23 13:09:06', '2022-08-31 16:13:39');
INSERT INTO `sys_role` VALUES (9, '????????????2', 11, '????????????1', '?????????????????????', 'admin2', 'admin2', '2022-08-27 11:22:33', '2022-09-25 23:23:50');
INSERT INTO `sys_role` VALUES (10, '????????????3', 2, '??????', '???????????????', 'admin2', 'admin2', '2022-08-27 11:25:54', '2022-08-29 19:37:25');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
                                    `role_id` bigint(20) NOT NULL,
                                    `dept_id` bigint(20) NOT NULL,
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------
INSERT INTO `sys_roles_depts` VALUES (2, 2, 16);
INSERT INTO `sys_roles_depts` VALUES (2, 6, 17);

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
                                    `menu_id` bigint(20) NOT NULL COMMENT '??????ID',
                                    `role_id` bigint(20) NOT NULL COMMENT '??????ID',
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1690 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
INSERT INTO `sys_roles_menus` VALUES (122, 9, 549);
INSERT INTO `sys_roles_menus` VALUES (120, 9, 550);
INSERT INTO `sys_roles_menus` VALUES (121, 9, 551);
INSERT INTO `sys_roles_menus` VALUES (118, 9, 552);
INSERT INTO `sys_roles_menus` VALUES (126, 9, 553);
INSERT INTO `sys_roles_menus` VALUES (132, 9, 554);
INSERT INTO `sys_roles_menus` VALUES (133, 9, 555);
INSERT INTO `sys_roles_menus` VALUES (134, 9, 556);
INSERT INTO `sys_roles_menus` VALUES (117, 9, 557);
INSERT INTO `sys_roles_menus` VALUES (117, 2, 1506);
INSERT INTO `sys_roles_menus` VALUES (126, 2, 1507);
INSERT INTO `sys_roles_menus` VALUES (132, 2, 1508);
INSERT INTO `sys_roles_menus` VALUES (138, 2, 1509);
INSERT INTO `sys_roles_menus` VALUES (129, 2, 1510);
INSERT INTO `sys_roles_menus` VALUES (128, 2, 1511);
INSERT INTO `sys_roles_menus` VALUES (135, 2, 1512);
INSERT INTO `sys_roles_menus` VALUES (118, 2, 1513);
INSERT INTO `sys_roles_menus` VALUES (130, 2, 1514);
INSERT INTO `sys_roles_menus` VALUES (141, 2, 1515);
INSERT INTO `sys_roles_menus` VALUES (173, 2, 1516);
INSERT INTO `sys_roles_menus` VALUES (174, 2, 1517);
INSERT INTO `sys_roles_menus` VALUES (177, 2, 1518);
INSERT INTO `sys_roles_menus` VALUES (128, 1, 1648);
INSERT INTO `sys_roles_menus` VALUES (129, 1, 1649);
INSERT INTO `sys_roles_menus` VALUES (130, 1, 1650);
INSERT INTO `sys_roles_menus` VALUES (132, 1, 1651);
INSERT INTO `sys_roles_menus` VALUES (133, 1, 1652);
INSERT INTO `sys_roles_menus` VALUES (134, 1, 1653);
INSERT INTO `sys_roles_menus` VALUES (135, 1, 1654);
INSERT INTO `sys_roles_menus` VALUES (136, 1, 1655);
INSERT INTO `sys_roles_menus` VALUES (137, 1, 1656);
INSERT INTO `sys_roles_menus` VALUES (138, 1, 1657);
INSERT INTO `sys_roles_menus` VALUES (139, 1, 1658);
INSERT INTO `sys_roles_menus` VALUES (140, 1, 1659);
INSERT INTO `sys_roles_menus` VALUES (141, 1, 1660);
INSERT INTO `sys_roles_menus` VALUES (142, 1, 1661);
INSERT INTO `sys_roles_menus` VALUES (143, 1, 1662);
INSERT INTO `sys_roles_menus` VALUES (146, 1, 1663);
INSERT INTO `sys_roles_menus` VALUES (147, 1, 1664);
INSERT INTO `sys_roles_menus` VALUES (148, 1, 1665);
INSERT INTO `sys_roles_menus` VALUES (153, 1, 1666);
INSERT INTO `sys_roles_menus` VALUES (154, 1, 1667);
INSERT INTO `sys_roles_menus` VALUES (155, 1, 1668);
INSERT INTO `sys_roles_menus` VALUES (156, 1, 1669);
INSERT INTO `sys_roles_menus` VALUES (157, 1, 1670);
INSERT INTO `sys_roles_menus` VALUES (159, 1, 1671);
INSERT INTO `sys_roles_menus` VALUES (160, 1, 1672);
INSERT INTO `sys_roles_menus` VALUES (161, 1, 1673);
INSERT INTO `sys_roles_menus` VALUES (162, 1, 1674);
INSERT INTO `sys_roles_menus` VALUES (173, 1, 1675);
INSERT INTO `sys_roles_menus` VALUES (174, 1, 1676);
INSERT INTO `sys_roles_menus` VALUES (177, 1, 1677);
INSERT INTO `sys_roles_menus` VALUES (117, 1, 1678);
INSERT INTO `sys_roles_menus` VALUES (118, 1, 1679);
INSERT INTO `sys_roles_menus` VALUES (119, 1, 1680);
INSERT INTO `sys_roles_menus` VALUES (120, 1, 1681);
INSERT INTO `sys_roles_menus` VALUES (121, 1, 1682);
INSERT INTO `sys_roles_menus` VALUES (122, 1, 1683);
INSERT INTO `sys_roles_menus` VALUES (186, 1, 1684);
INSERT INTO `sys_roles_menus` VALUES (123, 1, 1685);
INSERT INTO `sys_roles_menus` VALUES (124, 1, 1686);
INSERT INTO `sys_roles_menus` VALUES (125, 1, 1687);
INSERT INTO `sys_roles_menus` VALUES (189, 1, 1688);
INSERT INTO `sys_roles_menus` VALUES (126, 1, 1689);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '????????????',
                             `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `avatar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `avatar_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????????????????',
                             `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `is_admin` bit(1) NULL DEFAULT b'0' COMMENT '?????????admin??????',
                             `enabled` bit(1) NULL DEFAULT NULL COMMENT '?????????1?????????0??????',
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `pwd_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '?????????????????????',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                             `now_role_id` bigint(20) NOT NULL DEFAULT 2 COMMENT '??????????????????????????????????????????????????????',
                             PRIMARY KEY (`user_id`) USING BTREE,
                             UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
                             UNIQUE INDEX `username`(`username`) USING BTREE,
                             UNIQUE INDEX `uniq_username`(`username`) USING BTREE,
                             UNIQUE INDEX `uniq_email`(`email`) USING BTREE,
                             INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
                             INDEX `FKpq2dhypk2qgt68nauh2by22jb`(`avatar_name`) USING BTREE,
                             INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 25, 'admin', '?????????', '???', '18260974475', '11111111@qq.com', 'avatar-2022051402392086.png', 'C:\\ehadmin\\avatar\\avatar-2022051402392086.png', '$2a$10$9i.C984Nw5N.LW9n7/fdmu84MB8jA.Y2wzICyAUG1vBvcGFWFbS9G', b'1', b'1', NULL, 'admin2', '2022-05-31 16:38:31', '2018-08-23 09:11:56', '2022-12-09 22:16:23', 1);
INSERT INTO `sys_user` VALUES (2, 24, 'test', '??????2333', '???', '19991169991', '231@qq.com', NULL, NULL, '$2a$2a$10$Kt4jtUA1K8/328xYtRQ0ye8i6/C8I1Z7zN9c1sP/Rc7IGZAAsT/j6', b'0', b'1', 'admin', 'admin2', NULL, '2020-05-05 11:15:49', '2022-11-30 22:26:04', 2);
INSERT INTO `sys_user` VALUES (3, 22, 'admin2', '??????sad', '???', '17342320974', 'ads@qq.com', 'bc3-20221105011712921.jpg', 'C:\\ehadmin\\avatar\\bc3-20221105011712921.jpg', '$2a$10$Kt4jtUA1K8/328xYtRQ0ye8i6/C8I1Z7zN9c1sP/Rc7IGZAAsT/j6', b'1', b'1', 'admin', 'admin2', NULL, '2022-08-03 20:44:22', '2022-11-30 22:26:04', 1);
INSERT INTO `sys_user` VALUES (4, 8, '??????', '?????????', '???', '17342320971', 'ads2@qq.com', NULL, NULL, '$2a$10$ucE5qufBEcpnhwMqzNNnuuZZWYzrsKyIa0xRrAxlvdWKjc26gmP0i', b'0', b'1', 'admin', 'admin2', NULL, '2022-08-03 20:47:51', '2022-10-22 13:07:45', 2);
INSERT INTO `sys_user` VALUES (6, 6, '??????', '??????dsaasdafasf', '???', '17342320977', 'asd@q.swa', NULL, NULL, '$2a$10$vauz0kyCWTQrpzX5w9MkpOa2RjjBtVYkRb1FR9cBnmkjvG/7Qxyd2', b'0', b'1', 'admin', 'admin2', NULL, '2022-08-03 20:50:42', '2022-11-25 20:06:30', 2);
INSERT INTO `sys_user` VALUES (7, 22, '??????2', '??????23333333', '???', '17222320977', 'as2d@q.swa', NULL, NULL, '$2a$10$Tc4q0bQzZa/cyME44KKkH.sDNtJvILDJ8awWzeB.7F84biZBQb2pa', b'0', b'1', 'admin', 'admin2', NULL, '2022-08-03 20:51:10', '2022-10-22 13:07:41', 2);
INSERT INTO `sys_user` VALUES (9, 2, 'sd', 'sadfg', '???', '17342326666', '2@q.a', 'bc2-20221203043756890.jpg', 'C:\\ehadmin\\avatar\\bc2-20221203043756890.jpg', '$2a$10$yvvSI.ZLIw0f5uad58fJsOIKdzatRw34BieTd7YQO12o/AgmUodmq', b'0', b'1', 'admin2', 'admin2', NULL, '2022-08-05 10:05:24', '2022-09-25 23:22:14', 2);
INSERT INTO `sys_user` VALUES (10, 2, 'sdd', 'sadfg', '???', '17342326661', '2@q.aa', NULL, NULL, '$2a$10$odxYPGfCe5qrkCEVnto/CucFDmxFmjkhIfBsDbAUL8l4ZCXagPuWW', b'0', b'1', 'admin2', 'admin2', NULL, '2022-08-05 10:05:36', '2022-08-29 20:41:10', 9);
INSERT INTO `sys_user` VALUES (11, 2, 'asd', 'dsa', '???', '17342320005', '1@q.w', NULL, NULL, '$2a$10$yFXx0Nm1kFTcZlbGXsrzDuFgB2w7uFnB8XLc3D3WOMMHIxktSHDH6', b'0', b'1', 'admin2', 'admin2', NULL, '2022-08-05 14:00:25', '2022-08-27 16:19:36', 2);
INSERT INTO `sys_user` VALUES (12, 15, 'sddd', 'sadfg', '???', '17342326662', '2@q.aaa', NULL, NULL, '$2a$10$pGcBavFZUCBUjtMR0u1SHOUzTRNh7EyVUIcYtoux.jydWbNfauj/q', b'0', b'1', 'admin2', 'admin2', NULL, '2022-08-05 10:05:50', '2022-08-15 15:58:07', 1);
INSERT INTO `sys_user` VALUES (13, 6, 'apple', '???????????????', '???', '17532585121', 'asfdawd@qq.ssad', NULL, NULL, '$2a$10$/jV2WmXemw/vTfVnF0z7jeMXHO3njE40g7PmLKabSLjL1EPWt6FcO', b'0', b'1', 'admin2', NULL, NULL, '2022-09-25 23:01:13', NULL, 2);

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
                                   `user_id` bigint(20) NOT NULL COMMENT '??????ID',
                                   `job_id` bigint(20) NOT NULL COMMENT '??????ID',
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES (2, 12, 2);
INSERT INTO `sys_users_jobs` VALUES (3, 10, 4);
INSERT INTO `sys_users_jobs` VALUES (3, 8, 5);
INSERT INTO `sys_users_jobs` VALUES (4, 12, 6);
INSERT INTO `sys_users_jobs` VALUES (5, 12, 7);
INSERT INTO `sys_users_jobs` VALUES (6, 10, 8);
INSERT INTO `sys_users_jobs` VALUES (7, 10, 9);
INSERT INTO `sys_users_jobs` VALUES (9, 8, 11);
INSERT INTO `sys_users_jobs` VALUES (10, 8, 12);
INSERT INTO `sys_users_jobs` VALUES (11, 8, 13);
INSERT INTO `sys_users_jobs` VALUES (11, 10, 14);
INSERT INTO `sys_users_jobs` VALUES (12, 10, 15);
INSERT INTO `sys_users_jobs` VALUES (13, 10, 23);
INSERT INTO `sys_users_jobs` VALUES (1, 12, 26);
INSERT INTO `sys_users_jobs` VALUES (1, 11, 27);
INSERT INTO `sys_users_jobs` VALUES (1, 10, 28);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
                                    `user_id` bigint(20) NOT NULL COMMENT '??????ID',
                                    `role_id` bigint(20) NOT NULL COMMENT '??????ID',
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES (3, 2, 4);
INSERT INTO `sys_users_roles` VALUES (3, 1, 5);
INSERT INTO `sys_users_roles` VALUES (4, 2, 6);
INSERT INTO `sys_users_roles` VALUES (5, 2, 7);
INSERT INTO `sys_users_roles` VALUES (6, 2, 8);
INSERT INTO `sys_users_roles` VALUES (7, 2, 9);
INSERT INTO `sys_users_roles` VALUES (11, 2, 13);
INSERT INTO `sys_users_roles` VALUES (12, 1, 23);
INSERT INTO `sys_users_roles` VALUES (1, 1, 29);
INSERT INTO `sys_users_roles` VALUES (1, 2, 30);
INSERT INTO `sys_users_roles` VALUES (10, 9, 36);
INSERT INTO `sys_users_roles` VALUES (10, 10, 37);
INSERT INTO `sys_users_roles` VALUES (2, 2, 39);
INSERT INTO `sys_users_roles` VALUES (13, 2, 40);
INSERT INTO `sys_users_roles` VALUES (9, 2, 41);
INSERT INTO `sys_users_roles` VALUES (9, 9, 42);

-- ----------------------------
-- Table structure for test_student
-- ----------------------------
DROP TABLE IF EXISTS `test_student`;
CREATE TABLE `test_student`  (
                                 `student_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                                 `like_food` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??????????????????',
                                 `age` int(11) NOT NULL COMMENT '??????',
                                 `clazz_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '?????????',
                                 `gender` bit(1) NOT NULL COMMENT '??????(1:??????0:???)',
                                 `avg_score` decimal(65, 30) NOT NULL COMMENT '????????????',
                                 `create_time` datetime(0) NOT NULL COMMENT '????????????',
                                 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                 `enabled` bit(1) NOT NULL COMMENT '??????',
                                 `expire_time` datetime(0) NOT NULL COMMENT '??????????????????',
                                 PRIMARY KEY (`student_id`) USING BTREE,
                                 UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '?????????????????????' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_student
-- ----------------------------
INSERT INTO `test_student` VALUES (8, '?????????', '[??????, ??????]', 123, '??????', b'0', 95.600000000000000000000000000000, '2022-12-04 16:09:36', NULL, b'1', '2022-12-30 16:09:25');

-- ----------------------------
-- Table structure for tool_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_alipay_config`;
CREATE TABLE `tool_alipay_config`  (
                                       `config_id` bigint(20) NOT NULL COMMENT 'ID',
                                       `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????ID',
                                       `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                       `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????? ????????????json',
                                       `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '??????',
                                       `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '??????',
                                       `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                       PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_alipay_config
-- ----------------------------
INSERT INTO `tool_alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config`  (
                                      `config_id` bigint(20) NOT NULL COMMENT 'ID',
                                      `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                      `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???????????????SMTP??????',
                                      `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                      `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                      `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????????????????',
                                      PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_email_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage`  (
                                       `storage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????????????????',
                                       `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                       `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                       `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                       `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                       `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                       `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                       `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
                                       `create_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                       `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????',
                                       PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_local_storage
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_config`;
CREATE TABLE `tool_qiniu_config`  (
                                      `config_id` bigint(20) NOT NULL COMMENT 'ID',
                                      `access_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'accessKey',
                                      `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket ?????????',
                                      `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????????',
                                      `secret_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'secretKey',
                                      `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                      `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
                                      PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '???????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_qiniu_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_content`;
CREATE TABLE `tool_qiniu_content`  (
                                       `content_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket ?????????',
                                       `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????????????????????????????',
                                       `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????url',
                                       `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                       `update_time` datetime(0) NULL DEFAULT NULL COMMENT '????????????????????????',
                                       PRIMARY KEY (`content_id`) USING BTREE,
                                       UNIQUE INDEX `uniq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '?????????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_qiniu_content
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
