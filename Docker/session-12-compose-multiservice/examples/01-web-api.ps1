$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 12: web + api ==="
if (-not (Test-ClinicDocker)) { exit 1 }

Set-Location $PSScriptRoot
docker compose -f compose.yml up -d --build
Start-Sleep -Seconds 3
docker compose -f compose.yml ps
try {
    $home = Invoke-WebRequest -Uri "http://localhost:8080/" -UseBasicParsing
    Write-Host "web status:" $home.StatusCode
    $api = Invoke-WebRequest -Uri "http://localhost:8080/api/" -UseBasicParsing
    Write-Host "api via proxy:" $api.Content
} catch {
    Write-Host "WARN: HTTP check failed:" $_.Exception.Message
}
docker compose -f compose.yml down
Write-Host "Done."
