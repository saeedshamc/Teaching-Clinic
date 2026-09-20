console.log("session-57 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const v = getComputedStyle(document.documentElement).getPropertyValue("--brand").trim();
  const out = document.getElementById("output");
  if (out) out.textContent = `--brand = ${v}`;
});
