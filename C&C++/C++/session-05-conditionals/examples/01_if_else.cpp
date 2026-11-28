// 01_if_else.cpp — if / else if / else و ترتیب شرط‌ها
// کامپایل: g++ -std=c++17 -Wall -Wextra -o cond examples/01_if_else.cpp

#include <iostream>
#include <string>

int main() {
    int score = 0;
    std::cout << "نمره (0 تا 100): ";
    if (!(std::cin >> score)) {
        std::cerr << "خطا: عدد وارد کنید.\n";
        return 1;
    }

    // بررسی دادهٔ نامعتبر اول از همه (guard clause)
    if (score < 0 || score > 100) {
        std::cerr << "نمرهٔ نامعتبر: " << score << "\n";
        return 1;
    }

    // ترتیب شرط‌ها از بزرگ به کوچک است
    std::string rank;
    if (score >= 90) {
        rank = "عالی";
    } else if (score >= 75) {
        rank = "خوب";
    } else if (score >= 50) {
        rank = "قبول";
    } else {
        rank = "مردود";
    }
    std::cout << "رتبه: " << rank << "\n";

    // شرط تودرتو: اول وضعیت قبولی، بعد جزئیات
    const bool passed = score >= 50;
    if (passed) {
        if (score >= 90) {
            std::cout << "وضعیت: قبول با افتخار\n";
        } else {
            std::cout << "وضعیت: قبول\n";
        }
    } else {
        std::cout << "وضعیت: نیاز به تلاش بیشتر\n";
    }

    // همین منطق با ترکیب && به‌جای تودرتو
    if (passed && score >= 90) {
        std::cout << "همان نتیجه با شرط ترکیبی\n";
    }

    // نشان دادن اینکه ترتیب اشتباه چه می‌کند
    if (score >= 50) {
        std::cout << "اگر این شرط اول بود، نمرهٔ " << score
                  << " همیشه «قبول» می‌شد.\n";
    }

    return 0;
}
