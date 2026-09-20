$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$root = New-ClinicSandbox -SessionId "12"
$server = Join-Path $root "server.git"
$work = Join-Path $root "work"

git init --bare $server | Out-Null
New-Item -ItemType Directory -Path $work | Out-Null
Set-Location $work
git init -b main | Out-Null
git config user.name "Dev"
git config user.email "dev@clinic.local"
Set-Content -Path "app.txt" -Value "stable`n" -Encoding utf8
git add app.txt
git commit -m "chore: stable main"
git remote add origin $server
git push -u origin main

git switch -c feature/note
Set-Content -Path "note.txt" -Value "feature work`n" -Encoding utf8
git add note.txt
git commit -m "feat: add note"
git push -u origin feature/note

git switch main
git merge feature/note
git push origin main
git log --oneline --graph --all -5

Write-Host "Done under $root"
