// جلسه ۳ — tuple در برابر object نام‌دار
const point: [number, number] = [10, 20];
const entry: [string, number] = ["age", 21];

type Player = { name: string; score: number };
const player: Player = { name: "سارا", score: 21 };

console.log("x:", point[0], "y:", point[1]);
console.log("entry:", entry[0], entry[1]);
console.log("player:", player.name, player.score);

// player خواناتر از tuple است وقتی فیلد معنا دارد.
