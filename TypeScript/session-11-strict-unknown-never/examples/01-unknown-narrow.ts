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

handle("ts");
handle(3.14);
handle(true);
