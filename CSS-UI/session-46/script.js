// جلسه 46: Logical Properties
console.log("session-46 ready");

const root = document.documentElement;
const out = document.getElementById("output");

function showDir() {
  if (out) out.textContent = `dir سند = ${root.getAttribute("dir")}`;
}

showDir();

document.getElementById("btn")?.addEventListener("click", () => {
  const next = root.getAttribute("dir") === "rtl" ? "ltr" : "rtl";
  root.setAttribute("dir", next);
  showDir();
});
