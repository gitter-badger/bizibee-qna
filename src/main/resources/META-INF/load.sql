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
(1,2,'E-Commerce tarafıyla ilgilenmesi için teknik donanıma sahip bir çalışanı işe almak',5),
(2,2,'Müşterinin websitesi için kendi ürünlerimizden oluşan ürün/kampanya görsellerini ajans aracılıgıyla oluşturmak',5),
(3,2,'E-commerce özelinde aktiviteler planlamak-öneri aksiyon fiyatlarının bir tık altına',5),
(4,2,'Reyon başı',6),
(5,2,'Gondol',6),
(6,2,'Kasaönü stand',6),
(7,2,'Gift Promosyonu (off pack)',8),
(8,2,'Copack Promosyonu',8),
(9,2,'Fiyat Promosyonu',8),
(10,2,'Motivasyon etkinliği',9),
(11,2,'Eğitim planlama',9),
(12,2,'Envanter sistemiyle aloke tabletlerin tedarik edilmesi',9);

