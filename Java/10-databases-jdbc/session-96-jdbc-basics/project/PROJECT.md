# پروژهٔ جلسه ۹۶ — سیستم مدیریت کاربران با JDBC

یک سیستم مدیریت کاربران بسازید که:
- `User` model با id, name, email, age
- `UserDao` با `createTable`, `insert`, `selectAll`, `selectById`, `update`, `delete`
- `JdbcUserManager` با `main` برای demo
- `schema.sql` برای table creation
- `try-with-resources` برای همهٔ operations
- `PreparedStatement` برای parameterized queries

نمونه:
```
=== User Manager ===
1. Add User
2. List Users
3. Find by ID
4. Update User
5. Delete User
0. Exit
```

## صورت پروژه
پاسخ در `solution/` — User.java + UserDao.java + JdbcUserManager.java.
