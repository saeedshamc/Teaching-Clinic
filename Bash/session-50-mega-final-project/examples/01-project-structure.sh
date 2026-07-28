#!/usr/bin/env bash

echo "=== Project Structure ==="

# تابع create project structure
create_project_structure() {
    local project_name=$1
    
    echo "1. Creating project structure for $project_name"
    
    mkdir -p "$project_name"/{src/{api,database,utils},tests/{unit,integration,e2e},docs/{api,guides},config/{dev,staging,prod},scripts/{deploy,monitor,maintenance},infrastructure/{terraform,ansible,k8s},logs}
    
    echo "✓ Project structure created"
}

# تابع create configuration files
create_config_files() {
    local project=$1
    
    echo -e "\n2. Creating configuration files:"
    
    # Main config
    cat > "$project/config/config.yml" << 'EOF'
# Main Configuration
app:
  name: devops-platform
  version: 1.0.0
  environment: development

database:
  host: localhost
  port: 5432
  name: devops_db
  user: admin
  password: ${DB_PASSWORD}

api:
  host: 0.0.0.0
  port: 8080
  workers: 4

logging:
  level: INFO
  file: logs/app.log
  max_size: 100MB
  backup_count: 10
EOF
    
    echo "  ✓ config.yml created"
    
    # Dev config
    cat > "$project/config/dev/config.yml" << 'EOF'
environment: development
debug: true
database:
  host: localhost
api:
  port: 8080
EOF
    
    echo "  ✓ dev/config.yml created"
    
    # Staging config
    cat > "$project/config/staging/config.yml" << 'EOF'
environment: staging
debug: false
database:
  host: staging-db.example.com
api:
  port: 8080
EOF
    
    echo "  ✓ staging/config.yml created"
    
    # Prod config
    cat > "$project/config/prod/config.yml" << 'EOF'
environment: production
debug: false
database:
  host: prod-db.example.com
api:
  port: 8080
EOF
    
    echo "  ✓ prod/config.yml created"
}

# تابع create Docker files
create_docker_files() {
    local project=$1
    
    echo -e "\n3. Creating Docker files:"
    
    cat > "$project/Dockerfile" << 'EOF'
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    bash \
    curl \
    jq \
    python3 \
    python3-pip

WORKDIR /app

COPY src/ ./src/
COPY config/ ./config/

CMD ["bash", "src/main.sh"]
EOF
    
    echo "  ✓ Dockerfile created"
    
    cat > "$project/docker-compose.yml" << 'EOF'
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    volumes:
      - ./config:/app/config
      - ./logs:/app/logs
    environment:
      - ENVIRONMENT=development
  
  database:
    image: postgres:14
    environment:
      POSTGRES_DB: devops_db
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data

volumes:
  db_data:
EOF
    
    echo "  ✓ docker-compose.yml created"
}

# تابع create Makefile
create_makefile() {
    local project=$1
    
    echo -e "\n4. Creating Makefile:"
    
    cat > "$project/Makefile" << 'EOF'
.PHONY: help build test deploy clean

help:
	@echo "Available commands:"
	@echo "  make build   - Build the application"
	@echo "  make test    - Run tests"
	@echo "  make deploy  - Deploy to environment"
	@echo "  make clean   - Clean build artifacts"

build:
	@echo "Building application..."
	@docker-compose build

test:
	@echo "Running tests..."
	@bash scripts/run-tests.sh

deploy:
	@echo "Deploying application..."
	@bash scripts/deploy.sh

clean:
	@echo "Cleaning build artifacts..."
	@rm -rf build/
	@docker-compose down -v
EOF
    
    echo "  ✓ Makefile created"
}

# تابع show structure
show_structure() {
    local project=$1
    
    echo -e "\n5. Project structure:"
    tree "$project" 2>/dev/null || find "$project" -type d | head -20
}

# استفاده
PROJECT_NAME="devops-platform"

create_project_structure "$PROJECT_NAME"
create_config_files "$PROJECT_NAME"
create_docker_files "$PROJECT_NAME"
create_makefile "$PROJECT_NAME"
show_structure "$PROJECT_NAME"
