# ============================================================
# تمرین ۲ جلسه ۴۵ — Strategy shipping + Observer
# ============================================================


class StandardShipping:
    def cost(self, weight_kg: float) -> int:
        return int(weight_kg * 10_000)


class ExpressShipping:
    def cost(self, weight_kg: float) -> int:
        return int(weight_kg * 25_000)


class OrderEvents:
    def __init__(self):
        self._listeners: list = []

    def subscribe(self, fn) -> None:
        self._listeners.append(fn)

    def emit(self, event: str) -> None:
        for fn in self._listeners:
            fn(event)


class ShippingCalculator:
    def __init__(self, strategy):
        self.strategy = strategy

    def quote(self, weight: float) -> int:
        return self.strategy.cost(weight)


if __name__ == "__main__":
    calc = ShippingCalculator(ExpressShipping())
    print("هزینه:", calc.quote(2.5))

    events = OrderEvents()
    events.subscribe(lambda e: print("LOG:", e))
    events.emit("order_created")
