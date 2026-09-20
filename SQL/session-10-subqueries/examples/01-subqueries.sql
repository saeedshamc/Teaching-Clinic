DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  total REAL NOT NULL
);

INSERT INTO users (id, name) VALUES (1, 'علی'), (2, 'سارا'), (3, 'رضا');
INSERT INTO orders (user_id, total) VALUES
  (1, 150000),
  (1, 20000),
  (2, 99000);

SELECT name
FROM users
WHERE id IN (SELECT user_id FROM orders WHERE total > 100000);

SELECT id, user_id, total
FROM orders
WHERE total > (SELECT AVG(total) FROM orders);

SELECT name
FROM users
WHERE id NOT IN (SELECT user_id FROM orders);
