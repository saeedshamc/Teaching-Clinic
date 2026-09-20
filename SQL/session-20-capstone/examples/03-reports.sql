.headers on
.mode column

SELECT 'open loans' AS report;
SELECT m.name, b.title, l.loaned_at
FROM loans l
JOIN members m ON m.id = l.member_id
JOIN books b ON b.id = l.book_id
WHERE l.returned_at IS NULL;

SELECT 'loan counts' AS report;
SELECT m.name, COUNT(l.id) AS loans
FROM members m
LEFT JOIN loans l ON l.member_id = m.id
GROUP BY m.id, m.name
ORDER BY loans DESC;

SELECT 'never loaned books' AS report;
SELECT b.title
FROM books b
LEFT JOIN loans l ON l.book_id = b.id
WHERE l.id IS NULL;

SELECT 'members with no loans' AS report;
SELECT m.name
FROM members m
LEFT JOIN loans l ON l.member_id = m.id
WHERE l.id IS NULL;
