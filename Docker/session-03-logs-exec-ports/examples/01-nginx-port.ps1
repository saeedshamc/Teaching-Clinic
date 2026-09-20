$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 03: logs / exec / ports ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$name = "clinic-s03-nginx"
docker rm -f $name 2>$null | Out-Null
docker pull nginx:alpine
docker run -d --name $name -p 8080:80 nginx:alpine
Start-Sleep -Seconds 2
docker logs $name
Write-Host ""
docker exec $name ls /usr/share/nginx/html
Write-Host ""
Write-Host "Open http://localhost:8080 then press Enter to clean up..."
Read-Host | Out-Null
docker rm -f $name | Out-Null
Write-Host "Done."
