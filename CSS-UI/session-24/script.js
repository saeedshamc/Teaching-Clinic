console.log("session-24 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const g = document.querySelector(".grid-demo");
  g?.classList.toggle("cols-3");
  const out = document.getElementById("output");
  if (out) out.textContent = g?.classList.contains("cols-3") ? "columns: 3" : "columns: 2";
});
