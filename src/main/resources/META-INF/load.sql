INSERT INTO `SIMULATIONENTITY` VALUES 
(1,0,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,0,0,0,0,'Capsim Türkiye',0,0),
(2,100000,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,0.4,0.25,0.4,0.55,'Unilever 2016',5000000,0.2);

INSERT INTO `USERENTITY` (`ID`,`SIMULATION_ID`,`EMAIL`,`NAME`,`PASSWORD`,`USERGROUP`,`USERNAME`) VALUES 
(1,1,'cem@aripd.com','cem aripd','cem','Administrators','cem'),
(2,2,'ruler@aripd.com','ruler Unilever','ruler','Rulers','ruler'),
(3,2,'member@aripd.com','member Unilever','member','Members','member');

INSERT INTO `TEAMENTITY` VALUES 
(1,2,'Team1'),
(2,2,'Team2'),
(3,2,'Team3'),
(4,2,'Team4');

INSERT INTO `PLAYERENTITY` VALUES 
(1,2,'player1@aripd.com','Player1','player1','player1',null),
(2,2,'player2@aripd.com','Player2','player2','player2',null),
(3,2,'player3@aripd.com','Player3','player3','player3',null),
(4,2,'player4@aripd.com','Player4','player4','player4',null);

INSERT INTO `PRODUCTENTITY` VALUES 
(1,2,0,120,75,1,0,'B 350 ml bakım şampuanı',0),
(2,2,0,120,75,1,0,'B 350 ml dökülme karşıtı şampuan',0),
(3,2,0,120,75,1,0,'A 350 ml güzellik',0),
(4,2,0,120,75,1,0,'C 400 ml bakım şampuanı',0),
(5,2,0,120,75,1,0,'B 500 ml bakım şampuanı',0),
(6,2,0,120,75,1,0,'B 500 ml dökülme karşıtı şampuan',0),
(7,2,0,120,75,1,0,'C 600 ml bakım şampuanı',0),
(8,2,0,120,75,1,0,'C 600 ml güzellik',0),
(9,2,0,120,75,1,0,'A 600 ml kepek şampuanı',0),
(10,2,0,120,75,1,0,'A 600+200 ml kepek-güzellik copack',0),
(11,2,0,120,75,1,0,'B 900 ml bakım',0),
(12,2,0,120,75,1,0,'B 900 ml dökülme karşıtı şampuan',0),
(13,2,0,120,75,1,0,'TG',0),
(14,2,0,120,75,1,0,'DH',0);

INSERT INTO `DECISIONENTITY` VALUES 
(1,2,0,'SINGLE_CHOICE','Lorem ipsum',0,0,'Volume Hunting Mechanism',0),
(2,2,0,'SINGLE_CHOICE','Lorem ipsum',0,0,'Delisting',0),
(3,2,0,'MULTIPLE_CHOICE','Lorem ipsum',0,0,'Listing',0),
(4,2,0,'RANGE_PRODUCT_LISTING','Lorem ipsum',0,0,'Price Positioning',0),
(5,2,0,'MULTIPLE_CHOICE','Lorem ipsum',0,0,'E-Commerce Investment',0),
(6,2,0,'MULTIPLE_CHOICE_PRODUCT_LISTING','Lorem ipsum',0,0,'Display',0),
(7,2,0,'SINGLE_CHOICE','Lorem ipsum',0,0,'Assortment',0),
(8,2,0,'SINGLE_CHOICE_PRODUCT_LISTING','Lorem ipsum',0,0,'Promotion',0),
(9,2,0,'MULTIPLE_CHOICE','Lorem ipsum',0,0,'Team Management',0);

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,0,0.0001,120,75,1,0.0003,'A 600+200 ml kepek-güzellik copack',0.0005,1),
(2,2,0,0.0002,120,75,1,0.0005,'B 500 ml bakım şampuanı',0.0007,1),
(3,2,0,0.0003,120,75,1,0.0007,'C 600 ml bakım şampuanı',0.0009,1),
(4,2,0,0.0004,120,75,1,0.0009,'A 600 ml kepek şampuanı',0.0011,1),
(5,2,0,0.0005,120,75,1,0.0011,'B 900 ml dökülme karşıtı şampuan',0.0013,1),

(6,2,0,0.0001,120,75,1,0.0003,'No product delisted',0.0005,2),
(7,2,1000,0.0002,120,75,1,0.0005,'A 350 ml güzellik',0.0007,2),
(8,2,2000,0.0003,120,75,1,0.0007,'B 350 ml dökülme karşıtı',0.0009,2),
(9,2,3000,0.0004,120,75,1,0.0009,'B 500 ml dökülme karşıtı',0.0011,2),
(10,2,4000,0.0005,120,75,1,0.0011,'B 350 ml bakım',0.0013,2),
(11,2,5000,0.0006,120,75,1,0.0013,'C 400 ml bakım',0.0015,2),

(12,2,0,0.003782685093,120,75,1,0.008119014818,'No product listed',0.009652305001,3),
(13,2,1000,0.009620234368,120,75,1,0.003244649623,'TG',0.001240006948,3),
(14,2,20000,0.004764810841,120,75,1,0.009769760368,'DH',0.001628055219,3),

(15,2,0,0.0004936303895,120,75,1,0.007103098485,'No e-commerce investment',0.007735973618,5),
(16,2,36000,0.008284364257,120,75,1,0.003374872189,'Hiring someone with technical knowledge to deal e-commerce area',0.006874970942,5),
(17,2,20000,0.002952620555,120,75,1,0.002459780695,'Creating product/campaing visuals for our products via agency for customers webpage',0.008436672092,5),
(18,2,10000,0.009283852454,120,75,1,0.008911583135,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',0.007276897698,5),

(19,2,0,0,120,75,1,0,'No display',0,6),
(20,2,2000,0,120,75,1,0,'Beginning of rayon',0,6),
(21,2,1000,0,120,75,1,0,'Gondola',0,6),
(22,2,1500,0,120,75,1,0,'Checkout stand',0,6),

(23,2,0,0,120,75,1,0,'No assortment',0,7),
(24,2,2000,0,120,75,1,0,'Assortment1',0,7),
(25,2,1000,0,120,75,1,0,'Assortmen2',0,7),
(26,2,1500,0,120,75,1,0,'Assortmen3',0,7),

(27,2,0,0,120,75,1,0,'Gift Promotion(off pack)',0,8),
(28,2,0,0,120,75,1,0,'Copack Promotion',0,8),
(29,2,0,0,120,75,1,0,'Price Promotion',0,8),

(30,2,0,0,120,75,1,0,'No event nor improvement',0,9),
(31,2,10000,0,120,75,1,0,'Motivational event',0,9),
(32,2,15000,0,120,75,1,0,'Training',0,9),
(33,2,24000,0,120,75,1,0,'Providing tablets integrated to inventory system',0,9);

INSERT INTO `decisions_products` (`PRODUCT_ID`, `DECISION_ID`)
VALUES
	(5,1),
	(7,1),
	(9,1),
	(10,1),
	(12,1),
	(1,2),
	(2,2),
	(3,2),
	(4,2),
	(6,2),
	(13,3),
	(14,3),
	(8,4),
	(9,4),
	(11,4),
	(12,4),
	(5,6),
	(7,6),
	(9,6),
	(10,6),
	(12,6),
	(3,8),
	(4,8),
	(6,8),
	(8,8),
	(9,8),
	(12,8);
