#!/usr/bin/env bash

echo "=== Log Levels ==="

# تابع log با level
log() {
    local level=$1
    shift
    local message="$@"
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] $message"
}

# توابع log با levels مختلف
log_debug() {
    log "DEBUG" "$@"
}

log_info() {
    log "INFO" "$@"
}

log_warning() {
    log "WARNING" "$@"
}

log_error() {
    log "ERROR" "$@"
}

log_critical() {
    log "CRITICAL" "$@"
}

# استفاده
log_debug "Debug information"
log_info "Application started"
log_warning "Memory usage high"
log_error "Database connection failed"
log_critical "System crash imminent"
