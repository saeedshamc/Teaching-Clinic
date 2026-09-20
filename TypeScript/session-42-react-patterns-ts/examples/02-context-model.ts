type Theme = "light" | "dark";
type ThemeContext = { theme: Theme; toggle: () => void };
const sample: ThemeContext = { theme: "light", toggle: () => undefined };
console.log(sample.theme);
