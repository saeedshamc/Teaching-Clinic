# پاسخ تمرین‌های جلسه ۳۴

## آسان

**۱.**
```java
Path p = Paths.get("src", "main", "file.txt");
System.out.println(p.getFileName());   // file.txt
System.out.println(p.getParent());     // src/main
```

**۲.**
```java
Files.write(Paths.get("test.txt"), "Hello".getBytes());
List<String> lines = Files.readAllLines(Paths.get("test.txt"));
```

## متوسط

**۳.**
```java
Path base = Paths.get("/home/user");
Path file = Paths.get("docs", "readme.txt");
System.out.println(base.resolve(file));           // /home/user/docs/readme.txt
System.out.println(Paths.get("/a/b/../c").normalize());  // /a/c
```

**۴.**
```java
System.out.println(Files.exists(path));
System.out.println(Files.size(path));
System.out.println(Files.isDirectory(path));
```

## سخت

**۵.**
```java
try (Stream<Path> stream = Files.walk(Paths.get("."))) {
    stream.filter(Files::isRegularFile)
          .forEach(System.out::println);
}
```

**۶.**
```java
Files.copy(source, target);           // کپی
Files.move(source, target);           // move/rename
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
```
