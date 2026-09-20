$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "02"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "README.md" -Value "# sandbox 02`n" -Encoding utf8
git status
git add README.md
git commit -m "docs: add README"
git status
git log --oneline

Write-Host ""
Write-Host "Done in $path"
