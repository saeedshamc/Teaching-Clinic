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
  ('reza@ex.com', 'رضا', NULL);

SELECT * FROM users WHERE id = 2;
UPDATE users SET city = 'شیراز' WHERE id = 2;
SELECT * FROM users WHERE id = 2;

SELECT COUNT(*) AS null_cities FROM users WHERE city IS NULL;
DELETE FROM users WHERE city IS NULL;
SELECT * FROM users;
