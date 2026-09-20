try {
  JSON.parse("{");
} catch (err: unknown) {
  if (err instanceof Error) {
    console.log("parse error:", err.message);
  } else {
    console.log("non-Error throw:", err);
  }
}

function readMessage(err: unknown): string {
  if (err instanceof Error) return err.message;
  return String(err);
}

console.log(readMessage(new Error("boom")));
console.log(readMessage("oops"));
