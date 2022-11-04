CREATE TABLE IF NOT EXISTS `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `firstname` VARCHAR(255) NOT NULL,
    `lastname` VARCHAR(255) NOT NULL,
    `token` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`username`)
);

CREATE TABLE IF NOT EXISTS `courses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `image_url` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `topics` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `course_id` INT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `description` LONGTEXT NOT NULL,
    `content` LONGTEXT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK_TOPIC_COURSE` (`course_id`),
    CONSTRAINT `FK_TOPIC_COURSE` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
