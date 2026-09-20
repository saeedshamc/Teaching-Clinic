console.log("session-14 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const box = document.querySelector(".box-demo");
  const out = document.getElementById("output");
  if (!box || !out) return;
  const s = getComputedStyle(box);
  out.textContent =
    `width: ${s.width}\n` +
    `padding: ${s.padding}\n` +
    `border: ${s.borderWidth}\n` +
    `margin: ${s.margin}\n` +
    `box-sizing: ${s.boxSizing}`;
});
