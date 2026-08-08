# جلسهٔ ۳۵ — لیست پیوندی دوطرفه و حلقوی

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- لیست پیوندی دوطرفه (doubly linked list) با `prev` و `next` بسازید
- درج و حذف در ابتدا/انتها را با کارایی O(1) انجام دهید
- لیست حلقوی (circular) را بشناسید

---

## لیست دوطرفه چیست؟

هر گره به گرهٔ **قبلی و بعدی** اشاره می‌کند:

```c
typedef struct Node {
    int data;
    struct Node *prev;   /* گرهٔ قبلی */
    struct Node *next;   /* گرهٔ بعدی */
} Node;
```

مزیت: پیمایش در هر دو جهت، و حذف/درج جلوی هر گره بدون نیاز به tracking قبلی.

## ساختار

- `head->prev == NULL` (اولین گره)
- `tail->next == NULL` (آخرین گره)
- نگه‌داشتن اشاره‌گر tail اختیاری است ولی درج انتها را O(1) می‌کند.

## درج در ابتدا

```c
Node *prepend(Node *head, int value) {
    Node *new_node = malloc(sizeof(Node));
    if (new_node == NULL) return head;
    new_node->data = value;
    new_node->prev = NULL;
    new_node->next = head;
    if (head != NULL) {
        head->prev = new_node;   /* مهم! */
    }
    return new_node;
}
```

## حذف یک گره (با داشتن اشاره‌گر به آن)

```c
void delete_node(Node *node) {
    if (node->prev != NULL) node->prev->next = node->next;
    if (node->next != NULL) node->next->prev = node->prev;
    free(node);
}
```

## لیست حلقوی (Circular)

در لیست حلقوی، `tail->next = head` و `head->prev = tail` است — زنجیره بسته می‌شود. کاربرد: صف‌های چرخشی، برنامه‌ریزی round-robin.

## اشتباهات رایج

1. **فراموش کردن به‌روزرسانی `prev` گرهٔ بعدی** — ساختار دوطرفه خراب می‌شود.
2. **حذف head بدون به‌روزرسانی head** — اشاره‌گر آویزان.
3. **چک نکردن NULL برای prev/next در لبه‌ها.**
4. **در لیست حلقوی، شرط پایان `p != NULL` کار نمی‌کند** — باید `p != head` باشد.

## بهترین شیوه‌ها

- برای حذف، همیشه سه اشاره‌گر (prev→next، next→prev) را درست کنید.
- توابع head-بازگردان را برای درج ابتدا نگه دارید.
- در لیست حلقوی از گرهٔ sentinel یا شمارنده برای جلوگیری از حلقهٔ بی‌نهایت استفاده کنید.

## خلاصهٔ جلسه

- دوطرفه = prev + next؛ پیمایش دوطرفه و حذف سریع‌تر.
- حلقوی = آخر به اول وصل است.
- به‌روزرسانی کامل اشاره‌گرها در حذف/درج حیاتی است.

## تمرین‌ها

### تمرین ۱ (ساده)
لیست دوطرفه بسازید که از ابتدا و انتها پیمایش شود (پیش و پس از prepend).

### تمرین ۲ (متوسط)
تابع `delete_node` را برای لیست دوطرفه پیاده‌سازی کنید و head را درست به‌روز کنید.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_doubly_linked_list.c` — ساخت و پیمایش دوطرفه
- `examples/02_circular_list.c` — لیست حلقوی
- `exercises/exercise_01.c` — پیمایش دوطرفه
- `exercises/exercise_02.c` — حذف گره
