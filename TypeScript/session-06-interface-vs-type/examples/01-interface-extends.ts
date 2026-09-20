interface User {
  id: number;
  name: string;
}

interface Admin extends User {
  role: "admin";
}

function displayName(user: User): string {
  return user.name;
}

const admin: Admin = { id: 1, name: "سارا", role: "admin" };

console.log(displayName(admin));
console.log("role:", admin.role);
