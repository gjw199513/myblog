INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', 'gjw605134015', '郭儆炜', 'gjw199513@163.com');
INSERT INTO user (id, username, password, name, email)  VALUES (2, 'gjw199513', 'gjw605134015', '夜神月', '605134015@163.com');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);