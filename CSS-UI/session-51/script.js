console.log("session-51 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) {
    out.textContent =
      '@use "colors" as *;\n' +
      "// $brand و $surface از _colors.scss\n" +
      "// به‌جای @import قدیمی";
  }
});
