$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "04"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path ".gitignore" -Value "secret.env`n*.log`n" -Encoding utf8
Set-Content -Path "secret.env" -Value "PASSWORD=demo`n" -Encoding utf8
Set-Content -Path "app.log" -Value "noise`n" -Encoding utf8
Set-Content -Path "readme.txt" -Value "ok`n" -Encoding utf8

git add .
git status
git commit -m "chore: add gitignore and readme"
Write-Host "secret.env and app.log should NOT appear as committed files:"
git ls-files

Write-Host "Done in $path"
