interface Sorter {
  sort(nums: number[]): number[];
}
class Asc implements Sorter {
  sort(nums: number[]): number[] {
    return [...nums].sort((a, b) => a - b);
  }
}
console.log(new Asc().sort([3, 1, 2]));
