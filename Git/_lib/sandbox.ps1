# اشتراک sandbox برای اسکریپت‌های دوره
# استفاده: . $PSScriptRoot\..\..\ _lib\sandbox.ps1  — مسیر از examples درست تنظیم شود

function Get-ClinicSandboxPath {
    param([Parameter(Mandatory = $true)][string]$SessionId)
    return (Join-Path $env:TEMP "clinic-git-$SessionId")
}

function New-ClinicSandbox {
    param([Parameter(Mandatory = $true)][string]$SessionId)
    $path = Get-ClinicSandboxPath -SessionId $SessionId
    if (Test-Path $path) {
        Remove-Item -LiteralPath $path -Recurse -Force
    }
    New-Item -ItemType Directory -Path $path | Out-Null
    return $path
}

function Initialize-ClinicRepo {
    param([Parameter(Mandatory = $true)][string]$Path)
    Push-Location $Path
    try {
        git init -b main | Out-Null
        git config user.name "Clinic Student"
        git config user.email "student@clinic.local"
    }
    finally {
        Pop-Location
    }
}
