-- To login from terminal
-- mysql -u root -p

-- user related commands
SELECT User, Host FROM mysql.user;
CREATE USER 'someuser'@'localhost' IDENTIFIED BY 'somepassword';

-- update user's password
ALTER USER 'someuser'@'localhost' IDENTIFIED BY 'newpassword';

-- rename user
RENAME USER 'someuser'@'localhost' TO 'somenewuser'@'localhost';
DROP USER 'someuser'@'localhost';

-- database related commands
SHOW DATABASES;
CREATE DATABASE newdb;
CREATE DATABASE IF NOT EXISTS newdb;
USE newdb;
DROP DATABASE newdb;

-- grant and privileges
GRANT ALL PRIVILEGES ON * . * TO 'someuser'@'localhost';
GRANT ALL PRIVILEGES ON newdb.* TO 'someuser'@'localhost';
GRANT SELECT, INSERT, UPDATE ON newdb.* TO 'someuser'@'localhost';
SHOW GRANTS FOR  'someuser'@'localhost';

FLUSH PRIVILEGES;

-- checking contraints in mysql
select COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_COLUMN_NAME, REFERENCED_TABLE_NAME
from information_schema.KEY_COLUMN_USAGE
where TABLE_NAME = 'tableName';
