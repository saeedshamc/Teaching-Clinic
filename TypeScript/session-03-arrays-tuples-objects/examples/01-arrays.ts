// جلسه ۳ — آرایهٔ همگن
const scores: number[] = [18, 20, 14];
const empty: string[] = [];
empty.push("سارا");

const doubled = scores.map((n) => n * 2);
const high = scores.filter((n) => n >= 18);

console.log({ scores, doubled, high, empty });
