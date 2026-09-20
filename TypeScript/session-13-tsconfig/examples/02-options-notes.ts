const notes = {
  strict: "بستهٔ ایمنی null/any ضمنی",
  noEmit: "فقط typecheck؛ بدون ساخت JS",
  NodeNext: "ESM Node؛ پسوند .js در import نسبی",
  skipLibCheck: "سرعت؛ چک سطحی‌تر روی .d.ts وابستگی",
} as const;

for (const [key, effect] of Object.entries(notes)) {
  console.log(`${key}: ${effect}`);
}
