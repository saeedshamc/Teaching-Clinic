# پاسخ تمرین‌های جلسه ۲۳

## آسان

**۱.**
```java
class Outer {
    int x = 10;
    class Inner {
        void print() { System.out.println("x=" + x); }
    }
}
Outer o = new Outer();
Outer.Inner i = o.new Inner();
i.print();   // x=10
```

**۲.**
```java
class Outer {
    static int y = 20;
    static class Nested {
        void print() { System.out.println("y=" + y); }
    }
}
Outer.Nested n = new Outer.Nested();   // بدون outer instance
n.print();
```

## متوسط

**۳.**
```java
Runnable r = new Runnable() {
    @Override public void run() { System.out.println("Running!"); }
};
r.run();
```

**۴.**
```java
void method() {
    class Local {
        void print() { System.out.println("Local!"); }
    }
    Local l = new Local();
    l.print();
}
```

## سخت

**۵.**
```java
class Outer {
    private int secret = 42;
    class Inner {
        int getSecret() { return secret; }   // دسترسی به private Outer
    }
}
```

**۶.**
```java
interface A { void a(); }
interface B { void b(); }
A a = new A() { public void a() { System.out.println("A"); } };
B b = new B() { public void b() { System.out.println("B"); } };
```
