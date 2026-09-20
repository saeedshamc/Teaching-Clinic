console.log("session-06 ready");
document.getElementById("pref-form")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  const data = new FormData(form);
  const topics = data.getAll("topics").join(", ") || "—";
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      `نقش: ${data.get("role")}\n` +
      `سطح: ${data.get("level")}\n` +
      `موضوع‌ها: ${topics}\n` +
      `درباره: ${data.get("bio") || "—"}`;
  }
});
