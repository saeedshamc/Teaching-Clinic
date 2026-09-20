// جلسه ۴ — literal types برای state
type Status = "idle" | "loading" | "success" | "error";

function label(status: Status): string {
  switch (status) {
    case "idle":
      return "آماده";
    case "loading":
      return "بارگذاری";
    case "success":
      return "موفق";
    case "error":
      return "خطا";
  }
}

const current: Status = "loading";
console.log(label(current));
// const bad: Status = "pending"; // Error
