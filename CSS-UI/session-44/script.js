// جلسه 44: Design Tokens
console.log("session-44 ready");

let alt = false;
const btn = document.getElementById("btn");
const out = document.getElementById("output");

function showBrand() {
  const value = getComputedStyle(document.documentElement)
    .getPropertyValue("--color-brand")
    .trim();
  if (out) out.textContent = `--color-brand = ${value}`;
}

showBrand();

btn?.addEventListener("click", () => {
  alt = !alt;
  document.documentElement.style.setProperty(
    "--color-brand",
    alt ? "#0f766e" : "#7c3aed"
  );
  showBrand();
});
