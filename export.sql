CREATE DATABASE microservices;

USE microservices;

CREATE TABLE client(
id bigint AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
phone VARCHAR(20),
address VARCHAR(100),
PRIMARY KEY (id)
);

CREATE TABLE client_import(
id bigint AUTO_INCREMENT,
client_fk_id bigint NOT NULL,
client_import bigint,
PRIMARY KEY (id),
FOREIGN KEY (client_fk_id) REFERENCES client(id)
);

