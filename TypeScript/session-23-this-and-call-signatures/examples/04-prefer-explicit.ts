function greet(user: { name: string }): string {
  return user.name;
}
console.log(greet({ name: "علی" }));
