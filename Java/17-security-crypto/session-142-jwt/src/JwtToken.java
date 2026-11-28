// JwtToken.java — JWT token model
// قابل کامپایل با javac — بدون dependency
public class JwtToken {

    private final String token;
    private final String username;
    private final long expiration;

    public JwtToken(String token, String username, long expiration) {
        this.token = token;
        this.username = username;
        this.expiration = expiration;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public long getExpiration() { return expiration; }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiration;
    }

    @Override
    public String toString() {
        return String.format("JwtToken{username='%s', expiration=%d}", username, expiration);
    }
}
