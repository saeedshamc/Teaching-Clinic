# ============================================================
# تمرین ۲ جلسه ۴۰ — TOML نمونه و parse version
# ============================================================

PYPROJECT_TOML = """
[project]
name = "my-cli-tool"
version = "1.2.3"
requires-python = ">=3.10"
dependencies = ["click>=8.0"]

[project.scripts]
mytool = "my_cli_tool.main:run"

[build-system]
requires = ["setuptools>=68"]
build-backend = "setuptools.build_meta"
"""


def parse_version(version: str) -> tuple[int, int, int]:
    parts = version.split(".")
    if len(parts) != 3:
        raise ValueError("فرمت semver نامعتبر")
    return int(parts[0]), int(parts[1]), int(parts[2])


def bump_patch(version: str) -> str:
    major, minor, patch = parse_version(version)
    return f"{major}.{minor}.{patch + 1}"


if __name__ == "__main__":
    print("=== نمونه pyproject.toml ===")
    print(PYPROJECT_TOML.strip())
    v = "1.2.3"
    print(f"\nparse {v} → {parse_version(v)}")
    print(f"bump patch → {bump_patch(v)}")
