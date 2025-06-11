
drop database if exists autoecole;

create database autoecole;

use autoecole;
CREATE TABLE CANDIDAT
(
  idcandidat int NOT NULL auto_increment ,
   nom VARCHAR(32) NULL  ,
   prenom VARCHAR(32) NULL  ,
   age INT(3) NOT NULL,
   email VARCHAR(32) NULL  ,
    mdp VARCHAR(32) NULL ,
   tel VARCHAR(32) NULL  ,
     PRIMARY KEY (idcandidat)
);

DROP TABLE IF EXISTS MONITEUR;
CREATE TABLE MONITEUR
(
   idmoniteur int(3) NOT NULL auto_increment ,
   nom VARCHAR(32) NULL  ,
   prenom VARCHAR(32) NULL  ,
   email VARCHAR(32) NULL  ,
   mdp VARCHAR(32) NULL ,
   role enum('admin','moniteur') NOT NULL,
   tel VARCHAR(32) NULL  ,
   PRIMARY KEY (idmoniteur)
);

CREATE TABLE EXAMEN
(
   idexamen int NOT NULL auto_increment  ,
   dateExamen VARCHAR(32) NULL  ,
   lieu VARCHAR(32) NULL  ,
   typePermis VARCHAR(32) NULL ,
   PRIMARY KEY (idexamen)
);

CREATE TABLE VEHICULE
(
   idvehicule int(3) NOT NULL auto_increment ,
   marque VARCHAR(32) NULL  ,
   modele VARCHAR(32) NULL  ,
   matricule VARCHAR(32) NULL,
   PRIMARY KEY (idvehicule)
);

CREATE TABLE LECON
(
   idlecon int(3) NOT NULL auto_increment,
   typeLecon VARCHAR(32) NULL  ,
   description VARCHAR(32) NULL  ,
   dateLecon date ,
   idmoniteur int(3) NOT NULL  ,
   idcandidat int NOT NULL  ,
   PRIMARY KEY (idlecon),
   foreign key (idmoniteur) references MONITEUR(idmoniteur),   
   foreign key (idcandidat) references CANDIDAT(idcandidat) 
);

create table passage(
idpassage int(11) not null auto_increment,
idexamen int(11) not null,
idcandidat int(1) not null,
datePassage DATETIME NOT NULL,
PRIMARY KEY (idpassage),
foreign key (idexamen) references EXAMEN(idexamen),
foreign key (idcandidat) references CANDIDAT(idcandidat) 
);
 
  insert into moniteur values (null, "samy", "samy", "a@gmail.com","123", "admin","8525625");