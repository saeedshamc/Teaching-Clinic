DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  total REAL NOT NULL
);

INSERT INTO orders (user_id, total) VALUES
  (1, 150000),
  (1, 20000),
  (2, 99000),
  (2, 1000),
  (2, 5000),
  (3, 100000);

SELECT user_id,
       COUNT(*) AS order_count,
       SUM(total) AS revenue,
       AVG(total) AS avg_total
FROM orders
GROUP BY user_id
ORDER BY revenue DESC;

SELECT user_id, COUNT(*) AS order_count
FROM orders
GROUP BY user_id
HAVING COUNT(*) > 1;
