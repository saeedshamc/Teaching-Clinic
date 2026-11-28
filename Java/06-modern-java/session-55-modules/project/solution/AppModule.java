// AppModule.java — پاسخ پروژهٔ جلسه ۵۵ — app module
package com.example.app;

import com.example.core.CoreModule;

public class AppModule {

    public static void main(String[] args) {
        System.out.println("App module running");
        System.out.println(CoreModule.greet("World"));
        System.out.println("2 + 3 = " + CoreModule.add(2, 3));
    }
}
