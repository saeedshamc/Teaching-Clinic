// UIFactory.java — Abstract Factory pattern demo
// قابل کامپایل با javac — بدون dependency
public class UIFactory {

    public static void main(String[] args) {
        // Windows UI
        BaseUIFactory windowsFactory = new WindowsFactory();
        windowsFactory.createUI();

        // Mac UI
        BaseUIFactory macFactory = new MacFactory();
        macFactory.createUI();
    }

    interface Button { void render(); }
    interface CheckBox { void render(); }

    static class WindowsButton implements Button {
        @Override public void render() { System.out.println("[Windows] Rendering button"); }
    }
    static class WindowsCheckBox implements CheckBox {
        @Override public void render() { System.out.println("[Windows] Rendering checkbox"); }
    }

    static class MacButton implements Button {
        @Override public void render() { System.out.println("[Mac] Rendering button"); }
    }
    static class MacCheckBox implements CheckBox {
        @Override public void render() { System.out.println("[Mac] Rendering checkbox"); }
    }

    abstract static class BaseUIFactory {
        public abstract Button createButton();
        public abstract CheckBox createCheckBox();

        public void createUI() {
            Button button = createButton();
            CheckBox checkBox = createCheckBox();
            button.render();
            checkBox.render();
        }
    }

    static class WindowsFactory extends BaseUIFactory {
        @Override public Button createButton() { return new WindowsButton(); }
        @Override public CheckBox createCheckBox() { return new WindowsCheckBox(); }
    }

    static class MacFactory extends BaseUIFactory {
        @Override public Button createButton() { return new MacButton(); }
        @Override public CheckBox createCheckBox() { return new MacCheckBox(); }
    }
}
