$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 06: build & run ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$app = Join-Path $PSScriptRoot "app"
$tag = "clinic-s06:latest"
docker build -t $tag $app
docker rm -f clinic-s06 2>$null | Out-Null
docker run -d --name clinic-s06 -p 8080:80 $tag
Start-Sleep -Seconds 1
Write-Host "Listening on http://localhost:8080 — cleaning up in 3s..."
Start-Sleep -Seconds 3
docker rm -f clinic-s06 | Out-Null
Write-Host "Done. Image kept as $tag (docker rmi $tag to remove)."
