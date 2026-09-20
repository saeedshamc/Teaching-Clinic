interface Box {
  width: number;
}

interface Box {
  height: number;
}

const box: Box = { width: 10, height: 20 };
console.log("merged Box:", box.width * box.height);
console.log("یادداشت: merging را در مدل دامنهٔ خودی عادت نکنید.");
