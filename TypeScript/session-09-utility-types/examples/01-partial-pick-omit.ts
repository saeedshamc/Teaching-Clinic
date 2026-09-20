type User = {
  id: number;
  name: string;
  email: string;
};

type UserPatch = Partial<User>;
type UserPreview = Pick<User, "id" | "name">;
type UserPublic = Omit<User, "email">;

function applyPatch(user: User, patch: UserPatch): User {
  return { ...user, ...patch };
}

const user: User = { id: 1, name: "علی", email: "a@ex.com" };
const patched = applyPatch(user, { name: "علی رضایی" });
const preview: UserPreview = { id: patched.id, name: patched.name };
const published: UserPublic = { id: patched.id, name: patched.name };

console.log(patched);
console.log(preview);
console.log(published);
