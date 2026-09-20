console.log("session-13 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) out.textContent = "p → 0,0,1\n.text → 0,1,0\n#p1 → 1,0,0\nبرنده: #p1 (قرمز)";
});
