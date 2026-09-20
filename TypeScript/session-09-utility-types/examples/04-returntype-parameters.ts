function createUser(name: string) {
  return { id: Date.now(), name };
}

type Created = ReturnType<typeof createUser>;
type Args = Parameters<typeof createUser>;

const created: Created = createUser("سارا");
const sampleArgs: Args = ["demo"];

console.log(created);
console.log("args example:", sampleArgs);
