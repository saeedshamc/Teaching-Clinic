# پاسخ تمرین‌های جلسه ۰۸

## آسان

**۱.**
```java
int[] nums = {10, 20, 30, 40, 50};
int sum = 0;
for (int i = 0; i < nums.length; i++) sum += nums[i];
System.out.println(sum);   // 150
```

**۲.**
```java
String[] names = {"Ali", "Sara", "Reza"};
for (String n : names) System.out.println(n);
```

## متوسط

**۳.**
```java
int[][] m = {{1,2,3},{4,5,6},{7,8,9}};
for (int i = 0; i < m.length; i++) {
    System.out.print(m[i][i] + " ");   // قطر اصلی: 1 5 9
}
```

**۴.**
```java
int[] arr = {5, 2, 8, 1, 9};
Arrays.sort(arr);
int idx = Arrays.binarySearch(arr, 8);   // 3
System.out.println(arr[idx]);             // 8
```

## سخت

**۵.**
```java
int[][] ragged = {{1,2,3}, {4,5}, {6,7,8,9}};
for (int i = 0; i < ragged.length; i++) {
    int sum = 0;
    for (int j = 0; j < ragged[i].length; j++) sum += ragged[i][j];
    System.out.println("row " + i + " sum=" + sum);
}
// row 0 sum=6, row 1 sum=9, row 2 sum=30
```

**۶.**
```java
int[] orig = {1, 2, 3};
int[] copy = Arrays.copyOf(orig, 3);
copy[0] = 99;
System.out.println(orig[0]);   // 1 — کپی مستقل است
System.out.println(copy[0]);   // 99
```
