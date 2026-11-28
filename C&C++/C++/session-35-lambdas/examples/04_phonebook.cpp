// 04_phonebook.cpp — پروژهٔ ارزیابی فاز ۵: دفترچهٔ تلفن
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o phonebook examples/04_phonebook.cpp

#include <algorithm>
#include <iostream>
#include <map>
#include <string>
#include <vector>

using PhoneBook = std::map<std::string, std::vector<std::string>>;

void addContact(PhoneBook& book, const std::string& name, const std::string& number) {
    book[name].push_back(number);
}

// جستجوی جزئی روی نام و شماره‌ها با لامبدا
std::vector<std::string> search(const PhoneBook& book, const std::string& part) {
    std::vector<std::string> matches;
    for (const auto& [name, numbers] : book) {
        const bool nameMatches = name.find(part) != std::string::npos;
        const bool numberMatches = std::any_of(numbers.begin(), numbers.end(),
                                               [&part](const std::string& number) {
                                                   return number.find(part) != std::string::npos;
                                               });
        if (nameMatches || numberMatches) {
            matches.push_back(name);
        }
    }
    return matches;
}

int main() {
    PhoneBook book;
    addContact(book, "Sara", "0912-111-2233");
    addContact(book, "Ali", "0913-444-5566");
    addContact(book, "Ali", "021-8877-6655");
    addContact(book, "Saeed", "0919-777-8899");
    addContact(book, "Omid", "0912-333-4455");

    std::cout << "--- همهٔ مخاطبین ---\n";
    for (const auto& [name, numbers] : book) {
        std::cout << "  " << name << ": ";
        for (const auto& number : numbers) {
            std::cout << number << ' ';
        }
        std::cout << "\n";
    }

    std::cout << "--- جستجوی «0912» ---\n";
    for (const auto& name : search(book, "0912")) {
        std::cout << "  " << name << "\n";
    }

    std::cout << "--- جستجوی «Sa» ---\n";
    for (const auto& name : search(book, "Sa")) {
        std::cout << "  " << name << "\n";
    }

    // مرتب‌سازی نتایج بر اساس تعداد شماره، پایدار تا ترتیب حروف حفظ شود
    std::vector<std::string> names;
    for (const auto& entry : book) {
        names.push_back(entry.first);
    }
    std::stable_sort(names.begin(), names.end(),
                     [&book](const std::string& left, const std::string& right) {
                         return book.at(left).size() > book.at(right).size();
                     });

    std::cout << "--- مرتب بر اساس تعداد شماره ---\n";
    for (const auto& name : names) {
        std::cout << "  " << name << " (" << book.at(name).size() << ")\n";
    }

    std::cout << "--- یافتن شمارهٔ یک نفر ---\n";
    const auto it = book.find("Ali");
    if (it != book.end()) {
        std::cout << "  Ali: " << it->second.size() << " شماره\n";
    }

    return 0;
}
