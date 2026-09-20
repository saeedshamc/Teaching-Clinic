declare const userBrand: unique symbol;
export type UserId = string & { readonly [userBrand]: "UserId" };
export function asUserId(raw: string): UserId {
  if (!raw) throw new Error("empty id");
  return raw as UserId;
}
const id = asUserId("u1");
console.log(id);
