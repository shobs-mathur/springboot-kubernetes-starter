USE spring_envers_db;

CREATE TABLE `limit_entity`
(
    `id`           BIGINT(20)     NOT NULL AUTO_INCREMENT,
    `limit_key`    VARCHAR(1000)  NOT NULL,
    `amount`       decimal(19, 2) NOT NULL,
    `created_date` DATETIME       NOT NULL,
    `updated_date` DATETIME DEFAULT NULL,
    CONSTRAINT limit_key_unique UNIQUE (limit_key),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;