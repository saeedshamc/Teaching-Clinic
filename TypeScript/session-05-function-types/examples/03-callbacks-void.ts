type OnScore = (score: number) => void;

function play(rounds: number, onScore: OnScore): void {
  for (let i = 1; i <= rounds; i++) {
    onScore(i * 10);
  }
}

function log(msg: string): void {
  console.log(msg);
}

function maybeFind(nums: number[], target: number): number | undefined {
  return nums.find((n) => n === target);
}

play(3, (score) => {
  console.log("امتیاز:", score);
});

log("فقط side-effect");
console.log("find 2:", maybeFind([1, 2, 3], 2));
console.log("find 9:", maybeFind([1, 2, 3], 9));
