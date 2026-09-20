$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$root = New-ClinicSandbox -SessionId "11"
$server = Join-Path $root "server.git"
$alice = Join-Path $root "alice"
$bob = Join-Path $root "bob"

git init --bare $server | Out-Null
New-Item -ItemType Directory -Path $alice | Out-Null
Set-Location $alice
git init -b main | Out-Null
git config user.name "Alice"
git config user.email "alice@clinic.local"
Set-Content -Path "readme.txt" -Value "hello remote`n" -Encoding utf8
git add readme.txt
git commit -m "docs: hello"
git remote add origin $server
git push -u origin main

git clone $server $bob
Set-Location $bob
git config user.name "Bob"
git config user.email "bob@clinic.local"
Write-Host "=== bob log ==="
git log --oneline

Set-Location $alice
Add-Content -Path "readme.txt" -Value "alice update`n"
git add readme.txt
git commit -m "docs: alice update"
git push

Set-Location $bob
git pull
Get-Content readme.txt

Write-Host "Done under $root"
