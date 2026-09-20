declare const userBrand: unique symbol;
declare const orderBrand: unique symbol;
type UserId = string & { readonly [userBrand]: "UserId" };
type OrderId = string & { readonly [orderBrand]: "OrderId" };
function asUserId(s: string): UserId { return s as UserId; }
function asOrderId(s: string): OrderId { return s as OrderId; }
function loadUser(id: UserId): string { return `user:${id}`; }
console.log(loadUser(asUserId("u1")));
// loadUser(asOrderId("o1")); // ❌
