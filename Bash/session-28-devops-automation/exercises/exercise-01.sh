#!/usr/bin/env bash

# تمرین ۱: Config Management
# هدف: یک اسکریپت بنویسید که:
# 1. config برای multiple environments ایجاد کند
# 2. config را validate کند
# 3. config را اعمال کند

# راه‌حل:
echo "=== Config Management ==="

# تابع ایجاد config
create_config() {
    local env=$1
    local config_file="/tmp/app_${env}.conf"
    
    echo "ایجاد config برای $env"
    
    case $env in
        dev)
            cat > "$config_file" << EOF
APP_ENV=development
APP_DEBUG=true
APP_DB_HOST=localhost
APP_DB_PORT=5432
APP_LOG_LEVEL=debug
EOF
            ;;
        staging)
            cat > "$config_file" << EOF
APP_ENV=staging
APP_DEBUG=false
APP_DB_HOST=staging-db.example.com
APP_DB_PORT=5432
APP_LOG_LEVEL=info
EOF
            ;;
        prod)
            cat > "$config_file" << EOF
APP_ENV=production
APP_DEBUG=false
APP_DB_HOST=prod-db.example.com
APP_DB_PORT=5432
APP_LOG_LEVEL=warning
EOF
            ;;
    esac
    
    echo "✓ Config ایجاد شد: $config_file"
}

# تابع validate config
validate_config() {
    local config_file=$1
    
    echo "اعتبارسنجی config: $config_file"
    
    if [ ! -f "$config_file" ]; then
        echo "✗ فایل config وجود ندارد"
        return 1
    fi
    
    source "$config_file"
    
    local required_vars=("APP_ENV" "APP_DB_HOST" "APP_DB_PORT")
    local valid=true
    
    for var in "${required_vars[@]}"; do
        if [ -z "${!var}" ]; then
            echo "✗ متغیر $name تنظیم نشده است"
            valid=false
        fi
    done
    
    if [ "$valid" = true ]; then
        echo "✓ Config معتبر است"
        return 0
    else
        echo "✗ Config نامعتبر است"
        return 1
    fi
}

# تابع اعمال config
apply_config() {
    local env=$1
    local config_file="/tmp/app_${env}.conf"
    
    echo "اعمال config برای $env"
    
    if validate_config "$config_file"; then
        source "$config_file"
        echo "✓ Config اعمال شد"
        echo "Environment: $APP_ENV"
        echo "DB Host: $APP_DB_HOST"
        echo "DB Port: $APP_DB_PORT"
        echo "Log Level: $APP_LOG_LEVEL"
        
        # پاکسازی
        rm "$config_file"
        return 0
    else
        return 1
    fi
}

# اجرا برای همه environments
for env in dev staging prod; do
    echo -e "\n=== Environment: $env ==="
    create_config "$env"
    apply_config "$env"
done
