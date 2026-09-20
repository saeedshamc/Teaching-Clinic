export function createNote(text) {
    return {
        id: crypto.randomUUID(),
        text: text.trim(),
        done: false
    };
}

export function addNote(notes, text) {
    const note = createNote(text);
    if (!note.text) {
        return notes;
    }
    return [...notes, note];
}

export function toggleNote(notes, id) {
    return notes.map(function (note) {
        return note.id === id ? { ...note, done: !note.done } : note;
    });
}

export function removeNote(notes, id) {
    return notes.filter(function (note) {
        return note.id !== id;
    });
}

export function visibleNotes(notes, onlyOpen) {
    if (!onlyOpen) {
        return notes;
    }
    return notes.filter(function (note) {
        return !note.done;
    });
}
