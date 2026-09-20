interface User {
  id: number;
  name: string;
}

interface Admin extends User {
  role: "admin";
}

type WithEmail = User & { email: string };

const admin: Admin = {
  id: 1,
  name: "رضا",
  role: "admin",
};

const mailed: WithEmail = {
  id: 2,
  name: "سارا",
  email: "sara@example.com",
};

function who(u: User): string {
  return `#${u.id} ${u.name}`;
}

console.log(who(admin));
console.log(who(mailed));
console.log("admin.role:", admin.role);
