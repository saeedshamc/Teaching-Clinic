console.log("session-86 ready — layout patterns");

const toggle = document.getElementById("navToggle");
const mobile = document.getElementById("navMobile");

toggle?.addEventListener("click", () => {
  const open = mobile?.classList.toggle("hidden") === false;
  toggle.setAttribute("aria-expanded", String(open));
});
