console.log("session-98 ready — Bulma components");

document.querySelectorAll(".navbar-burger").forEach((burger) => {
  burger.addEventListener("click", () => {
    const target = document.getElementById(burger.dataset.target);
    burger.classList.toggle("is-active");
    target?.classList.toggle("is-active");
  });
});

const modal = document.getElementById("demoModal");
const open = () => modal?.classList.add("is-active");
const close = () => modal?.classList.remove("is-active");

document.getElementById("openModal")?.addEventListener("click", (e) => {
  e.preventDefault();
  open();
});
document.getElementById("closeModal")?.addEventListener("click", close);
document.getElementById("closeModal2")?.addEventListener("click", close);
modal?.querySelector(".modal-background")?.addEventListener("click", close);
