SELECT @@auto_increment_increment;

SET @@auto_increment_increment=1;

INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');

select * from roles;
