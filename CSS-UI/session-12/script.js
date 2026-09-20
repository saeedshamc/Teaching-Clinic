console.log("session-12 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const out = document.getElementById("output");
  if (out) out.textContent = "hover را با ماوس و focus را با Tab روی لینک/دکمه تمرین کنید.";
});
