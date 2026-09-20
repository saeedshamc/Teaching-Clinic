import { addNote, filterOpen, toggleNote } from "./notes.js";
import type { Note } from "./types.js";

let notes: Note[] = [];
notes = addNote(notes, "نوشتن تمرین آسان");
notes = addNote(notes, "نوشتن چالش");
notes = toggleNote(notes, notes[0]!.id);

console.log("open only:", filterOpen(notes));
console.log("all count:", notes.length);
