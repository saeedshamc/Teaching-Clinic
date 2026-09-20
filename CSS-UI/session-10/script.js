console.log("session-10 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) out.textContent = `p=${document.querySelectorAll("p").length} | .hl=${document.querySelectorAll(".hl").length} | #unique=${document.querySelectorAll("#unique").length}`;
});
