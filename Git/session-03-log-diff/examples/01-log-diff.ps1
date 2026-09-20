$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "03"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "app.txt" -Value "v1`n" -Encoding utf8
git add app.txt
git commit -m "feat: add app.txt"

Add-Content -Path "app.txt" -Value "v2 line`n"
Write-Host "=== git diff (unstaged) ==="
git diff
git add app.txt
Write-Host "=== git diff --staged ==="
git diff --staged
git commit -m "feat: bump app to v2"
Write-Host "=== git log --oneline ==="
git log --oneline
Write-Host "=== git show HEAD --stat ==="
git show HEAD --stat

Write-Host "Done in $path"
