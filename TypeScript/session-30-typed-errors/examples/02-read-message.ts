function readMessage(err: unknown): string {
  if (err instanceof Error) return err.message;
  return String(err);
}
console.log(readMessage(new Error("x")), readMessage(1));
