INSERT INTO `SIMULATIONENTITY` VALUES 
(1,0,'ARIPDCOM',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,null,0,0,0,0,'ARI Business Management Systems',0,0),
(2,100000,'UNILEVER2016',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut commodo nisl velit, at scelerisque leo tempor eget. Sed at orci auctor, lacinia dolor id, dignissim sapien. Ut pulvinar sapien vel mi dignissim vulputate. Nunc viverra ligula eget dignissim lacinia. Nulla pulvinar imperdiet eros, eget rhoncus dui rutrum non. Sed sit amet tristique tortor, sit amet sollicitudin augue. Sed volutpat orci eget ipsum dictum, vitae posuere arcu dignissim. Mauris fermentum interdum finibus. Ut erat massa, congue quis feugiat sed, sodales non risus. Proin sit amet enim sed lacus aliquam vulputate ut sed leo. Suspendisse tincidunt nisi nec massa dignissim dignissim.',0.4,0.25,0.4,0.55,'Unilever Beyond Sales 2016',5000000,0.2);

INSERT INTO `USERENTITY` (`ID`,`SIMULATION_ID`,`EMAIL`,`NAME`,`PASSWORD`,`USERGROUP`,`USERNAME`) VALUES 
(1,1,'cem@aripd.com','cem aripd','cem','Administrators','cem'),
(2,2,'ruler@aripd.com','ruler Unilever','ruler','Rulers','ruler'),
(3,2,'player@aripd.com','player Unilever','player','Players','player');

INSERT INTO `TEAMENTITY` VALUES 
(1,'A Team'),
(2,'B Team'),
(3,'C Team'),
(4,'D Team'),
(5,'E Team'),
(6,'F Team');

INSERT INTO `BRANDENTITY` VALUES 
(1,2,'A Brand'),
(2,2,'B Brand'),
(3,2,'C Brand');

INSERT INTO `SKUENTITY` (`ID`,`SIMULATION_ID`,`GM`,`INDEXMAX`,`INDEXMIN`,`INDEXSTEP`,`MS`,`NAME`,`USG`,`BRAND_ID`) VALUES 
(1,2,RAND(),120,75,1,RAND(),'200 ml güzellik',RAND(),1),
(2,2,RAND(),120,75,1,RAND(),'200 ml kepek',RAND(),1),
(3,2,RAND(),120,75,1,RAND(),'350 ml bakım',RAND(),2),
(4,2,RAND(),120,75,1,RAND(),'350 ml dökülme karşıtı',RAND(),2),
(5,2,RAND(),120,75,1,RAND(),'350 ml güzellik',RAND(),1),
(6,2,RAND(),120,75,1,RAND(),'350 ml kepek',RAND(),1),
(7,2,RAND(),120,75,1,RAND(),'400 ml bakım',RAND(),3),
(8,2,RAND(),120,75,1,RAND(),'400 ml güzellik',RAND(),3),
(9,2,RAND(),120,75,1,RAND(),'500 ml bakım',RAND(),2),
(10,2,RAND(),120,75,1,RAND(),'500 ml dökülme karşıtı',RAND(),2),
(11,2,RAND(),120,75,1,RAND(),'500ml+350ml bakım+dökülme karşıtı',RAND(),2),
(12,2,RAND(),120,75,1,RAND(),'600 ml + 200ml bakım + güzellik copack',RAND(),3),
(13,2,RAND(),120,75,1,RAND(),'600 ml + 200ml güzellik + kepek copack',RAND(),1),
(14,2,RAND(),120,75,1,RAND(),'600 ml bakım şampuanı',RAND(),3),
(15,2,RAND(),120,75,1,RAND(),'600 ml güzellik şampuanı',RAND(),1),
(16,2,RAND(),120,75,1,RAND(),'600 ml güzellik şampuanı',RAND(),3),
(17,2,RAND(),120,75,1,RAND(),'600 ml kepek şampuanı',RAND(),1),
(18,2,RAND(),120,75,1,RAND(),'600ml+200ml güzellik + bakım copack',RAND(),3),
(19,2,RAND(),120,75,1,RAND(),'600ml+200ml kepek+güzellik copack',RAND(),1),
(20,2,RAND(),120,75,1,RAND(),'900 ml bakım şampuanı',RAND(),2),
(21,2,RAND(),120,75,1,RAND(),'900 ml dökülme karşıtı şampuanı',RAND(),2),
(22,2,RAND(),120,75,1,RAND(),'TG',RAND(),null),
(23,2,RAND(),120,75,1,RAND(),'DH',RAND(),null);

INSERT INTO `DECISIONENTITY` (`ID`, `SIMULATION_ID`, `BUDGET`, `DECISIONTYPE`, `DESCRIPTION`, `GM`, `MS`, `NAME`, `REQUIRED`, `SORTORDER`, `USG`)
VALUES
	(1,2,FLOOR(RAND()*(36000-1000+1))+1000,'SINGLE_SKU_LISTING','Description about Volume Hunting',RAND(),RAND(),'Volume Hunting',1,1,RAND()),
	(2,2,FLOOR(RAND()*(36000-1000+1))+1000,'SINGLE_SKU_LISTING','Description about Delisting',RAND(),RAND(),'Delisting',0,2,RAND()),
	(3,2,FLOOR(RAND()*(36000-1000+1))+1000,'MULTIPLE_SKU_LISTING','Description about Listing',RAND(),RAND(),'Listing',0,3,RAND()),
	(4,2,FLOOR(RAND()*(36000-1000+1))+1000,'RANGE_SKU_LISTING','Description about Price Positioning',RAND(),RAND(),'Price Positioning',1,4,RAND()),
	(5,2,FLOOR(RAND()*(36000-1000+1))+1000,'MULTIPLE_CHOICE','Description about E-Commerce Investment',RAND(),RAND(),'E-Commerce Investment',0,5,RAND()),
	(6,2,FLOOR(RAND()*(36000-1000+1))+1000,'MULTIPLE_CHOICE_SKU_LISTING','Description about Display',RAND(),RAND(),'Display',0,6,RAND()),
	(7,2,FLOOR(RAND()*(36000-1000+1))+1000,'SINGLE_CHOICE','Description about Assortment',RAND(),RAND(),'Assortment',0,7,RAND()),
	(8,2,FLOOR(RAND()*(36000-1000+1))+1000,'SINGLE_CHOICE_SKU_LISTING','Description about Promotion',RAND(),RAND(),'Promotion',0,8,RAND()),
	(9,2,FLOOR(RAND()*(36000-1000+1))+1000,'MULTIPLE_CHOICE','Description about Team Management',RAND(),RAND(),'Team Management',0,9,RAND());

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'No e-commerce investment',RAND(),5),
(2,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Hiring someone with technical knowledge to deal e-commerce area',RAND(),5),
(3,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Creating campaign visuals via agency for customers webpage',RAND(),5),
(4,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Planning activities special to e-commerce, where prices are slightly lower than normal ones',RAND(),5),

(5,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'No display',RAND(),6),
(6,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Beginning of rayon',RAND(),6),
(7,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Gondola',RAND(),6),
(8,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Checkout stand',RAND(),6),

(9,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'No assortment',RAND(),7),
(10,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Assortment1',RAND(),7),
(11,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Assortment2',RAND(),7),
(12,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Assortment3',RAND(),7),

(13,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'No promotion',RAND(),8),
(14,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Gift Promotion (off pack)',RAND(),8),
(15,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Copack Promotion',RAND(),8),
(16,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Price Promotion',RAND(),8),

(17,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'No event nor improvement',RAND(),9),
(18,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Motivational event',RAND(),9),
(19,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Training',RAND(),9),
(20,2,FLOOR(RAND()*(36000-1000+1))+1000,RAND(),120,75,1,RAND(),'Providing tablets integrated to inventory system',RAND(),9);

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
	(5,8),
	(7,8),
	(10,8),
	(16,8),
	(17,8),
	(21,8);

/*
INSERT INTO `RESPONSEENTITY` (`ID`, `USER_ID`, `SIMULATION_ID`, `OUTCOME`, `DECISION_ID`)
VALUES
	(1,3,2,'{\"id\":14,\"name\":\"600 ml bakım şampuanı\",\"gm\":0.0}',1),
	(2,3,2,'{\"id\":5,\"name\":\"350 ml güzellik\",\"gm\":0.0}',2),
	(3,3,2,'{\"skus\":[{\"id\":22,\"name\":\"TG\",\"gm\":0.0},{\"id\":23,\"name\":\"DH\",\"gm\":0.0}]}',3),
	(4,3,2,'[{\"sku\":17,\"value\":75}, {\"sku\":19,\"value\":76}, {\"sku\":20,\"value\":77}, {\"sku\":21,\"value\":78}]',4),
	(5,3,2,'{\"decisionchoices\":[{\"id\":2,\"name\":\"Hiring someone with technical knowledge to deal e-commerce area\",\"gm\":0.008284364257},{\"id\":3,\"name\":\"Creating campaign visuals via agency for customers webpage\",\"gm\":0.002952620555},{\"id\":4,\"name\":\"Planning activities special to e-commerce, where prices are slightly lower than normal ones\",\"gm\":0.009283852454}]}',5),
	(6,3,2,'[{\"sku\":9,\"decisionchoices\":[{\"decisionchoice\":5}]}, {\"sku\":13,\"decisionchoices\":[{\"decisionchoice\":6},{\"decisionchoice\":7}]}, {\"sku\":14,\"decisionchoices\":[{\"decisionchoice\":7},{\"decisionchoice\":8}]}, {\"sku\":17,\"decisionchoices\":[{\"decisionchoice\":6},{\"decisionchoice\":7},{\"decisionchoice\":8}]}, {\"sku\":21,\"decisionchoices\":[{\"decisionchoice\":6}]}]',6),
	(7,3,2,'{\"id\":12,\"name\":\"Assortment3\",\"gm\":0.0}',7),
	(8,3,2,'[{\"sku\":5,\"decisionchoice\":13}, {\"sku\":7,\"decisionchoice\":14}, {\"sku\":10,\"decisionchoice\":15}, {\"sku\":16,\"decisionchoice\":16}, {\"sku\":17,\"decisionchoice\":15}, {\"sku\":21,\"decisionchoice\":14}]',8),
	(9,3,2,'{\"decisionchoices\":[{\"id\":18,\"name\":\"Motivational event\",\"gm\":0.0},{\"id\":19,\"name\":\"Training\",\"gm\":0.0},{\"id\":20,\"name\":\"Providing tablets integrated to inventory system\",\"gm\":0.0}]}',9);
*/
