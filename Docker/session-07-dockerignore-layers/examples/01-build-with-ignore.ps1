$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 07: dockerignore ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$app = Join-Path $PSScriptRoot "app"
$tag = "clinic-s07:latest"
docker build -t $tag $app
$hasSecret = docker run --rm $tag ls /usr/share/nginx/html/secret.tmp 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "FAIL: secret.tmp should be ignored"
    exit 1
}
Write-Host "OK: secret.tmp not in image (ignored by .dockerignore)"
docker run --rm $tag cat /usr/share/nginx/html/note.txt
Write-Host "Done."
