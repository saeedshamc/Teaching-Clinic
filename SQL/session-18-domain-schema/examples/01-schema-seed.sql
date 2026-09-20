PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS members;

CREATE TABLE members (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE
);

CREATE TABLE books (
  id INTEGER PRIMARY KEY,
  title TEXT NOT NULL,
  author TEXT NOT NULL
);

CREATE TABLE loans (
  id INTEGER PRIMARY KEY,
  member_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  loaned_at TEXT NOT NULL,
  returned_at TEXT,
  FOREIGN KEY (member_id) REFERENCES members(id),
  FOREIGN KEY (book_id) REFERENCES books(id)
);

INSERT INTO members (name, email) VALUES
  ('علی', 'ali@ex.com'),
  ('سارا', 'sara@ex.com');

INSERT INTO books (title, author) VALUES
  ('SQL پایه', 'کلینیک'),
  ('الگوریتم', 'کلینیک'),
  ('شبکه', 'کلینیک');

INSERT INTO loans (member_id, book_id, loaned_at, returned_at) VALUES
  (1, 1, date('now', '-10 day'), date('now', '-2 day')),
  (1, 2, date('now', '-1 day'), NULL),
  (2, 3, date('now', '-5 day'), NULL);

SELECT 'seed ok' AS status;
SELECT COUNT(*) AS members FROM members;
SELECT COUNT(*) AS books FROM books;
SELECT COUNT(*) AS loans FROM loans;
