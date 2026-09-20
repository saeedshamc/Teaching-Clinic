console.log("session-36 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const h = document.querySelector("h1");
  const out = document.getElementById("output");
  if (h && out) out.textContent = `h1 font-size = ${getComputedStyle(h).fontSize}`;
});
