INSERT INTO `COMPANYENTITY` VALUES 
(1,NOW() + INTERVAL 30 DAY,'Company1'),
(2,NOW() + INTERVAL 30 DAY,'Company2');

INSERT INTO `USERENTITY` (`ID`,`EMAIL`,`FIRSTNAME`,`LASTNAME`,`LOCALE`,`PASSWORD`,`USERGROUP`,`USERSTATUS`,`COMPANY_ID`) VALUES 
(1,'cem@aripd.com','cem','aripd','tr_TR','cem','Administrators','Confirmed',1),
(2,'dev@aripd.com','dev','aripd','en_US','dev','Members','Confirmed',1),
(3,'test@aripd.com','test','aripd','en_US','test','Members','Confirmed',2);
