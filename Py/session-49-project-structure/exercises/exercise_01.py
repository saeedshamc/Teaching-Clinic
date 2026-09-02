# ============================================================
# تمرین ۱ جلسه ۴۹ — layout و Settings
# ============================================================

import os
from dataclasses import dataclass


BOOKSTORE_LAYOUT = [
    "src/bookstore/models/book.py",
    "src/bookstore/services/catalog.py",
    "src/bookstore/cli/main.py",
    "tests/test_catalog.py",
]


@dataclass
class Settings:
    debug: bool = False

    @classmethod
    def from_env(cls) -> "Settings":
        return cls(debug=os.getenv("DEBUG", "0") == "1")


if __name__ == "__main__":
    print("Bookstore layout:")
    for path in BOOKSTORE_LAYOUT:
        print(" ", path)
    print("Settings:", Settings.from_env())
