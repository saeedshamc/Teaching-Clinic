console.log("session-04 ready");
document.getElementById("sum-btn")?.addEventListener("click", () => {
  const rows = document.querySelectorAll("tbody tr").length;
  const out = document.getElementById("output");
  if (out) out.textContent = `تعداد ردیف داده در tbody: ${rows}`;
});
