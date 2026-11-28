// ChainExample.java — Chain of Responsibility demo
// قابل کامپایل با javac — بدون dependency
public class ChainExample {

    public static void main(String[] args) {
        // Build chain: Auth → Validation → Logging → Response
        Handler chain = new AuthHandler()
            .setNext(new ValidationHandler())
            .setNext(new LoggingHandler())
            .setNext(new ResponseHandler());

        // Valid request
        chain.handle(new Request(true, true, "GET /api/users"));

        // Invalid request — auth fails
        chain.handle(new Request(false, true, "GET /api/users"));

        // Invalid request — validation fails
        chain.handle(new Request(true, false, "POST /api/users"));
    }

    static class Request {
        private final boolean authenticated;
        private final boolean valid;
        private final String endpoint;

        Request(boolean authenticated, boolean valid, String endpoint) {
            this.authenticated = authenticated;
            this.valid = valid;
            this.endpoint = endpoint;
        }

        boolean isAuthenticated() { return authenticated; }
        boolean isValid() { return valid; }
        String getEndpoint() { return endpoint; }
    }

    abstract static class Handler {
        protected Handler next;

        public Handler setNext(Handler next) {
            this.next = next;
            return this;
        }

        public abstract void handle(Request request);
    }

    static class AuthHandler extends Handler {
        @Override public void handle(Request request) {
            System.out.println("[AuthHandler] Checking auth...");
            if (!request.isAuthenticated()) {
                System.out.println("[AuthHandler] Auth failed!");
                return;
            }
            System.out.println("[AuthHandler] Auth OK");
            if (next != null) next.handle(request);
        }
    }

    static class ValidationHandler extends Handler {
        @Override public void handle(Request request) {
            System.out.println("[ValidationHandler] Checking validation...");
            if (!request.isValid()) {
                System.out.println("[ValidationHandler] Validation failed!");
                return;
            }
            System.out.println("[ValidationHandler] Validation OK");
            if (next != null) next.handle(request);
        }
    }

    static class LoggingHandler extends Handler {
        @Override public void handle(Request request) {
            System.out.println("[LoggingHandler] Logging: " + request.getEndpoint());
            if (next != null) next.handle(request);
        }
    }

    static class ResponseHandler extends Handler {
        @Override public void handle(Request request) {
            System.out.println("[ResponseHandler] Sending response for: " + request.getEndpoint());
        }
    }
}
