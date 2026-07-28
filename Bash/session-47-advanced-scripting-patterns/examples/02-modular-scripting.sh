#!/usr/bin/env bash

echo "=== Modular Scripting ==="

# تابع load library
load_library() {
    local lib=$1
    
    if [ -f "$lib" ]; then
        source "$lib"
        echo "✓ Library loaded: $lib"
    else
        echo "✗ Library not found: $lib"
        return 1
    fi
}

# تابع create module
create_module() {
    local name=$1
    
    cat > "${name}.sh" << EOF
#!/usr/bin/env bash
# Module: $name
# Description: ${name} module for handling ${name} operations

${name}_init() {
    echo "Initializing $name module"
    # Initialization logic
}

${name}_run() {
    echo "Running $name module"
    # Main logic
}

${name}_cleanup() {
    echo "Cleaning up $name module"
    # Cleanup logic
}
EOF
    
    chmod +x "${name}.sh"
    echo "✓ Module created: ${name}.sh"
}

# تابع namespace
namespace() {
    local ns=$1
    local func=$2
    
    echo "Namespace: $ns"
    echo "Function: ${ns}_${func}"
}

# تابع create library
create_library() {
    local lib_name=$1
    
    cat > "${lib_name}.lib.sh" << EOF
#!/usr/bin/env bash
# Library: $lib_name
# Version: 1.0.0

# Utility functions
${lib_name}_log() {
    local level=\$1
    local message=\$2
    echo "[\$level] \$message"
}

${lib_name}_error() {
    local message=\$1
    ${lib_name}_log "ERROR" "\$message" >&2
}

${lib_name}_info() {
    local message=\$1
    ${lib_name}_log "INFO" "\$message"
}
EOF
    
    echo "✓ Library created: ${lib_name}.lib.sh"
}

# تابع import functions
import_functions() {
    local source_file=$1
    local prefix=$2
    
    if [ -f "$source_file" ]; then
        while IFS= read -r line; do
            if [[ $line =~ ^([a-zA-Z_][a-zA-Z0-9_]*)\(\) ]]; then
                local func="${BASH_REMATCH[1]}"
                echo "Imported: ${prefix}_${func}"
            fi
        done < "$source_file"
    fi
}

# استفاده
echo "1. Creating modules:"
create_module "logger"
create_module "database"
create_module "api"

echo -e "\n2. Creating library:"
create_library "utils"

echo -e "\n3. Namespace management:"
namespace "logger" "log"
namespace "database" "connect"

echo -e "\n4. Import functions:"
import_functions "logger.sh" "myapp"
