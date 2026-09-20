$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 14: debug flow ==="
if (-not (Test-ClinicDocker)) { exit 1 }

Set-Location $PSScriptRoot
docker compose -f compose.yml up -d --build
Write-Host "-- ps --"
docker compose -f compose.yml ps
Write-Host "-- logs (tail) --"
docker compose -f compose.yml logs --tail 20
Write-Host "-- exec ls --"
docker compose -f compose.yml exec -T web ls /usr/share/nginx/html
docker compose -f compose.yml down
Write-Host "Debug checklist printed via real commands. Done."
