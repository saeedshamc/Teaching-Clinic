$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "05"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "note.txt" -Value "clean`n" -Encoding utf8
git add note.txt
git commit -m "feat: add note"

Set-Content -Path "note.txt" -Value "dirty mistake`n" -Encoding utf8
Write-Host "=== before restore ==="
git status -s
git restore note.txt
Write-Host "=== after restore ==="
git status -s
Get-Content note.txt

Set-Content -Path "note.txt" -Value "staged edit`n" -Encoding utf8
git add note.txt
git restore --staged note.txt
Write-Host "=== after unstage ==="
git status -s

Write-Host "Done in $path"
