$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 01: check ==="
if (-not (Test-ClinicDocker)) { exit 1 }

docker version
Write-Host ""
docker info --format "Server Version: {{.ServerVersion}} | OS: {{.OperatingSystem}}"
Write-Host ""
Write-Host "OK — daemon reachable. Do not experiment on Teaching Clinic apps."
