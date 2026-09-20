// جلسه 43: ITCSS
console.log("session-43 ready");

document.getElementById("btn")?.addEventListener("click", () => {
  const items = document.querySelectorAll("#layers li");
  items.forEach((li, i) => {
    li.classList.remove("hot");
    setTimeout(() => li.classList.add("hot"), i * 180);
  });
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      "مسیر پیشنهادی:\n" +
      "settings → tools → generic → elements → objects → components → utilities";
  }
});
