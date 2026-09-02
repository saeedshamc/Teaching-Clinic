# ============================================================
# جلسه ۱۱ — handle خطای requests
# فایل: 03_requests_error.py
# ============================================================

def fetch_url(url, timeout=5):
    try:
        import requests
    except ImportError:
        return None, "requests نصب نیست"

    try:
        r = requests.get(url, timeout=timeout)
        r.raise_for_status()
        return r.status_code, r.text[:100]
    except requests.exceptions.Timeout:
        return None, "timeout"
    except requests.exceptions.HTTPError as e:
        return None, f"HTTP: {e.response.status_code}"
    except requests.exceptions.RequestException as e:
        return None, str(e)


for url in [
    "https://httpbin.org/status/200",
    "https://httpbin.org/status/404",
    "https://invalid.example.test",
]:
    code, body = fetch_url(url)
    print(url, "→", code or body)
