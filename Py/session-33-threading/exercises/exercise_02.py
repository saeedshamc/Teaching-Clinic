# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۳ (پیشرفته)
# ============================================================

import threading
from queue import Queue


class BankAccount:
    def __init__(self, balance: float = 0):
        self._balance = balance
        self._lock = threading.Lock()

    def deposit(self, amount: float) -> None:
        with self._lock:
            self._balance += amount

    def withdraw(self, amount: float) -> bool:
        with self._lock:
            if self._balance >= amount:
                self._balance -= amount
                return True
            return False

    @property
    def balance(self) -> float:
        with self._lock:
            return self._balance


def stress_test(account: BankAccount, n: int) -> None:
    for _ in range(n):
        account.deposit(1)
        account.withdraw(1)


acc = BankAccount(1000)
threads = [threading.Thread(target=stress_test, args=(acc, 1000)) for _ in range(5)]
for t in threads:
    t.start()
for t in threads:
    t.join()

print(f"موجودی نهایی (انتظار 1000): {acc.balance}")

# Queue demo
q: Queue = Queue()
for i in range(10):
    q.put(f"item-{i}")
while not q.empty():
    print(q.get())
