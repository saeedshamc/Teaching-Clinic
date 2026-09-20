const roles = ["admin", "user"] as const;
type Role = (typeof roles)[number];
const r: Role = "admin";
console.log(r, roles);
