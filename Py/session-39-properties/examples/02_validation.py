# ============================================================
# جلسه ۳۹ — اعتبارسنجی با property
# فایل: 02_validation.py
# ============================================================


class Product:
    def __init__(self, name: str, price: int, stock: int):
        self.name = name
        self.price = price
        self.stock = stock

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        cleaned = value.strip()
        if len(cleaned) < 2:
            raise ValueError("نام محصول خیلی کوتاه است")
        self._name = cleaned

    @property
    def price(self) -> int:
        return self._price

    @price.setter
    def price(self, value: int) -> None:
        if value < 0:
            raise ValueError("قیمت منفی مجاز نیست")
        self._price = value

    @property
    def stock(self) -> int:
        return self._stock

    @stock.setter
    def stock(self, value: int) -> None:
        if value < 0:
            raise ValueError("موجودی منفی مجاز نیست")
        self._stock = value

    def sell(self, quantity: int) -> None:
        if quantity > self.stock:
            raise ValueError("موجودی کافی نیست")
        self.stock -= quantity


item = Product("  کیبورد  ", 850_000, 10)
print(f"{item.name}: {item.price:,} تومان، موجودی {item.stock}")
item.sell(3)
print("بعد از فروش:", item.stock)
