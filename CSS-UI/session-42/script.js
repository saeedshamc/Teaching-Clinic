// جلسه 42: BEM
console.log("session-42 ready");

document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (!out) return;
  out.textContent =
    "Block:  card\n" +
    "Element: card__btn\n" +
    "Modifier: card__btn--primary\n" +
    "Block modifier روی کارت: card--featured";
});
