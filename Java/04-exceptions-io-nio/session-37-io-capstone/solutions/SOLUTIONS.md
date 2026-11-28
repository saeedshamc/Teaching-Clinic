# پاسخ تمرین‌های جلسه ۳۷

## آسان

**۱.**
```java
void deleteFile(String filename) {
    try {
        Files.delete(directory.resolve(filename));
        scanDirectory();
    } catch (IOException e) { System.out.println(e.getMessage()); }
}
```

**۲.**
```java
void renameFile(String oldName, String newName) {
    try {
        Files.move(directory.resolve(oldName), directory.resolve(newName));
        scanDirectory();
    } catch (IOException e) { System.out.println(e.getMessage()); }
}
```

## متوسط

**۳.**
```java
// در scanDirectory: .endsWith(".java") را هم اضافه کنید
// یا: .filter(p -> p.toString().matches(".*\\.(txt|java)$"))
```

**۴.**
```java
long totalSize = 0;
for (TextFile f : files) totalSize += f.size;
System.out.println("Total files: " + files.size() + ", Total size: " + totalSize);
```

## سخت

**۵.**
```java
class Settings implements Serializable {
    String lastDir;
    List<String> filters;
    static final long serialVersionUID = 1L;
}
// save/load با ObjectOutputStream/ObjectInputStream
```

**۶.**
```java
// از FileChannel برای copy binary استفاده کنید — جلسهٔ ۳۵
```
