$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 08: multistage ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$app = Join-Path $PSScriptRoot "app"
$tag = "clinic-s08:latest"
docker build -t $tag $app
docker run --rm $tag cat /usr/share/nginx/html/index.html
Write-Host ""
docker images $tag --format "Size: {{.Size}}"
Write-Host "Done."
