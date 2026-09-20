function handle(value: unknown): void {
  if (typeof value === "string") {
    console.log("string:", value.toUpperCase());
    return;
  }
  if (typeof value === "number") {
    console.log("number:", value.toFixed(1));
    return;
  }
  console.log("نوع پشتیبانی‌نشده");
}

type Level = "low" | "high";

function assertNever(x: never): never {
  throw new Error(`unexpected: ${String(x)}`);
}

function label(level: Level): string {
  switch (level) {
    case "low":
      return "کم";
    case "high":
      return "زیاد";
    default:
      return assertNever(level);
  }
}

handle("ts");
handle(3.14);
handle(true);
console.log(label("low"), label("high"));

// در catch مدرن:
try {
  JSON.parse("{");
} catch (err: unknown) {
  if (err instanceof Error) {
    console.log("parse error:", err.message);
  }
}
