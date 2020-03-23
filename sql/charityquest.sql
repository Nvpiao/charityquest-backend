/*
 Navicat Premium Data Transfer

 Source Server         : teamwork
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : teamwork:3306
 Source Schema         : charityquest

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 23/03/2020 19:18:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charity_user
-- ----------------------------
DROP TABLE IF EXISTS `charity_user`;
CREATE TABLE `charity_user`
(
    `id`          varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of charity',
    `email`       varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'email of charity',
    `password`    varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'password of charity',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'name of charity',
    `number`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'number of charity',
    `photo`       longtext COLLATE utf8mb4_unicode_ci COMMENT 'avatar of charity',
    `description` longtext COLLATE utf8mb4_unicode_ci COMMENT 'description of charity',
    `case_photo`  longtext COLLATE utf8mb4_unicode_ci COMMENT 'case photo of charity',
    `case_video`  longtext COLLATE utf8mb4_unicode_ci COMMENT 'case video address of charity',
    `case_desc`   longtext COLLATE utf8mb4_unicode_ci COMMENT 'case description of charity',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='user of charity';

-- ----------------------------
-- Table structure for donation
-- ----------------------------
DROP TABLE IF EXISTS `donation`;
CREATE TABLE `donation`
(
    `id`             varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id of donation',
    `public_id`      varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id of public (who donated)',
    `type`           varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'donation' COMMENT 'donation or fundraising. default donation',
    `charity_id`     varchar(36) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'id of charity (to who)',
    `fundraising_id` varchar(36) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'id of fundraising',
    `donate_type`    varchar(9) COLLATE utf8mb4_unicode_ci           DEFAULT 'once' COMMENT 'type of donation, types: once, weekly, monthly, quarterly, yearly, default: once',
    `money`          float(255, 0) unsigned                 NOT NULL DEFAULT '0' COMMENT 'money of donation',
    `time`           datetime                               NOT NULL COMMENT 'time of donation',
    PRIMARY KEY (`id`),
    KEY `public_id_donation` (`public_id`),
    KEY `charity_id_donation` (`charity_id`),
    KEY `fundraising_id_donation` (`fundraising_id`),
    CONSTRAINT `charity_id_donation` FOREIGN KEY (`charity_id`) REFERENCES `charity_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT `fundraising_id_donation` FOREIGN KEY (`fundraising_id`) REFERENCES `fundraising` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT `public_id_donation` FOREIGN KEY (`public_id`) REFERENCES `public_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='donation from public';

-- ----------------------------
-- Table structure for fundraising
-- ----------------------------
DROP TABLE IF EXISTS `fundraising`;
CREATE TABLE `fundraising`
(
    `id`           varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of fundraising.',
    `charity_id`   varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of charity.',
    `url`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'url for fundraising',
    `start_time`   datetime                                NOT NULL COMMENT 'start time of fundraising.',
    `end_time`     datetime                                NOT NULL COMMENT 'end time of fundraising.',
    `raise_money`  int(11) unsigned                        NOT NULL DEFAULT '0' COMMENT 'how much do this project raise for now.',
    `target_money` int(11) unsigned                        NOT NULL COMMENT 'target of fundraising',
    `description`  text COLLATE utf8mb4_unicode_ci COMMENT 'description for this fundraising',
    PRIMARY KEY (`id`),
    KEY `charity_id_fundraising` (`charity_id`),
    CONSTRAINT `charity_id_fundraising` FOREIGN KEY (`charity_id`) REFERENCES `charity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='fundraising for a charity';

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`
(
    `id`          varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of message',
    `charity_id`  varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of charity, it is a foreign key',
    `create_time` datetime                                NOT NULL COMMENT 'time of creation',
    `modify_time` datetime                                NOT NULL COMMENT 'time of modification',
    `sent_time`   datetime                                         DEFAULT NULL COMMENT 'lastest sending time',
    `subject`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'subject of message',
    `content`     text COLLATE utf8mb4_unicode_ci COMMENT 'content of message',
    `status`      varchar(6) COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT 'draft' COMMENT 'status of message. types: draft, sent, failed. default: draft.',
    `error`       varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'error message when it send failed.',
    PRIMARY KEY (`id`),
    KEY `charity_id_message_foreign` (`charity_id`),
    CONSTRAINT `charity_id_message_foreign` FOREIGN KEY (`charity_id`) REFERENCES `charity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='message of charity';

-- ----------------------------
-- Table structure for public_user
-- ----------------------------
DROP TABLE IF EXISTS `public_user`;
CREATE TABLE `public_user`
(
    `id`         varchar(36) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'id of public user',
    `email`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'email address of public user',
    `password`   varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'password of public user',
    `title`      varchar(5) COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT 'Mr' COMMENT 'title of public user, content:''Mr'', ''Mrs'', ''Miss'', ''Ms'', ''Dr'', ''Other''. default: Mr',
    `first_name` varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'first name of public user',
    `last_name`  varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'last name of public user',
    `tel_pre`    varchar(5) COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT 'telephone country code of public user',
    `tel`        varchar(20) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT 'telephone number of public user',
    `photo`      longtext COLLATE utf8mb4_unicode_ci COMMENT 'avatar of public user',
    `location`   varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'location of public user',
    PRIMARY KEY (`id`),
    UNIQUE KEY `public_user_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='users of public';

SET FOREIGN_KEY_CHECKS = 1;
