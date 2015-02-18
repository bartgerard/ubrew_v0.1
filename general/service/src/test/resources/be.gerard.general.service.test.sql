-- Test App

insert into general_application (app_key, app_pass) values ('ubrew-test', 'pQVBx1biguqijPX1ESKcelo+MQOVL8TZ85Phe18hovYrOZ/eXyGuJBCbldJu/T7s');
insert into general_application_detail (id, app_key, app_name, create_ts, update_ts, create_user, update_user) values (0, 'ubrew-test', 'ubrew-test', '2015-01-01 12:00:00', '2015-01-01 12:00:00', 'bart.gerard@ubrew.be', 'bart.gerard@ubrew.be');

INSERT INTO general_user (username, password, enabled) VALUES ('bart.gerard@ubrew.be', 'pQVBx1biguqijPX1ESKcelo+MQOVL8TZ85Phe18hovYrOZ/eXyGuJBCbldJu/T7s', TRUE);
INSERT INTO general_user_detail (id, username, firstname, lastname, birthdate, create_ts, update_ts, create_user, update_user) VALUES (0, 'bart.gerard@ubrew.be', 'Bart', 'Gerard', '1988-05-03 12:00:00', '2015-01-01 12:00:00', '2015-01-01 12:00:00', 'bart.gerard@ubrew.be', 'bart.gerard@ubrew.be');

