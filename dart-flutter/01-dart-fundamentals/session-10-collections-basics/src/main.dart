// کالکشن‌های پایه — جلسه ۱۰
// اجرا: dart run src/main.dart
void main() {
  // ---------- List ----------
  var list = <int>[1, 2, 3, 4, 5];
  print('list: $list');
  list.add(6);
  list.add(7);
  print('after add: $list');
  list[0] = 10;
  print('after update: $list');
  print('length: ${list.length}');
  print('contains 5: ${list.contains(5)}');
  list.remove(3);
  print('after remove: $list');

  // ---------- Set ----------
  var set = <String>{'apple', 'banana', 'cherry'};
  print('set: $set');
  set.add('date');
  set.add('apple');   // تکراری — اضافه نمی‌شود
  print('after add: $set');
  print('contains banana: ${set.contains('banana')}');

  // ---------- Map ----------
  var map = <String, dynamic>{
    'name': 'Ali',
    'age': 30,
    'city': 'Tehran',
  };
  print('map: $map');
  map['email'] = 'ali@mail.com';
  print('after add: $map');
  print('name: ${map['name']}');
  print('keys: ${map.keys}');
  print('values: ${map.values}');

  // ---------- iteration ----------
  print('for-in list:');
  for (var x in list) {
    print('  $x');
  }

  print('forEach set:');
  set.forEach((x) => print('  $x'));

  print('map entries:');
  map.forEach((k, v) => print('  $k = $v'));

  // ---------- collection if/for ----------
  var evens = [for (var i in list) if (i.isEven) i];
  print('evens: $evens');
}
