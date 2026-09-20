$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 16: capstone ==="
if (-not (Test-ClinicDocker)) { exit 1 }

Set-Location $PSScriptRoot
docker compose -f compose.yml up -d --build
Start-Sleep -Seconds 4
docker compose -f compose.yml ps
try {
    Invoke-WebRequest -Uri "http://localhost:8080/" -UseBasicParsing | Out-Null
    $a = Invoke-WebRequest -Uri "http://localhost:8080/api/" -UseBasicParsing
    $b = Invoke-WebRequest -Uri "http://localhost:8080/api/" -UseBasicParsing
    Write-Host "first:" $a.Content
    Write-Host "second:" $b.Content
} catch {
    Write-Host "WARN:" $_.Exception.Message
}
docker compose -f compose.yml down
Write-Host "Capstone OK (volume may remain). Done."
