// TemplateEngine.java — پاسخ پروژهٔ جلسه ۵۴
record Template(String name, String content) {}

public class TemplateEngine {

    static Template getTemplate(String type, String name) {
        return switch (type) {
            case "welcome" -> new Template("welcome", """
                <html>
                    <body>
                        <h1>Welcome, %s!</h1>
                        <p>Your account is ready.</p>
                    </body>
                </html>
                """.formatted(name));
            case "error" -> new Template("error", """
                <html>
                    <body>
                        <h1>Error</h1>
                        <p>%s</p>
                    </body>
                </html>
                """.formatted(name));
            case "success" -> new Template("success", """
                <html>
                    <body>
                        <h1>Success!</h1>
                        <p>%s completed successfully.</p>
                    </body>
                </html>
                """.formatted(name));
            default -> new Template("default", """
                <html>
                    <body>
                        <p>Unknown template: %s</p>
                    </body>
                </html>
                """.formatted(type));
        };
    }

    public static void main(String[] args) {
        // welcome template
        Template welcome = getTemplate("welcome", "Ali");
        System.out.println("=== " + welcome.name() + " ===");
        System.out.println(welcome.content());

        // error template
        Template error = getTemplate("error", "Invalid credentials");
        System.out.println("\n=== " + error.name() + " ===");
        System.out.println(error.content());

        // success template
        Template success = getTemplate("success", "Registration");
        System.out.println("\n=== " + success.name() + " ===");
        System.out.println(success.content());

        // default template
        Template def = getTemplate("unknown", "test");
        System.out.println("\n=== " + def.name() + " ===");
        System.out.println(def.content());
    }
}
