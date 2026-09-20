export type User = {
  id: number;
  name: string;
};

export function createUser(name: string): User {
  return { id: Date.now(), name };
}
