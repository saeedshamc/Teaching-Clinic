// CompositeExample.java — Composite pattern demo
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class CompositeExample {

    public static void main(String[] args) {
        // Build tree
        Composite root = new Composite("Root");
        Composite branch1 = new Composite("Branch 1");
        Composite branch2 = new Composite("Branch 2");

        root.add(new Leaf("Leaf A"));
        root.add(branch1);
        root.add(branch2);

        branch1.add(new Leaf("Leaf B"));
        branch1.add(new Leaf("Leaf C"));
        branch2.add(new Leaf("Leaf D"));

        // Execute
        root.operation();
    }

    interface Component {
        void operation();
    }

    static class Leaf implements Component {
        private final String name;
        Leaf(String name) { this.name = name; }
        @Override public void operation() { System.out.println("Leaf: " + name); }
    }

    static class Composite implements Component {
        private final String name;
        private final List<Component> children = new ArrayList<>();

        Composite(String name) { this.name = name; }

        public void add(Component component) { children.add(component); }

        @Override
        public void operation() {
            System.out.println("Composite: " + name);
            for (Component child : children) {
                child.operation();
            }
        }
    }
}
