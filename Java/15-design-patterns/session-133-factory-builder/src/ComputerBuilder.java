// ComputerBuilder.java — Builder pattern demo
// قابل کامپایل با javac — بدون dependency
public class ComputerBuilder {

    public static void main(String[] args) {
        // Gaming PC
        Computer gamingPC = new Computer.Builder()
            .cpu("Intel i9-13900K")
            .ram(32)
            .storage(2000)
            .hasGPU(true)
            .build();

        // Office PC
        Computer officePC = new Computer.Builder()
            .cpu("Intel i5")
            .ram(8)
            .storage(512)
            .build();

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
    }

    static class Computer {
        private final String cpu;
        private final int ram;
        private final int storage;
        private final boolean hasGPU;

        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.hasGPU = builder.hasGPU;
        }

        public static class Builder {
            private String cpu = "Intel i5";
            private int ram = 8;
            private int storage = 256;
            private boolean hasGPU = false;

            public Builder cpu(String cpu) { this.cpu = cpu; return this; }
            public Builder ram(int ram) { this.ram = ram; return this; }
            public Builder storage(int storage) { this.storage = storage; return this; }
            public Builder hasGPU(boolean hasGPU) { this.hasGPU = hasGPU; return this; }

            public Computer build() {
                return new Computer(this);
            }
        }

        @Override
        public String toString() {
            return String.format("Computer{cpu='%s', ram=%dGB, storage=%dGB, GPU=%s}",
                cpu, ram, storage, hasGPU ? "Yes" : "No");
        }
    }
}
