CREATE TABLE Cours (
  idCours        integer(10) NOT NULL, 
  "Date"         date, 
  HeureDebut     timestamp, 
  HeureFin       timestamp, 
  Intervenant    varchar(255), 
  Salle          varchar(255), 
  "Column"       integer(10), 
  NomCours       varchar(255), 
  ModuleidModule integer(10) NOT NULL, 
  PRIMARY KEY (idCours), 
  FOREIGN KEY(ModuleidModule) REFERENCES Module(idModule));
CREATE TABLE Etudiant (
  idEtudiant     INTEGER NOT NULL PRIMARY KEY, 
  NomEtudiant    varchar(255) NOT NULL, 
  PrenomEtudiant varchar(255) NOT NULL, 
  GroupeidGroupe integer(10) NOT NULL, 
  FOREIGN KEY(GroupeidGroupe) REFERENCES Groupe(idGroupe));
CREATE TABLE Groupe (
  idGroupe  INTEGER NOT NULL PRIMARY KEY, 
  NomGroupe varchar(255) NOT NULL);
CREATE TABLE Groupe_Cours (
  CoursidCours   integer(10) NOT NULL, 
  GroupeidGroupe integer(10) NOT NULL, 
  PRIMARY KEY (CoursidCours, 
  GroupeidGroupe), 
  FOREIGN KEY(CoursidCours) REFERENCES Cours(idCours), 
  FOREIGN KEY(GroupeidGroupe) REFERENCES Groupe(idGroupe));
CREATE TABLE Module (
  idModule  integer(10) NOT NULL, 
  NomModule varchar(255), 
  PRIMARY KEY (idModule));

