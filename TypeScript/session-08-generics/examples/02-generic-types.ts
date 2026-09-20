type Box<T> = { value: T };

type ApiResponse<T> = {
  data: T;
  status: number;
};

const numberBox: Box<number> = { value: 42 };
const userRes: ApiResponse<{ id: number; name: string }> = {
  data: { id: 1, name: "علی" },
  status: 200,
};

console.log(numberBox.value);
console.log(userRes.status, userRes.data.name);
