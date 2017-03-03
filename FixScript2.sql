#DROP TABLE PhoneNumber;
#DROP TABLE Jobs;
#DROP TABLE Hobbies;
#DROP TABLE Cars;
#DROP TABLE Pets;
#SHOW TABLE Person;

#SHOW TABLES; #Debug




        

CREATE TABLE IF NOT EXISTS Person( #Create the table if it does not exist 
        Id INTEGER(11) NOT NULL UNIQUE, 
		Name VARCHAR(50) NOT NULL, 
        BornIn VARCHAR(50), Hates VARCHAR(50), Pets INTEGER(5),
        PRIMARY KEY(Id)); #Assign Id to be the Primary key of this Table
        
INSERT IGNORE INTO Person VALUES(1244, 'Tomas','Ireland','Beans', 0);#1
INSERT IGNORE INTO Person VALUES(255, 'Henrik',  'Sweden','Macaroni', 0); #2
INSERT IGNORE INTO Person VALUES(3006, 'Fredrik','Germany','Bratwurst', 0); #3
INSERT IGNORE INTO Person VALUES(4891, 'Mikael', 'Sweden','Tortellini', 0); #4
INSERT IGNORE INTO Person VALUES(5555, 'Mary', 'Switzerland','Brie', 0);#5
INSERT IGNORE INTO Person VALUES(6666, 'Joe', 'US',  'Bacon', 0);#6
INSERT IGNORE INTO Person VALUES(66, 'Castello', 'Italy','Risotto', 0);#7
INSERT IGNORE INTO Person VALUES(8912, 'Marco', 'Brazil','Sandwiches', 0);#8
INSERT IGNORE INTO Person VALUES(1002, 'Elin', 'Norway','Meatloaf', 0);#9
INSERT IGNORE INTO Person VALUES(5011, 'Cecilia', 'Denmark','Omelette', 0);#10

CREATE TABLE IF NOT EXISTS Pets(PetId INTEGER(11) PRIMARY KEY UNIQUE AUTO_INCREMENT, IdOfOwner INTEGER(11) NOT NULL, Name VARCHAR(50) NOT NULL, Race VARCHAR(50), Loves VARCHAR(50),
        FOREIGN KEY (IdOfOwner) REFERENCES Person(Id));

#Det är en One to many, eftersom, en person, kan ha många husdjur.

#En one to one relation, hade varit t.e.x, en Person och en sak som är bunden till deras Id
#t.e.x ett idKort




UPDATE Person SET Name = 'Mark' WHERE Id = 255; #4actor
UPDATE Person SET BornIn = 'Columbia' WHERE Id = 6666;
UPDATE Person SET BornIn = 'Island' WHERE Id = 5011;
        
CREATE TABLE IF NOT EXISTS Jobs(Id INTEGER(11), Title VARCHAR(50), #id, Title, Name, Location, Salary
							   Workplace VARCHAR(50), 
                               Salary INTEGER(30) NOT NULL, Employment VARCHAR(50) NOT NULL,
                               FOREIGN KEY (Id) REFERENCES Person(Id));  #The idea, is that, we get PersonID to be a Foreignkey
                               #from Person Table, to reflect PersonID

#Remove dependancies and remove Surplus information
#if related to the person in question, put it in relevant table
#Remove surplus name from Jobs and Hobbies
#Change Aquired and Owner from PhoneNumber
#Put races and type in one Table, if we have a general attribute that can be applied to several of the different values
#They become a foreign key to the table
#Registry number for CarID
CREATE TABLE IF NOT EXISTS Cars(CarID INTEGER(11) NOT NULL UNIQUE PRIMARY KEY,
        BelongsToId INTEGER(11) NOT NULL, Colour VARCHAR(50) NOT NULL,
        Price INTEGER(11) NOT NULL, MilesDriven INTEGER(11) NOT NULL, Serviced VARCHAR(50) NOT NULL, FOREIGN KEY (BelongsToId) REFERENCES Person(Id));

CREATE TABLE IF NOT EXISTS Hobbies(Id INTEGER(11), Hobby VARCHAR(50), Location VARCHAR(50), HoursPerWeek INTEGER(50), FOREIGN KEY (Id) REFERENCES Person(Id));

CREATE TABLE IF NOT EXISTS PhoneNumber(NumberId INTEGER(11) UNIQUE PRIMARY KEY, Id INTEGER(11), PhoneNr INTEGER(11) UNIQUE NOT NULL, Created VARCHAR(50), FOREIGN KEY (Id) REFERENCES Person(Id));

INSERT IGNORE INTO PhoneNumber VALUES(1, 1244,
0701999999, '2011-11-21');

INSERT IGNORE INTO PhoneNumber VALUES(2, 255,
0872111111, '2004-01-23');

INSERT IGNORE INTO PhoneNumber VALUES(3, 3006,
80234568, '2005-03-06');

INSERT IGNORE INTO PhoneNumber VALUES(4, 4891,
070142104, '1998-01-05');

INSERT IGNORE INTO PhoneNumber VALUES(5, 5555,
070515902, '1999-02-03');

INSERT IGNORE INTO PhoneNumber VALUES(6, 6666,
07011130, '1990-01-05');

INSERT IGNORE INTO PhoneNumber VALUES(7, 66,
070405819, '2005-05-01');

INSERT IGNORE INTO PhoneNumber VALUES(8, 8912,
070141298, '2006-06-01');

INSERT IGNORE INTO PhoneNumber VALUES(9, 1002,
0701249084, '2010-10-01');

INSERT IGNORE INTO PhoneNumber VALUES(10, 5011,
070124974, '2011-11-01');




#SELECT Name FROM Person WHERE Id IN
#SELECT   Hobbies FROM Person WHERE Id in 

INSERT IGNORE INTO Hobbies VALUES(1244, 'Mountaineering', 'Everest', 2);
INSERT IGNORE INTO Hobbies VALUES(1244, 'Biking', 'Nepal', 1);
INSERT IGNORE INTO Hobbies VALUES(255,'Golf', 'Miami', 15);
INSERT IGNORE INTO Hobbies VALUES(255, 'Biking', 'Nepal', 10);
INSERT IGNORE INTO Hobbies VALUES(3006,'Programming', 'Home', 12);
INSERT IGNORE INTO Hobbies VALUES(4891, 'Tennis', 'Båstad', 20);
INSERT IGNORE INTO Hobbies VALUES(5555, 'Knitting', 'Clubhouse', 30);
INSERT IGNORE INTO Hobbies VALUES(6666, 'Pottery', 'Workshop', 15);
INSERT IGNORE INTO Hobbies VALUES(66, 'Musician', 'Garage', 10);
INSERT IGNORE INTO Hobbies VALUES(8912,  'Jogging', 'Gym', 15);
INSERT IGNORE INTO Hobbies VALUES(1002, 'Engineering', 'Workshop', 2);
INSERT IGNORE INTO Hobbies VALUES(5011, 'Smithing', 'Smithy', 40);

DELETE FROM Hobbies WHERE Id IN (255) AND Location = 'Nepal';

#ID, Jobs, Name, Location, Salary, Employment, Workphone
INSERT IGNORE INTO Jobs VALUES(1244,'Plumber', #ID, Title, Name, Location, Salary
'Montreal', 10000, 'Hours');

INSERT IGNORE INTO Jobs VALUES(255,'Fireman', #ID, Title, Name, Location, Salary
'Chicago', 15320, 'Fulltime');



INSERT IGNORE INTO Jobs VALUES(4891, 'Programmer',  #ID, Title, Name, Location, Salary
'Dallas', 60000, 'Fulltime');

INSERT IGNORE INTO Jobs VALUES(5555, 'Senator',  #ID, Title, Name, Location, Salary
'Stockholm', 30000, '25%');

INSERT IGNORE INTO Jobs VALUES(6666, 'Designer',  #ID, Title, Name, Location, Salary
'Madrid', 50320, '75%');

INSERT IGNORE INTO Jobs VALUES(66, 'Farmer',  #ID, Title, Name, Location, Salary
'Rejkavik', 5000, 'Fulltime');

INSERT IGNORE INTO Jobs VALUES(8912,'Cashier',  #ID, Title, Name, Location, Salary
'Melbourne', 7000, 'Hours');

INSERT IGNORE INTO Jobs VALUES(1002,'Playtester',  #ID, Title, Name, Location, Salary
'Umeå', 25000, 'Fulltime');

INSERT IGNORE INTO Jobs VALUES(5011,'Writer',  #ID, Title, Name, Location, Salary
'Kopenhagen', 5000, 'Fulltime');
#We can only reference a index that is in range, in terms of the ID in
#the table of IDs for Persons - When we get 5, it's out of range, so we get a null value on everything.


UPDATE Jobs SET Employment='75%' WHERE Id IN (5555);
UPDATE Jobs SET Employment='Hours' WHERE Id in (66);
UPDATE Jobs SET Employment='Fulltime' WHERE Id in (8912);
UPDATE Jobs SET Employment='50%' WHERE Id in (255);


SELECT Person.Id, Person.BornIn, PhoneNumber.PhoneNr FROM Person RIGHT JOIN PhoneNumber USING (Id);
SELECT Person.Id, Jobs.Employment FROM Person RIGHT JOIN Jobs USING(Id);



SELECT Person.Id, Jobs.Workplace, Jobs.Salary FROM Person RIGHT JOIN Jobs ON Jobs.Id = Person.Id WHERE Person.Name LIKE ('C%');
SELECT Person.Id, Jobs.Workplace, Jobs.Salary FROM Person RIGHT JOIN Jobs ON Jobs.Id = Person.Id WHERE Person.BornIn LIKE ('S%');



#DELETE FROM Person WHERE Id IN (2, 4); #2

INSERT IGNORE INTO Pets VALUES(1,5011, 'Stompy',  'Yorkshire', 'Carrots');
INSERT IGNORE INTO Pets VALUES(2,5011, 'Wumpus',  'Terrier', 'Sticks');
INSERT IGNORE INTO Pets VALUES(3, 1002, 'Nightmare',  'IslandPony', 'Trotting');
INSERT IGNORE INTO Pets VALUES(4, 66, 'Moppen', 'Perser', 'Garn');  
INSERT IGNORE INTO Pets VALUES(5, 1244, 'Floppen',  'Bombay', 'Mice');  
INSERT IGNORE INTO Pets VALUES(6, 8912, 'Kroppen',  'Birman', 'Cheese');  
INSERT IGNORE INTO Pets VALUES(7, 5555, 'Shoppen',  'Asian', 'Food');  
INSERT IGNORE INTO Pets VALUES(8, 255, 'Proppen',  'Aegean', 'Dogs');  
INSERT IGNORE INTO Pets VALUES(9, 1244, 'Toppen',  'Perser', 'Sleeping');  
INSERT IGNORE INTO Pets VALUES(10, 4891, 'Droppen',  'Burmilla', 'Everything');  
INSERT IGNORE INTO Pets VALUES(11, 1244, 'Ryu',  'Snapjaw', 'Strawberries');  
INSERT IGNORE INTO Pets VALUES(12, 1244, 'Lee',  'Labrador', 'Fetch');  
INSERT IGNORE INTO Pets VALUES(13, 6666, 'Carpedium',  'Komodore', 'Bugs');  

DELETE FROM Pets WHERE PetId IN (10);
DELETE FROM Pets WHERE PetId IN (6);
DELETE FROM Pets WHERE PetId IN (2);
DELETE FROM Pets WHERE PetId IN (1);


#TAKE ALL THE VALUES, FROM EVERY SINGLE LANGUAGE, AND CLUMP THEM TOGETHER.
#THEN WE TAKE THE AVERAGE OF THESE VALUES.
#BUT, SINCE SOME VALUES, ARE 0, IT IS A FLAWED CALCULATION
#AS SOME VALUES, WILL ONLY HAVE 0 AND 100%, AND THUS, END UP AS 100%
#0, 10 = 10/2, 5

#Lista av Betyg, G, VG, MVG
#select Betyg, avg(Betyg) <-- (är felaktig, eftersom vi har inte Count commandot)
#from Betyglista
#group by Betyg;




UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 1244) WHERE Id = 1244;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 66) WHERE Id = 66;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 255) WHERE Id = 255;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 1002) WHERE Id = 1002;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 4891) WHERE Id = 4891;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 3006) WHERE Id = 3006;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 5011) WHERE Id = 5011;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 5555) WHERE Id = 5555;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 6666) WHERE Id = 6666;
UPDATE Person SET Pets = (SELECT COUNT(IdOfOwner) as Something FROM Pets WHERE IdOfOwner = 8912) WHERE Id = 8912;



SELECT Pets.IdOfOwner, Person.Name AS OwnedBy, Pets.Name FROM Person RIGHT JOIN Pets ON Pets.IdOfOwner = Person.Id;

#CarID, BelongsToId, Model, Price, MilesDriven, Serviced
#CarID, BelongsToId, Colour, Price, MilesDriven, Serviced
INSERT IGNORE INTO Cars VALUES(1, 5011, 'Yellow', 50000, 500, '2016-10-05');
INSERT IGNORE INTO Cars VALUES(2, 5011, 'Red', 5000, 50, 'Never');
INSERT IGNORE INTO Cars VALUES(3, 66, 'Grey', 65000, 535, '2011-01-16');
INSERT IGNORE INTO Cars VALUES(4, 255, 'Black', 25341, 550, '2015-11-10');
INSERT IGNORE INTO Cars VALUES(5, 1002, 'White', 33010, 1000, '1999-10-11');
INSERT IGNORE INTO Cars VALUES(6, 1244, 'Green', 330000, 1000, '2001-12-11');
INSERT IGNORE INTO Cars VALUES(7, 8912, 'Pink', 44000, 4000, '1998-09-11');
INSERT IGNORE INTO Cars VALUES(8, 3006, 'Purple', 54031, 25, 'Never');
INSERT IGNORE INTO Cars VALUES(9, 1002, 'Blue', 68891, 100, '1997-11-25');
INSERT IGNORE INTO Cars VALUES(10, 6666, 'Cyan', 53201, 132, '1995-10-11');

SELECT * FROM Person; #1
SELECT * FROM Jobs;
SELECT * FROM Hobbies;
SELECT * FROM PhoneNumber;
SELECT * FROM Pets;
SELECT * FROM Cars;