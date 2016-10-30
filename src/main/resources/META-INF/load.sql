INSERT INTO `SIMULATIONENTITY` VALUES 
(1,0,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,0,0,0,0,'Capsim Türkiye',0,0),
(2,100000,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,0.4,0.25,0.4,0.55,'Unilever 2016',5000000,0.2);

INSERT INTO `USERENTITY` (`ID`,`EMAIL`,`FIRSTNAME`,`LASTNAME`,`LOCALE`,`PASSWORD`,`USERGROUP`,`USERNAME`,`SIMULATION_ID`) VALUES 
(1,'cem@aripd.com','cem','aripd','tr_TR','cem','Administrators','cem',1),
(2,'ruler@aripd.com','ruler','Unilever','tr_TR','ruler','Rulers','ruler',2),
(3,'member@aripd.com','member','Unilever','en_US','member','Members','member',2);

INSERT INTO `TEAMENTITY` VALUES 
(1,2,'Team1'),
(2,2,'Team2'),
(3,2,'Team3'),
(4,2,'Team4');

INSERT INTO `PRODUCTENTITY` VALUES 
(1,2,0,120,75,1,0,'Product1',0),
(2,2,0,120,75,1,0,'Product2',0),
(3,2,0,120,75,1,0,'Product3',0),
(4,2,0,120,75,1,0,'Product4',0);

INSERT INTO `DECISIONENTITY` VALUES 
(1,2,0,'SINGLE_CHOICE',0,0,'Volume Hunting Mechanism',0),
(2,2,0,'SINGLE_CHOICE',0,0,'Delisting',0),
(3,2,0,'MULTIPLE_CHOICE',0,0,'Listing',0),
(4,2,0,'RANGE_PRODUCT_LISTING',0,0,'Price Positioning',0),
(5,2,0,'MULTIPLE_CHOICE',0,0,'E-Commerce Investment',0),
(6,2,0,'MULTIPLE_CHOICE_PRODUCT_LISTING',0,0,'Display',0),
(7,2,0,'SINGLE_CHOICE',0,0,'Assortment',0),
(8,2,0,'SINGLE_CHOICE_PRODUCT_LISTING',0,0,'Promotion',0),
(9,2,0,'MULTIPLE_CHOICE',0,0,'Team Management',0);

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,0,0.0001,0.0003,'A 600+200 ml kepek-güzellik copack',0.0005,1),
(2,2,0,0.0002,0.0005,'B 500 ml bakım şampuanı',0.0007,1),
(3,2,0,0.0003,0.0007,'C 600 ml bakım şampuanı',0.0009,1),
(4,2,0,0.0004,0.0009,'A 600 ml kepek şampuanı',0.0011,1),
(5,2,0,0.0005,0.0011,'B 900 ml dökülme karşıtı şampuan',0.0013,1),

(6,2,0,0.0001,0.0003,'No product delisted',0.0005,2),
(7,2,1000,0.0002,0.0005,'A 350 ml güzellik',0.0007,2),
(8,2,2000,0.0003,0.0007,'B 350 ml dökülme karşıtı',0.0009,2),
(9,2,3000,0.0004,0.0009,'B 500 ml dökülme karşıtı',0.0011,2),
(10,2,4000,0.0005,0.0011,'B 350 ml bakım',0.0013,2),
(11,2,5000,0.0006,0.0013,'C 400 ml bakım',0.0015,2),

(12,2,0,0.003782685093,0.008119014818,'No product listed',0.009652305001,3),
(13,2,1000,0.009620234368,0.003244649623,'TG',0.001240006948,3),
(14,2,20000,0.004764810841,0.009769760368,'DH',0.001628055219,3),

(15,2,0,0.0004936303895,0.007103098485,'No e-commerce investment',0.007735973618,5),
(16,2,36000,0.008284364257,0.003374872189,'Hiring someone with technical knowledge to deal e-commerce area',0.006874970942,5),
(17,2,20000,0.002952620555,0.002459780695,'Creating product/campaing visuals for our products via agency for customers webpage',0.008436672092,5),
(18,2,10000,0.009283852454,0.008911583135,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',0.007276897698,5),

(19,2,0,0,0,'No display',0,6),
(20,2,2000,0,0,'Beginning of rayon',0,6),
(21,2,1000,0,0,'Gondola',0,6),
(22,2,1500,0,0,'Checkout stand',0,6),

(23,2,0,0,0,'Gift Promotion(off pack)',0,8),
(24,2,0,0,0,'Copack Promotion',0,8),
(25,2,0,0,0,'Price Promotion',0,8),

(26,2,0,0,0,'No event nor improvement',0,9),
(27,2,10000,0,0,'Motivational event',0,9),
(28,2,15000,0,0,'Training',0,9),
(29,2,24000,0,0,'Providing tablets integrated to inventory system',0,9);

