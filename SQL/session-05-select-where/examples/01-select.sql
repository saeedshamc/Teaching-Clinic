DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  name TEXT NOT NULL,
  city TEXT
);

INSERT INTO users (email, name, city) VALUES
  ('ali@ex.com', 'علی', 'تهران'),
  ('sara@ex.com', 'سارا', 'اصفهان'),
  ('reza@ex.com', 'رضا', 'تهران'),
  ('mina@ex.com', 'مینا', NULL);

SELECT id, name FROM users WHERE city = 'تهران';
SELECT * FROM users WHERE name LIKE 'س%';
SELECT * FROM users WHERE id IN (1, 3);
SELECT * FROM users ORDER BY name LIMIT 2;
