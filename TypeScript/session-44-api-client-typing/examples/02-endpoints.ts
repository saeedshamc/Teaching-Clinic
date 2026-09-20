import type { User } from "./01-user.js";
export type Endpoints = {
  "/me": User;
};
export function getMe(): Endpoints["/me"] {
  return { id: 1, name: "سارا" };
}
console.log(getMe());
