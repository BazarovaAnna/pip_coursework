DROP TABLE "Groups";
DROP TABLE "Members";
DROP TABLE "Sessions";
DROP TABLE "Games";
DROP TABLE "Characters_Effects";
DROP TABLE "Characters_Abilities";
DROP TABLE "Inventory";
DROP TABLE "Effects";
DROP TABLE "Abilities";
DROP TABLE "Items";
DROP TABLE "Rules";
DROP TABLE "Characters";
DROP TABLE "Users_Genres";
DROP TABLE "Genres";
DROP TABLE "Users";


CREATE TABLE "Genres"
(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL UNIQUE
);

CREATE TABLE "Users"
(
	Id SERIAL PRIMARY KEY,
	Login TEXT NOT NULL UNIQUE,
	Password VARCHAR(40) NOT NULL,
	Mail TEXT NOT NULL,
	Date_Birth TIMESTAMP NOT NULL CHECK(Date_Birth < current_timestamp),
	Language VARCHAR(5) NOT NULL,
	Sex VARCHAR(1) NULL CHECK(Sex in ('m','f',' ')),
	Date_Register TIMESTAMP NOT NULL,
	Date_Exit TIMESTAMP NULL CHECK(Date_Exit > Date_Register OR Date_Exit IS NULL)
);

CREATE TABLE "Users_Genres"
(
	User_Id INTEGER REFERENCES "Users" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT, 
	Genre_Id INTEGER REFERENCES "Genres" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (User_Id, Genre_Id)
);

CREATE TABLE "Characters"
(
	Id SERIAL PRIMARY KEY,
	User_Id INTEGER NOT NULL REFERENCES "Users" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Class TEXT NOT NULL,
	Race TEXT NOT NULL,
	Story TEXT NULL,
	Name TEXT NOT NULL UNIQUE,
	Sex VARCHAR(1) NULL CHECK(Sex in ('m','f',' ')),
	Condition VARCHAR NOT NULL CHECK(Condition in 
	('alive', 'dead', 'ill', 'poisoned', 'damaged', 'damned')),
	Pers_Money REAL DEFAULT 0 NOT NULL CHECK(Pers_Money >= 0),
	Max_Weight REAL NOT NULL
);

CREATE TABLE "Effects"
(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL UNIQUE,
	Description TEXT NULL
);

CREATE SEQUENCE Abilities_Id_seq;

CREATE TABLE "Abilities"
(
	Id INTEGER PRIMARY KEY DEFAULT nextval('Abilities_Id_seq'),
	Name TEXT NOT NULL UNIQUE,
	Description TEXT NULL,
	Perk_Ability VARCHAR(1) NOT NULL CHECK(Perk_Ability in ('p','a'))
);

ALTER SEQUENCE Abilities_Id_seq OWNED BY "Abilities".Id;

CREATE TABLE "Characters_Abilities"
(
	Character_Id INTEGER REFERENCES "Characters" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Ability_Id INTEGER REFERENCES "Abilities" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Time_Learning TIMESTAMP NOT NULL DEFAULT current_timestamp,
	PRIMARY KEY (Character_Id, Ability_Id)
);

CREATE TABLE "Characters_Effects"
(
	Character_Id INTEGER REFERENCES "Characters" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Effect_Id INTEGER REFERENCES "Effects" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Time_Overlay TIMESTAMP NOT NULL DEFAULT current_timestamp,
	Time_Removal TIMESTAMP NULL CHECK(Time_Removal > Time_Overlay OR Time_Removal IS NULL),
	PRIMARY KEY (Character_Id, Effect_Id)
);

CREATE TABLE "Items"
(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL UNIQUE,
	Description TEXT NULL,
	Price REAL DEFAULT 0 NOT NULL CHECK(Price >= 0),
	Weight REAL DEFAULT 0 NOT NULL CHECK(Weight >= 0)
);

CREATE TABLE "Inventory"
(
	Character_Id INTEGER REFERENCES "Characters" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Item_Id INTEGER REFERENCES "Items" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Time_Getting TIMESTAMP UNIQUE DEFAULT current_timestamp,
	Time_Saling TIMESTAMP NULL CHECK(Time_Saling > Time_Getting OR Time_Saling IS NULL),
	PRIMARY KEY (Character_Id, Item_Id, Time_Getting)
);

CREATE TABLE "Rules"
(
	Id SERIAL PRIMARY KEY,
	Creator INTEGER NULL REFERENCES "Users" (Id)  ON UPDATE RESTRICT ON DELETE RESTRICT,
	Title TEXT NOT NULL UNIQUE,
	Description TEXT NOT NULL
);

CREATE TABLE "Games"
(
	Id SERIAL PRIMARY KEY,
	Genre_Id INTEGER NOT NULL REFERENCES "Genres" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Rules_Id INTEGER NOT NULL REFERENCES "Rules" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	GM_Id INTEGER NOT NULL REFERENCES "Users" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	State TEXT NOT NULL CHECK(State IN ('wiped', 'on session', 'not active')),
	Time_Creating TIMESTAMP NOT NULL DEFAULT current_timestamp,
	Time_Deleting TIMESTAMP NULL CHECK(Time_Deleting > Time_Creating OR Time_Deleting IS NULL)
);

CREATE TABLE "Groups"
(
	Game_Id INTEGER REFERENCES "Games" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Character_Id INTEGER REFERENCES "Characters" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (Game_Id, Character_Id)
);

CREATE TABLE "Sessions"
(
	Id SERIAL PRIMARY KEY,
	Game_Id INTEGER NOT NULL REFERENCES "Games" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	GMs_Rating REAL DEFAULT 5 NOT NULL CHECK(GMs_Rating BETWEEN 0 and 5),
	Date_Start TIMESTAMP NOT NULL,
	Date_End TIMESTAMP NULL CHECK(Date_End >= Date_Start OR Date_End IS NULL)
);

CREATE TABLE "Members"
(
	Session_Id INTEGER REFERENCES "Sessions" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Character_Id INTEGER REFERENCES "Characters" (Id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Characters_Rating REAL DEFAULT 5 NOT NULL CHECK(Characters_Rating BETWEEN 0 and 5),
	PRIMARY KEY (Session_Id, Character_Id)
);