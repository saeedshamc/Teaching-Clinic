// جلسه 1: وب، مرورگر و محیط توسعه
console.log("session-01 ready");

const checklist = document.getElementById("checklist");
const checkBtn = document.getElementById("check-btn");
const output = document.getElementById("output");
const timeBtn = document.getElementById("time-btn");
const note = document.getElementById("note");

checkBtn?.addEventListener("click", () => {
  const items = checklist ? [...checklist.querySelectorAll("li")] : [];
  items.forEach((li) => li.classList.add("done"));

  const stamp = new Date().toLocaleString("fa-IR");
  if (output) {
    output.textContent =
      `محیط جلسه ۱ ثبت شد — ${stamp}\n` +
      `موارد چک‌لیست: ${items.length}\n` +
      "بعدی: جلسه ۲ (اسکلت HTML5)";
  }
});

timeBtn?.addEventListener("click", () => {
  const now = new Date().toLocaleTimeString("fa-IR");
  if (note) {
    note.textContent = `زمان تمرین شما: ${now} — اگر جملهٔ خودتان را نوشتید، همین‌جا بماند.`;
  }
});
