import { addNote, filterOpen, removeNote, toggleNote } from "./notes.js";
import type { Note } from "./types.js";

let notes: Note[] = [];
notes = addNote(notes, "خواندن جلسه ۱۵");
notes = addNote(notes, "ساختن Vite شخصی");
notes = toggleNote(notes, notes[0]!.id);

console.log("all:", notes);
console.log("open:", filterOpen(notes));

notes = removeNote(notes, notes[1]!.id);
console.log("after remove:", notes);
console.log("typecheck: npm run typecheck");
