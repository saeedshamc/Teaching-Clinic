type ScriptName = "typecheck" | "run:example";

function describe(script: ScriptName): string {
  if (script === "typecheck") {
    return "اجرای tsc --noEmit";
  }
  return "اجرای tsx روی یک فایل";
}

console.log(describe("typecheck"));
console.log(describe("run:example"));
console.log("دستور: npm run typecheck");
