type Status = "idle" | "loading" | "success" | "error";

function printId(id: string | number): void {
  if (typeof id === "string") {
    console.log("string id:", id.toUpperCase());
  } else {
    console.log("number id:", id.toFixed(0));
  }
}

function label(status: Status): string {
  switch (status) {
    case "idle":
      return "آماده";
    case "loading":
      return "بارگذاری";
    case "success":
      return "موفق";
    case "error":
      return "خطا";
  }
}

type Result =
  | { ok: true; data: string }
  | { ok: false; error: string };

function show(result: Result): void {
  if (result.ok) {
    console.log("data:", result.data);
  } else {
    console.log("error:", result.error);
  }
}

printId("abc");
printId(42);
console.log(label("loading"));
show({ ok: true, data: "done" });
show({ ok: false, error: "network" });
