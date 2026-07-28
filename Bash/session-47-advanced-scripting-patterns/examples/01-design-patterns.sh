#!/usr/bin/env bash

echo "=== Design Patterns ==="

# Singleton Pattern
singleton() {
    local instance_file="/tmp/singleton.lock"
    
    if [ -f "$instance_file" ]; then
        echo "Instance already exists"
        return 1
    fi
    
    touch "$instance_file"
    echo "Singleton instance created"
    echo "Instance file: $instance_file"
}

# Factory Pattern
factory() {
    local type=$1
    
    case "$type" in
        "file")
            echo "Creating file handler"
            echo "  Handler: FileHandler"
            echo "  Methods: read, write, delete"
            ;;
        "db")
            echo "Creating database handler"
            echo "  Handler: DatabaseHandler"
            echo "  Methods: connect, query, close"
            ;;
        "api")
            echo "Creating API handler"
            echo "  Handler: APIHandler"
            echo "  Methods: get, post, put, delete"
            ;;
        *)
            echo "Unknown type: $type"
            return 1
            ;;
    esac
}

# Observer Pattern
observer() {
    local event=$1
    local callback=$2
    
    echo "Event: $event"
    echo "Callback: $callback"
    echo "Executing callback..."
    eval "$callback"
}

# Strategy Pattern
strategy() {
    local strategy=$1
    local data=$2
    
    case "$strategy" in
        "sort_asc")
            echo "Sorting ascending: $data"
            ;;
        "sort_desc")
            echo "Sorting descending: $data"
            ;;
        "filter")
            echo "Filtering: $data"
            ;;
    esac
}

# Builder Pattern
builder() {
    local object=""
    
    add_property() {
        local prop=$1
        local value=$2
        object+="$prop=$value "
    }
    
    add_property "name" "test"
    add_property "type" "object"
    add_property "id" "123"
    
    echo "Built object: $object"
}

# استفاده
echo "1. Singleton Pattern:"
singleton
singleton

echo -e "\n2. Factory Pattern:"
factory "file"
factory "db"
factory "api"

echo -e "\n3. Observer Pattern:"
observer "data_changed" "echo 'Data updated'"

echo -e "\n4. Strategy Pattern:"
strategy "sort_asc" "data1,data2,data3"

echo -e "\n5. Builder Pattern:"
builder
