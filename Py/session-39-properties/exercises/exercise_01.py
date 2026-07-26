# ============================================================
# تمرین جلسه ۳۹
# هدف: property برای قیمت غیرمنفی بسازید.
# ============================================================

class Product:
    def __init__(self, price: int):
        self.price = price

    @property
    def price(self) -> int:
        return self._price

    @price.setter
    def price(self, value: int) -> None:
        if value < 0:
            raise ValueError("قیمت منفی مجاز نیست")
        self._price = value

item = Product(1000)
print(item.price)
