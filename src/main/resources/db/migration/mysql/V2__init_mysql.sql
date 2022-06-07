USE spring_envers_db;

CREATE TABLE IF NOT EXISTS sequence_number
(
    sequence_number     BIGINT AUTO_INCREMENT,
    create_date         datetime,
    PRIMARY KEY (sequence_number)
    )
    ENGINE = InnoDB;