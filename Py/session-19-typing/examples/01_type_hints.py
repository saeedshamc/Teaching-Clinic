# ============================================================
# جلسه ۱۹ — Type Hints
# فایل: 01_type_hints.py
# ============================================================


def greet(name: str) -> str:
    return f"سلام {name}"


def average(numbers: list[float]) -> float:
    return sum(numbers) / len(numbers)


def find_age(people: dict[str, int], name: str) -> int:
    return people[name]


message: str = greet("سعید")
print(message)

print("میانگین:", average([10.0, 20.0, 30.0]))

ages: dict[str, int] = {"سعید": 30, "مینا": 25}
print("سن مینا:", find_age(ages, "مینا"))
