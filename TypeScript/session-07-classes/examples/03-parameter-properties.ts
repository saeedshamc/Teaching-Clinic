interface Logger {
  log(message: string): void;
}

class PrefixedLogger implements Logger {
  constructor(private readonly prefix: string) {}

  log(message: string): void {
    console.log(`${this.prefix}: ${message}`);
  }
}

class Animal {
  constructor(protected name: string) {}
}

class Dog extends Animal {
  bark(): string {
    return `${this.name}: هاپ`;
  }
}

new PrefixedLogger("api").log("ok");
console.log(new Dog("رکس").bark());
