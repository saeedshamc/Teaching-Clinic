# ============================================================
# تمرین جلسه ۹
# هدف: کلاسی برای Book بسازید.
# ============================================================

class Book:
    def __init__(self, title, author):
        self.title = title
        self.author = author

    def info(self):
        print(self.title, "نوشته شده توسط", self.author)


book = Book("پایتون", "سعید")
book.info()
