// 01_multiple_inheritance.cpp — وراثت چندگانه و رفع ابهام نام
// کامپایل: g++ -std=c++17 -Wall -Wextra -o multi examples/01_multiple_inheritance.cpp

#include <iostream>
#include <string>

class Printer {
public:
    void print(const std::string& document) {
        ++printed_;
        std::cout << "  چاپ: " << document << "\n";
    }

    int printed() const {
        return printed_;
    }

private:
    int printed_ = 0;
};

class Scanner {
public:
    std::string scan(const std::string& document) {
        ++scanned_;
        return "تصویر " + document;
    }

    int scanned() const {
        return scanned_;
    }

private:
    int scanned_ = 0;
};

// دستگاه سه‌کاره: هم چاپگر است، هم اسکنر
class Copier : public Printer, public Scanner {
public:
    void copy(const std::string& document) {
        const std::string image = scan(document);   // از Scanner
        print(image);                               // از Printer
    }
};

// ---- حالت ابهام: هر دو پایه متدی با نام یکسان دارند ----
class Alpha {
public:
    std::string status() const {
        return "Alpha";
    }
};

class Beta {
public:
    std::string status() const {
        return "Beta";
    }
};

class Gamma : public Alpha, public Beta {
public:
    // بدون این تعریف، gamma.status() خطای ابهام می‌دهد
    std::string status() const {
        return Alpha::status() + " + " + Beta::status();
    }
};

int main() {
    Copier copier;
    copier.copy("گزارش.pdf");
    copier.copy("قرارداد.pdf");

    std::cout << "\nچاپ‌شده: " << copier.printed()
              << " ، اسکن‌شده: " << copier.scanned() << "\n";

    // دو پایه، دو شمارندهٔ مستقل
    std::cout << "دسترسی جدا: Printer::printed = " << copier.Printer::printed()
              << " ، Scanner::scanned = " << copier.Scanner::scanned() << "\n";

    const Gamma gamma;
    std::cout << "\ngamma.status() = " << gamma.status() << "\n";

    // فراخوانی صریح نسخهٔ هر پایه هم ممکن است
    std::cout << "Alpha::status() = " << gamma.Alpha::status() << "\n";
    std::cout << "Beta::status()  = " << gamma.Beta::status() << "\n";

    // اگر Gamma تعریف status را نداشت، این خط خطای کامپایل می‌داد:
    //   gamma.status();   // ❌ ambiguous request for member 'status'

    return 0;
}
