console.log("session-59 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) out.textContent = `innerWidth = ${window.innerWidth}px`;
});
