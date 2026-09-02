# ============================================================
# جلسه ۴۷ — secrets و environment
# فایل: 03_secrets_env.py
# ============================================================

import os


def get_api_key() -> str:
    key = os.environ.get("API_KEY")
    if not key:
        print("⚠️ API_KEY تنظیم نشده — از placeholder استفاده می‌شود")
        return "dev-placeholder-key"
    return key


def mask_secret(value: str, visible: int = 4) -> str:
    if len(value) <= visible:
        return "***"
    return value[:visible] + "..." + "*" * (len(value) - visible)


key = get_api_key()
print("API key (masked):", mask_secret(key))
print("\n💡 export API_KEY=your-secret-before-run")
