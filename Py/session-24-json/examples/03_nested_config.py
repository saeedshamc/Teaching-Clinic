# ============================================================
# جلسه ۲۴ — config تو در تو
# فایل: 03_nested_config.py
# ============================================================

import json

config = {
    "app_name": "TaskManager",
    "version": "1.0.0",
    "database": {
        "host": "localhost",
        "port": 5432,
        "name": "tasks_db",
        "pool": {"min": 2, "max": 10},
    },
    "logging": {
        "level": "INFO",
        "file": "app.log",
    },
    "features": {
        "auth": True,
        "export_json": True,
        "export_csv": False,
    },
}

# --- دسترسی تو در تو ---
db_host = config["database"]["host"]
pool_max = config["database"]["pool"]["max"]
log_level = config.get("logging", {}).get("level", "DEBUG")

print(f"DB: {db_host}:{config['database']['port']}")
print(f"Pool max: {pool_max}")
print(f"Log level: {log_level}")

# --- فیلتر featureهای فعال ---
active_features = [k for k, v in config["features"].items() if v]
print("Features فعال:", active_features)

# --- serialize ---
text = json.dumps(config, ensure_ascii=False, indent=2)
print("\nJSON:")
print(text[:200], "...")

# --- deserialize و merge ---
loaded = json.loads(text)
loaded["version"] = "1.1.0"
print("نسخه جدید:", loaded["version"])
