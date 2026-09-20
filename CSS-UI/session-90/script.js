console.log("session-90 ready — dark mode class strategy");

document.getElementById("themeBtn")?.addEventListener("click", () => {
  const root = document.documentElement;
  const dark = root.classList.toggle("dark");
  localStorage.setItem("tw-theme", dark ? "dark" : "light");
});
