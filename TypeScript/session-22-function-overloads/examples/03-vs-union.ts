function len(x: string | unknown[]): number {
  return x.length;
}
console.log(len("ab"), len([1, 2]));
