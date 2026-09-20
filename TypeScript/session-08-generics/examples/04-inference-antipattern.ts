function wrap<T>(value: T): { value: T } {
  return { value };
}

type Note = { id: number; title: string };

function saveNote(note: Note): Note {
  return note;
}

console.log(wrap(true));
console.log(saveNote({ id: 1, title: "خرید" }));
console.log("دامنه با نام Note؛ جنریک برای کمک‌تابع‌های عمومی.");
