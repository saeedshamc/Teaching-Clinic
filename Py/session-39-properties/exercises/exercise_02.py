# ============================================================
# تمرین ۲ جلسه ۳۹ — Temperature و ShoppingCart
# ============================================================


class Temperature:
    def __init__(self, celsius: float):
        self.celsius = celsius

    @property
    def celsius(self) -> float:
        return self._celsius

    @celsius.setter
    def celsius(self, value: float) -> None:
        if value < -273.15:
            raise ValueError("دمای زیر صفر مطلق ممکن نیست")
        self._celsius = value

    @property
    def fahrenheit(self) -> float:
        return self._celsius * 9 / 5 + 32

    @property
    def kelvin(self) -> float:
        return self._celsius + 273.15


class ShoppingCart:
    def __init__(self):
        self._items: list[tuple[str, int]] = []

    def add_item(self, name: str, price: int) -> None:
        if price < 0:
            raise ValueError("قیمت منفی مجاز نیست")
        self._items.append((name, price))

    @property
    def total(self) -> int:
        return sum(price for _, price in self._items)


if __name__ == "__main__":
    t = Temperature(25)
    print(f"{t.celsius}°C = {t.fahrenheit:.1f}°F = {t.kelvin:.2f}K")

    cart = ShoppingCart()
    cart.add_item("کتاب", 120_000)
    cart.add_item("خودکار", 15_000)
    print("جمع سبد:", cart.total)
