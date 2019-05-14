-- executed during each launch of h2-db
-- manually executed once to setup persist-db
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');