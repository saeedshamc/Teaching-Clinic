const http = require("http");
const fs = require("fs");
const path = "/data/count.txt";

function readCount() {
  try {
    return parseInt(fs.readFileSync(path, "utf8"), 10) || 0;
  } catch {
    return 0;
  }
}

function writeCount(n) {
  fs.writeFileSync(path, String(n));
}

http
  .createServer((_req, res) => {
    const n = readCount() + 1;
    writeCount(n);
    res.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
    res.end(JSON.stringify({ ok: true, hits: n, service: "clinic-s16-api" }));
  })
  .listen(3000, "0.0.0.0");
