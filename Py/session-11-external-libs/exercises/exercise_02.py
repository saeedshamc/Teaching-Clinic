# ============================================================
# تمرین جلسه ۱۱ — پاسخ نمونه (خانه)
# ============================================================
import json


def fetch_json(url, timeout=5):
    try:
        import requests
        r = requests.get(url, timeout=timeout)
        r.raise_for_status()
        return r.json()
    except Exception as e:
        print("خطا:", e)
        return None


# offline fallback
def fetch_json_mock(url):
    return {"url": url, "mock": True}


data = fetch_json_mock("https://httpbin.org/get")
print(json.dumps(data, ensure_ascii=False, indent=2))
