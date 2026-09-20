const http = require("http");
const port = 3000;
http
  .createServer((_req, res) => {
    res.writeHead(200, { "Content-Type": "application/json; charset=utf-8" });
    res.end(JSON.stringify({ ok: true, service: "clinic-s12-api" }));
  })
  .listen(port, "0.0.0.0");
