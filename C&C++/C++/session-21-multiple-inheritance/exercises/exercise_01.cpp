// exercise_01.cpp — تمرین ۱: ترکیب دو قابلیت با وراثت چندگانه
// TODO: سه کلاس بسازید:
//
//   class Printable {
//   public:
//       void printLine(const std::string& line);   // چاپ با شمارهٔ خط
//       int lineCount() const;
//   private:
//       int lineCount_ = 0;
//   };
//
//   class Serializable {
//   public:
//       void addField(const std::string& key, const std::string& value);
//       std::string serialize() const;   // "key1=value1;key2=value2"
//   private:
//       std::vector<std::string> fields_;
//   };
//
//   class Document : public Printable, public Serializable {
//   public:
//       Document(const std::string& title);
//       void show() const;      // عنوان را با printLine چاپ می‌کند
//   };
//
// در main:
//   ۱) یک Document بسازید
//   ۲) چند فیلد اضافه کنید (author، pages، ...)
//   ۳) show() را صدا بزنید و serialize() را چاپ کنید
//   ۴) lineCount را چاپ کنید
//
// نمونهٔ انتظاری:
//   [1] عنوان: گزارش فنی
//   [2] author=Saeed
//   [3] pages=12
//   خطوط چاپ‌شده: 3
//   serialize: author=Saeed;pages=12
//
// راهنما: دو پایه هیچ عضو هم‌نامی ندارند، پس ابهامی پیش نمی‌آید.
//          اگر هر دو متدی به نام label داشتند، باید با
//          Printable::label() و Serializable::label() مشخص می‌کردید.
//          توجه: show() در Document باید const باشد ولی printLine
//          وضعیت را تغییر می‌دهد — یا show را غیر const کنید، یا
//          شمارنده را mutable بگذارید (جلسهٔ ۴۱).

#include <iostream>
#include <string>
#include <vector>

// TODO: تعریف Printable، Serializable و Document

int main() {
    // TODO: ساختن Document و آزمایش

    return 0;
}
