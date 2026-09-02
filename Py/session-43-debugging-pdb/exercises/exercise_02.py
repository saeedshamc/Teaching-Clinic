# ============================================================
# تمرین ۲ جلسه ۴۳ — traceback و parse_config
# ============================================================

import traceback


def parse_config(data: dict) -> str:
    # باگ: کلید اشتباه
    return data["host"] + ":" + str(data["port"])


def safe_parse(data: dict) -> str:
    host = data.get("host", "localhost")
    port = data.get("port", 8080)
    return f"{host}:{port}"


if __name__ == "__main__":
    bad = {"hostname": "127.0.0.1", "port": 3000}
    try:
        parse_config(bad)
    except KeyError:
        print("=== traceback ===")
        traceback.print_exc()

    print("\n=== fixed ===")
    print(safe_parse(bad))
