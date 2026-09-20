type Result =
  | { ok: true; value: string }
  | { ok: false; error: string };

function message(r: Result): string {
  if (r.ok) return r.value;
  return r.error;
}

console.log(message({ ok: true, value: "saved" }));
console.log(message({ ok: false, error: "network" }));
