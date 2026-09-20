console.log("session-28 ready");
let alt = false;
document.getElementById("btn")?.addEventListener("click", () => {
  alt = !alt;
  document.documentElement.style.setProperty("--brand", alt ? "#c026d3" : "#1d4ed8");
  const out = document.getElementById("output");
  if (out) out.textContent = `--brand = ${getComputedStyle(document.documentElement).getPropertyValue("--brand").trim()}`;
});
