# ============================================================
# تمرین ۲ جلسه ۴۲ — Service با mock repository
# ============================================================

from unittest.mock import Mock
from dataclasses import dataclass


@dataclass
class Product:
    id: int
    name: str
    price: int


class ProductRepo:
    def get(self, product_id: int) -> Product | None:
        raise NotImplementedError


class PriceService:
    def __init__(self, repo: ProductRepo):
        self.repo = repo

    def format_price(self, product_id: int) -> str:
        product = self.repo.get(product_id)
        if product is None:
            return "محصول یافت نشد"
        return f"{product.name}: {product.price:,} تومان"


def run_tests():
    repo = Mock(spec=ProductRepo)
    repo.get.return_value = Product(1, "ماوس", 350_000)
    svc = PriceService(repo)
    assert "ماوس" in svc.format_price(1)

    repo.get.return_value = None
    assert svc.format_price(99) == "محصول یافت نشد"
    print("✅ PriceService tests OK")


if __name__ == "__main__":
    run_tests()
