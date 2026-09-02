# ============================================================
# جلسه ۴۰ — version و entry point
# فایل: 04_version_and_entry.py
# ============================================================

__version__ = "0.1.0"


def main() -> None:
    """Entry point — معادل [project.scripts] clinic = pkg.cli:main"""
    print(f"Teaching Clinic Tools v{__version__}")
    print("دستورات: greet | info")


def cli_greet(name: str) -> None:
    print(f"سلام {name}!")


if __name__ == "__main__":
    main()
    cli_greet("دانشجو")
