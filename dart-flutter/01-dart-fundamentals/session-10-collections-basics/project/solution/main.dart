// دفترچهٔ تلفن — پاسخ پروژهٔ جلسه ۱۰
void main() {
  var contacts = <String, String>{};

  void addContact(String name, String phone) {
    contacts[name] = phone;
  }

  String? findContact(String name) {
    return contacts[name];
  }

  void listContacts() {
    print('Contacts:');
    contacts.forEach((name, phone) => print('  $name: $phone'));
  }

  addContact('Ali', '0912-345-6789');
  addContact('Sara', '0935-987-6543');
  addContact('Reza', '0911-111-1111');

  listContacts();
  print('Find Ali: ${findContact('Ali')}');
  print('Find Unknown: ${findContact('Unknown') ?? 'not found'}');
}
