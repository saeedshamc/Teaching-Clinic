try {
  throw "boom";
} catch (e: unknown) {
  console.log(typeof e, e);
}
