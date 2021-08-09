DROP DATABASE IF EXISTS outfitter_db;
CREATE DATABASE outfitter_db;

USE outfitter_db;


DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    outfitter BOOL NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS properties;
CREATE TABLE properties (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    location TEXT NOT NULL,
    slots INT NOT NULL,
    guided BOOL NOT NULL,
    lodging BOOL NOT NULL,
    method VARCHAR(50) NOT NULL,
    owner_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS package;
CREATE TABLE package (
    id INT UNSIGNED NOT NULL,
    property_id INT UNSIGNED NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT NOT NULL,
    guided BOOL NOT NULL,
    lodging BOOL NOT NULL,
    duration INT NOT NULL,
    post_exp DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (property_id) REFERENCES properties (id)
);

DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    body TEXT NOT NULL,
    rating INT UNSIGNED NOT NULL,
    land_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (land_id) REFERENCES properties (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
    id INT UNSIGNED NOT NULL,
    name VARCHAR(75),
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS post_animals;
CREATE TABLE post_animals (
    animal_id INT UNSIGNED NOT NULL,
    property_id INT UNSIGNED NOT NULL,
    FOREIGN KEY(animal_id) REFERENCES animals (id),
    FOREIGN KEY(property_id) REFERENCES properties (id)
);

DROP TABLE IF EXISTS bookings;
CREATE TABLE bookings (
    id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    property_id INT UNSIGNED NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (property_id) REFERENCES properties (id)
);

DROP TABLE IF EXISTS bookmarks;
CREATE TABLE bookmarks (
    user_id INT UNSIGNED NOT NULL,
    post_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (post_id) REFERENCES properties (id)
);
