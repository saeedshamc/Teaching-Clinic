interface User {
  id: number;
  name: string;
}

type Timestamped = User & { createdAt: Date };

type Result =
  | { ok: true; data: string }
  | { ok: false; error: string };

function describe(r: Result): string {
  if (r.ok) return `ok: ${r.data}`;
  return `err: ${r.error}`;
}

const stamped: Timestamped = {
  id: 7,
  name: "علی",
  createdAt: new Date("2026-01-01"),
};

console.log(stamped.name, stamped.createdAt.toISOString());
console.log(describe({ ok: true, data: "saved" }));
console.log(describe({ ok: false, error: "network" }));
