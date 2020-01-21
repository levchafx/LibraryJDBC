


CREATE TABLE IF NOT EXISTS users (
  id int NOT NULL auto_increment,
  name varchar(255) NOT NULL default '',
  surname varchar(255) NOT NULL,
 email varchar(255) unique NOT NULL,
 age int NOT NULL,
  PRIMARY KEY  (id)
 );
 CREATE TABLE IF NOT EXISTS authenticate (
  id int NOT NULL auto_increment,
 login varchar(45) unique NOT NULL  ,
  password varchar(45) NOT NULL,
  profile_enable boolean NOT NULL,
  PRIMARY KEY  (id)
 );
 CREATE TABLE IF NOT EXISTS roles (
  id int NOT NULL auto_increment,
 role varchar(45) NOT NULL default '',
  PRIMARY KEY  (id)
 );
 CREATE TABLE IF NOT EXISTS users_roles (
  id int NOT NULL auto_increment,
user_id int,
role_id int,
  PRIMARY KEY  (id)
 ) ;
 CREATE TABLE IF NOT EXISTS image (
  `id` INT NOT NULL AUTO_INCREMENT,
  book_id int not null,
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
  CREATE TABLE IF NOT EXISTS books_authors (
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
  description VARCHAR(45) NOT NULL,
  `image_id` INT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id`));
 delete from users;
 delete from authenticate;
 delete from users_roles;