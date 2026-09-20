console.log("session-37 ready");
document.getElementById("action-btn")?.addEventListener("click", () => {
  document.body.classList.toggle("dark");
  const out = document.getElementById("output");
  if (out) out.textContent = document.body.classList.contains("dark") ? "تم تاریک" : "تم روشن";
});
