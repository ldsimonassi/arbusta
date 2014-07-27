
DROP TABLE `hits`;
CREATE TABLE `hits` (
  `hit_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `hit_type_id` INT UNSIGNED NOT NULL,
  `hit_group_id`  INT UNSIGNED NOT NULL,
  `hit_layout_id`  INT UNSIGNED NOT NULL,
  `creation_time` date NOT NULL,
  `hit_status` varchar(10) NOT NULL,
  `lifetime_in_seconds` decimal(8,0) DEFAULT NULL,
  `max_assignments` decimal(3,0) DEFAULT NULL,
  `expiration` date DEFAULT NULL,
  `question` varchar(1024) DEFAULT NULL,
  `requester_annotation` varchar(100) DEFAULT NULL,
  `hit_review_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`hit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE `hit_types`;
CREATE TABLE `hit_types` (
  `hit_type_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `description` varchar(144) DEFAULT NULL,
  `keywords` varchar(1024) DEFAULT NULL,
  `reward` decimal(10,1) NOT NULL,
  `assignment_duration_in_seconds` decimal(8,0) DEFAULT NULL,
  `auto_approval_delay_in_seconds` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`hit_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE `qualification_requirements`;
CREATE TABLE `qualification_requirements` (
  `hit_type_id`  INT UNSIGNED NOT NULL,
  `qualification_type_id` INT UNSIGNED NOT NULL,
  `comparator` varchar(20) NOT NULL,
  `integer_value` INT NOT NULL,
  PRIMARY KEY (`hit_type_id`, `qualification_type_id`, `comparator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE `qualification_types`;
CREATE TABLE `qualification_types` (
  `qualification_type_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `keywords` varchar(500) NOT NULL,
  `retry_delay_in_seconds` INT NOT NULL,
  `status` varchar(30) NOT NULL,
  `test` varchar(65535) NOT NULL,
  `answer_key` varchar(65535) NOT NULL,
  `test_duration_in_seconds` INT NOT NULL,
  `auto_granted` varchar(7) NOT NULL,
  `auto_granted_value` INT,
  PRIMARY KEY (`qualification_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE `hits`;
CREATE TABLE `hits` (
  `hit_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `hit_type_id` INT UNSIGNED NOT NULL,
  `question` VARCHAR(65535) NOT NULL,
  `lifetime_in_seconds` INT UNSIGNED,
  `max_assignments` INT NOT NULL DEFAULT 1,
  `requester_annotation` VARCHAR(50),
  `review_status` VARCHAR(12) DEFAULT 'NotReviewed',
  PRIMARY KEY (`hit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


select * from qualification_types

select * from hit_types

select * from qualification_requirements

DROP USER 'arbusta';

CREATE USER 'arbusta'@'%' IDENTIFIED BY  'arbusto';

GRANT ALL PRIVILEGES ON arbusta.* to 'arbusta'@'%';

