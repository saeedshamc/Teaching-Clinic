import type { Note } from "./types.js";

export function createNote(text: string): Note {
  return {
    id: `${Date.now()}-${Math.random().toString(16).slice(2)}`,
    text: text.trim(),
    done: false,
  };
}

export function addNote(notes: Note[], text: string): Note[] {
  const note = createNote(text);
  if (!note.text) {
    return notes;
  }
  return [...notes, note];
}

export function toggleNote(notes: Note[], id: string): Note[] {
  return notes.map((note) =>
    note.id === id ? { ...note, done: !note.done } : note,
  );
}

export function removeNote(notes: Note[], id: string): Note[] {
  return notes.filter((note) => note.id !== id);
}

export function filterOpen(notes: Note[]): Note[] {
  return notes.filter((note) => !note.done);
}
