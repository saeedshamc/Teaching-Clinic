# ============================================================
# جلسه ۴۷ — hashing و token
# فایل: 04_hashing_tokens.py
# ============================================================

import hashlib
import secrets


def hash_password(password: str, salt: str | None = None) -> tuple[str, str]:
    if salt is None:
        salt = secrets.token_hex(16)
    digest = hashlib.pbkdf2_hmac(
        "sha256",
        password.encode(),
        salt.encode(),
        100_000,
    )
    return salt, digest.hex()


def verify_password(password: str, salt: str, expected_hex: str) -> bool:
    _, computed = hash_password(password, salt)
    return secrets.compare_digest(computed, expected_hex)


def generate_session_token() -> str:
    return secrets.token_urlsafe(32)


salt, hashed = hash_password("my-secure-password")
print("salt:", salt)
print("hash:", hashed[:32], "...")
print("verify OK:", verify_password("my-secure-password", salt, hashed))
print("verify fail:", verify_password("wrong", salt, hashed))
print("session token:", generate_session_token()[:20], "...")
