$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "09"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "base.txt" -Value "base`n" -Encoding utf8
git add base.txt
git commit -m "chore: base"

git switch -c feature/x
Set-Content -Path "feat.txt" -Value "feat`n" -Encoding utf8
git add feat.txt
git commit -m "feat: add feat.txt"

git switch main
Set-Content -Path "main-only.txt" -Value "main`n" -Encoding utf8
git add main-only.txt
git commit -m "chore: main moves ahead"

git switch feature/x
git rebase main
Write-Host "=== after rebase ==="
git log --oneline --graph --all

Write-Host "Done in $path (local only)"
