console.log("session-50 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      "SCSS:\n.nav { a { &:hover, &.is-active { ... } } }\n\n" +
      "CSS خروجی تقریباً:\n.nav a:hover,\n.nav a.is-active { ... }";
  }
});
