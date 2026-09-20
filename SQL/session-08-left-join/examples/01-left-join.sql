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
INSERT INTO orders (user_id, total) VALUES (1, 150000), (2, 99000);

SELECT u.name, o.id AS order_id, o.total
FROM users AS u
LEFT JOIN orders AS o ON o.user_id = u.id
ORDER BY u.name;

SELECT u.name AS no_orders
FROM users AS u
LEFT JOIN orders AS o ON o.user_id = u.id
WHERE o.id IS NULL;
