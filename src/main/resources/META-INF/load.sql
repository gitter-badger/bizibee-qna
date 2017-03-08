INSERT INTO `SIMULATIONENTITY` (`ID`, `BUDGETSTART`, `BYTES`, `CODE`, `DATEEND`, `DATESTART`, `DESCRIPTION`, `GMSTART`, `GMWEIGHTED`, `MSSTART`, `MSWEIGHTED`, `NAME`, `SALESSTART`, `SCORESTART`, `THANKSDIALOGCONTENT`, `THANKSDIALOGHEADER`, `THANKSLINKHREF`, `THANKSLINKTEXT`, `USGWEIGHTED`)
VALUES
	(1,0,NULL,'ARIPDCOM',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,NULL,0,0,0,0,'ARI Business Management Systems',0,0,'','','','',0),
	(2,100000,NULL,'UNILEVER2017',NOW() + INTERVAL 15 DAY,NOW() - INTERVAL 15 DAY,'Your group are working as Customer Development Managers in Local Modern Trade in First Look Hair Co. First Look Hair Co. is one of leading Personal Care companies in Turkey. You can find detailed information about First Look Hair Co from next pages. You and your sales team are maintaining a sustainable partnership with your customer Planet. Planet is the biggest Supermarket Chains in İstanbul. You can find detailed information in the attached file about the case.',0.4,0.25,0.4,0.55,'Unilever Beyond Sales 2017',5000000,0,'Your simulations is completed. Thanks for your contribution.','Thanks for your contribution','http://www.unilever.com','Go to Competition Page',0.2);

INSERT INTO `USERENTITY` (`ID`, `BYTES`, `EMAIL`, `NAME`, `PASSWORD`, `TEAMNAME`, `USERGROUP`, `USERNAME`, `UUID`, `SIMULATION_ID`, `TEAM_ID`)
VALUES
	(1,NULL,'cem@aripd.com','cem aripd','cem',NULL,'Administrators','cem',NULL,1,NULL),
	(2,NULL,'ruler@aripd.com','ruler Unilever','ruler',NULL,'Rulers','ruler',NULL,2,NULL),
	(3,NULL,'player1@aripd.com','player1 Unilever','player1',NULL,'Players','player1',NULL,2,NULL),
	(4,NULL,'player2@aripd.com','player2 Unilever','player2',NULL,'Players','player2',NULL,2,NULL),
	(5,NULL,'player3@aripd.com','player3 Unilever','player3',NULL,'Players','player3',NULL,2,NULL),
	(6,NULL,'player4@aripd.com','player4 Unilever','player4',NULL,'Players','player4',NULL,2,NULL),
	(7,NULL,'player5@aripd.com','player5 Unilever','player5',NULL,'Players','player5',NULL,2,NULL),
	(8,NULL,'player6@aripd.com','player6 Unilever','player6',NULL,'Players','player6',NULL,2,NULL),
	(9,NULL,'player7@aripd.com','player7 Unilever','player7',NULL,'Players','player7',NULL,2,NULL),
	(10,NULL,'player8@aripd.com','player8 Unilever','player8',NULL,'Players','player8',NULL,2,NULL),
	(11,NULL,'player9@aripd.com','player9 Unilever','player9',NULL,'Players','player9',NULL,2,NULL),
	(12,NULL,'player10@aripd.com','player10 Unilever','player10',NULL,'Players','player10',NULL,2,NULL);

INSERT INTO `TEAMENTITY` (`ID`, `NAME`)
VALUES
	(1,'A Team'),
	(2,'B Team'),
	(3,'C Team'),
	(4,'D Team'),
	(5,'E Team'),
	(6,'F Team');

INSERT INTO `GUIDEENTITY` (`ID`, `SIMULATION_ID`, `DESCRIPTION`, `NAME`, `REMARK`, `SORTORDER`)
VALUES
(1,2,'','Price Positioning','Price is one of the core element of 6P. A concious shopper always check the price before buying process. That’s why every single company must check their products’ prices in the market. Price positioning is restructuring a product’s price by considering competition. While index to market average can bu used, index to biggest competitor can be used for positioning. However, possible changes in profitability and sales volume should be well predicted before positioning. Checking prices in the market and regulating them will be preferable for business results. Companies have 3 main purpose, USG-GM-MS, consider that they are related with eachother. For example; the lower prices you offer for market share gain, the lower gross margin you will have after that.',1),
(2,2,'','Volume Hunting','Volume Hunting is a mechanism where company sells products at favorable costs and renounces profitability in order to increase «product’s household penetration» Favorable costs to customer bring aggresive prices on shelf and it is likely to see an increase in market share and distribution. However, since profitability will be low during activity; timing, number of products and discount rates must be well organized because of gm target. ',2),
(3,2,'','Promotion','Promotion is a mechanism to impress shopper at points where shoppers and customers meet (inserts, in store visuals, virtual stores..). Some promotion tools are as follows. Gift Promotion(off pack): Giving away gifts for each product purchase on the cash-point Gift Copack Promotion: Mechanism where main product and gift are in the same package and displayed on the shelf as a copack. Price Promotion: Promotion where regular shelf prices are lowered during promotion period.',3),
(4,2,'','Team Management','Team management is the ability of an individual or an organization to administer and coordinate a group of individuals to perform a task. Team management involves teamwork, communication, objective setting and performance appraisals. Moreover, team management is the capability to identify problems and resolve conflicts within a team. There are various methods and leadership styles a team manager can take to increase personnel productivity and build an effective team',4),
(5,2,'','Display','Display is a method where products are exhibited to attract consumer in order to increase sales. There are some valuable areas in stores which aims sell-out growth Gondola: Cashier display: Ale-Central(additional display areas in the middle of the aisles), Some examples about these areas:',5),
(6,2,'','Planogram','For hair category’s shelf assorment, standing in front of shelf, prices of products are decreasing from left to right. This method is called «PIANO». Eye-hand level is the most precious area of shelf. Also, it is important to place products by their sizes.',6),
(7,2,'','E-Commerce Investment','Retail E-commerce sales are sales of goods and services where the business takes place over the Internet, an extra-net, Electronic Data Interchange (EDI) network, or other online system. Payment may or may not be made online. Business in this context is defined as an order placed by the buyer or price and terms of sale negotiated. For our company, being available at customer’s e-commerce system improves brand awareness, enables consumer to reach our products easily. Since online shopping has reached 10m people, being active at this platform will bring increase in USG and Markes Share.',7),
(8,2,'','Listing','Listing is openning a product to sales permanently. Although listing a product seems beneficial, if product does not perform well, delisting will be thought and customer’s confidence may hurt. Decrease in profitability may be observed for «to be listed» product as it requires marketing investment. Therefore, variables like return on sales, sustainability and profitability should be balanced and optimized during listing.',8),
(9,2,'','Delisting','Delisting is closing a product to sales permanently. This can happen because of product perception, unsuitability of product to shopper of customer. Likewise, low performances in market share, turnover and profitability can cause that. When a product is delisted, there will be loss of sales. Therefore, balance should be preserved. An alternative product can be proposed for delisted one, or a profitable product’s face area may be increased.',9);

INSERT INTO `GROUPENTITY` 
VALUES
(1,2,'Price Positioning'),
(2,2,'Volume Hunting'),
(3,2,'Promotion'),
(4,2,'Team Management'),
(5,2,'Display'),
(6,2,'Planogram'),
(7,2,'E-Commerce Investment'),
(8,2,'Listing'),
(9,2,'Delisting');

INSERT INTO `QUESTIONENTITY` 
VALUES
(1,2,NULL,0,0,0,0,0,'Confirmation for Question1','Are you sure to submit for Question1?','Description about Question1','QUESTION','Question1','Notification for Question1','To continue read notification for Question1','',TRUE,1,'SINGLE_CHOICE',UUID(),NULL,NULL),
(2,2,NULL,0,0,0,0,0,'Confirmation for Price Positioning','Are you sure to submit for Price Positioning?','In your analysis, you determined 4 main product’s price which will be organised again in Planet Market. Consider that, every decision you give will affect USG-GM and MS positively or negatively','SIMULATION','Price Positioning','Notification for Price Positioning','To continue read notification for Price Positioning','',TRUE,2,'RANGE_CHOICE',UUID(),1,1),
(3,2,NULL,0,0,0,0,0,'Confirmation for Question2','Are you sure to submit for Question2?','Description about Question2','QUESTION','Question2','Notification for Question2','To continue read notification for Question2','',TRUE,3,'SINGLE_CHOICE',UUID(),NULL,NULL),
(4,2,NULL,0,0,0,0,0,'Confirmation for Volume Hunting','Are you sure to submit for Volume Hunting?','There is a list of product choices for volume hunting below, Please choose the best alternative considiring back datas about products.','SIMULATION','Volume Hunting','Notification for Volume Hunting','To continue read notification for Volume Hunting','',TRUE,4,'SINGLE_CHOICE',UUID(),2,2),
(5,2,NULL,0,0,0,0,0,'Confirmation for Notification1','Are you sure to submit for Notification1?','Description about Notification1','NOTIFICATION','Notification1','Notification for Notification1','To continue read notification for Notification1','',TRUE,5,'SINGLE_CHOICE',UUID(),NULL,NULL),
(6,2,NULL,0,0,0,0,0,'Confirmation for Question4','Are you sure to submit for Question4?','Description about Question4','QUESTION','Question4','Notification for Question4','To continue read notification for Question4','',TRUE,6,'SINGLE_CHOICE',UUID(),NULL,NULL),
(7,2,NULL,0,0.0095722521558434,0.0277982339743227,0,0.0317916479093021,'Confirmation for Promotion - Pro 600 ml beauty','Are you sure to submit for Promotion - Pro 600 ml beauty?','You as a team will offer a promotion to your customer for the products below. It is up to you whether you make promotions for both products or none. ','SIMULATION','Promotion - Pro 600 ml beauty','Notification for Promotion - Pro 600 ml beauty','To continue read notification for Promotion - Pro 600 ml beauty','',TRUE,7,'SINGLE_CHOICE',UUID(),3,3),
(8,2,NULL,0,0.00198128908633738,0.000646099285919504,0,0.013004290323375,'Confirmation for Promotion - Neoru 350 Dailycare','Are you sure to submit for Promotion - Neoru 350 Dailycare?','You as a team will offer a promotion to your customer for the products below. It is up to you whether you make promotions for both products or none. ','SIMULATION','Promotion - Neoru 350 Dailycare','Notification for Promotion - Neoru 350 Dailycare','To continue read notification for Promotion - Neoru 350 Dailycare','',TRUE,8,'SINGLE_CHOICE',UUID(),3,3),
(9,2,NULL,0,0.00527883292133352,0.011540264154219,0,0.0142951287092616,'Confirmation for Promotion - Astro 600+200 güzellik+bakım','Are you sure to submit for Promotion - Astro 600+200 güzellik+bakım?','You as a team will offer a promotion to your customer for the products below. It is up to you whether you make promotions for both products or none. ','SIMULATION','Promotion - Astro 600+200 güzellik+bakım','Notification for Promotion - Astro 600+200 güzellik+bakım','To continue read notification for Promotion - Astro 600+200 güzellik+bakım','',TRUE,9,'SINGLE_CHOICE',UUID(),3,3),
(10,2,NULL,0,0.0100431684125287,0.0207608821946426,0,0.00402374025813545,'Confirmation for Promotion - ASTRO 600 ML GÜZELLİK','Are you sure to submit for Promotion - ASTRO 600 ML GÜZELLİK?','You as a team will offer a promotion to your customer for the products below. It is up to you whether you make promotions for both products or none. ','SIMULATION','Promotion - ASTRO 600 ML GÜZELLİK','Notification for Promotion - ASTRO 600 ML GÜZELLİK','To continue read notification for Promotion - ASTRO 600 ML GÜZELLİK','',TRUE,10,'SINGLE_CHOICE',UUID(),3,3),
(11,2,NULL,0,0,0,0,0,'Confirmation for Question5','Are you sure to submit for Question5?','Description about Question5','QUESTION','Question5','Notification for Question5','To continue read notification for Question5','',TRUE,11,'SINGLE_CHOICE',UUID(),NULL,NULL),
(12,2,NULL,0,0,0,0,0,'Confirmation for Team Management','Are you sure to submit for Team Management?','It’s time to focus on your field team. They have some needs like on the job training, motivational activites and the tools which make the job easier for them. Each activities have their own budget and you can choose more than one. If you submit before marking any decision, simulation get the choice ‘no activities’.','SIMULATION','Team Management','Notification for Team Management','To continue read notification for Team Management','',FALSE,12,'MULTIPLE_CHOICE',UUID(),4,4),
(13,2,NULL,0,0,0,0,0,'Confirmation for Question6','Are you sure to submit for Question6?','Description about Question6','QUESTION','Question6','Notification for Question6','To continue read notification for Question6','',TRUE,13,'SINGLE_CHOICE',UUID(),NULL,NULL),
(14,2,NULL,0,0,0,0,0,'Confirmation for Display - PRO 600+200 KEPEK+GÜZELLİK','Are you sure to submit for Display - PRO 600+200 KEPEK+GÜZELLİK?','Now its time to decide that in which areas the shopper see you in Planet Market Stores. If you have an activity in the Planet, you need extra displays for more visibility.  As a company, you want each shopper comes to Planet, must see our activities. Please choose which extra displays you want to position. Consider that each extra display has it’s own cost.','SIMULATION','Display - PRO 600+200 KEPEK+GÜZELLİK','Notification for Display - PRO 600+200 KEPEK+GÜZELLİK','To continue read notification for Display - PRO 600+200 KEPEK+GÜZELLİK','',FALSE,14,'MULTIPLE_CHOICE',UUID(),5,5),
(15,2,NULL,0,0,0.0247871081325893,0,0.0380973164528235,'Confirmation for Display - PRO 600+200 GÜZELLİK + KEPEK','Are you sure to submit for Display - PRO 600+200 GÜZELLİK + KEPEK?','Now its time to decide that in which areas the shopper see you in Planet Market Stores. If you have an activity in the Planet, you need extra displays for more visibility.  As a company, you want each shopper comes to Planet, must see our activities. Please choose which extra displays you want to position. Consider that each extra display has it’s own cost.','SIMULATION','Display - PRO 600+200 GÜZELLİK + KEPEK','Notification for Display - PRO 600+200 GÜZELLİK + KEPEK','To continue read notification for Display - PRO 600+200 GÜZELLİK + KEPEK','',FALSE,15,'MULTIPLE_CHOICE',UUID(),5,5),
(16,2,NULL,0,0,0.0112264962194736,0,0.0231997500578521,'Confirmation for Display - NEURO 500+350 ml bakım+dökülme','Are you sure to submit for Display - NEURO 500+350 ml bakım+dökülme?','Now its time to decide that in which areas the shopper see you in Planet Market Stores. If you have an activity in the Planet, you need extra displays for more visibility.  As a company, you want each shopper comes to Planet, must see our activities. Please choose which extra displays you want to position. Consider that each extra display has it’s own cost.','SIMULATION','Display - NEURO 500+350 ml bakım+dökülme','Notification for Display - NEURO 500+350 ml bakım+dökülme','To continue read notification for Display - NEURO 500+350 ml bakım+dökülme','',FALSE,16,'MULTIPLE_CHOICE',UUID(),5,5),
(17,2,NULL,0,0,0.0786235155182692,0,0.0226166164604543,'Confirmation for Display - neuro 500 ml bakım','Are you sure to submit for Display - neuro 500 ml bakım?','Now its time to decide that in which areas the shopper see you in Planet Market Stores. If you have an activity in the Planet, you need extra displays for more visibility.  As a company, you want each shopper comes to Planet, must see our activities. Please choose which extra displays you want to position. Consider that each extra display has it’s own cost.','SIMULATION','Display - neuro 500 ml bakım','Notification for Display - neuro 500 ml bakım','To continue read notification for Display - neuro 500 ml bakım','',FALSE,17,'MULTIPLE_CHOICE',UUID(),5,5),
(18,2,NULL,0,0,0,0,0,'Confirmation for Question7','Are you sure to submit for Question7?','Description about Question7','QUESTION','Question7','Notification for Question7','To continue read notification for Question7','',TRUE,18,'SINGLE_CHOICE',UUID(),NULL,NULL),
(19,2,NULL,0,0,0,0,0,'Confirmation for Planogram','Are you sure to submit for Planogram?','Now its time to decide your Planogram. If you position your products and sizes in the right place, there will be best sell out datas.','SIMULATION','Planogram','Notification for Planogram','To continue read notification for Planogram','',TRUE,19,'PLANOGRAM2',UUID(),6,6),
(20,2,NULL,0,0,0,0,0,'Confirmation for Question8','Are you sure to submit for Question8?','Description about Question8','QUESTION','Question8','Notification for Question8','To continue read notification for Question8','',TRUE,20,'SINGLE_CHOICE',UUID(),NULL,NULL),
(21,2,NULL,0,0,0,0,0,'Confirmation for Question9','Are you sure to submit for Question9?','Description about Question9','QUESTION','Question9','Notification for Question9','To continue read notification for Question9','',TRUE,21,'SINGLE_CHOICE',UUID(),NULL,NULL),
(22,2,NULL,0,0,0,0,0,'Confirmation for E-Commerce Investment','Are you sure to submit for E-Commerce Investment?','For Planet Market in e-commerce area, we can use following mechanism. However, each requires certain budget.','SIMULATION','E-Commerce Investment','Notification for E-Commerce Investment','To continue read notification for E-Commerce Investment','',FALSE,22,'MULTIPLE_CHOICE',UUID(),7,7),
(23,2,NULL,0,0,0,0,0,'Confirmation for Question3','Are you sure to submit for Question3?','Description about Question3','QUESTION','Question3','Notification for Question3','To continue read notification for Question3','',TRUE,23,'SINGLE_CHOICE',UUID(),NULL,NULL),
(24,2,NULL,0,0,0,0,0,'Confirmation for Listing','Are you sure to submit for Listing?','You have to decide listing of Arthur&Guss and Donny Hagel premium hair shampoo brands, which are already listed at National Accounts, to Planet Market at Local Modern Trade channel. If you submit before marking any decision, simulation get the choice ‘no new listing’.','SIMULATION','Listing','Notification for Listing','To continue read notification for Listing','',FALSE,24,'MULTIPLE_CHOICE',UUID(),8,8),
(25,2,NULL,0,0,0,0,0,'Confirmation for Question10','Are you sure to submit for Question10?','Description about Question10','QUESTION','Question10','Notification for Question10','To continue read notification for Question10','',TRUE,25,'SINGLE_CHOICE',UUID(),NULL,NULL),
(26,2,NULL,0,0,0,0,0,'Confirmation for Delisting','Are you sure to submit for Delisting?','Some products which already listed in Planet Market consume your focus unnecessarily. If you delist some products in Planet Market, you can focus on core products effectively. But consider that there can be USG decrease after delist decision. If you submit before marking any decision, simulation get the choice ‘no delisting’.','SIMULATION','Delisting','Notification for Delisting','To continue read notification for Delisting','',TRUE,26,'SINGLE_CHOICE',UUID(),9,9);

INSERT INTO `ANSWERENTITY` 
VALUES 
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
(20,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer1 of Notification1',5),
(21,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2 of Notification1',5),
(22,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3 of Notification1',5),
(23,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4 of Notification1',5),
(24,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5 of Notification1',5),
(25,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',6),
(26,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',6),
(27,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',6),
(28,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',6),
(29,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',6),
(30,2,NULL,0,0.000554731321170431,0,0,0,0,0,0.0258587266481498,0,0,0,0,0,0,0.00284029463980363,0,0,'Gift Promotion (off pack)',7),
(31,2,NULL,0,0.0178658821301574,0,0,0,0,0,0.0253709046260541,0,0,0,0,0,0,0.0078936953693135,0,0,'Copack Promotion',7),
(32,2,NULL,0,0.0309586661850237,0,0,0,0,0,0.000127649679899143,0,0,0,0,0,0,0.0295014050332469,0,0,'Price Promotion',7),
(33,2,NULL,0,0.00153618719744184,0,0,0,0,0,0.0122122442807882,0,0,0,0,0,0,0.00428504266860318,0,0,'Gift Promotion (off pack)',8),
(34,2,NULL,0,0.00552345686141633,0,0,0,0,0,0.00418106257178617,0,0,0,0,0,0,0.0143895164793012,0,0,'Copack Promotion',8),
(35,2,NULL,0,0.00165396111345096,0,0,0,0,0,0.0121858989935502,0,0,0,0,0,0,0.0191001141059368,0,0,'Price Promotion',8),
(36,2,NULL,0,0.00994357990797213,0,0,0,0,0,0.0196903343856999,0,0,0,0,0,0,0.0228472725132474,0,0,'Gift Promotion (off pack)',9),
(37,2,NULL,0,0.0135561950780041,0,0,0,0,0,0.00362618225832486,0,0,0,0,0,0,0.0183990669354177,0,0,'Copack Promotion',9),
(38,2,NULL,0,0.0210981155020876,0,0,0,0,0,0.000581733868421173,0,0,0,0,0,0,0.00733002118746285,0,0,'Price Promotion',9),
(39,2,NULL,0,0.0118504126971322,0,0,0,0,0,0.0310876689962843,0,0,0,0,0,0,0.0136928509149133,0,0,'Gift Promotion (off pack)',10),
(40,2,NULL,0,0.0172310896251623,0,0,0,0,0,0.0358236180839369,0,0,0,0,0,0,0.00465344558543662,0,0,'Copack Promotion',10),
(41,2,NULL,0,0.00926879508969379,0,0,0,0,0,0.0238684291789337,0,0,0,0,0,0,0.0268489767261808,0,0,'Price Promotion',10),
(42,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',11),
(43,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',11),
(44,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',11),
(45,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',11),
(46,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',11),
(47,2,NULL,-10000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Motivational event',12),
(48,2,NULL,-15000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Training',12),
(49,2,NULL,-24000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Providing tablets integrated to inventory system',12),
(50,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',13),
(51,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',13),
(52,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',13),
(53,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',13),
(54,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',13),
(55,2,NULL,-2000,0,0,0,0,0,0,0.0148847445504671,0,0,0,0,0,0,0.00277029778849301,0,0,'Beginning of rayon',14),
(56,2,NULL,-1000,0,0,0,0,0,0,0.0101800968001363,0,0,0,0,0,0,0.00999625495691618,0,0,'Gondola',14),
(57,2,NULL,-1500,0,0,0,0,0,0,0.0515634634321503,0,0,0,0,0,0,0.0464090302173693,0,0,'Checkout stand',14),
(58,2,NULL,-2000,0,0,0,0,0,0,0.00307335492638855,0,0,0,0,0,0,0.0144551746812589,0,0,'Beginning of rayon',15),
(59,2,NULL,-1000,0,0,0,0,0,0,0.00899727161151646,0,0,0,0,0,0,0.0370963446175407,0,0,'Gondola',15),
(60,2,NULL,-1500,0,0,0,0,0,0,0.0163455702380348,0,0,0,0,0,0,0.0290039029538807,0,0,'Checkout stand',15),
(61,2,NULL,-2000,0,0,0,0,0,0,0.0118415438220902,0,0,0,0,0,0,0.0184626552110937,0,0,'Beginning of rayon',16),
(62,2,NULL,-1000,0,0,0,0,0,0,0.0186445343641527,0,0,0,0,0,0,0.0307414711332139,0,0,'Gondola',16),
(63,2,NULL,-1500,0,0,0,0,0,0,0.0128841947031982,0,0,0,0,0,0,0.0199539860580545,0,0,'Checkout stand',16),
(64,2,NULL,-2000,0,0,0,0,0,0,0.0712931497400595,0,0,0,0,0,0,0.0730787536009738,0,0,'Beginning of rayon',17),
(65,2,NULL,-1000,0,0,0,0,0,0,0.0809394851296969,0,0,0,0,0,0,0.0464295697486717,0,0,'Gondola',17),
(66,2,NULL,-1500,0,0,0,0,0,0,0.0874605862237325,0,0,0,0,0,0,0.0741622889398048,0,0,'Checkout stand',17),
(67,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',18),
(68,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',18),
(69,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',18),
(70,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',18),
(71,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',18),
(72,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml kepek şampuanı',19),
(73,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 600ml+200ml kepek+güzellik copack',19),
(74,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 200 ml kepek',19),
(75,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 350 ml kepek',19),
(76,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml güzellik şampuanı',19),
(77,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 600 ml + 200ml güzellik + kepek copack',19),
(78,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 200 ml güzellik',19),
(79,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'PRO 350 ml güzellik',19),
(80,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 900 ml bakım şampuanı',19),
(81,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 500ml+350ml bakım+dökülme karşıtı',19),
(82,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 350 ml bakım',19),
(83,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 500 ml bakım',19),
(84,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 900 ml dökülme karşıtı şampuanı',19),
(85,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 350 ml dökülme karşıtı',19),
(86,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'NEURO 500 ml dökülme karşıtı',19),
(87,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml güzellik şampuanı',19),
(88,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 600ml+200ml güzellik + bakım copack',19),
(89,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 400 ml güzellik',19),
(90,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml bakım şampuanı',19),
(91,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 600 ml + 200ml bakım + güzellik copack',19),
(92,2,NULL,0,0,0,0,23,1,1,0,0,0,0,0,0,0,0,0,0,'ASTRO 400 ml bakım',19),
(93,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',20),
(94,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',20),
(95,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',20),
(96,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',20),
(97,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',20),
(98,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',21),
(99,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',21),
(100,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',21),
(101,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',21),
(102,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',21),
(103,2,NULL,-36000,0,0,0,0,0,0,0.009174,0,0,0,0,0,0,0.05,0,0,'Hiring someone with technical knowledge to deal e-commerce area',22),
(104,2,NULL,-20000,0,0,0,0,0,0,0.0018348,0,0,0,0,0,0,0.01,0,0,'Creating campaign visuals via agency for customers webpage',22),
(105,2,NULL,-10000,0,0,0,0,0,0,0.009174,0,0,0,0,0,0,0.05,0,0,'Planning activities special to e-commerce, where prices are slightly lower than normal ones',22),
(106,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',23),
(107,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',23),
(108,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',23),
(109,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',23),
(110,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',23),
(111,2,NULL,-10000,0.009,0,0,0,0,0,0.0244648318042813,0,0,0,0,0,0,0.024,0,0,'TG',24),
(112,2,NULL,-20000,0.014,0,0,0,0,0,0.0458715596330275,0,0,0,0,0,0,0.043,0,0,'DH',24),
(113,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,'Answer1',25),
(114,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer2',25),
(115,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer3',25),
(116,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer4',25),
(117,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Answer5',25),
(118,2,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Hicbir Urunu Delist Etme',26),
(119,2,NULL,0,-0.00072,0,0,0,0,0,-0.013,0,0,0,0,0,0,-0.03976,0,0,'pro 200 ml kepek',26),
(120,2,NULL,0,-0.00014,0,0,0,0,0,-0.006,0,0,0,0,0,0,-0.01835,0,0,'pro 200 ml güzellik',26),
(121,2,NULL,0,0,0,0,0,0,0,-0.0025,0,0,0,0,0,0,-0.00765,0,0,'astro 400 ml daycare',26),
(122,2,NULL,0,-0.000727,0,0,0,0,0,-0.057,0,0,0,0,0,0,-0.17431,0,0,'neuro 900 ml dökülme karşıtı',26),
(123,2,NULL,0,0.000336,0,0,0,0,0,-0.0065,0,0,0,0,0,0,-0.01988,0,0,'astro 600 ml bakım',26);

INSERT INTO `RESPONSEENTITY` (`ID`, `SIMULATION_ID`, `BYTES`, `OUTCOME`, `QUESTION_ID`, `USER_ID`)
VALUES
	(1,2,NULL,'{\"id\":5}',1,3),
	(2,2,NULL,'[{\"answer\":6,\"value\":101}, {\"answer\":7,\"value\":91}, {\"answer\":8,\"value\":91}, {\"answer\":9,\"value\":90}]',2,3),
	(3,2,NULL,'{\"id\":13}',3,3),
	(4,2,NULL,'{\"id\":16}',4,3),
	(5,2,NULL,'{\"id\":24}',5,3),
	(6,2,NULL,'{\"id\":28}',6,3),
	(7,2,NULL,'{\"id\":30}',7,3),
	(8,2,NULL,'{\"id\":34}',8,3),
	(9,2,NULL,'{\"id\":38}',9,3),
	(10,2,NULL,'{\"id\":41}',10,3),
	(11,2,NULL,'{\"id\":46}',11,3),
	(12,2,NULL,'{\"answers\":[{\"id\":48}]}',12,3),
	(13,2,NULL,'{\"id\":54}',13,3),
	(14,2,NULL,'{\"answers\":[{\"id\":56}]}',14,3),
	(15,2,NULL,'{\"answers\":[{\"id\":59}]}',15,3),
	(16,2,NULL,'{\"answers\":[{\"id\":62}]}',16,3),
	(17,2,NULL,'{\"answers\":[{\"id\":65}]}',17,3),
	(18,2,NULL,'{\"id\":71}',18,3),
	(19,2,NULL,'[{\"answer\":72,\"value\":1}, {\"answer\":73,\"value\":2}, {\"answer\":74,\"value\":3}, {\"answer\":75,\"value\":4}, {\"answer\":76,\"value\":5}, {\"answer\":77,\"value\":6}, {\"answer\":78,\"value\":7}, {\"answer\":79,\"value\":8}, {\"answer\":80,\"value\":9}, {\"answer\":81,\"value\":10}, {\"answer\":82,\"value\":11}, {\"answer\":83,\"value\":12}, {\"answer\":84,\"value\":13}, {\"answer\":85,\"value\":14}, {\"answer\":86,\"value\":15}, {\"answer\":87,\"value\":16}, {\"answer\":88,\"value\":17}, {\"answer\":89,\"value\":18}, {\"answer\":90,\"value\":19}, {\"answer\":91,\"value\":20}, {\"answer\":92,\"value\":21}]',19,3),
	(20,2,NULL,'{\"id\":97}',20,3),
	(21,2,NULL,'{\"id\":102}',21,3),
	(22,2,NULL,'{\"answers\":[]}',22,3),
	(23,2,NULL,'{\"id\":110}',23,3),
	(24,2,NULL,'{\"answers\":[{\"id\":111},{\"id\":112}]}',24,3),
	(25,2,NULL,'{\"id\":117}',25,3),
	(26,2,NULL,'{\"id\":119}',26,3);
