# ============================================================
# جلسه ۳۸ — Repository pattern با ABC
# ============================================================

from abc import ABC, abstractmethod
from dataclasses import dataclass


@dataclass
class User:
    id: int
    name: str
    email: str


class UserRepository(ABC):
    @abstractmethod
    def get(self, user_id: int) -> User:
        pass

    @abstractmethod
    def save(self, user: User) -> None:
        pass

    @abstractmethod
    def delete(self, user_id: int) -> bool:
        pass

    def exists(self, user_id: int) -> bool:
        try:
            self.get(user_id)
            return True
        except KeyError:
            return False


class InMemoryUserRepository(UserRepository):
    def __init__(self):
        self._store: dict[int, User] = {}

    def get(self, user_id: int) -> User:
        if user_id not in self._store:
            raise KeyError(user_id)
        return self._store[user_id]

    def save(self, user: User) -> None:
        self._store[user.id] = user

    def delete(self, user_id: int) -> bool:
        return self._store.pop(user_id, None) is not None


class UserService:
    def __init__(self, repo: UserRepository):
        self.repo = repo

    def register(self, user: User) -> None:
        if self.repo.exists(user.id):
            raise ValueError("user exists")
        self.repo.save(user)

    def get_name(self, user_id: int) -> str:
        return self.repo.get(user_id).name


repo = InMemoryUserRepository()
service = UserService(repo)
service.register(User(1, "سعید", "s@x.com"))
service.register(User(2, "مریم", "m@x.com"))
print("User 1:", service.get_name(1))
print("exists 3?", repo.exists(3))
