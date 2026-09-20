// جلسه ۴ — discriminated union
type Result =
  | { ok: true; data: string }
  | { ok: false; error: string };

type LoadState =
  | { status: "idle" }
  | { status: "loading" }
  | { status: "ready"; payload: string }
  | { status: "failed"; message: string };

function show(result: Result): void {
  if (result.ok) {
    console.log("data:", result.data);
  } else {
    console.log("error:", result.error);
  }
}

function describe(state: LoadState): string {
  switch (state.status) {
    case "idle":
      return "آماده";
    case "loading":
      return "صبر کنید";
    case "ready":
      return state.payload;
    case "failed":
      return state.message;
  }
}

show({ ok: true, data: "done" });
show({ ok: false, error: "network" });
console.log(describe({ status: "ready", payload: "hello" }));
