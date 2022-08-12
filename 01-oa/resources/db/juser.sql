DROP TABLE IF EXISTS juser;

CREATE TABLE juser(
	id INT PRIMARY KEY auto_increment,
	username VARCHAR(255),
	password VARCHAR(255)
);

INSERT INTO juser(username, password) VALUES('vv', '123');
INSERT INTO juser(username, password) VALUES('ee', '456');
commit;

SELECT * FROM juser;