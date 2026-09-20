$ErrorActionPreference = "Stop"
$lib = Join-Path $PSScriptRoot "..\..\_lib\sandbox.ps1"
. $lib

$path = New-ClinicSandbox -SessionId "08"
Initialize-ClinicRepo -Path $path
Set-Location $path

Set-Content -Path "story.txt" -Value "line from main`n" -Encoding utf8
git add story.txt
git commit -m "chore: story on main"

git switch -c feature/edit
Set-Content -Path "story.txt" -Value "line from feature`n" -Encoding utf8
git add story.txt
git commit -m "feat: feature edits story"

git switch main
Set-Content -Path "story.txt" -Value "line from main changed`n" -Encoding utf8
git add story.txt
git commit -m "chore: main edits story"

Write-Host "=== merging (expect conflict) ==="
$merge = git merge feature/edit 2>&1
Write-Host $merge
Write-Host "=== conflicted file ==="
Get-Content story.txt

# resolve in favor of a combined line
Set-Content -Path "story.txt" -Value "resolved: main + feature`n" -Encoding utf8
git add story.txt
git commit -m "merge: resolve story conflict"
git log --oneline --graph -5

Write-Host "Done in $path"
