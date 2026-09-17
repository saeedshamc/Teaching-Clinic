# ============================================================
# جلسه ۳۷ — .env loader (بدون dependency)
# ============================================================

import os
from pathlib import Path


def load_dotenv(path: Path | str = ".env", override: bool = False) -> int:
    """بارگذاری KEY=VALUE از فایل به os.environ. تعداد خطوط loaded."""
    p = Path(path)
    if not p.exists():
        return 0
    count = 0
    for line in p.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#"):
            continue
        if "=" not in line:
            continue
        key, _, value = line.partition("=")
        key, value = key.strip(), value.strip().strip('"').strip("'")
        if override or key not in os.environ:
            os.environ[key] = value
            count += 1
    return count


# ساخت فایل demo
env_file = Path(__file__).parent / "_demo.env"
env_file.write_text(
    "# تنظیمات demo\n"
    "APP_HOST=0.0.0.0\n"
    "APP_PORT=3000\n"
    "DEBUG=true\n"
    "API_KEY=demo-key-not-real\n",
    encoding="utf-8",
)

loaded = load_dotenv(env_file)
print(f"بارگذاری {loaded} متغیر")
print("HOST:", os.environ.get("APP_HOST"))
print("PORT:", os.environ.get("APP_PORT"))
print("DEBUG:", os.environ.get("DEBUG"))

env_file.unlink(missing_ok=True)
