$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "10"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "app.txt" -Value "v1`n" -Encoding utf8
git add app.txt
git commit -m "feat: v1"
git tag v0.1.0

Set-Content -Path "app.txt" -Value "wip messy`n" -Encoding utf8
git stash push -m "messy wip"
Write-Host "=== after stash ==="
git status -s
git stash list

git stash pop
Write-Host "=== after pop ==="
git status -s
git tag -l

Write-Host "Done in $path"
