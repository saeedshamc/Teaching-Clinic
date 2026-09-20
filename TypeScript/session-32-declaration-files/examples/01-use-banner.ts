// نمونهٔ آموزشی: در پروژهٔ واقعی امضا از .d.ts می‌آید.
// اینجا پیاده‌سازی محلی می‌گذاریم تا مثال بدون ambient اجباری اجرا شود.
function clinicBanner(title: string): void {
  console.log("[banner]", title);
}

clinicBanner("session-32");
