// exercise_01_solution.cpp — راه‌حل تمرین ۱: ذخیره و بازیابی رکوردها
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o records exercises/exercise_01_solution.cpp

#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace fs = std::filesystem;

struct Record {
    std::string name;
    int score;
};

void saveRecords(const fs::path& path, const std::vector<Record>& records) {
    std::ofstream output(path);
    if (!output) {
        std::cerr << "نوشتن ناموفق بود\n";
        return;
    }
    for (const auto& record : records) {
        output << record.name << ';' << record.score << '\n';
    }
}

std::vector<Record> loadRecords(const fs::path& path) {
    std::vector<Record> records;
    std::ifstream input(path);
    if (!input) {
        return records;
    }
    std::string line;
    while (std::getline(input, line)) {
        std::istringstream parts(line);
        std::string name;
        std::string scorePart;
        if (!std::getline(parts, name, ';') || !std::getline(parts, scorePart, ';')) {
            continue;
        }
        records.push_back({name, std::stoi(scorePart)});
    }
    return records;
}

void print(const std::vector<Record>& records) {
    for (const auto& record : records) {
        std::cout << "  " << record.name << ": " << record.score << "\n";
    }
}

int main() {
    const std::vector<Record> original{{"Sara", 95}, {"Ali", 88}, {"Omid", 72}};
    const fs::path path = fs::temp_directory_path() / "session39_records.txt";

    saveRecords(path, original);
    std::cout << "ذخیره شد: " << original.size() << " رکورد\n";
    std::cout << "اندازهٔ فایل: " << fs::file_size(path) << " بایت\n";

    const std::vector<Record> loaded = loadRecords(path);
    std::cout << "--- بازخوانی ---\n";
    print(loaded);
    std::cout << "تعداد خوانده‌شده: " << loaded.size() << "\n";

    const Record* best = nullptr;
    for (const auto& record : loaded) {
        if (best == nullptr || record.score > best->score) {
            best = &record;
        }
    }
    if (best != nullptr) {
        std::cout << "رکورد با بالاترین نمره: " << best->name << " (" << best->score << ")\n";
    }

    fs::remove(path);
    return 0;
}
