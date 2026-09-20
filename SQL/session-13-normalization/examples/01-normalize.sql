-- ضدالگو: لیست ویرگولی
DROP TABLE IF EXISTS bad_orders;
CREATE TABLE bad_orders (
  id INTEGER PRIMARY KEY,
  customer TEXT,
  products TEXT -- 'کیبورد,ماوس'
);
INSERT INTO bad_orders (customer, products) VALUES ('علی', 'کیبورد,ماوس');

-- الگوی بهتر
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE products (
  id INTEGER PRIMARY KEY,
  title TEXT NOT NULL,
  price REAL NOT NULL
);

CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  customer_id INTEGER NOT NULL
);

CREATE TABLE order_items (
  order_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  qty INTEGER NOT NULL,
  PRIMARY KEY (order_id, product_id)
);

INSERT INTO customers (name) VALUES ('علی');
INSERT INTO products (title, price) VALUES ('کیبورد', 850000), ('ماوس', 350000);
INSERT INTO orders (customer_id) VALUES (1);
INSERT INTO order_items (order_id, product_id, qty) VALUES (1, 1, 1), (1, 2, 2);

SELECT c.name, p.title, oi.qty
FROM order_items oi
JOIN orders o ON o.id = oi.order_id
JOIN customers c ON c.id = o.customer_id
JOIN products p ON p.id = oi.product_id;
