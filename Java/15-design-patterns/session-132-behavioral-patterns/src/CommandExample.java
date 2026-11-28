// CommandExample.java — Command pattern demo
// قابل کامپایل با javac — بدون dependency
public class CommandExample {

    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        // Undo
        remote.undo();
    }

    interface Command {
        void execute();
        void undo();
    }

    static class Light {
        public void on() { System.out.println("Light is ON"); }
        public void off() { System.out.println("Light is OFF"); }
    }

    static class LightOnCommand implements Command {
        private final Light light;
        public LightOnCommand(Light light) { this.light = light; }
        @Override public void execute() { light.on(); }
        @Override public void undo() { light.off(); }
    }

    static class LightOffCommand implements Command {
        private final Light light;
        public LightOffCommand(Light light) { this.light = light; }
        @Override public void execute() { light.off(); }
        @Override public void undo() { light.on(); }
    }

    static class RemoteControl {
        private Command command;
        private Command lastCommand;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
            lastCommand = command;
        }

        public void undo() {
            if (lastCommand != null) {
                lastCommand.undo();
            }
        }
    }
}
