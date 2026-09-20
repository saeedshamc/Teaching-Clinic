type User = { id: number; name: string };

function display(user: User): string {
  return `${user.id}:${user.name}`;
}

const user: User = { id: 1, name: "سارا" };
console.log(display(user));
console.log("برای DTO کلاس لازم نیست — type + تابع کافی است.");
