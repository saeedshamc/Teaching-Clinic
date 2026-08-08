# جلسهٔ ۳۸ — مبانی درخت دودویی (Binary Tree)

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- ساختار درخت و گره (root، child، leaf) را بفهمید
- درخت دودویی را با اشاره‌گر بسازید
- پیمایش‌های پیشوندی/میانوندی/پسوندی (preorder/inorder/postorder) را انجام دهید
- تابع‌های بازگشتی روی درخت (شمارش گره، ارتفاع) بنویسید

---

## درخت چیست؟

درخت ساختار دادهٔ **غیرخطی و سلسله‌مراتبی** است: یک ریشه (root)، گره‌های فرزند و برگ‌ها (leaf = بدون فرزند). **درخت دودویی** یعنی هر گره حداکثر **دو فرزند** دارد: left و right.

```c
typedef struct Node {
    int data;
    struct Node *left;    /* زیردرخت چپ */
    struct Node *right;   /* زیردرخت راست */
} Node;
```

## ساخت گره

```c
Node *new_node(int value) {
    Node *n = malloc(sizeof(Node));
    if (n == NULL) return NULL;
    n->data = value;
    n->left = NULL;
    n->right = NULL;
    return n;
}
```

## پیمایش‌ها (Traversal)

```c
void preorder(Node *r) {   /* ریشه، چپ، راست */
    if (r == NULL) return;
    printf("%d ", r->data);
    preorder(r->left);
    preorder(r->right);
}

void inorder(Node *r) {    /* چپ، ریشه، راست */
    if (r == NULL) return;
    inorder(r->left);
    printf("%d ", r->data);
    inorder(r->right);
}

void postorder(Node *r) {  /* چپ، راست، ریشه */
    if (r == NULL) return;
    postorder(r->left);
    postorder(r->right);
    printf("%d ", r->data);
}
```

## تابع‌های بازگشتی

```c
int count_nodes(Node *r) {
    if (r == NULL) return 0;
    return 1 + count_nodes(r->left) + count_nodes(r->right);
}

int height(Node *r) {
    if (r == NULL) return -1;
    int l = height(r->left), rt = height(r->right);
    return (l > rt ? l : rt) + 1;
}
```

## نکات

- بازگشت روی درخت طبیعی‌ترین راه است.
- ترتیب inorder در BST مرتب است (جلسهٔ بعد).
- آزادسازی درخت هم بازگشتی است: اول فرزندان، بعد خود گره.

## اشتباهات رایج

1. **فراموش کردن شرط NULL** — کرش در بازگشت.
2. **free کردن گره قبل از فرزندان** — از دست دادن زیردرخت (در free_tree اول بچه‌ها).
3. **ساختن درخت نامتعادل با درج‌های ترتیبی** — در BST بدتر می‌شود (جلسهٔ بعد).
4. **فراموش کردن `left`/`right = NULL` در گرهٔ جدید.**

## بهترین شیوه‌ها

- همیشه حالت پایه (NULL) اول بنویسید.
- پیمایش و محاسبات را بازگشتی نگه دارید.
- برای دیباگ، ابتدا درخت کوچک بسازید.

## خلاصهٔ جلسه

- درخت دودویی: گره با دو فرزند left/right.
- پیمایش‌های preorder/inorder/postorder.
- شمارش گره و ارتفاع بازگشتی.

## تمرین‌ها

### تمرین ۱ (ساده)
درختی با ۵ گره بسازید و تعداد گره‌ها را با تابع بازگشتی چاپ کنید.

### تمرین ۲ (متوسط)
تابع `height` بنویسید که ارتفاع درخت (بیشترین تعداد یال از ریشه تا عمیق‌ترین برگ) را برگرداند.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_tree_basics.c` — ساخت و پیمایش‌ها
- `examples/02_tree_metrics.c` — شمارش گره و ارتفاع
- `exercises/exercise_01.c` — شمارش گره‌ها
- `exercises/exercise_02.c` — ارتفاع
