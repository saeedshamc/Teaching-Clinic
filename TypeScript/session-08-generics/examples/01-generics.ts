function first<T>(items: T[]): T | undefined {
  return items[0];
}

type Box<T> = {
  value: T;
};

type ApiResponse<T> = {
  data: T;
  status: number;
};

function pluck<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}

const names = ["سارا", "علی"];
console.log("first:", first(names));

const box: Box<number> = { value: 10 };
console.log("box:", box);

const user = { id: 1, name: "رضا" };
console.log("pluck name:", pluck(user, "name"));

const response: ApiResponse<typeof user> = {
  data: user,
  status: 200,
};
console.log("api:", response.status, response.data.name);
