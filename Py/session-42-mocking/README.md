# جلسه ۴۲: Mocking و شبیه‌سازی وابستگی (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم test double و mock را توضیح دهید
- با `unittest.mock.Mock` و `MagicMock` کار کنید
- `return_value` و `side_effect` تنظیم کنید
- `assert_called_with` برای تأیید فراخوانی استفاده کنید
- `patch` برای جایگزینی موقت وابستگی بنویسید
- تفاوت mock، stub و fake را بدانید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | چرا mock؟ | isolation، سرعت، قطعی بودن |
| 10–30 | Mock پایه | return_value |
| 30–45 | side_effect | exception، sequence |
| 45–60 | assert_called | تأیید interaction |
| 60–75 | patch | monkeypatch موقت |
| 75–85 | تمرین | mock API، DB |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. مشکل وابستگی خارجی

```python
def get_weather(city: str) -> dict:
    response = requests.get(f"https://api.weather.com/{city}")
    return response.json()
```

تست واقعی:
- به اینترنت نیاز دارد
- کند است
- ممکن است fail شود (rate limit، downtime)

**راه‌حل:** وابستگی را mock کنید — رفتار را شبیه‌سازی بدون اجرای واقعی.

---

## ۲. Mock چیست؟

شیء جعلی که:
- متدها را دارد (هر نامی)
- رفتار قابل تنظیم (`return_value`)
- تاریخچه فراخوانی را ثبت می‌کند

```python
from unittest.mock import Mock

api = Mock()
api.fetch.return_value = {"temp": 25}
assert api.fetch("Tehran")["temp"] == 25
api.fetch.assert_called_once_with("Tehran")
```

---

## ۳. return_value vs side_effect

```python
mock = Mock()
mock.rand.return_value = 42          # همیشه 42

mock2 = Mock()
mock2.rand.side_effect = [1, 2, 3]   # هر بار مقدار بعدی
mock2.rand.side_effect = ValueError  # raise exception
```

---

## ۴. MagicMock

مثل Mock ولی magic methods (`__str__`, `__iter__`) هم پشتیبانی می‌شوند.

```python
from unittest.mock import MagicMock
file_mock = MagicMock()
file_mock.read.return_value = "محتوا"
```

---

## ۵. assert_called_* 

| متد | کاربرد |
|-----|--------|
| `assert_called()` | حداقل یک بار |
| `assert_called_once()` | دقیقاً یک بار |
| `assert_called_with(x)` | با آرگومان x |
| `assert_not_called()` | هرگز |

تست **interaction** نه فقط **state**.

---

## ۶. patch — جایگزینی موقت

```python
from unittest.mock import patch

@patch("mymodule.requests.get")
def test_weather(mock_get):
    mock_get.return_value.json.return_value = {"temp": 20}
    result = get_weather("Tehran")
    assert result["temp"] == 20
```

`patch` object اصلی را موقتاً عوض می‌کند — بعد از تست برمی‌گردد.

---

## ۷. patch as context manager

```python
with patch("builtins.open", mock_open(read_data="hello")):
    content = read_config("file.txt")
    assert content == "hello"
```

---

## ۸. mock_open برای فایل

```python
from unittest.mock import mock_open, patch

m = mock_open(read_data='{"key": "value"}')
with patch("builtins.open", m):
    ...
```

---

## ۹. pytest + mock

```python
from unittest.mock import Mock

def test_service():
    repo = Mock()
    repo.find.return_value = User("سعید")
    service = UserService(repo)
    assert service.get_name(1) == "سعید"
    repo.find.assert_called_once_with(1)
```

pytest به mock نیاز خاص ندارد — unittest.mock کافی است.

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_mock_basics.py](./examples/01_mock_basics.py) | Mock و return_value |
| [02_side_effect.py](./examples/02_side_effect.py) | side_effect |
| [03_patch.py](./examples/03_patch.py) | patch decorator |
| [04_service_mock.py](./examples/04_service_mock.py) | mock repository |

---

## ۱۱. mock vs stub vs fake

| نوع | توضیح |
|-----|-------|
| Stub | پاسخ از پیش تعیین‌شده |
| Mock | + verify interaction |
| Fake | پیاده‌سازی ساده واقعی (FakeDB) |
| Spy | wrapper روی real object |

---

## ۱۲. چه چیزی mock کنیم؟

| ✅ | ❌ |
|----|-----|
| HTTP، DB، filesystem | منطق ساده داخلی |
| زمان (`datetime.now`) | هر dependency |
| random | class under test |

**قانون:** mock at boundary.

---

## ۱۳. اجرای مثال‌ها

```bash
python session-42-mocking/examples/01_mock_basics.py
python session-42-mocking/examples/02_side_effect.py
python session-42-mocking/examples/03_patch.py
python session-42-mocking/examples/04_service_mock.py
pytest session-42-mocking/examples/03_patch.py -v
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| patch مسیر اشتباه | mock اعمال نمی‌شود | patch where used |
| over-mocking | تست شکننده | فقط boundary |
| فراموش assert_called | interaction unchecked | assert |
| mock spec ندارید | typo silent pass | spec=Class |

---

## ۱۵. spec و autospec

```python
real = MyClass()
mock = Mock(spec=real)  # فقط متدهای واقعی
```

---

## ۱۶. تمرین کلاسی

1. Mock API با return_value
2. side_effect برای fail دومین فراخوانی
3. patch `random.randint`

---

## ۱۷. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۸. خلاصه

```
Mock()              → شیء جعلی
.return_value       → خروجی ثابت
.side_effect        → sequence / exception
@patch("path")      → جایگزینی موقت
assert_called_with  → verify interaction
mock at boundary    → isolation
```

---

**جلسه قبل:** [۴۱ — pytest](../session-41-pytest-intro/) | **بعد:** [۴۳ — Debugging](../session-43-debugging-pdb/)

---

## ۱۹. patch.where used not where defined

```python
# myapp/service.py
from requests import get

# test — patch محل استفاده:
@patch("myapp.service.get")
```

---

## ۲۰. MagicMock spec_set

```python
mock = Mock(spec=MyClass, spec_set=True)
mock.typo = 1  # AttributeError
```

---

## ۲۲. pytest-mock fixture

```python
def test_api(mocker):
    mock_get = mocker.patch("app.requests.get")
    mock_get.return_value.status_code = 200
```

---

## ۲۳. autospec

```python
mock = Mock(autospec=RealClass)
```

متدهای واقعی enforce می‌شوند — typo fail می‌کند.
