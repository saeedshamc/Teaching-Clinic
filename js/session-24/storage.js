const KEY = 'clinic.session24.notes';

export function loadNotes() {
    try {
        const raw = localStorage.getItem(KEY);
        const data = raw ? JSON.parse(raw) : [];
        return Array.isArray(data) ? data : [];
    } catch (err) {
        return [];
    }
}

export function saveNotes(notes) {
    localStorage.setItem(KEY, JSON.stringify(notes));
}
