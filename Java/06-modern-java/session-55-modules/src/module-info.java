// module-info.java — نمونه برای ماژول com.example.myapp
// این فایل باید در root ماژول (src/) باشد، نه داخل package
module com.example.myapp {

    // requires — وابستگی به moduleهای سیستمی و دیگر
    requires java.base;
    requires java.sql;

    // exports — پکیج‌هایی که برای moduleهای دیگر قابل دسترسی‌اند
    // exports com.example.myapp.api;      // باید پکیج واقعی وجود داشته باشد
    // exports com.example.myapp.model;

    // opens — برای reflection (Jackson, Spring و غیره)
    // opens com.example.myapp.internal to com.example.framework;

    // uses — service consumer
    // uses com.example.spi.NotificationService;

    // provides — service provider
    // provides com.example.spi.NotificationService
    //     with com.example.impl.EmailNotificationService;
}
