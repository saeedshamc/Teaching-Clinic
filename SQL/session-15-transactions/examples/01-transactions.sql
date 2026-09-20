DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  balance REAL NOT NULL
);

INSERT INTO accounts (id, name, balance) VALUES
  (1, 'علی', 500),
  (2, 'سارا', 100);

SELECT * FROM accounts;

BEGIN;
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;
ROLLBACK;

SELECT 'after rollback' AS phase, * FROM accounts;

BEGIN;
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;

SELECT 'after commit' AS phase, * FROM accounts;
