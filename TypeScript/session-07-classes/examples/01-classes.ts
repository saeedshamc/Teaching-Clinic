interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  constructor(private readonly prefix: string) {}

  log(message: string): void {
    console.log(`${this.prefix}: ${message}`);
  }
}

class Counter {
  private value = 0;

  inc(): void {
    this.value += 1;
  }

  getValue(): number {
    return this.value;
  }
}

const logger = new ConsoleLogger("app");
logger.log("شروع");

const counter = new Counter();
counter.inc();
counter.inc();
console.log("count:", counter.getValue());
