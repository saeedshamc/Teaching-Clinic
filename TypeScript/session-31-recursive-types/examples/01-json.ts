type Json =
  | null
  | boolean
  | number
  | string
  | Json[]
  | { [key: string]: Json };
const data: Json = { a: [1, "x", null], ok: true };
console.log(data);
