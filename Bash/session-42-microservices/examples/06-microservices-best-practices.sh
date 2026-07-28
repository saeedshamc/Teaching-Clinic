#!/usr/bin/env bash

echo "=== Microservices Best Practices ==="

# 1. Service Design
echo -e "\n1. Service Design:"
echo "✓ Single responsibility per service"
echo "✓ Define bounded contexts"
echo "✓ API-first design approach"
echo "✓ Keep services small and focused"

# Example of good service design
good_service_design() {
    echo -e "\nExample - User Service:"
    echo "  Responsibilities:"
    echo "    - User registration"
    echo "    - Authentication"
    echo "    - Profile management"
    echo "  NOT responsible for:"
    echo "    - Order processing"
    echo "    - Payment processing"
}

# 2. Communication
echo -e "\n2. Communication:"
echo "✓ Use appropriate protocols (REST/gRPC/events)"
echo "✓ Implement retry logic"
echo "✓ Use circuit breakers"
echo "✓ Prefer async communication"

# Example of communication pattern
communication_pattern() {
    echo -e "\nCommunication Pattern:"
    echo "  Synchronous: REST/gRPC for queries"
    echo "  Asynchronous: Message queues for commands"
    echo "  Events: Event bus for notifications"
}

# 3. Data Management
echo -e "\n3. Data Management:"
echo "✓ Database per service"
echo "✓ Eventual consistency where needed"
echo "✓ Event sourcing for audit trail"
echo "✓ Careful with shared databases"

# Example of data strategy
data_strategy() {
    echo -e "\nData Strategy:"
    echo "  User Service: user_db"
    echo "  Order Service: order_db"
    echo "  Product Service: product_db"
    echo "  Sync via events (not direct DB access)"
}

# 4. Deployment
echo -e "\n4. Deployment:"
echo "✓ Independent deployment"
echo "✓ Blue-green deployment"
echo "✓ Canary releases"
echo "✓ Feature flags"

# Example of deployment strategy
deployment_strategy() {
    echo -e "\nDeployment Strategy:"
    echo "  1. Deploy to staging"
    echo "  2. Run integration tests"
    echo "  3. Canary to production (10%)"
    echo "  4. Monitor metrics"
    echo "  5. Full rollout if OK"
}

# 5. Observability
echo -e "\n5. Observability:"
echo "✓ Distributed tracing (Jaeger/Zipkin)"
echo "✓ Centralized logging (ELK)"
echo "✓ Metrics collection (Prometheus)"
echo "✓ Real-time monitoring"

# Example of observability setup
observability_setup() {
    echo -e "\nObservability Stack:"
    echo "  Logging: ELK Stack"
    echo "  Metrics: Prometheus + Grafana"
    echo "  Tracing: Jaeger"
    echo "  Alerts: Alertmanager"
}

# Execute examples
good_service_design
communication_pattern
data_strategy
deployment_strategy
observability_setup
