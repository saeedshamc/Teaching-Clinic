// جلسه 48: performance
console.log("session-48 ready — end of architecture block");

document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      "چک‌لیست:\n" +
      "1) حرکت با transform/opacity\n" +
      "2) پرهیز از layout thrash\n" +
      "3) CSS مرده کمتر با معماری لایه‌ای\n" +
      "4) critical CSS را مفهومی بفهمید";
  }
});
