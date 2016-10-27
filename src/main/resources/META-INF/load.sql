INSERT INTO `COMPANYENTITY` VALUES 
(1,NOW() + INTERVAL 30 DAY,'Capsim Türkiye'),
(2,NOW() + INTERVAL 30 DAY,'Unilever');

INSERT INTO `USERENTITY` (`ID`,`EMAIL`,`FIRSTNAME`,`LASTNAME`,`LOCALE`,`PASSWORD`,`USERGROUP`,`USERSTATUS`,`COMPANY_ID`) VALUES 
(1,'cem@aripd.com','cem','aripd','tr_TR','cem','Administrators','Confirmed',1),
(2,'dev@aripd.com','dev','aripd','tr_TR','dev','Administrators','Confirmed',2),
(3,'test@aripd.com','test','aripd','en_US','test','Members','Confirmed',2);

INSERT INTO `TEAMENTITY` VALUES 
(1,2,'Team1'),
(2,2,'Team2'),
(3,2,'Team3'),
(4,2,'Team4');

INSERT INTO `PRODUCTENTITY` VALUES 
(1,2,'A 600+200 ml kepek-güzellik copack'),
(2,2,'B 500 ml bakım şampuanı'),
(3,2,'C 600 ml bakım şampuanı'),
(4,2,'A 600 ml kepek şampuanı'),
(5,2,'B 900 ml dökülme karşıtı şampuan'),
(6,2,'A 350 ml güzellik'),
(7,2,'B 350 ml dökülme karşıtı'),
(8,2,'B 500 ml dökülme karşıtı'),
(9,2,'B 350 ml bakım'),
(10,2,'C 400 ml bakım'),
(11,2,'TG'),
(12,2,'DH');

INSERT INTO `DECISIONENTITY` VALUES 
(1,2,'Volume Hunting Mechanism'),
(2,2,'Delisting'),
(3,2,'Listing'),
(4,2,'Price Positioning'),
(5,2,'E-Commerce Investment'),
(6,2,'Display'),
(7,2,'Assortment'),
(8,2,'Promotion'),
(9,2,'Team Management');

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,0.7735973618,0.04936303895,'No e-commerce investment',0.7103098485,5),
(2,2,0.6874970942,0.8284364257,'Hiring someone with technical knowledge to deal e-commerce area',0.3374872189,5),
(3,2,0.8436672092,0.2952620555,'Creating product/campaing visuals for our products via agency for customers webpage',0.2459780695,5),
(4,2,0.7276897698,0.9283852454,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',0.8911583135,5),
(5,2,1,1,'Beginning of rayon',1,6),
(6,2,1,1,'Gondola',1,6),
(7,2,1,1,'Checkout stand',1,6),
(8,2,1,1,'Gift Promotion(off pack)',1,8),
(9,2,1,1,'Copack Promotion',1,8),
(10,2,1,1,'Price Promotion',1,8),
(11,2,1,1,'Motivational event',1,9),
(12,2,1,1,'Training',1,9),
(13,2,1,1,'Providing tablets integrated to inventory system',1,9);

