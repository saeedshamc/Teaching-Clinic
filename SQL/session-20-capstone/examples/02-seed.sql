PRAGMA foreign_keys = ON;

INSERT INTO members (name, email) VALUES
  ('علی', 'ali@ex.com'),
  ('سارا', 'sara@ex.com'),
  ('رضا', 'reza@ex.com');

INSERT INTO books (title, author) VALUES
  ('SQL پایه', 'کلینیک'),
  ('الگوریتم', 'کلینیک'),
  ('شبکه', 'کلینیک'),
  ('سیستم‌عامل', 'کلینیک');

INSERT INTO loans (member_id, book_id, loaned_at, returned_at) VALUES
  (1, 1, date('now', '-10 day'), date('now', '-2 day')),
  (1, 2, date('now', '-1 day'), NULL),
  (2, 3, date('now', '-5 day'), NULL);
