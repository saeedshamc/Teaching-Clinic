$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 11: compose basics ==="
if (-not (Test-ClinicDocker)) { exit 1 }

Set-Location $PSScriptRoot
docker compose -f compose.yml up -d --build
docker compose -f compose.yml ps
Start-Sleep -Seconds 2
docker compose -f compose.yml down
Write-Host "Done."
