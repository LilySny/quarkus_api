DROP DATABASE `login`;
CREATE DATABASE `login`;
USE `login` ;


CREATE TABLE `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_username` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`));


CREATE TABLE `groups` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`group_id`));


CREATE TABLE `members` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `users_user_id` INT NOT NULL,
  `groups_group_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `users_user_id`, `groups_group_id`),
  CONSTRAINT `fk_members_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_members_groups`
    FOREIGN KEY (`groups_group_id`)
    REFERENCES `groups` (`group_id`));
    
    




