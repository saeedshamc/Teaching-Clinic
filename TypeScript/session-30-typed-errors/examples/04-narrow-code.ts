type AppError =
  | { code: "NOT_FOUND"; id: string }
  | { code: "FORBIDDEN" };
function label(e: AppError): string {
  switch (e.code) {
    case "NOT_FOUND":
      return e.id;
    case "FORBIDDEN":
      return "no";
  }
}
console.log(label({ code: "NOT_FOUND", id: "1" }));
