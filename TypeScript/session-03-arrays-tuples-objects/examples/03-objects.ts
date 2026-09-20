// جلسه ۳ — object: optional و readonly
type Note = {
  readonly id: string;
  title: string;
  tag?: string;
};

const a: Note = { id: "n1", title: "خرید نان" };
const b: Note = { id: "n2", title: "تمرین TS", tag: "آموزش" };

function printTag(note: Note): void {
  if (note.tag !== undefined) {
    console.log(note.id, note.tag.toUpperCase());
  } else {
    console.log(note.id, "(بدون برچسب)");
  }
}

printTag(a);
printTag(b);

const editable = { ...b, title: "تمرین TypeScript" };
console.log("کپی با عنوان جدید:", editable.title);
// b.id = "x"; // ❌ readonly
