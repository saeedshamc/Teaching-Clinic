import { addNote } from "../domain/notes.js";
import type { Note } from "../types/note.js";
let notes: Note[] = [];
notes = addNote(notes, "ساختار پروژه");
console.log(notes);
