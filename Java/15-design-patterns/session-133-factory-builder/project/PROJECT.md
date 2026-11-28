# پروژهٔ جلسه ۱۳۳ — سیستم مدیریت اشیاء با Factory & Builder

یک سیستم مدیریت اشیاء با Factory & Builder بسازید:
- `Factory Method` برای `Document` creation
- `Abstract Factory` برای `UIFactory` families
- `Builder` برای `Computer` complex object
- `Prototype` برای `Document` cloning
- `Deep copy` vs `Shallow copy` در `clone()`

نمونه:
```
Document: PdfDocument, WordDocument, TextDocument
UIFactory: WindowsFactory, MacFactory
Computer: Gaming PC, Office PC
Document clone: original + 2 copies with different content
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
