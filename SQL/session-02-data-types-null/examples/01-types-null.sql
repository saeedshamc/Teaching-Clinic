DROP TABLE IF EXISTS demo_types;
CREATE TABLE demo_types (
  id INTEGER,
  title TEXT,
  price REAL,
  note TEXT
);

INSERT INTO demo_types (id, title, price, note) VALUES
  (1, 'کتاب', 120000, NULL),
  (2, 'قلم', 15000, ''),
  (3, 'دفتر', NULL, 'بدون قیمت');

SELECT id, title, price, note,
       CASE WHEN note IS NULL THEN 'null' ELSE 'not-null' END AS note_state
FROM demo_types;

SELECT COUNT(*) AS all_rows, COUNT(price) AS priced_rows, COUNT(note) AS noted_rows
FROM demo_types;
