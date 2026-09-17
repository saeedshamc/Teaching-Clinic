# جلسه ۳۱: آرگومان‌های خط فرمان (argparse) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ساخت CLI با `ArgumentParser`
- positional و optional arguments
- `--help` خودکار
- type، choices، default، nargs
- subparsers برای دستورات چندگانه
- الگوهای real-world: ابزار فایل، ماشین‌حساب CLI

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | sys.argv vs argparse |
| 10–25 | positional args |
| 25–40 | optional (--flag) |
| 40–55 | type, choices, action |
| 55–70 | subparsers |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. sys.argv

```python
import sys
print(sys.argv)  # ['script.py', 'arg1', '--verbose']
```

دستی و خطاپذیر — argparse استاندارد است.

---

## ۲. ArgumentParser پایه

```python
import argparse

parser = argparse.ArgumentParser(description="ماشین‌حساب ساده")
parser.add_argument("x", type=int, help="عدد اول")
parser.add_argument("y", type=int, help="عدد دوم")
args = parser.parse_args(["3", "4"])
print(args.x + args.y)
```

---

## ۳. optional arguments

```python
parser.add_argument("--name", "-n", default="کاربر", help="نام")
parser.add_argument("--verbose", "-v", action="store_true")
parser.add_argument("--count", "-c", type=int, default=1)
```

---

## ۴. action های رایج

| action | کاربرد |
|--------|--------|
| store | مقدار ذخیره (پیش‌فرض) |
| store_true | flag boolean |
| store_false | --no-xxx |
| append | چندبار همان arg |
| count | -vvv → verbose=3 |

---

## ۵. choices و type

```python
parser.add_argument("--format", choices=["json", "csv", "txt"])
parser.add_argument("--port", type=int, default=8000)
parser.add_argument("--ratio", type=float)
```

---

## ۶. nargs

```python
parser.add_argument("files", nargs="+")       # یک یا بیشتر
parser.add_argument("--tags", nargs="*")      # صفر یا بیشتر
parser.add_argument("coords", nargs=2, type=float)
```

---

## ۷. mutually exclusive

```python
group = parser.add_mutually_exclusive_group()
group.add_argument("--quiet", action="store_true")
group.add_argument("--verbose", action="store_true")
```

---

## ۸. subparsers

```python
parser = argparse.ArgumentParser()
subs = parser.add_subparsers(dest="command")

add_p = subs.add_parser("add", help="افزودن")
add_p.add_argument("item")

list_p = subs.add_parser("list", help="لیست")
```

---

## ۹. epilog و formatter

```python
parser = argparse.ArgumentParser(
    description="ابزار مدیریت فایل",
    epilog="مثال: python tool.py copy a.txt b.txt",
    formatter_class=argparse.RawDescriptionHelpFormatter,
)
```

---

## ۱۰. parse_known_args

```python
args, rest = parser.parse_known_args()
# rest = آرگومان‌های ناشناخته
```

---

## ۱۱. الگوی main

```python
def build_parser() -> argparse.ArgumentParser:
    ...

def main(argv: list[str] | None = None) -> int:
    args = build_parser().parse_args(argv)
    ...
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_argparse_basics.py](./examples/01_argparse_basics.py) | positional |
| [02_optional_flags.py](./examples/02_optional_flags.py) | optional |
| [03_subparsers.py](./examples/03_subparsers.py) | subcommands |
| [04_file_tool_cli.py](./examples/04_file_tool_cli.py) | ابزار real-world |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش type=int | string concatenation |
| positional بعد از optional | SyntaxError در add_argument order |
| parse_args() در import | side effect — argv واقعی |
| dest نام‌گذاری با `-` | از `--output-file` → output_file |

---

## ۱۴. argparse vs click/typer

| | argparse | click/typer |
|---|----------|-------------|
| dependency | stdlib | third-party |
| decorator | خیر | بله |
| یادگیری | پایه دوره | پروژه |

---

## ۱۵. تمرین کلاسی

1. دو عدد positional — جمع
2. `--name` برای سلام
3. `--verbose` flag
4. subparser add/list

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. تست با argv مصنوعی

```python
args = parser.parse_args(["--name", "سعید", "10", "20"])
```

بدون نیاز به خط فرمان واقعی.

---

## ۱۷. nargs='?'

```python
parser.add_argument("--output", nargs="?", const="stdout", default="report.txt")
# بدون --output → report.txt
# --output → stdout
# --output file.txt → file.txt
```

---

## ۱۸. type= argparse.FileType

```python
parser.add_argument("infile", type=argparse.FileType("r", encoding="utf-8"))
```

---

## ۱۹. exit codes

```python
def main() -> int:
    ...
    return 0  # success
    return 1  # error

raise SystemExit(main())
```

---

## ۲۰. سوالات کلاسی

- positional vs optional؟
- `action="store_true"` چه می‌کند؟
- چرا subparsers برای git-like CLI؟

---

## ۲۱. metavar و dest

```python
parser.add_argument("-v", "--verbose", action="count", dest="verbosity")
# -v → 1, -vv → 2
```

---

## ۲۲. fromfile برای تست

```python
with open("args.txt") as f:
    args = parser.parse_args(f.read().split())
```

---

## ۲۳. جمع‌بندی

argparse پایه **هر CLI پایتون** است — `--help` خودکار و validation type از اول طراحی کنید.

**تکلیف خانه:** [question.md](./exercises/question.md) — subparser و file tool.

### چک‌لیست پایان جلسه

- [ ] positional و optional
- [ ] `--help` خوانده شد
- [ ] subparser ساخته شد
- [ ] parse_args با argv تست شد

**زمان باقی‌مانده:** Q&A — ساخت CLI برای پروژه شخصی.

**جلسه بعد:** SQLite برای ذخیره پایدار.

---

## 📚 مطالعه تکمیلی

- [argparse — Python docs](https://docs.python.org/3/library/argparse.html)

---

**جلسه قبل:** [۳۰ — Collections](../session-30-collections/) | **بعد:** [۳۲ — SQLite](../session-32-sqlite/)
