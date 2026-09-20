console.log("session-99 ready — Bulma shop project");

const modal = document.getElementById("productModal");
const titleEl = document.getElementById("modalTitle");

document.querySelectorAll(".js-detail").forEach((el) => {
  el.addEventListener("click", (e) => {
    e.preventDefault();
    if (titleEl) titleEl.textContent = el.dataset.title || "محصول";
    modal?.classList.add("is-active");
  });
});

const close = () => modal?.classList.remove("is-active");
document.getElementById("modalClose")?.addEventListener("click", close);
modal?.querySelector(".modal-background")?.addEventListener("click", close);
