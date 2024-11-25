CREATE DATABASE IF NOT EXISTS `labs_db`;

USE `labs_db`;

CREATE TABLE `function` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `c_function_name` VARCHAR(255),
    `c_count` INT CHECK (`c_count` >= 2),
    `c_x_from` DOUBLE,
    `c_x_to` DOUBLE
);

CREATE TABLE `point` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `function_id` INT,
    `c_x_value` DOUBLE,
    `c_y_value` DOUBLE,
    FOREIGN KEY (`function_id`) REFERENCES `function`(`id`) ON DELETE CASCADE
);
