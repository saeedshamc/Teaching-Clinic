// جلسه 49: مقدمه Sass
console.log("session-49 ready");

document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      "منبع: main.scss\n" +
      "خروجی مرورگر: style.css\n" +
      "دستور: npx sass main.scss style.css";
  }
});
