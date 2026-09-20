function splitName(full: string): [first: string, last: string] {
  const parts = full.trim().split(/\s+/);
  return [parts[0] ?? "", parts[1] ?? ""];
}
console.log(splitName("Sara Ahmadi"));
