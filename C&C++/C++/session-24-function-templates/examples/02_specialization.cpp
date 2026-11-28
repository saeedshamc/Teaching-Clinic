// 02_specialization.cpp — تخصص (specialization) قالب تابع
// کامپایل: g++ -std=c++17 -Wall -Wextra -o spec examples/02_specialization.cpp

#include <iomanip>
#include <iostream>
#include <sstream>
#include <string>

// قالب اصلی: برای هر نوعی که با << چاپ می‌شود
template <typename T>
std::string formatValue(const T& value) {
    std::ostringstream out;
    out << value;
    return out.str();
}

// تخصص کامل برای bool: true/false به‌جای 1/0
template <>
std::string formatValue<bool>(const bool& value) {
    return value ? "true" : "false";
}

// تخصص برای رشته: با گیومه، تا از عدد و کلمه تشخیص داده شود
template <>
std::string formatValue<std::string>(const std::string& value) {
    return "\"" + value + "\"";
}

// تخصص برای double: دو رقم اعشار ثابت
template <>
std::string formatValue<double>(const double& value) {
    std::ostringstream out;
    out << std::fixed << std::setprecision(2) << value;
    return out.str();
}

int main() {
    std::cout << "int      : " << formatValue(42) << "\n";
    std::cout << "bool     : " << formatValue(true) << "\n";
    std::cout << "bool     : " << formatValue(false) << "\n";
    std::cout << "double   : " << formatValue(3.14159) << "\n";
    std::cout << "string   : " << formatValue(std::string("سلام")) << "\n";
    std::cout << "char     : " << formatValue('X') << "\n";
    std::cout << "long     : " << formatValue(1234567890123L) << "\n";

    // نکته: تخصص باید قبل از اولین استفاده دیده شود؛ وگرنه قالب اصلی
    // انتخاب می‌شود و کامپایلر هیچ هشداری نمی‌دهد.

    // برای انواع دیگر، قالب اصلی استفاده می‌شود — حتی اگر specialization
    // «منطقی» دیگری وجود داشته باشد:
    const int smallNumber = 7;
    std::cout << "\nconst int هم از قالب اصلی: " << formatValue(smallNumber) << "\n";

    return 0;
}
