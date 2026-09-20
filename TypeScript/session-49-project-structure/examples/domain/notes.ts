import type { Note } from "../types/note.js";
export function addNote(notes: Note[], text: string): Note[] {
  const note: Note = {
    id: `n-${Date.now()}`,
    text: text.trim(),
    done: false,
  };
  if (!note.text) return notes;
  return [...notes, note];
}
