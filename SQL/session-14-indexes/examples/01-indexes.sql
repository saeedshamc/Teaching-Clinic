DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  total REAL NOT NULL
);

INSERT INTO orders (user_id, total) VALUES
  (1, 10), (1, 20), (2, 30), (2, 40), (3, 50);

EXPLAIN QUERY PLAN
SELECT * FROM orders WHERE user_id = 2;

CREATE INDEX IF NOT EXISTS idx_orders_user_id ON orders(user_id);

EXPLAIN QUERY PLAN
SELECT * FROM orders WHERE user_id = 2;

SELECT * FROM orders WHERE user_id = 2;
