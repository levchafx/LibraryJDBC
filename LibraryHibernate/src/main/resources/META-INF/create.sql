CREATE TABLE IF NOT EXISTS users (
  id int NOT NULL auto_increment,
  name varchar(255) NOT NULL default '',
  surname varchar(255) NOT NULL,
  role varchar(255) not null,
 email varchar(255) unique NOT NULL,
 age int NOT NULL,
  PRIMARY KEY  (id));
 CREATE TABLE IF NOT EXISTS authenticate (
  id int NOT NULL auto_increment,
 login varchar(45) unique NOT NULL  ,
  password varchar(45) NOT NULL,
  profileenabled boolean NOT NULL,
  PRIMARY KEY  (id));

 CREATE TABLE IF NOT EXISTS image (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` BLOB NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE IF NOT EXISTS message (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `message` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE IF NOT EXISTS book_instance (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `due_date` DATE NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE IF NOT EXISTS author_book (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` VARCHAR(45) NOT NULL,
  `author_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE IF NOT EXISTS author (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE IF NOT EXISTS book (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `quantity` INT NULL,
  description varchar(255),
  PRIMARY KEY (`id`));
 