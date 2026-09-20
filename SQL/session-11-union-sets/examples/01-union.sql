DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS guests;

CREATE TABLE users (id INTEGER PRIMARY KEY, email TEXT NOT NULL);
CREATE TABLE guests (id INTEGER PRIMARY KEY, email TEXT NOT NULL);

INSERT INTO users (email) VALUES ('a@ex.com'), ('b@ex.com'), ('c@ex.com');
INSERT INTO guests (email) VALUES ('b@ex.com'), ('d@ex.com');

SELECT email FROM users
UNION
SELECT email FROM guests;

SELECT email FROM users
UNION ALL
SELECT email FROM guests;

SELECT email FROM users
INTERSECT
SELECT email FROM guests;

SELECT email FROM users
EXCEPT
SELECT email FROM guests;
