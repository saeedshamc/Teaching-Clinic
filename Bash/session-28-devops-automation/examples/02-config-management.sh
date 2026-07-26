#!/usr/bin/env bash

echo "=== Configuration Management ==="

# تابع اعمال config
apply_config() {
    local env=$1
    local config_file="/tmp/config_${env}.conf"
    
    echo "اعمال config برای $env"
    
    # ایجاد config بر اساس environment
    case $env in
        dev)
            cat > "$config_file" << EOF
ENV=development
DB_HOST=localhost
DB_PORT=5432
DEBUG=true
EOF
            ;;
        prod)
            cat > "$config_file" << EOF
ENV=production
DB_HOST=prod-db.example.com
DB_PORT=5432
DEBUG=false
EOF
            ;;
    esac
    
    echo "✓ Config اعمال شد"
    cat "$config_file"
    
    # پاکسازی
    rm "$config_file"
}

# تابع validate config
validate_config() {
    local config_file=$1
    
    echo "اعتبارسنجی config..."
    
    if [ -f "$config_file" ]; then
        source "$config_file"
        
        if [ -z "$ENV" ] || [ -z "$DB_HOST" ]; then
            echo "✗ Config نامعتبر است"
            return 1
        else
            echo "✓ Config معتبر است"
            return 0
        fi
    else
        echo "✗ فایل config وجود ندارد"
        return 1
    fi
}

# اجرا
apply_config "dev"
apply_config "prod"
