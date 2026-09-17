# ============================================================
# جلسه ۴۵ — Observer
# فایل: 04_observer.py
# ============================================================

from typing import Protocol


class Observer(Protocol):
    def update(self, message: str) -> None: ...


class EmailNotifier:
    def update(self, message: str) -> None:
        print(f"[Email] {message}")


class SmsNotifier:
    def update(self, message: str) -> None:
        print(f"[SMS] {message}")


class OrderSubject:
    def __init__(self):
        self._observers: list[Observer] = []

    def attach(self, observer: Observer) -> None:
        self._observers.append(observer)

    def place_order(self, order_id: str) -> None:
        message = f"سفارش {order_id} ثبت شد"
        for obs in self._observers:
            obs.update(message)


subject = OrderSubject()
subject.attach(EmailNotifier())
subject.attach(SmsNotifier())
subject.place_order("ORD-42")
