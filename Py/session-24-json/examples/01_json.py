# ============================================================
# جلسه ۲۴ — JSON
# فایل: 01_json.py
# ============================================================

import json
from pathlib import Path

person = {"name": "سعید", "age": 30, "skills": ["Python", "Git"]}
text = json.dumps(person, ensure_ascii=False, indent=2)
print(text)

path = Path(__file__).with_name("person.json")
path.write_text(text, encoding="utf-8")

loaded = json.loads(path.read_text(encoding="utf-8"))
print("نام:", loaded["name"])
path.unlink(missing_ok=True)
