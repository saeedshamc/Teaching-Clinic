console.log("session-41 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  const bs = getComputedStyle(document.body).boxSizing;
  if (out) out.textContent = `body box-sizing = ${bs} (انتظار: border-box)`;
});
