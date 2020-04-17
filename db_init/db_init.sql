CREATE DATABASE IF NOT EXISTS wardrobe_test;

USE wardrobe_test;

CREATE TABLE IF NOT EXISTS items
  (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DTYPE VARCHAR(60) NOT NULL,
    sex VARCHAR(10) NOT NULL,
    sub_category VARCHAR(255),
    brand VARCHAR(255),
    price BIGINT,
    description VARCHAR(255),
    item_path VARCHAR(255),
    img_path VARCHAR(255)
  )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;