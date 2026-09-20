type User = { id: number; name: string; email: string };

type NameEmailPatch = Partial<Pick<User, "name" | "email">>;

function patchContact(user: User, patch: NameEmailPatch): User {
  return { ...user, ...patch };
}

const user: User = { id: 1, name: "علی", email: "a@ex.com" };
console.log(patchContact(user, { email: "new@ex.com" }));
// patchContact(user, { id: 9 }); // ❌ id در Pick نیست
