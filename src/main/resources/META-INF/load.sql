INSERT INTO `SIMULATIONENTITY` (`ID`, `BUDGETSTART`, `CODE`, `DATEEND`, `DATESTART`, `DESCRIPTION`, `GMSTART`, `GMWEIGHTED`, `MSSTART`, `MSWEIGHTED`, `NAME`, `SALESSTART`, `THANKS`, `USGWEIGHTED`)
VALUES
	(1,0,'ARIPDCOM','2017-01-17 16:29:48','2016-12-18 16:29:48',NULL,0,0,0,0,'ARI Business Management Systems',0,'',0),
	(2,100000,'UNILEVER2016','2017-01-17 16:29:48','2016-12-18 16:29:48','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut commodo nisl velit, at scelerisque leo tempor eget. Sed at orci auctor, lacinia dolor id, dignissim sapien. Ut pulvinar sapien vel mi dignissim vulputate. Nunc viverra ligula eget dignissim lacinia. Nulla pulvinar imperdiet eros, eget rhoncus dui rutrum non. Sed sit amet tristique tortor, sit amet sollicitudin augue. Sed volutpat orci eget ipsum dictum, vitae posuere arcu dignissim. Mauris fermentum interdum finibus. Ut erat massa, congue quis feugiat sed, sodales non risus. Proin sit amet enim sed lacus aliquam vulputate ut sed leo. Suspendisse tincidunt nisi nec massa dignissim dignissim.',0.4,0.25,0.4,0.55,'Unilever Beyond Sales 2016',5000000,'Thanks for your contribution',0.2);


INSERT INTO `GUIDEENTITY` (`ID`,`SIMULATION_ID`,`DESCRIPTION`,`NAME`,`REMARK`,`SORTORDER`) VALUES  
(1,2,'Description about 1','Name1','Remark about 1',1),
(2,2,'Description about 2','Name2','Remark about 2',2),
(3,2,'Description about 3','Name3','Remark about 3',3),
(4,2,'Description about 4','Name4','Remark about 4',4),
(5,2,'Description about 5','Name5','Remark about 5',5);

INSERT INTO `USERENTITY` (`ID`,`SIMULATION_ID`,`EMAIL`,`NAME`,`PASSWORD`,`USERGROUP`,`USERNAME`) VALUES 
(1,1,'cem@aripd.com','cem aripd','cem','Administrators','cem'),
(2,2,'ruler@aripd.com','ruler Unilever','ruler','Rulers','ruler'),
(3,2,'player1@aripd.com','player1 Unilever','player1','Players','player1'),
(4,2,'player2@aripd.com','player2 Unilever','player2','Players','player2');

INSERT INTO `TEAMENTITY` VALUES 
(1,'A Team'),
(2,'B Team'),
(3,'C Team'),
(4,'D Team'),
(5,'E Team'),
(6,'F Team');

INSERT INTO `BRANDENTITY` VALUES 
(1,2,'A&G'),
(2,2,'Donny Hagel'),
(3,2,'PRO'),
(4,2,'ASTRO'),
(5,2,'NEURO');

INSERT INTO `SKUENTITY` (`ID`,`SIMULATION_ID`,`BUDGET`,`GM`,`INDEXMAX`,`INDEXMIN`,`INDEXSTEP`,`MS`,`NAME`,`USG`,`BRAND_ID`) VALUES 
(1,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'200 ml güzellik',-1+2*RAND(),3),
(2,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'200 ml kepek',-1+2*RAND(),3),
(3,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'350 ml bakım',-1+2*RAND(),4),
(4,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'350 ml dökülme karşıtı',-1+2*RAND(),4),
(5,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'350 ml güzellik',-1+2*RAND(),3),
(6,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'350 ml kepek',-1+2*RAND(),3),
(7,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'400 ml bakım',-1+2*RAND(),5),
(8,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'400 ml güzellik',-1+2*RAND(),5),
(9,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'500 ml bakım',-1+2*RAND(),4),
(10,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'500 ml dökülme karşıtı',-1+2*RAND(),4),
(11,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'500ml+350ml bakım+dökülme karşıtı',-1+2*RAND(),4),
(12,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml + 200ml bakım + güzellik copack',-1+2*RAND(),5),
(13,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml + 200ml güzellik + kepek copack',-1+2*RAND(),3),
(14,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml bakım şampuanı',-1+2*RAND(),5),
(15,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml güzellik şampuanı',-1+2*RAND(),3),
(16,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml güzellik şampuanı',-1+2*RAND(),5),
(17,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600 ml kepek şampuanı',-1+2*RAND(),3),
(18,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600ml+200ml güzellik + bakım copack',-1+2*RAND(),5),
(19,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'600ml+200ml kepek+güzellik copack',-1+2*RAND(),3),
(20,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'900 ml bakım şampuanı',-1+2*RAND(),4),
(21,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'900 ml dökülme karşıtı şampuanı',-1+2*RAND(),4),
(22,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'TG',-1+2*RAND(),1),
(23,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'DH',-1+2*RAND(),2);

INSERT INTO `DECISIONENTITY` (`ID`, `SIMULATION_ID`, `UUID`, `BUDGET`, `DECISIONTYPE`, `DESCRIPTION`, `GM`, `MS`, `NAME`, `REMARK`, `REQUIRED`, `SORTORDER`, `USG`)
VALUES
	(1,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_SKU_LISTING','Description about Volume Hunting',-1+2*RAND(),-1+2*RAND(),'Volume Hunting','Remark about Volume Hunting',1,13,-1+2*RAND()),
	(2,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_SKU_LISTING','Description about Delisting',-1+2*RAND(),-1+2*RAND(),'Delisting','Remark about Delisting',1,3,-1+2*RAND()),
	(3,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_SKU_LISTING','Description about Listing',-1+2*RAND(),-1+2*RAND(),'Listing','Remark about Listing',1,5,-1+2*RAND()),
	(4,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'RANGE_SKU_LISTING','Description about Price Positioning',-1+2*RAND(),-1+2*RAND(),'Price Positioning','Remark about Price Positioning',1,7,-1+2*RAND()),
	(5,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE','Description about E-Commerce Investment',-1+2*RAND(),-1+2*RAND(),'E-Commerce Investment','Remark about E-Commerce Investment',1,9,-1+2*RAND()),
	(6,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE_SKU_LISTING','Description about Display',-1+2*RAND(),-1+2*RAND(),'Display','Remark about Display',1,11,-1+2*RAND()),
	(7,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'PLANOGRAM1','Description about Planogram',-1+2*RAND(),-1+2*RAND(),'Planogram','Remark about Planogram',1,1,-1+2*RAND()),
	(8,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_CHOICE_SKU_LISTING','Description about Promotion',-1+2*RAND(),-1+2*RAND(),'Promotion','Remark about Promotion',1,15,-1+2*RAND()),
	(9,2,UUID(),FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE','Description about Team Management',-1+2*RAND(),-1+2*RAND(),'Team Management','Remark about Team Management',1,17,-1+2*RAND()),
	
	(10,2,UUID(),0,'SINGLE_CHOICE','Description about Question1',0,0,'Question1','Remark about Question1',1,2,0),
	(11,2,UUID(),0,'SINGLE_CHOICE','Description about Question2',0,0,'Question2','Remark about Question2',1,4,0),
	(12,2,UUID(),0,'SINGLE_CHOICE','Description about Question3',0,0,'Question3','Remark about Question3',1,6,0),
	(13,2,UUID(),0,'SINGLE_CHOICE','Description about Question4',0,0,'Question4','Remark about Question4',1,8,0),
	(14,2,UUID(),0,'SINGLE_CHOICE','Description about Question5',0,0,'Question5','Remark about Question5',1,10,0),
	(15,2,UUID(),0,'SINGLE_CHOICE','Description about Question6',0,0,'Question6','Remark about Question6',1,12,0),
	(16,2,UUID(),0,'SINGLE_CHOICE','Description about Question7',0,0,'Question7','Remark about Question7',1,14,0),
	(17,2,UUID(),0,'SINGLE_CHOICE','Description about Question8',0,0,'Question8','Remark about Question8',1,16,0),
	(18,2,UUID(),0,'SINGLE_CHOICE','Description about Question9',0,0,'Question9','Remark about Question9',1,18,0);

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'No e-commerce investment',-1+2*RAND(),5),
(2,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Hiring someone with technical knowledge to deal e-commerce area',-1+2*RAND(),5),
(3,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Creating campaign visuals via agency for customers webpage',-1+2*RAND(),5),
(4,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Planning activities special to e-commerce, where prices are slightly lower than normal ones',-1+2*RAND(),5),

(5,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'No display',-1+2*RAND(),6),
(6,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Beginning of rayon',-1+2*RAND(),6),
(7,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Gondola',-1+2*RAND(),6),
(8,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Checkout stand',-1+2*RAND(),6),

(9,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'No promotion',-1+2*RAND(),8),
(10,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Gift Promotion (off pack)',-1+2*RAND(),8),
(11,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Copack Promotion',-1+2*RAND(),8),
(12,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Price Promotion',-1+2*RAND(),8),

(13,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'No event nor improvement',-1+2*RAND(),9),
(14,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Motivational event',-1+2*RAND(),9),
(15,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Training',-1+2*RAND(),9),
(16,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Providing tablets integrated to inventory system',-1+2*RAND(),9),

(17,2,0,0,0,0,0,0,'Answer1',0,10),
(18,2,0,0,0,0,0,0,'Answer2',0,10),
(19,2,0,0,0,0,0,0,'Answer3',0,10),

(20,2,0,0,0,0,0,0,'Answer1',0,11),
(21,2,0,0,0,0,0,0,'Answer2',0,11),
(22,2,0,0,0,0,0,0,'Answer3',0,11),

(23,2,0,0,0,0,0,0,'Answer1',0,12),
(24,2,0,0,0,0,0,0,'Answer2',0,12),
(25,2,0,0,0,0,0,0,'Answer3',0,12),

(26,2,0,0,0,0,0,0,'Answer1',0,13),
(27,2,0,0,0,0,0,0,'Answer2',0,13),
(28,2,0,0,0,0,0,0,'Answer3',0,13),

(29,2,0,0,0,0,0,0,'Answer1',0,14),
(30,2,0,0,0,0,0,0,'Answer2',0,14),
(31,2,0,0,0,0,0,0,'Answer3',0,14),

(32,2,0,0,0,0,0,0,'Answer1',0,15),
(33,2,0,0,0,0,0,0,'Answer2',0,15),
(34,2,0,0,0,0,0,0,'Answer3',0,15),

(35,2,0,0,0,0,0,0,'Answer1',0,16),
(36,2,0,0,0,0,0,0,'Answer2',0,16),
(37,2,0,0,0,0,0,0,'Answer3',0,16),

(38,2,0,0,0,0,0,0,'Answer1',0,17),
(39,2,0,0,0,0,0,0,'Answer2',0,17),
(40,2,0,0,0,0,0,0,'Answer3',0,17),

(41,2,0,0,0,0,0,0,'Answer1',0,18),
(42,2,0,0,0,0,0,0,'Answer2',0,18),
(43,2,0,0,0,0,0,0,'Answer3',0,18);

INSERT INTO `decisions_skus` (`SKU_ID`, `DECISION_ID`)
VALUES
	(9,1),
	(14,1),
	(17,1),
	(19,1),
	(21,1),
	(3,2),
	(4,2),
	(5,2),
	(7,2),
	(10,2),
	(22,3),
	(23,3),
	(17,4),
	(19,4),
	(20,4),
	(21,4),
	(9,6),
	(13,6),
	(14,6),
	(17,6),
	(21,6),
	(1,7),
	(2,7),
	(3,7),
	(4,7),
	(5,7),
	(6,7),
	(7,7),
	(8,7),
	(9,7),
	(10,7),
	(11,7),
	(12,7),
	(13,7),
	(14,7),
	(15,7),
	(16,7),
	(17,7),
	(18,7),
	(19,7),
	(20,7),
	(21,7),
	(22,7),
	(23,7),
	(5,8),
	(7,8),
	(10,8),
	(16,8),
	(17,8),
	(21,8);

