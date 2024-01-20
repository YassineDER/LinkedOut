USE securecapita;

CREATE TABLE IF NOT EXISTS securecapita.`Costumer`
(
    `customer_id` INT          NOT NULL AUTO_INCREMENT,
    `full_name`   VARCHAR(255) NOT NULL,
    `photo_url`   VARCHAR(255) NOT NULL DEFAULT 'https://img.icons8.com/glyph-neue/100/user-male-circle.png',
    `email`       VARCHAR(45)  NOT NULL,
    `phone`       VARCHAR(15)  NOT NULL,
    `address`     VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`customer_id`)
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS securecapita.`Invoice`
(
    `invoice_id`  INT         NOT NULL AUTO_INCREMENT,
    `date`        DATETIME    NOT NULL,
    `number`      VARCHAR(45) NOT NULL,
    `customer_id` INT         NULL,
    PRIMARY KEY (`invoice_id`),
    INDEX `fk_Invoice_Costumer1_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_Invoice_Costumer1`
        FOREIGN KEY (`customer_id`)
            REFERENCES securecapita.`Costumer` (`customer_id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS securecapita.`Service`
(
    `service_id` INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(75) NOT NULL,
    `price`      FLOAT       NOT NULL,
    PRIMARY KEY (`service_id`)
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS securecapita.`Invoice_management`
(
    `idInvoice_management` INT NOT NULL AUTO_INCREMENT,
    `invoice_id`           INT NOT NULL,
    `service_id`           INT NOT NULL,
    PRIMARY KEY (`idInvoice_management`),
    INDEX `fk_Invoice_management_Invoice1_idx` (`invoice_id` ASC) VISIBLE,
    INDEX `fk_Invoice_management_Service1_idx` (`service_id` ASC) VISIBLE,
    CONSTRAINT `fk_Invoice_management_Invoice1`
        FOREIGN KEY (`invoice_id`)
            REFERENCES securecapita.`Invoice` (`invoice_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_Invoice_management_Service1`
        FOREIGN KEY (`service_id`)
            REFERENCES securecapita.`Service` (`service_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

USE `securecapita`;

CREATE TABLE IF NOT EXISTS `securecapita`.`user`
(
    `user_id`      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `email`        VARCHAR(255) NOT NULL,
    `first_name`   VARCHAR(255) NOT NULL,
    `last_name`    VARCHAR(255) NOT NULL,
    `address`      VARCHAR(255) NOT NULL,
    `phone`        VARCHAR(30)  NOT NULL,
    `enabled`      TINYINT      NULL DEFAULT '0',
    `using_mfa`    TINYINT      NULL DEFAULT '0',
    `created_date` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `image_url`    VARCHAR(255) NULL DEFAULT 'https://img.icons8.com/glyph-neue/100/user-male-circle.png',
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `email` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `securecapita`.`account_reset`
(
    `account_reset_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`          INT UNSIGNED NOT NULL,
    `url`              VARCHAR(255) NOT NULL,
    `expires_at`       DATETIME     NOT NULL,
    PRIMARY KEY (`account_reset_id`),
    UNIQUE INDEX `user_id` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `url` (`url` ASC) VISIBLE,
    CONSTRAINT `account_reset_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `securecapita`.`user` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `securecapita`.`account_verification`
(
    `account_verification_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`                 INT UNSIGNED NOT NULL,
    `url`                     VARCHAR(255) NOT NULL,
    `expires_at`              DATETIME     NOT NULL,
    PRIMARY KEY (`account_verification_id`),
    UNIQUE INDEX `user_id` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `url` (`url` ASC) VISIBLE,
    CONSTRAINT `account_verification_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `securecapita`.`user` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `securecapita`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `securecapita`.`role`
(
    `role_id`     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `permissions` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `name` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `securecapita`.`two_factor_authentication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `securecapita`.`two_factor_authentication`
(
    `2fa_id`     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    INT UNSIGNED NOT NULL,
    `code`       VARCHAR(10)  NOT NULL,
    `expires_at` DATETIME     NOT NULL,
    PRIMARY KEY (`2fa_id`),
    UNIQUE INDEX `user_id` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `code` (`code` ASC) VISIBLE,
    CONSTRAINT `two_factor_authentication_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `securecapita`.`user` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `securecapita`.`user_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `securecapita`.`user_event`
(
    `user_event_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`       INT UNSIGNED NOT NULL,
    `created_date`  DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `device`        VARCHAR(255) NULL DEFAULT NULL,
    `ip_address`    VARCHAR(255) NULL DEFAULT NULL,
    `type`          VARCHAR(255) NOT NULL,
    `browser`       VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`user_event_id`),
    INDEX `user_id` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_event_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `securecapita`.`user` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `securecapita`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `securecapita`.`user_role`
(
    `user_role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`      INT UNSIGNED NOT NULL,
    `role_id`      INT UNSIGNED NOT NULL,
    PRIMARY KEY (`user_role_id`),
    UNIQUE INDEX `user_id` (`user_id` ASC) VISIBLE,
    INDEX `role_id` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_role_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `securecapita`.`user` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `user_role_ibfk_2`
        FOREIGN KEY (`role_id`)
            REFERENCES `securecapita`.`role` (`role_id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;

