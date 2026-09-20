console.log("session-70 ready");
(() => {
  const form = document.getElementById("bf");
  form?.addEventListener("submit", (e) => {
    if (!form.checkValidity()) {
      e.preventDefault();
      e.stopPropagation();
    }
    form.classList.add("was-validated");
    e.preventDefault();
  });
})();
