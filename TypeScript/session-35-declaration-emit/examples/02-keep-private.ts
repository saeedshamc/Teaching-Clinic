function internalHash(s: string): string {
  return s.length.toString(16);
}
export function token(s: string): string {
  return `t_${internalHash(s)}`;
}
console.log(token("abc"));
