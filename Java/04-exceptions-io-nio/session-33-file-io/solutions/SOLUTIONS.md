# پاسخ تمرین‌های جلسه ۳۳

## آسان

**۱.**
```java
File f = new File("test.txt");
System.out.println(f.exists());
System.out.println(f.length());
System.out.println(f.getName());
```

**۲.**
```java
try (FileWriter fw = new FileWriter("out.txt")) {
    fw.write("Hello");
}
try (FileReader fr = new FileReader("out.txt")) {
    int c; while ((c = fr.read()) != -1) System.out.print((char) c);
}
```

## متوسط

**۳.**
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line; int n = 1;
    while ((line = br.readLine()) != null) System.out.println(n++ + ": " + line);
}
```

**۴.**
```java
try (PrintWriter pw = new PrintWriter("out.txt")) {
    pw.printf("%s: %d%n", "Ali", 20);
}
```

## سخت

**۵.**
```java
File dir = new File(".");
File[] files = dir.listFiles();
for (File f : files) {
    if (f.isFile()) System.out.println(f.getName() + " — " + f.length() + " bytes");
}
```

**۶.**
```java
try (FileWriter fw = new FileWriter("log.txt", true)) {   // append mode
    fw.write("New entry\n");
}
```
