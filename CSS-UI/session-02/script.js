// جلسه 2: اسکلت HTML5
console.log("session-02 ready");

const output = document.getElementById("output");
const inspectBtn = document.getElementById("inspect-btn");
const titleBtn = document.getElementById("title-btn");
const practiceNote = document.getElementById("practice-note");

inspectBtn?.addEventListener("click", () => {
  const root = document.documentElement;
  const lines = [
    `lang = ${root.getAttribute("lang")}`,
    `dir = ${root.getAttribute("dir")}`,
    `title = ${document.title}`,
    `charset meta = ${document.querySelector("meta[charset]")?.getAttribute("charset") ?? "—"}`,
    `viewport = ${document.querySelector('meta[name="viewport"]')?.content ?? "—"}`,
    `description = ${document.querySelector('meta[name="description"]')?.content ?? "—"}`,
  ];
  if (output) output.textContent = lines.join("\n");
});

titleBtn?.addEventListener("click", () => {
  if (practiceNote) {
    practiceNote.textContent = `title فعلی سند: «${document.title}»`;
  }
});
