// CICDPipeline.java — پاسخ کامل پروژهٔ جلسه ۱۴۵
// CI/CD pipeline demo
public class CICDPipeline {

    public static void main(String[] args) {
        System.out.println("=== CI/CD Pipeline Demo ===");
        System.out.println("Stages:");
        System.out.println("  1. Checkout");
        System.out.println("  2. Build (mvn clean package)");
        System.out.println("  3. Test (mvn test)");
        System.out.println("  4. Docker Build (docker build)");
        System.out.println("  5. Docker Push (docker push)");
        System.out.println("  6. Deploy");

        System.out.println("\nPipeline completed successfully!");
        System.out.println("Image: myapp:latest");
        System.out.println("Deployed to: production");
    }
}
