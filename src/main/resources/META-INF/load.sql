INSERT INTO `SIMULATIONENTITY` (`ID`, `BUDGETSTART`, `CODE`, `DATEEND`, `DATESTART`, `DESCRIPTION`, `GMSTART`, `GMWEIGHTED`, `MSSTART`, `MSWEIGHTED`, `NAME`, `SALESSTART`, `SCORESTART`, `THANKS`, `USGWEIGHTED`)
VALUES
	(1,0,'ARIPDCOM','2017-01-17 16:29:48','2016-12-18 16:29:48',NULL,0,0,0,0,'ARI Business Management Systems',0,0,'',0),
	(2,100000,'UNILEVER2016','2017-01-17 16:29:48','2016-12-18 16:29:48','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut commodo nisl velit, at scelerisque leo tempor eget. Sed at orci auctor, lacinia dolor id, dignissim sapien. Ut pulvinar sapien vel mi dignissim vulputate. Nunc viverra ligula eget dignissim lacinia. Nulla pulvinar imperdiet eros, eget rhoncus dui rutrum non. Sed sit amet tristique tortor, sit amet sollicitudin augue. Sed volutpat orci eget ipsum dictum, vitae posuere arcu dignissim. Mauris fermentum interdum finibus. Ut erat massa, congue quis feugiat sed, sodales non risus. Proin sit amet enim sed lacus aliquam vulputate ut sed leo. Suspendisse tincidunt nisi nec massa dignissim dignissim.',0.4,0.25,0.4,0.55,'Unilever Beyond Sales 2016',5000000,0,'Thanks for your contribution',0.2);


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

INSERT INTO `QUESTIONENTITY` VALUES
(1,2,NULL,'Description about Question1','Question1','Remark about Question1',TRUE,1,'SINGLE_CHOICE',UUID()),
(2,2,NULL,'Description about Price Positioning','Price Positioning','Remark about Price Positioning',TRUE,2,'RANGE_CHOICE',UUID()),
(3,2,NULL,'Description about Question2','Question2','Remark about Question2',TRUE,3,'SINGLE_CHOICE',UUID()),
(4,2,NULL,'Description about Volume Hunting','Volume Hunting','Remark about Volume Hunting',TRUE,4,'SINGLE_CHOICE',UUID()),
(5,2,NULL,'Description about Listing','Listing','Remark about Listing',TRUE,5,'MULTIPLE_CHOICE',UUID()),
(6,2,NULL,'Description about Question3','Question3','Remark about Question3',TRUE,6,'SINGLE_CHOICE',UUID()),
(7,2,NULL,'Description about Question4','Question4','Remark about Question4',TRUE,7,'SINGLE_CHOICE',UUID()),
(8,2,NULL,'Description about Promotion - Pro 600 ml beauty','Promotion - Pro 600 ml beauty','Remark about Promotion - Pro 600 ml beauty',TRUE,8,'SINGLE_CHOICE',UUID()),
(9,2,NULL,'Description about Promotion - Neoru 350 Dailycare','Promotion - Neoru 350 Dailycare','Remark about Promotion - Neoru 350 Dailycare',TRUE,9,'SINGLE_CHOICE',UUID()),
(10,2,NULL,'Description about Promotion - Astro 600+200 güzellik+bakım','Promotion - Astro 600+200 güzellik+bakım','Remark about Promotion - Astro 600+200 güzellik+bakım',TRUE,10,'SINGLE_CHOICE',UUID()),
(11,2,NULL,'Description about Promotion - ASTRO 600 ML GÜZELLİK','Promotion - ASTRO 600 ML GÜZELLİK','Remark about Promotion - ASTRO 600 ML GÜZELLİK',TRUE,11,'SINGLE_CHOICE',UUID()),
(12,2,NULL,'Description about Question5','Question5','Remark about Question5',TRUE,12,'SINGLE_CHOICE',UUID()),
(13,2,NULL,'Description about Team Management','Team Management','Remark about Team Management',TRUE,13,'MULTIPLE_CHOICE',UUID()),
(14,2,NULL,'Description about Question6','Question6','Remark about Question6',TRUE,14,'SINGLE_CHOICE',UUID()),
(15,2,NULL,'Description about Display - PRO 600+200 KEPEK+GÜZELLİK','Display - PRO 600+200 KEPEK+GÜZELLİK','Remark about Display - PRO 600+200 KEPEK+GÜZELLİK',TRUE,15,'MULTIPLE_CHOICE',UUID()),
(16,2,NULL,'Description about Display - PRO 600+200 GÜZELLİK + KEPEK','Display - PRO 600+200 GÜZELLİK + KEPEK','Remark about Display - PRO 600+200 GÜZELLİK + KEPEK',TRUE,16,'MULTIPLE_CHOICE',UUID()),
(17,2,NULL,'Description about Display - NEURO 500+350 ml bakım+dökülme','Display - NEURO 500+350 ml bakım+dökülme','Remark about Display - NEURO 500+350 ml bakım+dökülme',TRUE,17,'MULTIPLE_CHOICE',UUID()),
(18,2,NULL,'Description about Display - neuro 500 ml bakım','Display - neuro 500 ml bakım','Remark about Display - neuro 500 ml bakım',TRUE,18,'MULTIPLE_CHOICE',UUID()),
(19,2,NULL,'Description about Question7','Question7','Remark about Question7',TRUE,19,'SINGLE_CHOICE',UUID()),
(20,2,NULL,'Description about Question8','Question8','Remark about Question8',TRUE,20,'SINGLE_CHOICE',UUID()),
(21,2,NULL,'Description about Question9','Question9','Remark about Question9',TRUE,21,'SINGLE_CHOICE',UUID()),
(22,2,NULL,'Description about E-Commerce Investment','E-Commerce Investment','Remark about E-Commerce Investment',TRUE,22,'MULTIPLE_CHOICE',UUID()),
(23,2,NULL,'Description about Question10','Question10','Remark about Question10',TRUE,23,'SINGLE_CHOICE',UUID()),
(24,2,NULL,'Description about Delisting','Delisting','Remark about Delisting',TRUE,24,'SINGLE_CHOICE',UUID()),
(25,2,NULL,'Description about Planogram','Planogram','Remark about Planogram',TRUE,25,'PLANOGRAM1',UUID());

INSERT INTO `ANSWERENTITY` VALUES 
(1,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',1),
(2,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',1),
(3,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',1),
(4,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',1),
(5,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',1),
(6,2,NULL,0,0,0,-0.005775,152,100,1,0,135,117,0.005775,-0.005775,117,0,0,0.0055,-0.0055,'350 ml kepek pro ürünü için 100 index ile 152 index arasinda bir fiyat belirleyin',2),
(7,2,NULL,0,0,0,-0.042378,120,90,1,0,111,99,0.042378,-0.042378,99,0,0,0.04036,-0.04036,'900 ml bakım şampuanı neuro ürünü için 90 index ile 120 index arasinda bir fiyat belirleyin',2),
(8,2,NULL,0,0,0,-0.004494,122,90,1,0,113,99,0.004494,-0.004494,99,0,0,0.00428,-0.00428,'500 ml dökülme karşıtı neuro ürünü için 90 index ile 122 index arasinda bir fiyat belirleyin',2),
(9,2,NULL,0,0,0,-0.003528,130,90,1,0,113,107,0.003528,-0.003528,107,0,0,0.00336,-0.00336,'400 ml güzellik astro ürünü için 90 index ile 130 index arasinda bir fiyat belirleyin',2),
(10,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',3),
(11,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',3),
(12,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',3),
(13,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',3),
(14,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',3),
(15,2,NULL,0,-0.050921,0,0,0,0,0,0.025,0,0,0,0,0,0,0.0484966622162884,0,0,'Neuro 350 ml Saç Dkülme Karşıtı',4),
(16,2,NULL,0,-0.006496,0,0,0,0,0,0.01,0,0,0,0,0,0,0.00625,0,0,'Pro 350 ml beauty',4),
(17,2,NULL,0,-0.006653,0,0,0,0,0,0.13,0,0,0,0,0,0,0.00625,0,0,'Pro 600 ml kepek',4),
(18,2,NULL,0,-0.051891,0,0,0,0,0,0.18,0,0,0,0,0,0,0.0484966622162884,0,0,'Neuro 900 ml bak?m',4),
(19,2,NULL,0,-0.004877,0,0,0,0,0,0.04,0,0,0,0,0,0,0.00464,0,0,'Astro 600+200 ml dailycare+beauty',4),
(20,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Hicbir Urun Getirme',5),
(21,2,NULL,-10000,0.009,0,0,0,0,0,0.0244648318042813,0,0,0,0,0,0,0.024,0,0,'TG',5),
(22,2,NULL,-20000,0.014,0,0,0,0,0,0.0458715596330275,0,0,0,0,0,0,0.043,0,0,'DH',5),
(23,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',6),
(24,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',6),
(25,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',6),
(26,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',6),
(27,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',6),
(28,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',7),
(29,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',7),
(30,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',7),
(31,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',7),
(32,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',7),
(33,2,NULL,0,0.0095722521558434,0,0,0,0,0,0.0277982339743227,0,0,0,0,0,0,0.0317916479093021,0,0,'No promotion',8),
(34,2,NULL,0,0.000554731321170431,0,0,0,0,0,0.0258587266481498,0,0,0,0,0,0,0.00284029463980363,0,0,'Gift Promotion (off pack)',8),
(35,2,NULL,0,0.0178658821301574,0,0,0,0,0,0.0253709046260541,0,0,0,0,0,0,0.0078936953693135,0,0,'Copack Promotion',8),
(36,2,NULL,0,0.0309586661850237,0,0,0,0,0,0.000127649679899143,0,0,0,0,0,0,0.0295014050332469,0,0,'Price Promotion',8),
(37,2,NULL,0,0.00198128908633738,0,0,0,0,0,0.000646099285919504,0,0,0,0,0,0,0.013004290323375,0,0,'No promotion',9),
(38,2,NULL,0,0.00153618719744184,0,0,0,0,0,0.0122122442807882,0,0,0,0,0,0,0.00428504266860318,0,0,'Gift Promotion (off pack)',9),
(39,2,NULL,0,0.00552345686141633,0,0,0,0,0,0.00418106257178617,0,0,0,0,0,0,0.0143895164793012,0,0,'Copack Promotion',9),
(40,2,NULL,0,0.00165396111345096,0,0,0,0,0,0.0121858989935502,0,0,0,0,0,0,0.0191001141059368,0,0,'Price Promotion',9),
(41,2,NULL,0,0.00527883292133352,0,0,0,0,0,0.011540264154219,0,0,0,0,0,0,0.0142951287092616,0,0,'No promotion',10),
(42,2,NULL,0,0.00994357990797213,0,0,0,0,0,0.0196903343856999,0,0,0,0,0,0,0.0228472725132474,0,0,'Gift Promotion (off pack)',10),
(43,2,NULL,0,0.0135561950780041,0,0,0,0,0,0.00362618225832486,0,0,0,0,0,0,0.0183990669354177,0,0,'Copack Promotion',10),
(44,2,NULL,0,0.0210981155020876,0,0,0,0,0,0.000581733868421173,0,0,0,0,0,0,0.00733002118746285,0,0,'Price Promotion',10),
(45,2,NULL,0,0.0100431684125287,0,0,0,0,0,0.0207608821946426,0,0,0,0,0,0,0.00402374025813545,0,0,'No promotion',11),
(46,2,NULL,0,0.0118504126971322,0,0,0,0,0,0.0310876689962843,0,0,0,0,0,0,0.0136928509149133,0,0,'Gift Promotion (off pack)',11),
(47,2,NULL,0,0.0172310896251623,0,0,0,0,0,0.0358236180839369,0,0,0,0,0,0,0.00465344558543662,0,0,'Copack Promotion',11),
(48,2,NULL,0,0.00926879508969379,0,0,0,0,0,0.0238684291789337,0,0,0,0,0,0,0.0268489767261808,0,0,'Price Promotion',11),
(49,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',12),
(50,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',12),
(51,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',12),
(52,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',12),
(53,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',12),
(54,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'No event nor improvement',13),
(55,2,NULL,-10000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Motivational event',13),
(56,2,NULL,-15000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Training',13),
(57,2,NULL,-24000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Providing tablets integrated to inventory system',13),
(58,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',14),
(59,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',14),
(60,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',14),
(61,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',14),
(62,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',14),
(63,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'No display',15),
(64,2,NULL,-2000,0,0,0,0,0,0,0.0148847445504671,0,0,0,0,0,0,0.00277029778849301,0,0,'Beginning of rayon',15),
(65,2,NULL,-1000,0,0,0,0,0,0,0.0101800968001363,0,0,0,0,0,0,0.00999625495691618,0,0,'Gondola',15),
(66,2,NULL,-1500,0,0,0,0,0,0,0.0515634634321503,0,0,0,0,0,0,0.0464090302173693,0,0,'Checkout stand',15),
(67,2,NULL,0,0,0,0,0,0,0,0.0247871081325893,0,0,0,0,0,0,0.0380973164528235,0,0,'No display',16),
(68,2,NULL,-2000,0,0,0,0,0,0,0.00307335492638855,0,0,0,0,0,0,0.0144551746812589,0,0,'Beginning of rayon',16),
(69,2,NULL,-1000,0,0,0,0,0,0,0.00899727161151646,0,0,0,0,0,0,0.0370963446175407,0,0,'Gondola',16),
(70,2,NULL,-1500,0,0,0,0,0,0,0.0163455702380348,0,0,0,0,0,0,0.0290039029538807,0,0,'Checkout stand',16),
(71,2,NULL,0,0,0,0,0,0,0,0.0112264962194736,0,0,0,0,0,0,0.0231997500578521,0,0,'No display',17),
(72,2,NULL,-2000,0,0,0,0,0,0,0.0118415438220902,0,0,0,0,0,0,0.0184626552110937,0,0,'Beginning of rayon',17),
(73,2,NULL,-1000,0,0,0,0,0,0,0.0186445343641527,0,0,0,0,0,0,0.0307414711332139,0,0,'Gondola',17),
(74,2,NULL,-1500,0,0,0,0,0,0,0.0128841947031982,0,0,0,0,0,0,0.0199539860580545,0,0,'Checkout stand',17),
(75,2,NULL,0,0,0,0,0,0,0,0.0786235155182692,0,0,0,0,0,0,0.0226166164604543,0,0,'No display',18),
(76,2,NULL,-2000,0,0,0,0,0,0,0.0712931497400595,0,0,0,0,0,0,0.0730787536009738,0,0,'Beginning of rayon',18),
(77,2,NULL,-1000,0,0,0,0,0,0,0.0809394851296969,0,0,0,0,0,0,0.0464295697486717,0,0,'Gondola',18),
(78,2,NULL,-1500,0,0,0,0,0,0,0.0874605862237325,0,0,0,0,0,0,0.0741622889398048,0,0,'Checkout stand',18),
(79,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',19),
(80,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',19),
(81,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',19),
(82,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',19),
(83,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',19),
(84,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',20),
(85,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',20),
(86,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',20),
(87,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',20),
(88,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',20),
(89,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',21),
(90,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',21),
(91,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',21),
(92,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',21),
(93,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',21),
(94,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'No e-commerce investment',22),
(95,2,NULL,-36000,0,0,0,0,0,0,0.009174,0,0,0,0,0,0,0.05,0,0,'Hiring someone with technical knowledge to deal e-commerce area',22),
(96,2,NULL,-20000,0,0,0,0,0,0,0.0018348,0,0,0,0,0,0,0.01,0,0,'Creating campaign visuals via agency for customers webpage',22),
(97,2,NULL,-10000,0,0,0,0,0,0,0.009174,0,0,0,0,0,0,0.05,0,0,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',22),
(98,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',23),
(99,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',23),
(100,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',23),
(101,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',23),
(102,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',23),
(103,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Hicbir Urunu Delist Etme',24),
(104,2,NULL,0,-0.00072,0,0,0,0,0,-0.013,0,0,0,0,0,0,-0.03976,0,0,'pro 200 ml kepek',24),
(105,2,NULL,0,-0.00014,0,0,0,0,0,-0.006,0,0,0,0,0,0,-0.01835,0,0,'pro 200 ml güzellik',24),
(106,2,NULL,0,0,0,0,0,0,0,-0.0025,0,0,0,0,0,0,-0.00765,0,0,'astro 400 ml daycare',24),
(107,2,NULL,0,-0.000727,0,0,0,0,0,-0.057,0,0,0,0,0,0,-0.17431,0,0,'neuro 900 ml dökülme karşıtı',24),
(108,2,NULL,0,0.000336,0,0,0,0,0,-0.0065,0,0,0,0,0,0,-0.01988,0,0,'astro 600 ml bakım',24),
(109,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml kepek şampuanı',25),
(110,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 600ml+200ml kepek+güzellik copack',25),
(111,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 200 ml kepek',25),
(112,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 350 ml kepek',25),
(113,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml güzellik şampuanı',25),
(114,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml + 200ml güzellik + kepek copack',25),
(115,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 200 ml güzellik',25),
(116,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'PRO 350 ml güzellik',25),
(117,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 900 ml bakım şampuanı',25),
(118,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 500ml+350ml bakım+dökülme karşıtı',25),
(119,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 350 ml bakım',25),
(120,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 500 ml bakım',25),
(121,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 900 ml dökülme karşıtı şampuanı',25),
(122,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 350 ml dökülme karşıtı',25),
(123,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'NEURO 500 ml dökülme karşıtı',25),
(124,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml güzellik şampuanı',25),
(125,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 600ml+200ml güzellik + bakım copack',25),
(126,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 400 ml güzellik',25),
(127,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml bakım şampuanı',25),
(128,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml + 200ml bakım + güzellik copack',25),
(129,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'ASTRO 400 ml bakım',25),
(130,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'DH',25),
(131,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'A&G',25);