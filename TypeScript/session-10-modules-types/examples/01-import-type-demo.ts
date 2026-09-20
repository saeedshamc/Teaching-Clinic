import type { User } from "./user.js";
import { createUser } from "./user.js";

function label(user: User): string {
  return user.name;
}

const u = createUser("مینا");
console.log(label(u));
