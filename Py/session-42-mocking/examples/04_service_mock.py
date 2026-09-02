# ============================================================
# جلسه ۴۲ — mock repository
# فایل: 04_service_mock.py
# ============================================================

from unittest.mock import Mock
from dataclasses import dataclass


@dataclass
class User:
    id: int
    name: str


class UserRepository:
    def find_by_id(self, user_id: int) -> User | None:
        raise NotImplementedError


class UserService:
    def __init__(self, repo: UserRepository):
        self.repo = repo

    def get_display_name(self, user_id: int) -> str:
        user = self.repo.find_by_id(user_id)
        if user is None:
            return "ناشناس"
        return f"کاربر: {user.name}"


def test_get_display_name_found():
    repo = Mock(spec=UserRepository)
    repo.find_by_id.return_value = User(1, "سعید")
    service = UserService(repo)
    assert service.get_display_name(1) == "کاربر: سعید"
    repo.find_by_id.assert_called_once_with(1)
    print("✅ found case OK")


def test_get_display_name_missing():
    repo = Mock(spec=UserRepository)
    repo.find_by_id.return_value = None
    service = UserService(repo)
    assert service.get_display_name(99) == "ناشناس"
    print("✅ missing case OK")


if __name__ == "__main__":
    test_get_display_name_found()
    test_get_display_name_missing()
