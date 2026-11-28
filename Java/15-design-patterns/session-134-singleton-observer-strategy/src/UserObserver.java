// UserObserver.java — Observer pattern با PropertyChangeSupport
// قابل کامپایل با javac — بدون dependency
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserObserver {

    public static void main(String[] args) {
        User user = new User("Ali", "ali@mail.com");

        // Add listeners
        user.addPropertyChangeListener(evt -> {
            System.out.println("[Listener1] " + evt.getPropertyName() +
                " changed: " + evt.getOldValue() + " → " + evt.getNewValue());
        });

        user.addPropertyChangeListener(evt -> {
            if ("name".equals(evt.getPropertyName())) {
                System.out.println("[Listener2] Name updated to: " + evt.getNewValue());
            }
        });

        // Trigger changes
        user.setName("Sara");
        user.setEmail("sara@mail.com");
    }

    static class User {
        private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            pcs.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            pcs.removePropertyChangeListener(listener);
        }

        public void setName(String name) {
            String oldName = this.name;
            this.name = name;
            pcs.firePropertyChange("name", oldName, name);
        }

        public void setEmail(String email) {
            String oldEmail = this.email;
            this.email = email;
            pcs.firePropertyChange("email", oldEmail, email);
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
