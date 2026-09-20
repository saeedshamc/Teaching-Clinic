type S = Awaited<Promise<Promise<string>>>;
const s: S = "nested";
console.log(s);
