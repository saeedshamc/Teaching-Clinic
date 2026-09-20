function makeDate(timestamp: number): Date;
function makeDate(y: number, m: number, d: number): Date;
function makeDate(y: number, m?: number, d?: number): Date {
  if (m !== undefined && d !== undefined) return new Date(y, m, d);
  return new Date(y);
}
console.log(makeDate(Date.now()).toISOString());
console.log(makeDate(2026, 0, 1).toDateString());
