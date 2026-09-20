$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 04: named volume ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$vol = "clinic-s04-data"
$w = "clinic-s04-write"
$r = "clinic-s04-read"

docker volume create $vol | Out-Null
docker rm -f $w $r 2>$null | Out-Null

docker run --rm --name $w -v "${vol}:/data" alpine:3.20 sh -c "echo hello-from-volume > /data/note.txt"
docker run --rm --name $r -v "${vol}:/data" alpine:3.20 cat /data/note.txt

Write-Host ""
docker volume ls --filter "name=$vol"
Write-Host "Cleanup: docker volume rm $vol"
Write-Host "Done."
