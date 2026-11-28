// GatewayConfig.java — API Gateway configuration
// نیازمند: spring-cloud-starter-gateway jar برای کامپایل
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.*;

public class GatewayConfig {

    // @Bean
    // public RouteLocator routes(RouteLocatorBuilder builder) {
    //     return builder.routes()
    //         .route("user-service", r -> r.path("/api/users/**")
    //             .uri("http://user-service:8081"))
    //         .route("product-service", r -> r.path("/api/products/**")
    //             .uri("http://product-service:8082"))
    //         .route("order-service", r -> r.path("/api/orders/**")
    //             .uri("http://order-service:8083"))
    //         .build();
    // }

    public static void main(String[] args) {
        System.out.println("API Gateway routes:");
        System.out.println("  /api/users/** → http://user-service:8081");
        System.out.println("  /api/products/** → http://product-service:8082");
        System.out.println("  /api/orders/** → http://order-service:8083");
    }
}
