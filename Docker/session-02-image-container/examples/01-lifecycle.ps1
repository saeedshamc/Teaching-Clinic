$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 02: lifecycle ==="
if (-not (Test-ClinicDocker)) { exit 1 }

docker pull hello-world
docker run --rm hello-world

Write-Host ""
docker pull alpine:3.20
$name = "clinic-s02-demo"
docker rm -f $name 2>$null | Out-Null
docker run -d --name $name alpine:3.20 sleep 30
docker ps --filter "name=$name"
docker stop $name
docker ps -a --filter "name=$name"
docker rm $name
Write-Host "Removed container $name"
Write-Host "Done."
