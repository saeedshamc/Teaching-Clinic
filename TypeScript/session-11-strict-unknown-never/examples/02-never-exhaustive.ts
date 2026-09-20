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

console.log(label("low"), label("high"));
