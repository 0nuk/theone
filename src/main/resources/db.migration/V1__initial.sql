DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   lastname VARCHAR(255) NULL,
   second_lastname VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   `role` VARCHAR(255) NULL,
   enabled BIT(1) NOT NULL,
   created_on datetime NULL,
   updated_on datetime NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);
