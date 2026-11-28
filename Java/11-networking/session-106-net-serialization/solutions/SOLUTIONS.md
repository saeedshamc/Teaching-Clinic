# پاسخ تمرین‌های جلسه ۱۰۶

## آسان

**۱ و ۲.**
```java
public class Message implements Serializable {
    private String sender;
    private String content;
}

ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
oos.writeObject(message);
```

## متوسط

**۳ و ۴.**
```java
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
Message msg = (Message) ois.readObject();

private transient String password;  // serialize نمی‌شود
```

## سخت

**۵ و ۶.**
```java
private static final long serialVersionUID = 1L;

try {
    oos.writeObject(object);
} catch (NotSerializableException e) {
    System.out.println("Object not serializable");
}
```
