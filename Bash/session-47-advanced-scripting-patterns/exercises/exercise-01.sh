#!/usr/bin/env bash

# تمرین ۱: Modular Script
# هدف: یک اسکریپت بنویسید که:
# 1. module را ایجاد کند
# 2. module را load کند
# 3. functions را call کند

# راه‌حل:
echo "=== Modular Script Exercise ==="

# تابع create logger module
create_logger_module() {
    local module_name="logger"
    
    echo "1. Creating logger module:"
    
    cat > "${module_name}.sh" << 'EOF'
#!/usr/bin/env bash
# Logger Module
# Provides logging functionality

LOG_LEVEL=${LOG_LEVEL:-INFO}
LOG_FILE=${LOG_FILE:-/tmp/app.log}

logger_log() {
    local level=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] $message" | tee -a "$LOG_FILE"
}

logger_info() {
    logger_log "INFO" "$1"
}

logger_warn() {
    logger_log "WARN" "$1"
}

logger_error() {
    logger_log "ERROR" "$1"
}

logger_debug() {
    if [ "$LOG_LEVEL" = "DEBUG" ]; then
        logger_log "DEBUG" "$1"
    fi
}

logger_init() {
    echo "Logger initialized"
    logger_info "Logger module loaded"
}
EOF
    
    chmod +x "${module_name}.sh"
    echo "✓ Logger module created: ${module_name}.sh"
}

# تابع create database module
create_database_module() {
    local module_name="database"
    
    echo -e "\n2. Creating database module:"
    
    cat > "${module_name}.sh" << 'EOF'
#!/usr/bin/env bash
# Database Module
# Provides database connectivity

DB_HOST=${DB_HOST:-localhost}
DB_PORT=${DB_PORT:-5432}
DB_NAME=${DB_NAME:-mydb}

database_connect() {
    echo "Connecting to database at $DB_HOST:$DB_PORT/$DB_NAME"
    # Connection logic
}

database_query() {
    local query=$1
    echo "Executing query: $query"
    # Query logic
}

database_disconnect() {
    echo "Disconnecting from database"
    # Disconnect logic
}

database_init() {
    echo "Database module initialized"
}
EOF
    
    chmod +x "${module_name}.sh"
    echo "✓ Database module created: ${module_name}.sh"
}

# تابع load module
load_module() {
    local module=$1
    
    echo -e "\n3. Loading module: $module"
    
    if [ -f "${module}.sh" ]; then
        source "${module}.sh"
        echo "✓ Module loaded: $module"
        ${module}_init
    else
        echo "✗ Module not found: ${module}.sh"
        return 1
    fi
}

# تابع use logger functions
use_logger() {
    echo -e "\n4. Using logger functions:"
    logger_info "Application started"
    logger_warn "This is a warning"
    logger_error "This is an error"
    logger_debug "Debug information"
}

# تابع use database functions
use_database() {
    echo -e "\n5. Using database functions:"
    database_connect
    database_query "SELECT * FROM users"
    database_disconnect
}

# تابع create main script
create_main_script() {
    echo -e "\n6. Creating main script:"
    
    cat > "main.sh" << 'EOF'
#!/usr/bin/env bash
# Main Application Script

# Load modules
source logger.sh
source database.sh

# Initialize
logger_init
database_init

# Run application
logger_info "Starting application"
database_connect
logger_info "Application finished"
EOF
    
    chmod +x "main.sh"
    echo "✓ Main script created: main.sh"
}

# تابع show module structure
show_structure() {
    echo -e "\n7. Module structure:"
    echo "  ├── logger.sh"
    echo "  │   ├── logger_log()"
    echo "  │   ├── logger_info()"
    echo "  │   ├── logger_warn()"
    echo "  │   ├── logger_error()"
    echo "  │   └── logger_debug()"
    echo "  ├── database.sh"
    echo "  │   ├── database_connect()"
    echo "  │   ├── database_query()"
    echo "  │   └── database_disconnect()"
    echo "  └── main.sh"
}

# اجرا
create_logger_module
create_database_module
load_module "logger"
load_module "database"
use_logger
use_database
create_main_script
show_structure
