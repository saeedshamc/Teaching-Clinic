function parseId(data: unknown): number {
  if (typeof data !== "number" || !Number.isFinite(data)) throw new Error("id");
  return data;
}
console.log(parseId(7));
