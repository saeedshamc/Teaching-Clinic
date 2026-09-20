// جلسه ۳ — type alias برای حذف تکرار
type Note = {
  readonly id: string;
  title: string;
  done: boolean;
};

function complete(note: Note): Note {
  return { ...note, done: true };
}

const notes: Note[] = [
  { id: "n1", title: "خواندن جلسه ۳", done: false },
  { id: "n2", title: "نوشتن تمرین", done: false },
];

const doneNotes = notes.map(complete);
console.log(doneNotes);
