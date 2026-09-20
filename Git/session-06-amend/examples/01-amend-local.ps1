$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "06"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "a.txt" -Value "a`n" -Encoding utf8
git add a.txt
git commit -m "feat: add a (typo message)"
git log --oneline -1

Set-Content -Path "b.txt" -Value "forgot`n" -Encoding utf8
git add b.txt
git commit --amend -m "feat: add a and b"
git log --oneline -1
git show --stat --oneline HEAD

Write-Host "Amended only inside sandbox: $path"
Write-Host "Never amend+force onto shared main of Teaching Clinic."
