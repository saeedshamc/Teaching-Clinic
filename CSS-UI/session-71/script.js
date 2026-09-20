console.log("session-71 ready");

document.querySelectorAll('[data-bs-toggle="tooltip"]').forEach((el) => {
  new bootstrap.Tooltip(el);
});

document.querySelectorAll('[data-bs-toggle="popover"]').forEach((el) => {
  new bootstrap.Popover(el);
});

const toastEl = document.getElementById("liveToast");
const toast = toastEl ? new bootstrap.Toast(toastEl) : null;

document.getElementById("showToastBtn")?.addEventListener("click", () => {
  toast?.show();
});
