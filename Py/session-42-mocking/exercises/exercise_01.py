# ============================================================
# تمرین ۱ جلسه ۴۲ — Mock API
# ============================================================

from unittest.mock import Mock


def fetch_user(api, user_id: int) -> dict:
    return api.get_user(user_id)


def test_fetch_user():
    api = Mock()
    api.get_user.return_value = {"id": 1, "name": "سعید"}
    result = fetch_user(api, 1)
    assert result["name"] == "سعید"
    api.get_user.assert_called_once_with(1)
    print("✅ test_fetch_user OK")


def test_retry_side_effect():
    api = Mock()
    api.get_user.side_effect = [ConnectionError("fail"), {"id": 2, "name": "مینا"}]

    try:
        api.get_user(2)
    except ConnectionError:
        result = api.get_user(2)
    assert result["name"] == "مینا"
    assert api.get_user.call_count == 2
    print("✅ test_retry_side_effect OK")


if __name__ == "__main__":
    test_fetch_user()
    test_retry_side_effect()
