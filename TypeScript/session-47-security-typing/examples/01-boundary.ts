function handleBody(body: unknown): void {
  if (typeof body !== "object" || body === null) throw new Error("body");
  console.log("ok shape gate");
}
handleBody({ a: 1 });
