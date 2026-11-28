// Book Manager — پاسخ capstone جلسهٔ ۱۵
// تمام مفاهیم جلسات ۴–۱۴: متغیرها، توابع، کالکشن‌ها، کنترل جریان، exception، I/O
import 'dart:io';

// ---------- داده ----------
final List<Map<String, dynamic>> books = [];

// ---------- توابع ----------
void addBook() {
  stdout.write('Title: ');
  String? title = stdin.readLineSync();
  stdout.write('Author: ');
  String? author = stdin.readLineSync();
  stdout.write('Year: ');
  String? yearStr = stdin.readLineSync();

  if (title == null || title.isEmpty || author == null || author.isEmpty) {
    print('Title and author are required!');
    return;
  }

  int year;
  try {
    year = int.parse(yearStr ?? '0');
  } on FormatException {
    print('Invalid year — must be a number');
    return;
  }

  books.add({'title': title, 'author': author, 'year': year});
  print('Book added!');
}

void listBooks() {
  if (books.isEmpty) {
    print('No books yet.');
    return;
  }
  print('=== Books ===');
  for (var i = 0; i < books.length; i++) {
    var b = books[i];
    print('${i + 1}. ${b['title']} by ${b['author']} (${b['year']})');
  }
}

void searchBooks() {
  stdout.write('Search (title or author): ');
  String? query = stdin.readLineSync()?.toLowerCase();
  if (query == null || query.isEmpty) return;

  var results = books.where((b) =>
      (b['title'] as String).toLowerCase().contains(query) ||
      (b['author'] as String).toLowerCase().contains(query)).toList();

  if (results.isEmpty) {
    print('No results.');
    return;
  }
  print('=== Results ===');
  for (var b in results) {
    print('${b['title']} by ${b['author']} (${b['year']})');
  }
}

void deleteBook() {
  stdout.write('Book number to delete: ');
  String? input = stdin.readLineSync();
  int index;
  try {
    index = int.parse(input ?? '0') - 1;
  } on FormatException {
    print('Invalid number');
    return;
  }
  if (index < 0 || index >= books.length) {
    print('Invalid book number');
    return;
  }
  var removed = books.removeAt(index);
  print('Deleted: ${removed['title']}');
}

void showStats() {
  if (books.isEmpty) {
    print('No books.');
    return;
  }
  int oldest = books.map((b) => b['year'] as int).reduce((a, b) => a < b ? a : b);
  int newest = books.map((b) => b['year'] as int).reduce((a, b) => a > b ? a : b);
  var authors = books.map((b) => b['author']).toSet();
  print('=== Stats ===');
  print('Total books: ${books.length}');
  print('Unique authors: ${authors.length}');
  print('Oldest: $oldest, Newest: $newest');
}

void menu() {
  while (true) {
    print('\n=== Book Manager ===');
    print('1) Add book');
    print('2) List books');
    print('3) Search');
    print('4) Delete');
    print('5) Stats');
    print('0) Exit');
    stdout.write('> ');

    String? choice = stdin.readLineSync();
    if (choice == null) break;

    switch (choice) {
      case '1': addBook(); break;
      case '2': listBooks(); break;
      case '3': searchBooks(); break;
      case '4': deleteBook(); break;
      case '5': showStats(); break;
      case '0': print('Goodbye!'); return;
      default: print('Invalid choice');
    }
  }
}

void main() {
  menu();
}
