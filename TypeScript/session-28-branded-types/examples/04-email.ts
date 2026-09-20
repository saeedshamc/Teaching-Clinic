type Email = string & { readonly __brand: "Email" };
function asEmail(s: string): Email {
  if (!s.includes("@")) throw new Error("email");
  return s as Email;
}
console.log(asEmail("a@b.com"));
