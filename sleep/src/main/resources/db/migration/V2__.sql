CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE sleep_log
(
    id                        BIGINT                      NOT NULL,
    sleep_date                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    time_in_bed_id            BIGINT,
    total_time_in_bed_seconds BIGINT                      NOT NULL,
    morning_feeling           VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_sleeplog PRIMARY KEY (id)
);

CREATE TABLE time_in_bed_interval
(
    id         BIGINT NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_timeinbedinterval PRIMARY KEY (id)
);

ALTER TABLE sleep_log
    ADD CONSTRAINT uc_sleeplog_time_in_bed UNIQUE (time_in_bed_id);

ALTER TABLE sleep_log
    ADD CONSTRAINT FK_SLEEPLOG_ON_TIME_IN_BED FOREIGN KEY (time_in_bed_id) REFERENCES time_in_bed_interval (id);