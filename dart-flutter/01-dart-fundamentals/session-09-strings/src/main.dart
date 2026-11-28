// رشته‌ها — جلسه ۹
// اجرا: dart run src/main.dart
void main() {
  String name = 'Ali';
  int age = 30;

  // ---------- interpolation ----------
  print('Name: $name, Age: $age');
  print('Double age: ${age * 2}');

  // ---------- basic methods ----------
  String s = '  Hello World  ';
  print('length: ${s.length}');
  print('trimmed: "${s.trim()}"');
  print('upper: ${s.trim().toUpperCase()}');
  print('lower: ${s.trim().toLowerCase()}');
  print('contains "World": ${s.contains('World')}');
  print('startsWith "  H": ${s.startsWith('  H')}');
  print('substring(7,12): ${s.substring(7, 12)}');
  print('indexOf "World": ${s.indexOf('World')}');
  print('replaceAll "o"→"0": ${s.replaceAll('o', '0')}');

  // ---------- split ----------
  String csv = 'apple,banana,cherry';
  List<String> parts = csv.split(',');
  print('split: $parts');

  // ---------- pad ----------
  print('padLeft: "${'5'.padLeft(3, '0')}"');
  print('padRight: "${'5'.padRight(3, '0')}"');

  // ---------- StringBuffer ----------
  var sb = StringBuffer();
  sb.write('Hello');
  sb.write(' ');
  sb.writeln('World');
  sb.write('!');
  print('buffer: ${sb.toString()}');

  // ---------- raw & multiline ----------
  var path = r'C:\Users\Ali\Documents';
  print('raw path: $path');
  var multi = '''
Line 1
Line 2
Line 3''';
  print('multi:\n$multi');
}
