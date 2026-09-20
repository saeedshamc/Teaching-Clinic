$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 13: restart + volume ==="
if (-not (Test-ClinicDocker)) { exit 1 }

Set-Location $PSScriptRoot
docker compose -f compose.yml up -d
Start-Sleep -Seconds 3
docker compose -f compose.yml logs reader
docker compose -f compose.yml ps
Write-Host "Down without -v keeps named volume"
docker compose -f compose.yml down
docker volume ls --filter "name=clinic_s13"
Write-Host "Optional cleanup later: docker volume rm <name>"
Write-Host "Done."
