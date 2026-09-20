$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "07"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "main.txt" -Value "main`n" -Encoding utf8
git add main.txt
git commit -m "chore: start on main"

git switch -c feature/hello
Set-Content -Path "hello.txt" -Value "hello from feature`n" -Encoding utf8
git add hello.txt
git commit -m "feat: add hello"

git switch main
Write-Host "=== branches ==="
git branch
Write-Host "=== graph ==="
git log --oneline --all --graph

Write-Host "Done in $path"
