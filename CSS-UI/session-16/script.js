console.log("session-16 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (!out) return;
  const lines = [...document.querySelectorAll(".s")].map((el, i) => {
    return `#${i + 1}: ${getComputedStyle(el).backgroundColor}`;
  });
  out.textContent = lines.join("\n");
});
