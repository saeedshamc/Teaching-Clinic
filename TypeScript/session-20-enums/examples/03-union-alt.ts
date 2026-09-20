type Role = "admin" | "user";
function canEdit(role: Role): boolean {
  return role === "admin";
}
console.log(canEdit("user"));
