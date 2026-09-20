const cfg = {
  host: "localhost",
  port: 3000,
} satisfies { host: string; port: number };
console.log(cfg.host, cfg.port);
