// exercise_01_solution.cpp — راه‌حل تمرین ۱: Printable + Serializable
// کامپایل: g++ -std=c++17 -Wall -Wextra -o document exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>
#include <vector>

class Printable {
public:
    void printLine(const std::string& line) {
        ++lineCount_;
        std::cout << "[" << lineCount_ << "] " << line << "\n";
    }

    int lineCount() const {
        return lineCount_;
    }

private:
    int lineCount_ = 0;
};

class Serializable {
public:
    void addField(const std::string& key, const std::string& value) {
        fields_.push_back(key + "=" + value);
    }

    std::string serialize() const {
        std::string result;
        for (std::size_t i = 0; i < fields_.size(); ++i) {
            if (i > 0) {
                result += ";";
            }
            result += fields_[i];
        }
        return result;
    }

    const std::vector<std::string>& fields() const {
        return fields_;
    }

private:
    std::vector<std::string> fields_;
};

class Document : public Printable, public Serializable {
public:
    explicit Document(const std::string& title)
        : title_(title) {
    }

    // غیر const، چون printLine شمارندهٔ خط را تغییر می‌دهد
    void show() {
        printLine("عنوان: " + title_);
        for (const std::string& field : Serializable::fields()) {
            printLine(field);
        }
    }

private:
    std::string title_;
};

int main() {
    Document document("گزارش فنی");
    document.addField("author", "Saeed");
    document.addField("pages", "12");

    document.show();

    std::cout << "خطوط چاپ‌شده: " << document.lineCount() << "\n";
    std::cout << "serialize: " << document.serialize() << "\n";

    return 0;
}
