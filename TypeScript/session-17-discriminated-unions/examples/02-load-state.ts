type LoadState =
  | { status: "idle" }
  | { status: "loading" }
  | { status: "success"; data: string }
  | { status: "error"; message: string };

function view(state: LoadState): string {
  switch (state.status) {
    case "idle":
      return "آماده";
    case "loading":
      return "...";
    case "success":
      return state.data;
    case "error":
      return state.message;
  }
}

console.log(view({ status: "success", data: "سلام" }));
