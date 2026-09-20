$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 09: ARG / ENV ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$app = Join-Path $PSScriptRoot "app"
$tag = "clinic-s09:latest"
docker build --build-arg APP_TITLE=TeachingClinic -t $tag $app
Write-Host "-- default ENV --"
docker run --rm $tag
Write-Host "-- override ENV --"
docker run --rm -e GREETING=salam $tag
Write-Host "Done."
