// EventHandler.java — پاسخ پروژهٔ جلسه ۵۳
sealed interface Event permits ClickEvent, KeyEvent, MouseEvent {}

record ClickEvent(int x, int y) implements Event {}
record KeyEvent(char key) implements Event {}
record MouseEvent(String action, int x, int y) implements Event {}

sealed interface Result permits Success, Failure {}
record Success(String message) implements Result {}
record Failure(String error) implements Result {}

public class EventHandler {

    static Result handleEvent(Event event) {
        if (event instanceof ClickEvent c) {
            return new Success("Click at (" + c.x() + ", " + c.y() + ")");
        } else if (event instanceof KeyEvent k) {
            return new Success("Key pressed: " + k.key());
        } else if (event instanceof MouseEvent m) {
            if (m.action().equals("moved")) {
                return new Failure("Mouse moved — ignored");
            }
            return new Success("Mouse " + m.action() + " at (" + m.x() + ", " + m.y() + ")");
        }
        return new Failure("Unknown event");
    }

    static String describeResult(Result result) {
        if (result instanceof Success s) {
            return "✓ " + s.message();
        } else if (result instanceof Failure f) {
            return "✗ " + f.error();
        }
        return "Unknown";
    }

    public static void main(String[] args) {
        Event[] events = {
            new ClickEvent(100, 200),
            new KeyEvent('a'),
            new MouseEvent("moved", 50, 60),
            new MouseEvent("clicked", 75, 85)
        };

        for (Event event : events) {
            Result result = handleEvent(event);
            System.out.println(describeResult(result));
        }
    }
}
