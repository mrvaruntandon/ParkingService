-- MYSQL version 8.0.22

CREATE TABLE user_v1 (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    last_name varchar(255),
    first_name varchar(255),
    email VARCHAR(255),
    password varchar(255),
    is_active varchar(1),
    is_admin VARCHAR(1),
    time_created INT,
    time_updated INT
);

ALTER TABLE user_v1 ADD INDEX `email_index` (`email`);