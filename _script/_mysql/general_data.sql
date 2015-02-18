use general;

INSERT INTO general.`general_user` (`username`, `password`, `enabled`) VALUES ('bart.gerard@gmail.com', 'pQVBx1biguqijPX1ESKcelo+MQOVL8TZ85Phe18hovYrOZ/eXyGuJBCbldJu/T7s', TRUE);
INSERT INTO general.`general_user_detail` (`id`, `username`, `firstname`, `lastname`, `birthdate`, `create_ts`, `update_ts`, `create_user`, `update_user`) VALUES (1, 'bart.gerard@gmail.com', 'Bart', 'Gerard', '1988-05-03', '2015-01-01', '2015-01-01', 'bart.gerard@gmail.com', 'bart.gerard@gmail.com');

insert into general_application_detail (id, app_key, app_name, create_ts, update_ts, create_user, update_user) values (1, 'ubrew-test', 'ubrew-test', '2015-01-01', '2015-01-01', 'bart.gerard@ubrew.be', 'bart.gerard@ubrew.be');

INSERT INTO `general`.`general_user` (`id`, `birthdate`, `create_time`, `enabled`, `firstname`, `lastname`, `password`, `update_time`, `username`) VALUES ('1', '1988-05-03', '2014-01-01', '1', 'Bart', 'Gerard', 'cAqWITQkZZ1M9DMohw8OA9M0llFKfOnSO2y4RftXXUsWF20rAV8s9RiXR0H', '2014-01-01', 'bart.gerard@gmail.com');

INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '1','general-web', 'bart', 'Create', 'nl', 'Nieuw');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '2','general-web', 'bart', 'Save', 'nl', 'Bewaar');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '3','general-web', 'bart', 'Edit', 'nl', 'Bewerk');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '4','general-web', 'bart', 'Delete', 'nl', 'Verwijder');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '5','general-web', 'bart', 'Cancel', 'nl', 'Annuleer');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '6','general-web', 'bart', 'View', 'nl', 'Bekijk');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '7','general-web', 'bart', 'Close', 'nl', 'Sluit');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '8','general-web', 'bart', 'Translations', 'nl', 'Vertalingen');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ( '9','general-web', 'bart', 'Translation', 'nl', 'Vertaling');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('10','general-web', 'bart', 'Application', 'nl', 'Applicatie');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('11','general-web', 'bart', 'Language', 'nl', 'Taal');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('12','general-web', 'bart', 'Key', 'nl', 'Sleutel');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('13','general-web', 'bart', 'Value', 'nl', 'Waarde');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('14','general-web', 'bart', 'ChangedBy', 'nl', 'Gewijzigd door');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('15','general-web', 'bart', 'Users', 'nl', 'Gebruikers');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('16','general-web', 'bart', 'User', 'nl', 'Gebruiker');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('17','general-web', 'bart', 'Username', 'nl', 'Gebruiker');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('18','general-web', 'bart', 'Firstname', 'nl', 'Voornaam');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('19','general-web', 'bart', 'Lastname', 'nl', 'Achternaam');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('20','general-web', 'bart', 'Birthdate', 'nl', 'Geboortedatum');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('21','general-web', 'bart', 'Id', 'nl', 'Ref.');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('22','general-web', 'bart', 'Password', 'nl', 'Wachtwoord');


INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('23','ubrew-web', 'bart', 'ApplicationName', 'nl', 'µBrew');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('24','ubrew-web', 'bart', 'ApplicationName', 'en', 'µBrew');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('25','ubrew-web', 'bart', 'Home', 'nl', 'Home');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('26','ubrew-web', 'bart', 'Home', 'en', 'Home');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('27','ubrew-web', 'bart', 'About', 'nl', 'Over');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('28','ubrew-web', 'bart', 'About', 'en', 'About');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('29','ubrew-web', 'bart', 'ClickAndBrew', 'nl', 'Klik & Brouw');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('30','ubrew-web', 'bart', 'ClickAndBrew', 'en', 'Click & Brew');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('31','ubrew-web', 'bart', 'Email', 'nl', 'Email');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('32','ubrew-web', 'bart', 'Email', 'en', 'Email');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('33','ubrew-web', 'bart', 'Password', 'nl', 'Wachtwoord');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('34','ubrew-web', 'bart', 'Password', 'en', 'Password');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('35','ubrew-web', 'bart', 'SignIn', 'nl', 'Aanmelden');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('36','ubrew-web', 'bart', 'SignIn', 'en', 'Sign in');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('37','ubrew-web', 'bart', 'Profile', 'nl', 'Profiel');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('38','ubrew-web', 'bart', 'Profile', 'en', 'Profile');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('39','ubrew-web', 'bart', 'Logout', 'nl', 'Afmelden');
INSERT INTO `general`.`general_translation` (`id`,`application`, `changedBy`, `translationkey`, `translationlanguage`, `translationvalue`) VALUES ('40','ubrew-web', 'bart', 'Logout', 'en', 'Logout');