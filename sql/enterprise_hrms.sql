/*
 Navicat Premium Data Transfer

 Source Server         : 124.222.118.90
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 124.222.118.90:3306
 Source Schema         : enterprise_hrms

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 25/06/2022 03:24:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `att_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤ID',
  `emp_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `puch_time` datetime(0) NULL DEFAULT NULL COMMENT '打卡时间',
  `att_type` int(3) NULL DEFAULT NULL COMMENT '考勤类型',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `vali_flag` int(3) NULL DEFAULT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`att_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '考勤信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('0242c0fc-8810-4c08-a9d8-d4edd29e31e2', '1', NULL, 3, '1133方法111', 1, '2022-05-05 00:00:00', '2022-05-20 13:40:21');
INSERT INTO `attendance` VALUES ('593b154d-df70-4e9b-94a6-ad3088dd5ff5', '1', NULL, 3, '1133方法111', 1, '2022-05-06 00:00:00', '2022-05-20 13:40:21');
INSERT INTO `attendance` VALUES ('84a80cd5-57bf-4d31-b16e-95a0128aff19', '1', NULL, 3, '1133方法111', 1, '2022-05-04 00:00:00', '2022-05-20 13:40:21');
INSERT INTO `attendance` VALUES ('95a0128aff29', '1', NULL, 0, '21', 1, '2022-05-09 13:42:36', '2022-05-20 13:42:39');
INSERT INTO `attendance` VALUES ('95a0128aff39', '1', NULL, 1, '2', 1, '2022-05-10 13:43:25', '2022-05-20 13:43:28');

-- ----------------------------
-- Table structure for attendance_sheet
-- ----------------------------
DROP TABLE IF EXISTS `attendance_sheet`;
CREATE TABLE `attendance_sheet`  (
  `att_sh_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤单ID',
  `emp_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `att_type` int(3) NOT NULL COMMENT '考勤类型',
  `start_time` datetime(0) NOT NULL COMMENT '起始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `check_status` int(3) NULL DEFAULT NULL COMMENT '审核状态',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事由',
  `vali_flag` int(3) NULL DEFAULT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`att_sh_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance_sheet
-- ----------------------------
INSERT INTO `attendance_sheet` VALUES ('53398b77-8097-4286-8ee1-d89edc7ceda3', '1', 3, '2022-05-04 00:00:00', '2022-05-06 00:00:00', 1, '1133方法111', 1, '2022-05-20 11:54:50', '2022-05-20 13:40:20');
INSERT INTO `attendance_sheet` VALUES ('bf45477f-9e89-447b-8fa1-c0e8b90d1205', '1', 3, '2022-05-04 00:00:00', '2022-05-11 00:00:00', 0, '22334', 1, '2022-05-20 09:04:52', '2022-05-23 01:06:24');

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate`  (
  `cand_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应聘者ID',
  `recr_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '招聘ID',
  `cand_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应聘者姓名',
  `cand_gender` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应聘者性别',
  `cand_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应聘者手机号',
  `cand_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应聘信息',
  `check_status` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `vali_flag` int(3) NULL DEFAULT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`cand_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应聘者信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of candidate
-- ----------------------------
INSERT INTO `candidate` VALUES ('c1', 'r1', '应聘者1', '男', '13134342', '熟悉Java、精通MySQL', '0', 1, '2022-05-08 21:48:05', '2022-05-08 21:48:09');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `position` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门职位',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('22b61e5e-0511-42d5-8002-4234de76019b', '开发部', '部长', 1, '2022-05-16 04:01:42', '2022-05-16 04:01:42');
INSERT INTO `department` VALUES ('d0774539-8c60-4ee9-8561-b40725bf1f4f', '人力资源部', NULL, 1, '2022-05-17 01:46:37', '2022-05-17 01:46:38');
INSERT INTO `department` VALUES ('d1', '后勤部', '部长', 1, '2022-03-09 10:38:08', '2022-03-14 10:38:10');
INSERT INTO `department` VALUES ('d2', '后勤部', '副部长', 1, '2022-05-08 21:32:46', '2022-05-08 21:32:48');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `emp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工姓名',
  `emp_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `idcard_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `gender` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '凡', 'admin', '$2a$10$rdVpkecS1V.Yart/Et8wluqrUGEKp4xbwlI6RKBQgPOFYyA98EGvG', 'https://s2.loli.net/2022/05/23/f1uPaZqsS9OKYXk.jpg', '41111111111111111', '男', '18634250758', '湖南省台中市临湘市', 'ddd', '人力资源部', 'cillum dolore aliquip commodo', 1, '2022-03-14 11:59:53', '2022-05-23 05:37:37');
INSERT INTO `employee` VALUES ('1524799511390769154', '王五', '55535545', '$2a$10$iBOO21azwr40.lOfOhHue.amAm6IylJ70w95pz/eUV3UxgirPYDQO', 'https://fan223.cn/img/profile.jpg', '22344355535545345', '男', '53435', '555', '4534', '开发部', '3', 1, '2022-05-13 01:11:48', '2022-05-31 02:14:41');
INSERT INTO `employee` VALUES ('1524803701680820225', '苏苏', '66466456', '$2a$10$lSaw0iWjrnTPr8Iw8YaCzeSBxI3y1YeP2R1xB/ScY3ZYeN9MeivaO', 'https://fan223.cn/img/profile.jpg', '464666664664564', '男', '6466', '46', '46', '人力资源部', '46', 1, '2022-05-13 01:28:27', '2022-05-31 02:14:59');
INSERT INTO `employee` VALUES ('1524803878407819266', '42352535555', '63455542', '$2a$10$/NWnNHAGtZd882OUKWNdb.X2vI5vWU/irt.eZuJtevKB8IX4u50ZK', 'https://fan223.cn/img/profile.jpg', '2355236345554234', '男', '3635535', '2535345', '5355', '5', '355', 0, '2022-05-13 01:29:09', '2022-05-13 01:29:09');
INSERT INTO `employee` VALUES ('1524804109199396866', '七七', '34545534', '$2a$10$o3.CMtUc/S/eQ5Etw.n9G.vHrqOTBotkH2l2JYXd3TP1uBEsQWVfu', 'https://s2.loli.net/2022/05/31/8DahxPIm12Xw9BT.jpg', '443545345455345345', '男', '35345', '353453', '34535', '人力资源部', '355', 1, '2022-05-13 01:30:04', '2022-06-02 09:06:37');
INSERT INTO `employee` VALUES ('1524804157924626434', '三七', '53553564', '$2a$10$A/Suz47CGSpDxLTPeULAqeS0Gwu0YWzunGWYDlqjRv768FBa4GCZe', 'https://fan223.cn/img/profile.jpg', '2344535355356455545', '男', '3534534', '545345', '3553', '开发部', '554355', 1, '2022-05-13 01:30:15', '2022-05-31 02:14:36');
INSERT INTO `employee` VALUES ('1524804210957406210', '莫莉', '54543454', '$2a$10$NOrtfKNrf5nWX0gjwk6AqeUanPimglYd04l4lLwX001kCfLWktkE.', 'https://fan223.cn/img/profile.jpg', '45353454543454545', '男', '5454', '4354', '545', '人力资源部', '45454', 1, '2022-05-13 01:30:28', '2022-05-31 02:14:53');
INSERT INTO `employee` VALUES ('1524804298215706626', '阿肆', '43434343', '$2a$10$7uPiDRyCx.ResFY/sFZnkOON4aLvnQD9aKrqO.MSNq9GU17gJRPI2', 'https://fan223.cn/img/profile.jpg', '3432434343434343', '男', '3434', '3434', '3434', '后勤部', '34', 1, '2022-05-13 01:30:49', '2022-05-31 02:14:16');
INSERT INTO `employee` VALUES ('1525785569565839361', '赵六', '45545454', '$2a$10$WbMXxM.hyriU9W0LdrctSuuUJeCuU5FzFg6yuMI9Pmq5GlWtH/2eG', 'https://fan223.cn/img/profile.jpg', '143345455454545', '男', '535', '4535', '35', '开发部', '354', 1, '2022-05-15 18:30:02', '2022-05-31 02:14:31');
INSERT INTO `employee` VALUES ('1525785622988689409', '李七', '45544656', '$2a$10$zMKPw3zmYxjZ14IcbWlLQuCKjIaYhiddQGN1C63q5MLUBjTaT5UoO', 'https://fan223.cn/img/profile.jpg', '454546455446564', '男', '45', '46', '546', '后勤部', '46', 1, '2022-05-15 18:30:15', '2022-05-31 02:14:19');
INSERT INTO `employee` VALUES ('1525785729050054657', '王八', '36554645', '$2a$10$aWTH5DCARNdurxdAc9pdWu5CDjypEfzwrt7PzStMykZgbt0VNAr9y', 'https://fan223.cn/img/profile.jpg', '123345365546454', '男', '3243', '345', '354', '后勤部', '254', 1, '2022-05-15 18:30:40', '2022-05-31 02:14:13');
INSERT INTO `employee` VALUES ('1527398783479111681', '贰贰', '35345345', '$2a$10$i8NzhzHqnIZ93v/6KbY5kefYiF.ICpXBNCi7Bw8J.VfvFqD2uOf0m', 'https://fan223.cn/img/profile.jpg', '345354353453454', '男', '45', '35', '35', '人力资源部', '354', 1, '2022-05-20 05:20:22', '2022-05-31 02:15:29');
INSERT INTO `employee` VALUES ('3', '李四', '222', '$2a$10$PWnknhyzXx94Ga.JMhXF0.YELhG2umbgaFUnUYZadVifZS8xfO1Z6', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', '1111111111111111', '男', '18132104543', '吉林省嘉义市永昌县', NULL, '人力资源部', 'cillum ad', 1, '2022-03-14 15:16:39', '2022-05-31 02:15:14');

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment`  (
  `recr_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '招聘ID',
  `recr_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '招聘标题',
  `recr_require` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '招聘要求',
  `recr_number` int(3) NULL DEFAULT NULL COMMENT '招聘人数',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`recr_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '招聘信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruitment
-- ----------------------------
INSERT INTO `recruitment` VALUES ('1', '招聘Java开发工程师', '熟悉Java', 3, 1, '2022-04-26 11:47:11', '2022-04-26 11:47:11');
INSERT INTO `recruitment` VALUES ('2', '招聘Python', '2', 3, 1, '2022-04-26 13:03:23', '2022-05-15 18:51:53');
INSERT INTO `recruitment` VALUES ('232407c3-c8ee-49d0-a8a7-3e22e3759466', '得到的', '12', 3, 1, '2022-05-15 18:50:29', '2022-05-15 18:50:41');
INSERT INTO `recruitment` VALUES ('b944a8b9-64f1-40c8-b112-8d3edbe7a085', '招聘前端', '熟悉JavaScript', 3, 1, '2022-05-16 03:20:48', '2022-05-16 03:20:54');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `sal_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '薪酬ID',
  `emp_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `base_sal` decimal(16, 2) NULL DEFAULT NULL COMMENT '基础工资',
  `meritpay` decimal(16, 2) NULL DEFAULT NULL COMMENT '绩效工资',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sal_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '薪酬表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('57fd34d4-9f2b-417e-80f7-e827f48b2641', '1524799511390769154', 4000.00, 400.00, 1, '2022-04-21 05:40:01', '2022-04-21 05:40:01');
INSERT INTO `salary` VALUES ('s1', '1', 3000.00, 600.00, 1, '2022-05-08 22:01:39', '2022-05-08 22:01:42');
INSERT INTO `salary` VALUES ('s2', '1524799511390769154', 4000.00, 800.00, 1, '2022-05-21 05:37:15', '2022-05-21 05:37:19');

-- ----------------------------
-- Table structure for sys_employee_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee_role`;
CREATE TABLE `sys_employee_role`  (
  `emp_role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工角色ID',
  `emp_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`emp_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_employee_role
-- ----------------------------
INSERT INTO `sys_employee_role` VALUES ('1524383093906157570', '1524306543139516418', '3');
INSERT INTO `sys_employee_role` VALUES ('1524383093918740482', '1524306543139516418', '6');
INSERT INTO `sys_employee_role` VALUES ('1524385925958938625', '2', '1524061615696277506');
INSERT INTO `sys_employee_role` VALUES ('1524385925958938626', '2', '3');
INSERT INTO `sys_employee_role` VALUES ('1524392284490248194', '1', '3');
INSERT INTO `sys_employee_role` VALUES ('1524392284490248195', '1', '6');
INSERT INTO `sys_employee_role` VALUES ('1524749782808211457', '3', '3');
INSERT INTO `sys_employee_role` VALUES ('1527526910050123778', '1524804109199396866', '3');
INSERT INTO `sys_employee_role` VALUES ('1528423443360575489', '1524804157924626434', '1524061615696277506');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `parent_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父菜单ID，一级菜单为0',
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单组件',
  `type` int(3) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(3) NULL DEFAULT NULL COMMENT '排序号',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('07ec05b0-61bc-4c2a-891a-c12816c0fc9c', '0', '薪酬管理', '', 'salary', '', 0, 'el-icon-s-order', 4, 1, '2022-05-12 15:56:22', '2022-05-15 19:00:54');
INSERT INTO `sys_menu` VALUES ('0c8fae8c-21bd-4da4-bbc4-cf660df05b93', 'c6a8c902-b9a9-4f6f-a9e3-888c2dfa7b05', '考勤单管理', '/attendance/list', 'attendance:list', 'attendance/AttendanceList', 1, 'el-icon-s-order', 52, 1, '2022-05-17 04:21:48', '2022-05-17 04:25:25');
INSERT INTO `sys_menu` VALUES ('1', '0', '权限管理', '', 'sys:manage', '', 0, 'el-icon-s-grid', 1, 1, '2021-01-15 18:58:18', '2022-05-12 22:16:15');
INSERT INTO `sys_menu` VALUES ('10', '2', '修改用户', NULL, 'employee:update', NULL, 2, NULL, 112, 1, '2021-01-17 21:49:03', '2022-05-15 19:08:39');
INSERT INTO `sys_menu` VALUES ('12', '2', '分配角色', NULL, 'employee:role', NULL, 2, NULL, 111, 1, '2021-01-17 21:49:58', '2022-05-15 19:12:18');
INSERT INTO `sys_menu` VALUES ('13', '2', '重置密码', NULL, 'employee:resetPassword', NULL, 2, NULL, 113, 1, '2021-01-17 21:50:36', '2022-05-15 19:12:26');
INSERT INTO `sys_menu` VALUES ('14', '3', '修改角色', NULL, 'sys:role:update', NULL, 2, NULL, 122, 1, '2021-01-17 21:51:14', '2022-05-15 19:09:39');
INSERT INTO `sys_menu` VALUES ('15', '3', '删除角色', NULL, 'sys:role:delete', NULL, 2, NULL, 123, 1, '2021-01-17 21:51:39', '2022-05-15 19:09:44');
INSERT INTO `sys_menu` VALUES ('16', '3', '分配权限', NULL, 'sys:role:permission', NULL, 2, NULL, 124, 1, '2021-01-17 21:52:02', '2022-05-15 19:09:56');
INSERT INTO `sys_menu` VALUES ('17', '4', '添加菜单', NULL, 'sys:menu:add', NULL, 2, NULL, 131, 1, '2021-01-17 21:53:53', '2022-05-15 19:10:13');
INSERT INTO `sys_menu` VALUES ('1737ee45-573a-4e14-ae0c-263f3c6680a1', '07ec05b0-61bc-4c2a-891a-c12816c0fc9c', '薪金设置', '/salary/set', 'salary:set', 'salary/salarySet', 1, 'el-icon-edit-outline', 41, 1, '2022-05-15 19:00:43', '2022-05-20 14:10:41');
INSERT INTO `sys_menu` VALUES ('18', '4', '修改菜单', NULL, 'sys:menu:update', NULL, 2, NULL, 132, 1, '2021-01-17 21:56:12', '2022-05-15 19:10:19');
INSERT INTO `sys_menu` VALUES ('19', '4', '删除菜单', NULL, 'sys:menu:delete', NULL, 2, NULL, 133, 1, '2021-01-17 21:56:36', '2022-05-15 19:10:24');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '/sys/user', 'employee:list', 'sys/user/User', 1, 'el-icon-user-solid', 11, 1, '2021-01-15 19:03:45', '2022-05-15 19:08:02');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/role', 'sys:role:list', 'sys/role/Role', 1, 'el-icon-s-check', 12, 1, '2021-01-15 19:03:45', '2022-05-15 19:08:13');
INSERT INTO `sys_menu` VALUES ('3e9671e4-9de5-4fc2-bf44-1d78e9362c3e', '99059faa-5ab5-4b3c-8950-c66cd66505bc', '删除员工', NULL, 'employee:delete', NULL, 2, NULL, 33, 1, '2022-05-15 20:30:44', '2022-05-15 20:30:44');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menu', 'sys:menu:list', 'sys/menu/Menu', 1, 'el-icon-menu', 13, 1, '2021-01-15 19:03:45', '2022-05-15 19:08:17');
INSERT INTO `sys_menu` VALUES ('49e120b6-1eea-4dfe-ad49-dd24f50656ed', '0', '部门职位管理', '/department', 'department', 'department/Department', 1, 'el-icon-film', 6, 1, '2022-05-12 16:02:01', '2022-05-12 22:25:00');
INSERT INTO `sys_menu` VALUES ('5bfa5601-a8a2-42eb-8d11-56d7cacfc093', '99059faa-5ab5-4b3c-8950-c66cd66505bc', '修改员工', NULL, 'employee:update', NULL, 2, NULL, 32, 1, '2022-05-15 20:30:16', '2022-05-31 09:04:51');
INSERT INTO `sys_menu` VALUES ('6d0ab924-b4d6-4e28-899b-79c435951e1f', 'c6a8c902-b9a9-4f6f-a9e3-888c2dfa7b05', '日常考勤', '/attendance/info', 'attendance:info', 'attendance/AttendanceInfo', 1, 'el-icon-date', 51, 1, '2022-05-17 04:20:05', '2022-05-17 04:24:14');
INSERT INTO `sys_menu` VALUES ('7', '3', '添加角色', '', 'sys:role:add', '', 2, '', 121, 1, '2021-01-15 23:02:25', '2022-05-15 19:09:34');
INSERT INTO `sys_menu` VALUES ('97e3bd94-ca78-44c7-8d63-178e225477f6', '0', '招聘管理', '', 'recruitment', '', 0, 'el-icon-s-shop', 2, 1, '2022-05-07 16:29:39', '2022-05-12 22:19:31');
INSERT INTO `sys_menu` VALUES ('99059faa-5ab5-4b3c-8950-c66cd66505bc', '0', '员工管理', '/employee', 'employee', 'employee/Employee', 1, 'el-icon-s-custom', 3, 1, '2022-05-10 18:48:52', '2022-05-12 22:19:57');
INSERT INTO `sys_menu` VALUES ('a39ea701-3964-46a6-83a0-d1e19bae08a3', '97e3bd94-ca78-44c7-8d63-178e225477f6', '招聘信息管理', '/recruitment', 'recruitment:info', 'recruitment/Recruitment', 1, 'el-icon-document', 21, 1, '2022-05-12 16:00:22', '2022-05-15 19:11:38');
INSERT INTO `sys_menu` VALUES ('bc5f781a-432a-4070-aba9-9898f0ee21fc', '07ec05b0-61bc-4c2a-891a-c12816c0fc9c', '报表统计', '/salary/list', 'salary:list', 'salary/salaryList', 1, 'el-icon-s-management', 42, 1, '2022-05-15 19:03:02', '2022-05-15 19:07:45');
INSERT INTO `sys_menu` VALUES ('c6a8c902-b9a9-4f6f-a9e3-888c2dfa7b05', '0', '考勤管理', '', 'attendance', '', 0, 'el-icon-message-solid', 5, 1, '2022-05-12 15:57:19', '2022-05-17 04:19:16');
INSERT INTO `sys_menu` VALUES ('e6fcc572-2593-463d-b9d8-1a3887bab64a', '97e3bd94-ca78-44c7-8d63-178e225477f6', '应聘者管理', '/candidate', 'recruitment:cand', 'candidate/Candidate', 1, 'el-icon-user', 22, 1, '2022-05-12 16:01:25', '2022-05-15 19:11:45');
INSERT INTO `sys_menu` VALUES ('e89ff1d9-2311-46b3-b010-db075f7b8578', '99059faa-5ab5-4b3c-8950-c66cd66505bc', '查询员工', NULL, 'employee:list', NULL, 2, NULL, 34, 1, '2022-05-20 13:52:00', '2022-05-20 13:52:07');
INSERT INTO `sys_menu` VALUES ('f57f85aa-8e56-4be2-bbd6-2e73087f2b56', '99059faa-5ab5-4b3c-8950-c66cd66505bc', '添加员工', NULL, 'employee:add', NULL, 2, '', 31, 1, '2022-05-15 19:24:35', '2022-05-15 20:38:58');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `vali_flag` int(3) NOT NULL COMMENT '有效标志',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1524061615696277506', '人资', 'hr', 1, '人力资源部门', '2022-05-11 00:19:40', '2022-05-31 02:07:50');
INSERT INTO `sys_role` VALUES ('1527398921773703169', '部门负责人', 'department', 1, '', '2022-05-20 05:20:55', '2022-05-31 02:08:00');
INSERT INTO `sys_role` VALUES ('3', '普通用户', 'normal', 1, '只有基本查看功能', '2022-05-03 10:30:15', '2022-05-15 20:28:23');
INSERT INTO `sys_role` VALUES ('6', '超级管理员', 'admin', 1, '系统默认最高权限，不可以编辑和任意修改', '2022-05-03 10:31:04', '2022-05-03 10:31:06');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色菜单ID',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1526296988547702785', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480002', '6', '2');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480003', '6', '12');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480004', '6', '10');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480005', '6', '13');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480006', '6', '3');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480007', '6', '7');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480008', '6', '14');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480009', '6', '15');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480010', '6', '16');
INSERT INTO `sys_role_menu` VALUES ('1526296988564480011', '6', '4');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811650', '6', '17');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811651', '6', '18');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811652', '6', '19');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811653', '6', '97e3bd94-ca78-44c7-8d63-178e225477f6');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811654', '6', 'a39ea701-3964-46a6-83a0-d1e19bae08a3');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811655', '6', 'e6fcc572-2593-463d-b9d8-1a3887bab64a');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811656', '6', '99059faa-5ab5-4b3c-8950-c66cd66505bc');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811657', '6', 'f57f85aa-8e56-4be2-bbd6-2e73087f2b56');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811658', '6', '5bfa5601-a8a2-42eb-8d11-56d7cacfc093');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811659', '6', '3e9671e4-9de5-4fc2-bf44-1d78e9362c3e');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811660', '6', '07ec05b0-61bc-4c2a-891a-c12816c0fc9c');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811661', '6', '1737ee45-573a-4e14-ae0c-263f3c6680a1');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811662', '6', 'bc5f781a-432a-4070-aba9-9898f0ee21fc');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811663', '6', 'c6a8c902-b9a9-4f6f-a9e3-888c2dfa7b05');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811664', '6', '6d0ab924-b4d6-4e28-899b-79c435951e1f');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811665', '6', '0c8fae8c-21bd-4da4-bbc4-cf660df05b93');
INSERT INTO `sys_role_menu` VALUES ('1526296988614811666', '6', '49e120b6-1eea-4dfe-ad49-dd24f50656ed');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300673', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300674', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300675', '3', '12');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300676', '3', '10');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300677', '3', '13');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300678', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300679', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300680', '3', '14');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300681', '3', '15');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300682', '3', '16');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300683', '3', '4');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300684', '3', '17');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300685', '3', '18');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300686', '3', '19');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300687', '3', 'a39ea701-3964-46a6-83a0-d1e19bae08a3');
INSERT INTO `sys_role_menu` VALUES ('1533481854582300688', '3', 'e89ff1d9-2311-46b3-b010-db075f7b8578');

SET FOREIGN_KEY_CHECKS = 1;
