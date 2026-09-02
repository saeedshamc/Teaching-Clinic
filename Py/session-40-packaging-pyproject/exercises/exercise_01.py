# ============================================================
# تمرین ۱ جلسه ۴۰ — متادیتا و layout
# ============================================================

PROJECT = {
    "name": "bookstore-api",
    "version": "0.2.0",
    "requires_python": ">=3.11",
    "dependencies": ["fastapi>=0.100", "uvicorn>=0.23"],
    "optional_dependencies": {
        "dev": ["pytest>=7", "ruff>=0.1"],
    },
}

LAYOUT = {
    "pyproject.toml": "متادیتا",
    "src/bookstore/__init__.py": "بسته",
    "src/bookstore/models.py": "مدل‌ها",
    "src/bookstore/api.py": "روتر API",
    "tests/test_api.py": "تست",
}


def print_metadata(project: dict) -> None:
    print(f"{project['name']} v{project['version']}")
    print(f"Python {project['requires_python']}")
    for dep in project["dependencies"]:
        print(f"  dep: {dep}")


if __name__ == "__main__":
    print_metadata(PROJECT)
    print("\n--- layout ---")
    for path, role in LAYOUT.items():
        print(f"{path:30} → {role}")
