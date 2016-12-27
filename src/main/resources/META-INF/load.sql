INSERT INTO `SIMULATIONENTITY` VALUES 
(1,0,'ARIPDCOM',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,null,0,0,0,0,'ARI Business Management Systems',0,0),
(2,100000,'UNILEVER2016',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut commodo nisl velit, at scelerisque leo tempor eget. Sed at orci auctor, lacinia dolor id, dignissim sapien. Ut pulvinar sapien vel mi dignissim vulputate. Nunc viverra ligula eget dignissim lacinia. Nulla pulvinar imperdiet eros, eget rhoncus dui rutrum non. Sed sit amet tristique tortor, sit amet sollicitudin augue. Sed volutpat orci eget ipsum dictum, vitae posuere arcu dignissim. Mauris fermentum interdum finibus. Ut erat massa, congue quis feugiat sed, sodales non risus. Proin sit amet enim sed lacus aliquam vulputate ut sed leo. Suspendisse tincidunt nisi nec massa dignissim dignissim.',0.4,0.25,0.4,0.55,'Unilever Beyond Sales 2016',5000000,0.2);

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

INSERT INTO `DECISIONENTITY` (`ID`, `SIMULATION_ID`, `BUDGET`, `DECISIONTYPE`, `DESCRIPTION`, `GM`, `MS`, `NAME`, `REMARK`, `REQUIRED`, `SORTORDER`, `USG`)
VALUES
	(1,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_SKU_LISTING','Description about Volume Hunting',-1+2*RAND(),-1+2*RAND(),'Volume Hunting','Remark about Volume Hunting',1,1,-1+2*RAND()),
	(2,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_SKU_LISTING','Description about Delisting',-1+2*RAND(),-1+2*RAND(),'Delisting','Remark about Delisting',0,2,-1+2*RAND()),
	(3,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_SKU_LISTING','Description about Listing',-1+2*RAND(),-1+2*RAND(),'Listing','Remark about Listing',0,3,-1+2*RAND()),
	(4,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'RANGE_SKU_LISTING','Description about Price Positioning',-1+2*RAND(),-1+2*RAND(),'Price Positioning','Remark about Price Positioning',1,4,-1+2*RAND()),
	(5,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE','Description about E-Commerce Investment',-1+2*RAND(),-1+2*RAND(),'E-Commerce Investment','Remark about E-Commerce Investment',0,5,-1+2*RAND()),
	(6,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE_SKU_LISTING','Description about Display',-1+2*RAND(),-1+2*RAND(),'Display','Remark about Display',0,6,-1+2*RAND()),
	(7,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'RANGE_SKU_LISTING','Description about Planogram',-1+2*RAND(),-1+2*RAND(),'Planogram','Remark about Planogram',0,7,-1+2*RAND()),
	(8,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'SINGLE_CHOICE_SKU_LISTING','Description about Promotion',-1+2*RAND(),-1+2*RAND(),'Promotion','Remark about Promotion',0,8,-1+2*RAND()),
	(9,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),'MULTIPLE_CHOICE','Description about Team Management',-1+2*RAND(),-1+2*RAND(),'Team Management','Remark about Team Management',0,9,-1+2*RAND());

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
(16,2,FLOOR(RAND()*(-1000-(-36000)+1))+(-36000),-1+2*RAND(),120,75,1,-1+2*RAND(),'Providing tablets integrated to inventory system',-1+2*RAND(),9);

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

