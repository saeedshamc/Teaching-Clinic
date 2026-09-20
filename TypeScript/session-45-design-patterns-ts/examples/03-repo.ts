interface Repo<T, Id> {
  get(id: Id): T | undefined;
}
type Note = { id: string; text: string };
class MapRepo implements Repo<Note, string> {
  constructor(private map = new Map<string, Note>()) {}
  set(note: Note): void { this.map.set(note.id, note); }
  get(id: string): Note | undefined { return this.map.get(id); }
}
const repo = new MapRepo();
repo.set({ id: "1", text: "hi" });
console.log(repo.get("1"));
