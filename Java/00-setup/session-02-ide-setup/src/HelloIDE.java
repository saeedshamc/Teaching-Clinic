// HelloIDE.java — Hello World با پکیج
// نکته: فایل باید در مسیر src/com/example/ باشد تا پکیج معتبر باشد.
// در IDE این را در src/com/example/HelloIDE.java بسازید.
package com.example;

public class HelloIDE {
    public static void main(String[] args) {
        // بدون IDE: javac -d out src/com/example/HelloIDE.java
        //             java -cp out com.example.HelloIDE
        // نام کامل کلاس = package + نام کلاس → com.example.HelloIDE
        System.out.println("Hello from IDE!");
    }
}
