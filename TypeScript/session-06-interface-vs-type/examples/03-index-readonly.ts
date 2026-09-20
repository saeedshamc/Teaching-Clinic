interface StringMap {
  [key: string]: number;
}

interface Config {
  readonly apiUrl: string;
  timeout: number;
}

const scores: StringMap = { ali: 10, sara: 18 };
scores.reza = 15;

const cfg: Config = { apiUrl: "https://api.example", timeout: 3000 };
cfg.timeout = 5000;
// cfg.apiUrl = "x"; // ❌ readonly

console.log("scores:", scores);
console.log("cfg:", cfg);
