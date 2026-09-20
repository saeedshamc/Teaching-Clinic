enum Role {
  Admin = "admin",
  User = "user",
}
function canEdit(role: Role): boolean {
  return role === Role.Admin;
}
console.log(canEdit(Role.Admin));
