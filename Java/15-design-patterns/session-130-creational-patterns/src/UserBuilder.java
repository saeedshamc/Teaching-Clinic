// UserBuilder.java — Builder pattern demo
// قابل کامپایل با javac — بدون dependency
public class UserBuilder {

    public static void main(String[] args) {
        // Builder — fluent API
        User user = new User.Builder()
            .name("Ali")
            .email("ali@mail.com")
            .age(25)
            .phone("+1234567890")
            .build();

        System.out.println(user);
    }

    static class User {
        private final String name;
        private final String email;
        private final int age;
        private final String phone;

        private User(Builder builder) {
            this.name = builder.name;
            this.email = builder.email;
            this.age = builder.age;
            this.phone = builder.phone;
        }

        public static class Builder {
            private String name;
            private String email;
            private int age;
            private String phone;

            public Builder name(String name) { this.name = name; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder age(int age) { this.age = age; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }

            public User build() {
                if (name == null || name.isBlank()) {
                    throw new IllegalStateException("Name is required");
                }
                return new User(this);
            }
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', email='%s', age=%d, phone='%s'}",
                name, email, age, phone);
        }
    }
}
