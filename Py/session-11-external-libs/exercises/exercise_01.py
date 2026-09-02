# ============================================================
# تمرین جلسه ۱۱ — پاسخ نمونه (کلاسی)
# ============================================================

def demo_requests():
    try:
        import requests
    except ImportError:
        print("pip install requests")
        return

    url = "https://httpbin.org/get"
    r = requests.get(url, params={"name": "python"}, timeout=10)
    print("Status:", r.status_code)
    print("Server:", r.headers.get("Server", "—"))


if __name__ == "__main__":
    demo_requests()
