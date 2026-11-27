# پاسخ تمرین‌های جلسه ۲۴

## آسان

**۱.**
```java
void removeBook(Book book) { books.remove(book); }
```

**۲.**
```java
Member findMemberById(int id) {
    for (Member m : members) if (m.id == id) return m;
    return null;
}
```

## متوسط

**۳.**
```java
void borrowBook(Member member, Book book) {
    if (member.borrowedBooks.size() >= 3) {
        System.out.println("Limit reached!");
        return;
    }
    member.borrowBook(book);
}
```

**۴.**
```java
// با java.time.LocalDate
LocalDate borrowDate = LocalDate.now().minusDays(20);
// در Book: LocalDate borrowedDate;
// overdue اگر: borrowedBy != null && borrowedDate.plusDays(14).isBefore(LocalDate.now())
```

## سخت

**۵.**
```java
enum TransactionType { BORROW, RETURN }
class Transaction {
    TransactionType type;
    Book book;
    Member member;
    LocalDateTime date;
}
// در Library: List<Transaction> log = new ArrayList<>();
// در borrowBook/returnBook: log.add(new Transaction(...))
```

**۶.**
```java
interface PaymentSystem {
    void charge(double amount);
}
class LateFee implements PaymentSystem {
    public void charge(double amount) {
        System.out.println("Charging late fee: " + amount);
    }
}
```
