CREATE DATABASE outfitter_db;

USE outfitter_db;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    outfitter BOOL NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS post_land;

CREATE TABLE post_land (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    animals TEXT NOT NULL,
    price DOUBLE NOT NULL,
    duration DATE NOT NULL,
    slots INT NOT NULL,
    guided BOOL NOT NULL,
    lodging BOOL NOT NULL,
    method VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS post_review;

CREATE TABLE post_review (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    body TEXT NOT NULL,
    rating INT UNSIGNED NOT NULL,
    land_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (land_id) REFERENCES post_land(id)
);