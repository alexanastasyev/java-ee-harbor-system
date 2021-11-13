INSERT INTO harbour_roles (title) VALUES ('admin');
INSERT INTO harbour_roles (title) VALUES ('captain');
INSERT INTO harbour_roles (title) VALUES ('dispatcher');
INSERT INTO harbour_roles (title) VALUES ('moderator');

INSERT INTO statuses (title) VALUES ('active');
INSERT INTO statuses (title) VALUES ('blocked');
INSERT INTO statuses (title) VALUES ('deleted');

INSERT INTO harbour_users (login, password, role_id, status_id) VALUES ('admin', 'admin', 1, 1);