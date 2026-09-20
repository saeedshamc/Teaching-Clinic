console.log("session-21 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const c1 = document.querySelector(".c1");
  c1?.classList.toggle("front");
  const out = document.getElementById("output");
  if (out) out.textContent = c1?.classList.contains("front")
    ? "حالا لایه خاکستری (z بالاتر) روی آبی است."
    : "حالت اولیه: آبی (z=2) روی خاکستری (z=1).";
});
