-- فرض: library.db قبلاً با session-18 seed شده است.
-- یا همین‌جا از نو بسازید با اجرای schema-seed قبل از این فایل.

.headers on
.mode column

SELECT '--- امانت‌های باز ---' AS report;
SELECT m.name AS member, b.title AS book, l.loaned_at
FROM loans l
JOIN members m ON m.id = l.member_id
JOIN books b ON b.id = l.book_id
WHERE l.returned_at IS NULL
ORDER BY l.loaned_at;

SELECT '--- تعداد امانت هر عضو ---' AS report;
SELECT m.name, COUNT(*) AS loan_count
FROM loans l
JOIN members m ON m.id = l.member_id
GROUP BY m.id, m.name
ORDER BY loan_count DESC;

SELECT '--- کتاب‌های بدون امانت ---' AS report;
SELECT b.title
FROM books b
LEFT JOIN loans l ON l.book_id = b.id
WHERE l.id IS NULL;
