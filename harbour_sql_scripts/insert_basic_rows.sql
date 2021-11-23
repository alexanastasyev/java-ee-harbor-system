INSERT INTO harbour_roles (title) VALUES ('admin');
INSERT INTO harbour_roles (title) VALUES ('captain');
INSERT INTO harbour_roles (title) VALUES ('dispatcher');
INSERT INTO harbour_roles (title) VALUES ('moderator');

INSERT INTO statuses (title) VALUES ('active');
INSERT INTO statuses (title) VALUES ('blocked');
INSERT INTO statuses (title) VALUES ('deleted');

INSERT INTO request_statuses (title) VALUES ('requested_arrival');
INSERT INTO request_statuses (title) VALUES ('approved_arrival');
INSERT INTO request_statuses (title) VALUES ('locked');
INSERT INTO request_statuses (title) VALUES ('requested_department');
INSERT INTO request_statuses (title) VALUES ('approved_department');

INSERT INTO harbour_users (login, password, role_id, status_id) VALUES ('admin', 'admin', 1, 1);