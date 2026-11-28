// سیستم مدیریت وظایف — پاسخ پروژهٔ جلسه ۸
import 'dart:io';

void addTask(List<Map<String, dynamic>> tasks, String title) {
  tasks.add({'title': title, 'done': false});
}

void completeTask(List<Map<String, dynamic>> tasks, int index) {
  if (index >= 0 && index < tasks.length) {
    tasks[index]['done'] = true;
  }
}

void listTasks(List<Map<String, dynamic>> tasks) {
  print('Tasks:');
  for (var i = 0; i < tasks.length; i++) {
    var t = tasks[i];
    print('  [${t['done'] ? 'x' : ' '}] ${t['title']}');
  }
}

void main() {
  var tasks = <Map<String, dynamic>>[];
  addTask(tasks, 'Buy milk');
  addTask(tasks, 'Read book');
  addTask(tasks, 'Call mom');
  completeTask(tasks, 1);
  listTasks(tasks);
}
