type LibUser = { id: number };
type AppUser = LibUser & { role: "admin" | "user" };
const u: AppUser = { id: 1, role: "user" };
console.log(u);
