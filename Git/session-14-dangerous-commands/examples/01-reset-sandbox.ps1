$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "14"
Initialize-ClinicRepo -Path $path
Set-Location $path

1..3 | ForEach-Object {
    Set-Content -Path "f$_.txt" -Value "$_`n" -Encoding utf8
    git add "f$_.txt"
    git commit -m "chore: add f$_"
}

git branch safety
Write-Host "=== before resets ==="
git log --oneline

git reset --soft HEAD~1
Write-Host "=== after --soft ==="
git status -s
git log --oneline -3

git commit -m "chore: add f3 again"
git reset --mixed HEAD~1
Write-Host "=== after --mixed ==="
git status -s

git add f3.txt
git commit -m "chore: add f3 third time"
git reset --hard HEAD~1
Write-Host "=== after --hard ==="
git status -s
git log --oneline

Write-Host "safety branch still has old tip:"
git log --oneline safety -3
Write-Host "NEVER run these resets/force-push on Teaching Clinic main."
Write-Host "Done in $path"
