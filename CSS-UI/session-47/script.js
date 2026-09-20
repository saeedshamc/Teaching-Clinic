// جلسه 47: DevTools
console.log("session-47 ready — open DevTools with F12");

document.getElementById("btn")?.addEventListener("click", () => {
  const bug = document.getElementById("bug");
  const conflict = document.getElementById("conflict");
  const out = document.getElementById("output");
  if (!bug || !out) return;

  const s = getComputedStyle(bug);
  const c = conflict ? getComputedStyle(conflict).color : "—";
  out.textContent =
    `box width: ${s.width}\n` +
    `padding: ${s.padding}\n` +
    `border-width: ${s.borderWidth}\n` +
    `margin: ${s.margin}\n` +
    `رنگ متن conflict (برنده): ${c}`;
});
