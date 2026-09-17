# ============================================================
# تمرین ۲ جلسه ۴۷ — path safe + token
# ============================================================

import secrets
from pathlib import Path


def safe_read(base_dir: Path, filename: str) -> str:
    base = base_dir.resolve()
    target = (base / filename).resolve()
    if not target.is_relative_to(base):
        raise ValueError("path traversal detected")
    if not target.is_file():
        raise FileNotFoundError(filename)
    return target.read_text(encoding="utf-8")


if __name__ == "__main__":
    print("token:", secrets.token_urlsafe(24))
    base = Path(".")
    print("safe path check OK for current dir")
