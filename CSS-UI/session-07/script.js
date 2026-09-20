console.log("session-07 ready");
document.getElementById("mark-btn")?.addEventListener("click", () => {
  const tags = ["header", "nav", "main", "section", "aside", "footer"];
  const lines = tags.map((t) => `${t}: ${document.querySelectorAll(t).length}`);
  const out = document.getElementById("output");
  if (out) out.textContent = lines.join("\n");
});
