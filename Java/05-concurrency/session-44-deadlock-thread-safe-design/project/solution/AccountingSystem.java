// AccountingSystem.java — پاسخ پروژهٔ جلسه ۴۴
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class Transaction {
    double amount;
    String type;
    long timestamp;
    Transaction(double amount, String type) {
        this.amount = amount; this.type = type; this.timestamp = System.currentTimeMillis();
    }
}

class Account {
    String name;
    double balance;
    List<Transaction> transactions;
    ReentrantLock lock = new ReentrantLock();

    Account(String name, double balance) {
        this.name = name; this.balance = balance; this.transactions = new ArrayList<>();
    }

    void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            transactions.add(new Transaction(amount, "CREDIT"));
        } finally { lock.unlock(); }
    }

    void withdraw(double amount) {
        lock.lock();
        try {
            if (amount <= balance) {
                balance -= amount;
                transactions.add(new Transaction(amount, "DEBIT"));
            }
        } finally { lock.unlock(); }
    }

    boolean transfer(Account target, double amount) {
        // lock ordering — بر اساس name برای جلوگیری از deadlock
        Account first = this.name.compareTo(target.name) < 0 ? this : target;
        Account second = first == this ? target : this;
        first.lock.lock();
        try {
            second.lock.lock();
            try {
                if (this.balance < amount) return false;
                this.balance -= amount;
                target.balance += amount;
                this.transactions.add(new Transaction(amount, "TRANSFER_OUT"));
                target.transactions.add(new Transaction(amount, "TRANSFER_IN"));
                return true;
            } finally { second.lock.unlock(); }
        } finally { first.lock.unlock(); }
    }

    void display() {
        System.out.println(name + ": balance=" + balance + ", transactions=" + transactions.size());
    }
}

public class AccountingSystem {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("A", 1000);
        Account b = new Account("B", 500);

        // چند thread همزمان transfer
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    a.transfer(b, 10);
                }
            }, "Worker-" + i);
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        a.display();
        b.display();
    }
}
