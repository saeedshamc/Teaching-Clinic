console.log("session-26 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) out.textContent = `عرض viewport: ${window.innerWidth}px`;
});
