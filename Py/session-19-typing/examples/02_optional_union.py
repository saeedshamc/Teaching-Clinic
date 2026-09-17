# ============================================================
# جلسه ۱۹ — Optional و Union
# فایل: 02_optional_union.py
# ============================================================


def find_index(items: list[str], target: str) -> int | None:
    """index عنصر یا None اگر پیدا نشد"""
    for i, item in enumerate(items):
        if item == target:
            return i
    return None


def format_value(value: int | float | str) -> str:
    """چند نوع ورودی — Union با |"""
    return f"مقدار: {value}"


def get_config(key: str, default: str | None = None) -> str:
    config = {"host": "localhost", "port": "8080"}
    return config.get(key, default or "نامشخص")


names = ["علی", "مریم", "رضا"]
print("index مریم:", find_index(names, "مریم"))
print("index سارا:", find_index(names, "سارا"))

print(format_value(42))
print(format_value(3.14))
print(format_value("سلام"))

print("host:", get_config("host"))
print("debug:", get_config("debug"))
