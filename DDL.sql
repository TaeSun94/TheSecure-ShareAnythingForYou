USE pjt2;

CREATE TABLE `member`(
    `member_email` VARCHAR(50) PRIMARY KEY,
    `member_nickname` VARCHAR(50) DEFAULT NULL
)DEFAULT CHARSET=utf8MB4; 

CREATE TABLE `wallet`(
	`wallet_id` INT PRIMARY KEY,
    `address` VARCHAR(200),
    `owner_id` VARCHAR(50) NOT NULL,
    `balance` LONG,
    `receiving_count` INT DEFAULT 0,
    `cash` INT DEFAULT 0,
    FOREIGN KEY(`owner_id`) REFERENCES `member`(`member_email`) ON DELETE CASCADE ON UPDATE CASCADE
)DEFAULT CHARSET=utf8MB4;

CREATE TABLE `host`(
	`host_num` INT PRIMARY KEY AUTO_INCREMENT,
    `member_email` VARCHAR(50) NOT NULL,
    `host_address` VARCHAR(50) NOT NULL,
    `host_type` VARCHAR(10) NOT NULL,
    `host_intro` VARCHAR(300) DEFAULT NULL,
    FOREIGN KEY(`member_email`) REFERENCES `member`(`member_email`) ON DELETE CASCADE ON UPDATE CASCADE
)DEFAULT CHARSET=utf8MB4;

CREATE TABLE `host_images`(
	`host_num` INT PRIMARY KEY,
	`img1` VARCHAR(300) DEFAULT NULL,
    `img2` VARCHAR(300) DEFAULT NULL,
    `img3` VARCHAR(300) DEFAULT NULL,
    `img4` VARCHAR(300) DEFAULT NULL,
    `img5` VARCHAR(300) DEFAULT NULL,
    `img6` VARCHAR(300) DEFAULT NULL,
    FOREIGN KEY(`host_num`) REFERENCES `host`(`host_num`) ON DELETE CASCADE ON UPDATE CASCADE 
)DEFAULT CHARSET=utf8MB4;

CREATE TABLE `host_items`(
	`host_num` INT PRIMARY KEY,
    `item1` BOOLEAN DEFAULT FALSE,
    `item2` BOOLEAN DEFAULT FALSE,
    `item3` BOOLEAN DEFAULT FALSE,
    `item4` BOOLEAN DEFAULT FALSE,
    `item5` BOOLEAN DEFAULT FALSE,
    `item6` BOOLEAN DEFAULT FALSE,
    `item7` BOOLEAN DEFAULT FALSE,
    `item8` BOOLEAN DEFAULT FALSE,
    `item9` BOOLEAN DEFAULT FALSE,
    `item10` BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(`host_num`) REFERENCES `host`(`host_num`) ON DELETE CASCADE ON UPDATE CASCADE
)DEFAULT CHARSET=utf8MB4;

CREATE TABLE `host_available_days`(
	`host_num` INT,
    `host_available_day` DATETIME,
    `is_reservation` BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(`host_num`) REFERENCES `host`(`host_num`) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(`host_num`,`host_available_day`)
)DEFAULT CHARSET=utf8MB4;
