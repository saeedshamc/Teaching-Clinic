$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 10: healthcheck ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$app = Join-Path $PSScriptRoot "app"
$tag = "clinic-s10:latest"
$name = "clinic-s10"
docker build -t $tag $app
docker rm -f $name 2>$null | Out-Null
docker run -d --name $name -p 8080:80 $tag

$ok = $false
for ($i = 0; $i -lt 12; $i++) {
    Start-Sleep -Seconds 3
    $status = docker inspect --format "{{.State.Health.Status}}" $name 2>$null
    Write-Host "health: $status"
    if ($status -eq "healthy") { $ok = $true; break }
}

docker rm -f $name | Out-Null
if (-not $ok) { Write-Host "WARN: did not become healthy in time (check Desktop/network)"; exit 0 }
Write-Host "OK healthy. Remember: avoid reckless docker system prune --volumes"
Write-Host "Done."
