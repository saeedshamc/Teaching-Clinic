console.log("session-22 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const row = document.getElementById("row");
  row?.classList.toggle("column");
  const out = document.getElementById("output");
  if (out) out.textContent = row?.classList.contains("column") ? "direction: column" : "direction: row";
});
