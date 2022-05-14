-- MYSQL version 8.0.22
CREATE TABLE parking(
    parking_id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null,
    lat Float (10,6),
    lng Float (10,6),
    pincode varchar(10) not null,
    city varchar(255) not null,
    is_active varchar(1) not null,
    time_created INT not null,
    time_updated INT not null
);

ALTER TABLE parking ADD INDEX `name_index` (`name`);

CREATE TABLE booking(
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    parking_id INT not null,
    start_time INT not null,
    end_time INT not null,
    is_active varchar(1) not null,
    time_created INT not null,
    time_updated INT not null
);

ALTER TABLE booking ADD INDEX `start_time_index` (`start_time`);
ALTER TABLE booking ADD INDEX `end_time_index` (`end_time`);

ALTER TABLE booking
ADD CONSTRAINT FK_booking_parking
FOREIGN KEY (parking_id) REFERENCES parking(parking_id);