type Option<T> = { some: true; value: T } | { some: false };
function first<T>(arr: T[]): Option<T> {
  if (arr.length === 0) return { some: false };
  return { some: true, value: arr[0]! };
}
console.log(first([1]), first([]));
