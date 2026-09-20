$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\check.ps1"
. $lib

Write-Host "=== Docker session 05: bridge DNS ==="
if (-not (Test-ClinicDocker)) { exit 1 }

$net = "clinic-s05-net"
$a = "clinic-s05-a"
$b = "clinic-s05-b"

docker network rm $net 2>$null | Out-Null
docker rm -f $a $b 2>$null | Out-Null
docker network create $net | Out-Null

docker run -d --name $a --network $net alpine:3.20 sleep 60
docker run --rm --name $b --network $net alpine:3.20 ping -c 3 $a

docker rm -f $a | Out-Null
docker network rm $net | Out-Null
Write-Host "Done."
