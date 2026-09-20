interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  private readonly prefix: string;

  constructor(prefix: string) {
    this.prefix = prefix;
  }

  log(message: string): void {
    console.log(`${this.prefix}: ${message}`);
  }
}

const logger = new ConsoleLogger("app");
logger.log("شروع");
