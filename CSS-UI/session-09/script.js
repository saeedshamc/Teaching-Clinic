console.log("session-09 ready");
document.getElementById("demo-btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      "external: فایل style.css (نگهداری بهتر)\n" +
      "internal: تگ <style> در head\n" +
      "inline: attribute style (قوی ولی شکننده)\n" +
      "پیشنهاد دوره: تقریباً همیشه external.";
  }
});
