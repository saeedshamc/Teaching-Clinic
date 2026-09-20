DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  created_at TEXT NOT NULL
);

INSERT INTO users (name, created_at) VALUES
  (' علی ', date('now')),
  ('سارا', date('now', '-2 day')),
  ('رضا', '2024-01-15');

SELECT id,
       trim(name) AS name_clean,
       upper(trim(name)) AS name_upper,
       length(trim(name)) AS name_len,
       'کاربر: ' || trim(name) AS label,
       created_at,
       date('now') AS today
FROM users;

SELECT * FROM users WHERE length(trim(name)) > 3;
