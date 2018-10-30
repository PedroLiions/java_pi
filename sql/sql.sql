drop database vanilla;

CREATE DATABASE vanilla; 

USE vanilla; 

CREATE TABLE users 
  ( 
     id             INT(11) NOT NULL auto_increment 
     ,first_name    VARCHAR(100) 
     ,last_name     VARCHAR(100) 
     ,cpf           VARCHAR(25) 
     ,date_of_birth DATE 
     ,create_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
     ,updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE 
      CURRENT_TIMESTAMP, 
     PRIMARY KEY (id), 
     UNIQUE (cpf) 
  ); 

CREATE TABLE type_documents 
  ( 
     id           ENUM('A', 'B', 'C') NOT NULL 
     ,description VARCHAR(255) NOT NULL, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE cnh 
  ( 
     id                INT(11) auto_increment 
     ,type_document_id ENUM('A', 'B', 'C') NOT NULL 
     ,user_id          INT(11) 
     ,cnh              DECIMAL(20) NOT NULL 
     ,cpf              VARCHAR(25) 
     ,emission_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
     ,due_date_at      DATE NOT NULL 
     ,qrcode           BLOB NULL, 
     PRIMARY KEY (id), 
     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
     FOREIGN KEY (type_document_id) REFERENCES type_documents(id)
  ); 

CREATE TABLE ctps 
  ( 
     id                INT(11) auto_increment 
     ,type_document_id ENUM('A', 'B', 'C') NOT NULL 
     ,user_id          INT(11) 
     ,ctps             DECIMAL(20) NOT NULL 
     ,cpf              VARCHAR(25) 
     ,emission_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
     ,due_date_at      DATE NOT NULL 
     ,qrcode           BLOB NULL, 
     PRIMARY KEY (id), 
     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
     FOREIGN KEY (type_document_id) REFERENCES type_documents(id)
  ); 

CREATE TABLE etitulo 
  ( 
     id                INT(11) auto_increment 
     ,type_document_id ENUM('A', 'B', 'C') NOT NULL 
     ,user_id          INT(11)
     ,first_name       VARCHAR(100) 
     ,last_name        VARCHAR(100) 
     ,etitulo          DECIMAL(20) NOT NULL 
     ,cpf              VARCHAR(25) 
     ,emission_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
     ,due_date_at      DATE NOT NULL 
     ,qrcode           BLOB NULL, 
     PRIMARY KEY (id), 
     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
     FOREIGN KEY (type_document_id) REFERENCES type_documents(id)
  ); 

CREATE TABLE query 
  ( 
     id                INT(11) auto_increment 
     ,user_id          INT(11) 
     ,first_name       VARCHAR(100) 
     ,last_name        VARCHAR(100) 
     ,type_document_id ENUM('A', 'B', 'C') NOT NULL 
     ,register         DECIMAL(20) NOT NULL 
     ,query_at         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
     PRIMARY KEY (id), 
     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
     FOREIGN KEY (type_document_id) REFERENCES type_documents(id)
  ); 
