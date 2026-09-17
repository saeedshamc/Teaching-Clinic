# ============================================================
# جلسه ۴۲ — Mock پایه
# فایل: 01_mock_basics.py
# ============================================================

from unittest.mock import Mock


class WeatherAPI:
    def fetch(self, city: str) -> dict:
        raise NotImplementedError("در production به API واقعی وصل می‌شود")


def get_temperature(api: WeatherAPI, city: str) -> int:
    data = api.fetch(city)
    return data["temp"]


api_mock = Mock(spec=WeatherAPI)
api_mock.fetch.return_value = {"temp": 28, "city": "Tehran"}

temp = get_temperature(api_mock, "Tehran")
print(f"دما در تهران: {temp}°C")
api_mock.fetch.assert_called_once_with("Tehran")
print("✅ mock و assert_called تأیید شد")
