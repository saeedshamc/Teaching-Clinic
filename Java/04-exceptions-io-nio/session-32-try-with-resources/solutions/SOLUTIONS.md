# پاسخ تمرین‌های جلسه ۳۲

## آسان

**۱.**
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) System.out.println(line);
} catch (IOException e) { System.out.println(e.getMessage()); }
```

**۲.**
```java
try (FileWriter fw = new FileWriter("out.txt");
     BufferedWriter bw = new BufferedWriter(fw)) {
    bw.write("Hello\n");
}
```

## متوسط

**۳.**
```java
catch (IOException e) {
    for (Throwable t : e.getSuppressed()) {
        System.out.println("Suppressed: " + t.getMessage());
    }
}
```

**۴.**
```java
static BufferedReader readFile(String f) throws IOException {
    return new BufferedReader(new FileReader(f));
}
// استفاده:
try (BufferedReader br = readFile("data.txt")) { ... }
```

## سخت

**۵.**
```java
class MyResource implements AutoCloseable {
    public void close() { System.out.println("Resource closed"); }
}
// استفاده:
try (MyResource r = new MyResource()) { ... }   // "Resource closed" چاپ می‌شود
```

**۶.**
```java
try (Scanner sc = new Scanner(System.in)) {
    String input = sc.nextLine();
}
// Scanner Closeable است — بعد از try نمی‌توان استفاده کرد
```
