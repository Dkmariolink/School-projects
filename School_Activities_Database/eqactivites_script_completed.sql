-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema eqactivities
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eqactivities
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eqactivities` DEFAULT CHARACTER SET utf8 ;
USE `eqactivities` ;

-- -----------------------------------------------------
-- Table `eqactivities`.`Dept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Dept` (
  `DeptID` INT NOT NULL,
  `dept_Name` VARCHAR(255) NULL,
  PRIMARY KEY (`DeptID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Person` (
  `personID` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(255) NULL,
  `City` VARCHAR(255) NULL,
  `State` VARCHAR(255) NULL,
  `Email` VARCHAR(255) NULL,
  `Zip` INT NULL,
  `Phone` INT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `DeptID` INT NULL,
  PRIMARY KEY (`personID`),
  INDEX `fk_Person_Dept1_idx` (`DeptID` ASC),
  CONSTRAINT `fk_Person_Dept1`
    FOREIGN KEY (`DeptID`)
    REFERENCES `eqactivities`.`Dept` (`DeptID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Faculty` (
  `facultyID` INT NOT NULL,
  `personID` INT NOT NULL,
  `highestDegree` VARCHAR(45) NULL,
  `hireDate` DATE NULL,
  PRIMARY KEY (`facultyID`, `personID`),
  INDEX `fk_Faculty_Person1_idx` (`personID` ASC),
  CONSTRAINT `fk_Faculty_Person1`
    FOREIGN KEY (`personID`)
    REFERENCES `eqactivities`.`Person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Student` (
  `studentID` INT NOT NULL,  
  `personID` INT NOT NULL,
  `Honors_YorN` VARCHAR(1) NULL,
  `majorName` VARCHAR(255) NULL,
  `gradYear` YEAR NULL,
  `GPA` FLOAT NULL,
  `facultyID` INT NULL,
  PRIMARY KEY (`studentID`, `personID`),
  INDEX `fk_Student_Faculty1_idx` (`facultyID` ASC),
  INDEX `fk_Student_Person1_idx` (`personID` ASC),
  CONSTRAINT `fk_Student_Faculty1`
    FOREIGN KEY (`facultyID`)
    REFERENCES `eqactivities`.`Faculty` (`facultyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_Person1`
    FOREIGN KEY (`personID`)
    REFERENCES `eqactivities`.`Person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Admin` (
  `adminID` INT NOT NULL,
  `personID` INT NOT NULL,
  `posTitle` VARCHAR(255) NULL,
  `hourlyRate` FLOAT NULL,
  PRIMARY KEY (`adminID`, `personID`),
  INDEX `fk_Admin_Person1_idx` (`personID` ASC),
  CONSTRAINT `fk_Admin_Person1`
    FOREIGN KEY (`personID`)
    REFERENCES `eqactivities`.`Person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Registration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Registration` (
  `eventID` INT NOT NULL,
  `personID` INT NOT NULL,
  `regDate` DATE NULL,
  `attendYorN` VARCHAR(1) NULL,
  PRIMARY KEY (`eventID`, `personID`),
  INDEX `fk_Registration_Person1_idx` (`personID` ASC),
  CONSTRAINT `fk_Registration_Person1`
    FOREIGN KEY (`personID`)
    REFERENCES `eqactivities`.`Person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Event` (
  `eventID` INT NOT NULL,
  `Length` FLOAT NULL COMMENT 'In minutes',
  `Description` TEXT(1000) NULL,
  `Date_Time` DATETIME NULL,
  `Title` VARCHAR(255) NULL,
  `Location_description` TEXT(500) NULL,
  `Tcket_cost` FLOAT NULL,
  PRIMARY KEY (`eventID`),
  CONSTRAINT `fk_Event_Registration1`
    FOREIGN KEY (`eventID`)
    REFERENCES `eqactivities`.`Registration` (`eventID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eqactivities`.`Evaluation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eqactivities`.`Evaluation` (
  `personID` INT NOT NULL,
  `eventID` INT NOT NULL,
  `Q1` TEXT(500) NULL,
  `Q2` TEXT(500) NULL,
  `Q3` TEXT(500) NULL,
  PRIMARY KEY (`personID`, `eventID`),
  INDEX `fk_Evaluation_Event1_idx` (`eventID` ASC),
  CONSTRAINT `fk_Evaluation_Event1`
    FOREIGN KEY (`eventID`)
    REFERENCES `eqactivities`.`Event` (`eventID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evaluation_Person1`
    FOREIGN KEY (`personID`)
    REFERENCES `eqactivities`.`Person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (0,"1384 Sit Av.","Tallahassee","Florida","Duis.cursus@Vestibulumanteipsum.ca","77644",854-5137,"Chaney","Hebert");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (1,"8590 Suspendisse Road","Pike Creek","Delaware","vel@miac.co.uk","43252",433-8680,"Rhonda","Dyer");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (2,"Ap #402-2108 Interdum. Rd.","Jacksonville","Florida","mi.Aliquam@nec.com","90811",925-9304,"Christopher","Cline");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (3,"Ap #566-9954 Ligula Avenue","Hattiesburg","Minnesota","ipsum.cursus@laoreetipsumCurabitur.ca","49172",420-2389,"Medge","Meyer");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (4,"7748 Purus, St.","Racine","Wisconsin","dolor.egestas.rhoncus@eget.ca","74740",718-2727,"Karyn","Norris");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (5,"404-5480 Sed Ave","Colorado Springs","Colorado","tincidunt.neque.vitae@et.edu","10205",144-8852,"Madeline","Holman");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (6,"719-4944 Duis St.","Hillsboro","Oregon","amet.lorem.semper@ipsumnonarcu.co.uk","41881",190-9471,"Amena","Odom");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (7,"Ap #337-5723 Amet Av.","Lowell","Massachusetts","pellentesque@nonnisi.ca","18717",255-2755,"Azalia","Bright", 3000);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (8,"253-7745 Elit. Av.","Memphis","Tennesee","non.massa.non@Donectempuslorem.co.uk","25522",471-1098,"Griffin","Gordon");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (9,"3315 Trenchard Lane.","Charlotte","North Carolina","ninaB@gmail.com","29652",642-2645,"Nina","Burns");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (10,"4187 Johnsburough CT","Brooklyn","New York","Johntheman@yahoo.com","85426",886-5155,"John","Adams", 3002);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (11,"1254 Sunset Drive","Los Angeles","California","pauldenino12@gmail.com","90021",587-0601,"Paul","Denino");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (12,"4532 Hanamura Drive","Shinjuku","Tokyo","rininusa@yahoo.com","56436",114-6528,"Rin","Sakura");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (13,"1516 Pede Avenue","Portland","Maine","a.felis@anteVivamus.org","47572",428-5926,"Quintessa","Merrill", 3002);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (14,"5776 Quisque Street","Auburn","Maine","magna.et.ipsum@sitamet.net","49109",836-3602,"Ira","Armstrong");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (15,"Ap #209-1197 Ligula Rd.","Gresham","Oregon","elit.pretium@dignissimMaecenasornare.org","16161",195-2889,"Allegra","Knox");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (16, "P.O. Box 921, 7172 Lorem Av.","Fort Worth","Texas","parturient@sagittis.ca","84994",773-5827,"Hashim","Lambert", 3001);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (17,"1671 Risus, Av.","Pittsburgh","Pennsylvania","turpis.Nulla@Duisvolutpat.com","99553",401-0804,"Jerry","Pace");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (18, "9271 Eu St.","Norfolk","Virginia","nisl@tempusnon.net","18281",590-0470,"Wayne","Barnes");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (19, "778-9586 Nunc Rd.","Jefferson City","Missouri","eros@Quisquefringilla.org","24117",394-9822,"Kendall","Gordon");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (20, "368-4915 Ut St.","Great Falls","Montana","Curabitur@ut.edu","25420",671-8839,"Wing","Frye", 3000);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (21,"P.O. Box 393, 7683 Convallis Ave","Sacramento","California","convallis.ante.lectus@auctorquis.ca","91756",655-2886,"Oscar","Santiago", 3001);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName, DeptID)
VALUES (22,"662-3687 Tempor St.","Lafayette","Louisiana","dui@elitelit.edu","76030",870-2119,"Alika","Bright", 3000);
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (23,"8893 Sit Street","Sandy","Utah","nisi@metusAenean.net","61420",611-3343,"Joshua","Mcfarland");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (24,"174-1466 Diam Road","Jacksonville","Florida","non@nequeNullamut.edu","39802",111-7723,"Ebony","West");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (25, "372-5617 Ac St.","Lakewood","Colorado","Nam.tempor.diam@nasceturridiculus.edu","44622",826-9017,"Moses","Fry");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (26, "849-7159 Dui. Rd.","Laramie","Wyoming","ante.Maecenas@enimcommodo.ca","88492",554-7149,"Lars","Stark");
REPLACE INTO Person (personID, Address, City, State, Email, Zip, Phone, firstName, lastName)
VALUES (27,"Ap #427-1882 Est. Rd.","Gillette","Wyoming","Curabitur.vel@sollicitudin.net","94447",941-5308,"Clementine","Carpenter");



REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1000,0,3.5,"Computer Science");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1001,1,3.3,"Computer Science");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1002,2,3.2,"Business");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1003,3,3.0,"Engineering");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1004,4,3.1,"Computer Science");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1005,5,2.9,"Psychology");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1006,6,2.8,"Japanese");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1007,7,3.7,"Art");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1008,8,2.5,"Business");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1009,9,3.1,"Art");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1010,10,3.2,"English");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1011,11,3.3,"Computer Science");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1012,12,2.7,"Japanese");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1013,13,2.9,"Psychology");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1014,14,3.1,"Art");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1015,15,3.0,"Business");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1016,16,3.2,"Engineering");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1017,17,3.6,"Japanese");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1018,18,2.1,"English");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1019,19,4.0,"Computer Science");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1020,26,3.1,"Math");
REPLACE INTO Student (studentID, personID, GPA, majorName)
VALUES (1021,27,2.7,"Math");


REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2007, 7,"Bachelor", '2007-04-18');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2010, 10,"Bachelor", '2007-04-18');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2013, 13,"Bachelor", '2007-04-18');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2016, 16,"Associates", '2007-04-18');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2020, 20,"Associates", '2007-04-18');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2021,21,"Master",'2003-06-22');
REPLACE INTO Faculty (facultyID, personID, highestDegree, hireDate)
VALUES (2022,22,"Doctorate",'2010-01-30');

REPLACE INTO Dept (DeptID, dept_Name)
VALUES (3000,"Asset Management");
REPLACE INTO Dept (DeptID, dept_Name)
VALUES (3001,"Funds Management");
REPLACE INTO Dept (DeptID, dept_Name)
VALUES (3002,"Recruitment");

REPLACE INTO Admin (adminID, personID, posTitle, hourlyRate) 
VALUES (4012,12,"Vice President",62938);
REPLACE INTO Admin (adminID, personID, posTitle, hourlyRate) 
VALUES (4008,8,"Assistant Manager",62938);
REPLACE INTO Admin (adminID, personID, posTitle, hourlyRate) 
VALUES (4023,23,"President",62938);
REPLACE INTO Admin (adminID, personID, posTitle, hourlyRate) 
VALUES (4024,24,"Secretary",62938);
REPLACE INTO Admin (adminID, personID, posTitle, hourlyRate) 
VALUES (4025,25,"Lead Manager",62938);

REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5000, 0, '2017-06-28', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5000, 1, '2017-06-29', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 2, '2017-07-20', "N");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 3, '2017-04-15', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5000, 4, '2017-06-30', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5003, 5, '2017-02-10', "N");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5003, 6, '2017-02-27', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 7, '2017-04-16', "N");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 8, '2017-07-30', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 9, '2017-08-03', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5000, 10, '2017-06-28', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 11, '2017-04-17', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5003, 12, '2017-03-10', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 13, '2017-04-16', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 14, '2017-08-14', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5000, 15, '2017-07-01', "N");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 16, '2017-07-25', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5003, 17, '2017-02-12', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5003, 18, '2017-02-22', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5001, 19, '2017-07-29', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 26, '2017-04-16', "Y");
REPLACE INTO Registration (eventID, personID, regDate, attendYorN)
VALUES (5002, 27, '2017-04-15', "Y");

REPLACE INTO Event (eventID, Length, Description, Date_Time, Title,Location_description, Tcket_cost)
VALUES (5000, 5, "Career Fair day for students to learn about respective careers", '2017-06-28 14:30:00', "Career Day", "Belk Gym", 7 );
REPLACE INTO Event (eventID, Length, Description, Date_Time, Title,Location_description, Tcket_cost)
VALUES (5001, 30, "Mock Interview sessions for practice before the real thing", '2017-07-20 15:00:00', "Mock Interview", "Belk Gym", 3 );
REPLACE INTO Event (eventID, Length, Description, Date_Time, Title,Location_description, Tcket_cost)
VALUES (5002, 7, "Guest Speakers in the field, one ticket is an all access pass to every speaker", '2017-04-15 16:30:00', "Professionals in the field", "Belk Gym", 10 );
REPLACE INTO Event (eventID, Length, Description, Date_Time, Title,Location_description, Tcket_cost)
VALUES (5003, 60, "Portfolio/Resume Building to ensure you have the best of the best before applying for your dream career", '2017-02-10 13:00:00', "Resume Builder", "Belk Gym", 5 );


REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (0, 5000,"On a scale of 1 to 5, was the event useful to you? - 3","On a scale of 1 to 5, would you recommend this event to another person? - 5 ","On a scale of 1 to 5, was the event relative to your major? - 3");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (1, 5000,"On a scale of 1 to 5, was the event useful to you? - 2","On a scale of 1 to 5, would you recommend this event to another person? - 3 ","On a scale of 1 to 5, was the event relative to your major? - 3");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (3, 5002,"On a scale of 1 to 5, was the event useful to you? - 4","On a scale of 1 to 5, would you recommend this event to another person? - 1 ","On a scale of 1 to 5, was the event relative to your major? - 4");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (4, 5000,"On a scale of 1 to 5, was the event useful to you? - 5","On a scale of 1 to 5, would you recommend this event to another person? - 3 ","On a scale of 1 to 5, was the event relative to your major? - 5");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (6, 5003,"On a scale of 1 to 5, was the event useful to you? - 5","On a scale of 1 to 5, would you recommend this event to another person? - 2 ","On a scale of 1 to 5, was the event relative to your major? - 1");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (8, 5001,"On a scale of 1 to 5, was the event useful to you? - 3","On a scale of 1 to 5, would you recommend this event to another person? - 3 ","On a scale of 1 to 5, was the event relative to your major? - 2");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (9, 5001,"On a scale of 1 to 5, was the event useful to you? - 3","On a scale of 1 to 5, would you recommend this event to another person? - 5 ","On a scale of 1 to 5, was the event relative to your major? - 4");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (10, 5000,"On a scale of 1 to 5, was the event useful to you? - 1","On a scale of 1 to 5, would you recommend this event to another person? - 4 ","On a scale of 1 to 5, was the event relative to your major? - 1");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (11, 5002,"On a scale of 1 to 5, was the event useful to you? - 2","On a scale of 1 to 5, would you recommend this event to another person? - 2 ","On a scale of 1 to 5, was the event relative to your major? - 4");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (12, 5003,"On a scale of 1 to 5, was the event useful to you? - 3","On a scale of 1 to 5, would you recommend this event to another person? - 5 ","On a scale of 1 to 5, was the event relative to your major? - 3");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (13, 5002,"On a scale of 1 to 5, was the event useful to you? - 4","On a scale of 1 to 5, would you recommend this event to another person? - 4 ","On a scale of 1 to 5, was the event relative to your major? - 5");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (14, 5001,"On a scale of 1 to 5, was the event useful to you? - 5","On a scale of 1 to 5, would you recommend this event to another person? - 2 ","On a scale of 1 to 5, was the event relative to your major? - 1");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (16, 5001,"On a scale of 1 to 5, was the event useful to you? - 3","On a scale of 1 to 5, would you recommend this event to another person? - 4 ","On a scale of 1 to 5, was the event relative to your major? - 3");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (17, 5003,"On a scale of 1 to 5, was the event useful to you? - 2","On a scale of 1 to 5, would you recommend this event to another person? - 5 ","On a scale of 1 to 5, was the event relative to your major? - 5");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (18, 5003,"On a scale of 1 to 5, was the event useful to you? - 2","On a scale of 1 to 5, would you recommend this event to another person? - 5 ","On a scale of 1 to 5, was the event relative to your major? - 3");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (19, 5001,"On a scale of 1 to 5, was the event useful to you? - 4","On a scale of 1 to 5, would you recommend this event to another person? - 4 ","On a scale of 1 to 5, was the event relative to your major? - 2");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (26, 5002,"On a scale of 1 to 5, was the event useful to you? - 4","On a scale of 1 to 5, would you recommend this event to another person? - 3 ","On a scale of 1 to 5, was the event relative to your major? - 4");
REPLACE INTO Evaluation (personID, eventID, Q1, Q2, Q3)
VALUES (27, 5002,"On a scale of 1 to 5, was the event useful to you? - 2","On a scale of 1 to 5, would you recommend this event to another person? - 4 ","On a scale of 1 to 5, was the event relative to your major? - 1");



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
