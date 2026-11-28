// 01_new_delete.cpp — تخصیص و آزادسازی یک شیء در heap
// کامپایل: g++ -std=c++17 -Wall -Wextra -o new_delete examples/01_new_delete.cpp

#include <iostream>
#include <string>

int main() {
    // --- تخصیص با مقدار اولیه ---
    int* single = new int(42);
    std::cout << "آدرس در heap: " << single << "\n";
    std::cout << "*single = " << *single << "\n";

    *single = 100;
    std::cout << "بعد از *single = 100 → " << *single << "\n";

    // --- آزادسازی ---
    delete single;          // حافظه برگردانده می‌شود
    single = nullptr;       // اشاره‌گر را هم بی‌خطر کنید
    std::cout << "بعد از delete: single == nullptr ؟ "
              << (single == nullptr ? "بله" : "خیر") << "\n";

    // --- تفاوت new int و new int() ---
    int* uninitialized = new int;    // مقدار نامعلوم! چاپ نکنید
    int* zeroed = new int();         // مقدار صفر
    std::cout << "\n*zeroed = " << *zeroed << " (new int() مقدار اولیه صفر می‌دهد)\n";
    delete uninitialized;
    delete zeroed;

    // --- تخصیص شیء پیچیده ---
    std::string* text = new std::string("heap");
    text->append(" allocation");     // -> برای دسترسی به اعضای شیء از طریق pointer
    std::cout << "\n*text = " << *text << " ، length = " << text->length() << "\n";
    delete text;
    text = nullptr;

    // --- چرا heap؟ ---
    // متغیرهای معمولی روی stack هستند و با پایان تابع از بین می‌روند.
    // heap مستقل از دامنه است، ولی به همین دلیل هم خودتان باید آزادش کنید.
    std::cout << "\nنکته: هر new دقیقاً یک delete دارد.\n";

    return 0;
}
