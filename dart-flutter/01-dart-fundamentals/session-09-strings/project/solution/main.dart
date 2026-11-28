// قالب ایمیل — پاسخ پروژهٔ جلسه ۹
void main() {
  String user = 'Ali';
  var items = [('Book', 15.99), ('Pen', 2.50), ('Notebook', 5.00)];
  double total = items.fold(0.0, (sum, item) => sum + item.$2);

  var sb = StringBuffer()
    ..writeln('<html><body>')
    ..writeln('<h1>Hello $user,</h1>')
    ..writeln('<p>Your order:</p><ul>');
  for (var (name, price) in items) {
    sb.writeln('<li>$name — \$$price</li>');
  }
  sb
    ..writeln('</ul>')
    ..writeln('<p><b>Total: \$$total</b></p>')
    ..writeln('</body></html>');

  print(sb.toString());
}
