# ============================================================
# جلسه ۴۸ — تولید README template
# فایل: 04_readme_generator.py
# ============================================================

PROJECT = {
    "name": "my-python-app",
    "description": "یک CLI ساده برای مدیریت کارها",
    "install": "pip install -e .",
    "usage": "myapp --help",
    "python": ">=3.10",
}


def generate_readme(meta: dict) -> str:
    return f"""# {meta['name']}

{meta['description']}

## پیش‌نیاز

- Python {meta['python']}

## نصب

```bash
{meta['install']}
```

## استفاده

```bash
{meta['usage']}
```

## توسعه

```bash
pip install -e \".[dev]\"
pytest -v
```

## مجوز

MIT
"""


readme = generate_readme(PROJECT)
print(readme)
