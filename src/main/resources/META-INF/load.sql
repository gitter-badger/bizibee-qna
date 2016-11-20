INSERT INTO `SIMULATIONENTITY` VALUES 
(1,0,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,null,0,0,0,0,'ARI Business Management Systems',0,0),
(2,100000,NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut commodo nisl velit, at scelerisque leo tempor eget. Sed at orci auctor, lacinia dolor id, dignissim sapien. Ut pulvinar sapien vel mi dignissim vulputate. Nunc viverra ligula eget dignissim lacinia. Nulla pulvinar imperdiet eros, eget rhoncus dui rutrum non. Sed sit amet tristique tortor, sit amet sollicitudin augue. Sed volutpat orci eget ipsum dictum, vitae posuere arcu dignissim. Mauris fermentum interdum finibus. Ut erat massa, congue quis feugiat sed, sodales non risus. Proin sit amet enim sed lacus aliquam vulputate ut sed leo. Suspendisse tincidunt nisi nec massa dignissim dignissim.\r\n\r\nInteger vel mauris vel quam auctor imperdiet. Nullam velit sem, auctor eget porttitor a, tincidunt eget libero. Integer augue dui, placerat a volutpat in, lobortis vel odio. Nunc consectetur fringilla orci. Praesent sagittis fringilla eros quis suscipit. Vivamus ac lobortis magna, nec vehicula dui. Fusce molestie fringilla leo, sit amet ullamcorper erat rhoncus nec. Cras fermentum mi massa, vitae vulputate nulla ornare in. Praesent blandit pellentesque elit vel fermentum. Phasellus tempor eu arcu sit amet gravida. Ut ipsum lorem, accumsan vel neque non, convallis tempor nisl. Curabitur maximus dui lorem, in pharetra neque dapibus nec. Cras finibus tortor vitae velit rutrum, sit amet malesuada quam aliquet.',0.4,0.25,0.4,0.55,'Unilever Best Seller 2016',5000000,0.2);

INSERT INTO `USERENTITY` (`ID`,`SIMULATION_ID`,`EMAIL`,`NAME`,`PASSWORD`,`USERGROUP`,`USERNAME`) VALUES 
(1,1,'cem@aripd.com','cem aripd','cem','Administrators','cem'),
(2,2,'ruler@aripd.com','ruler Unilever','ruler','Rulers','ruler'),
(3,2,'member@aripd.com','member Unilever','member','Members','member');

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
(1,2,0,120,75,1,0,'200 ml güzellik',0,1),
(2,2,0,120,75,1,0,'200 ml kepek',0,1),
(3,2,0,120,75,1,0,'350 ml bakım',0,2),
(4,2,0,120,75,1,0,'350 ml dökülme karşıtı',0,2),
(5,2,0,120,75,1,0,'350 ml güzellik',0,1),
(6,2,0,120,75,1,0,'350 ml kepek',0,1),
(7,2,0,120,75,1,0,'400 ml bakım',0,3),
(8,2,0,120,75,1,0,'400 ml güzellik',0,3),
(9,2,0,120,75,1,0,'500 ml bakım',0,2),
(10,2,0,120,75,1,0,'500 ml dökülme karşıtı',0,2),
(11,2,0,120,75,1,0,'500ml+350ml bakım+dökülme karşıtı',0,2),
(12,2,0,120,75,1,0,'600 ml + 200ml bakım + güzellik copack',0,3),
(13,2,0,120,75,1,0,'600 ml + 200ml güzellik + kepek copack',0,1),
(14,2,0,120,75,1,0,'600 ml bakım şampuanı',0,3),
(15,2,0,120,75,1,0,'600 ml güzellik şampuanı',0,1),
(16,2,0,120,75,1,0,'600 ml güzellik şampuanı',0,3),
(17,2,0,120,75,1,0,'600 ml kepek şampuanı',0,1),
(18,2,0,120,75,1,0,'600ml+200ml güzellik + bakım copack',0,3),
(19,2,0,120,75,1,0,'600ml+200ml kepek+güzellik copack',0,1),
(20,2,0,120,75,1,0,'900 ml bakım şampuanı',0,2),
(21,2,0,120,75,1,0,'900 ml dökülme karşıtı şampuanı',0,2),
(22,2,0,120,75,1,0,'TG',0,null),
(23,2,0,120,75,1,0,'DH',0,null);

INSERT INTO `DECISIONENTITY` VALUES 
(1,2,0,'SINGLE_SKU_LISTING','Description about Volume Hunting',0,0,'Volume Hunting',true,1,0),
(2,2,0,'SINGLE_SKU_LISTING','Description about Delisting',0,0,'Delisting',false,2,0),
(3,2,0,'MULTIPLE_SKU_LISTING','Description about Listing',0,0,'Listing',false,3,0),
(4,2,0,'RANGE_SKU_LISTING','Description about Price Positioning',0,0,'Price Positioning',true,4,0),
(5,2,0,'MULTIPLE_CHOICE','Description about E-Commerce Investment',0,0,'E-Commerce Investment',false,5,0),
(6,2,0,'MULTIPLE_CHOICE_SKU_LISTING','Description about Display',0,0,'Display',false,6,0),
(7,2,0,'SINGLE_CHOICE','Description about Assortment',0,0,'Assortment',false,7,0),
(8,2,0,'SINGLE_CHOICE_SKU_LISTING','Description about Promotion',0,0,'Promotion',false,8,0),
(9,2,0,'MULTIPLE_CHOICE','Description about Team Management',0,0,'Team Management',false,9,0);

INSERT INTO `DECISIONCHOICEENTITY` VALUES 
(1,2,0,0.0004936303895,120,75,1,0.007103098485,'No e-commerce investment',0.007735973618,5),
(2,2,36000,0.008284364257,120,75,1,0.003374872189,'Hiring someone with technical knowledge to deal e-commerce area',0.006874970942,5),
(3,2,20000,0.002952620555,120,75,1,0.002459780695,'Creating campaign visuals via agency for customers webpage',0.008436672092,5),
(4,2,10000,0.009283852454,120,75,1,0.008911583135,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',0.007276897698,5),

(5,2,0,0,120,75,1,0,'No display',0,6),
(6,2,2000,0,120,75,1,0,'Beginning of rayon',0,6),
(7,2,1000,0,120,75,1,0,'Gondola',0,6),
(8,2,1500,0,120,75,1,0,'Checkout stand',0,6),

(9,2,0,0,120,75,1,0,'No assortment',0,7),
(10,2,2000,0,120,75,1,0,'Assortment1',0,7),
(11,2,1000,0,120,75,1,0,'Assortment2',0,7),
(12,2,1500,0,120,75,1,0,'Assortment3',0,7),

(13,2,0,0,120,75,1,0,'No promotion',0,8),
(14,2,0,0,120,75,1,0,'Gift Promotion (off pack)',0,8),
(15,2,0,0,120,75,1,0,'Copack Promotion',0,8),
(16,2,0,0,120,75,1,0,'Price Promotion',0,8),

(17,2,0,0,120,75,1,0,'No event nor improvement',0,9),
(18,2,10000,0,120,75,1,0,'Motivational event',0,9),
(19,2,15000,0,120,75,1,0,'Training',0,9),
(20,2,24000,0,120,75,1,0,'Providing tablets integrated to inventory system',0,9);

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

