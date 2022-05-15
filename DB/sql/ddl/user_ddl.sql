-- MYSQL version 8.0.22
CREATE TABLE user_v1 (
  user_id int AUTO_INCREMENT PRIMARY KEY,
  last_name varchar(255),
  first_name varchar(255),
  email VARCHAR(255),
  password varchar(255),
  is_active varchar(1),
  is_admin VARCHAR(1),
  is_verified VARCHAR(1),
  time_created INT,
  time_updated INT
);
ALTER TABLE
  user_v1
ADD
  INDEX `email_index` (`email`);

-- at scale having an events table looks less sensible,
-- but what if you need to have a record of our user's login and logout (Audit Trail)
CREATE TABLE user_events (
    user_event_id int AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    event_type VARCHAR(255),
    time_created INT,
    time_updated INT
  );
Create table group_v1(
    group_id int AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(255),
    is_active varchar(1),
    time_created INT,
    time_updated INT
  );
Create table user_groups(
    user_group_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    group_id int,
    is_active varchar(1),
    time_created INT,
    time_updated INT
  );
create table permission(
    permission_id int AUTO_INCREMENT primary key,
    premission_name VARCHAR(255),
    is_active varchar(1),
    time_created INT,
    time_updated INT
  );
create table group_permission(
    group_permission_id int AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    permission_id INT,
    is_active varchar(1),
    time_created INT,
    time_updated INT
  );
create table user_permission(
    user_permission_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    permission_id int,
    is_active VARCHAR(1),
    time_created INT,
    time_updated INT
  );