DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  name TEXT NOT NULL
);

INSERT INTO users (email, name) VALUES ('ali@ex.com', 'علی');
INSERT INTO users (email, name) VALUES
  ('sara@ex.com', 'سارا'),
  ('reza@ex.com', 'رضا');

SELECT * FROM users;
