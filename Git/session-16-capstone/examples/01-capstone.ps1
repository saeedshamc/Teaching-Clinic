$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$root = New-ClinicSandbox -SessionId "16"
$server = Join-Path $root "server.git"
$work = Join-Path $root "work"
$verify = Join-Path $root "verify"

git init --bare $server | Out-Null
New-Item -ItemType Directory -Path $work | Out-Null
Set-Location $work
git init -b main | Out-Null
git config user.name "Capstone"
git config user.email "capstone@clinic.local"
Set-Content -Path "README.md" -Value "# capstone`n" -Encoding utf8
git add README.md
git commit -m "chore: start"
git remote add origin $server
git push -u origin main

git switch -c feature/extra
Set-Content -Path "extra.txt" -Value "done`n" -Encoding utf8
git add extra.txt
git commit -m "feat: extra"
git push -u origin feature/extra

git switch main
git merge feature/extra
git tag v0.1.0
git push origin main
git push origin v0.1.0

git clone $server $verify
Set-Location $verify
Write-Host "=== verify clone ==="
git log --oneline --decorate -5
git tag -l

Write-Host "Capstone OK under $root"
Write-Host "This never touched Teaching Clinic history."
