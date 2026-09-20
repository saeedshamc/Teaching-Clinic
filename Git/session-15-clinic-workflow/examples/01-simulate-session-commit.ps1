$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "15"
Initialize-ClinicRepo -Path $path
Set-Location $path

New-Item -ItemType Directory -Path "session-01" | Out-Null
Set-Content -Path "session-01/README.md" -Value "# session 01`n" -Encoding utf8
git add session-01/README.md
git status
git commit -m "Git: session-01 — intro three areas"

New-Item -ItemType Directory -Path "session-02" | Out-Null
Set-Content -Path "session-02/README.md" -Value "# session 02`n" -Encoding utf8
git add session-02/README.md
git commit -m "Git: session-02 — init and commit"

Set-Content -Path "README.md" -Value "# course`n" -Encoding utf8
Set-Content -Path "SYLLABUS.md" -Value "# syllabus`n" -Encoding utf8
git add README.md SYLLABUS.md
git commit -m "Git: finalize docs — README + SYLLABUS + SETUP"

git log --oneline
Write-Host "Simulated Clinic workflow in $path"
