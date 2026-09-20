# Helper shared by Docker course example scripts (PowerShell).
# Does not modify Teaching Clinic app projects.

function Test-ClinicDocker {
    if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
        Write-Host "ERROR: docker CLI not found. See Docker/SETUP.md"
        return $false
    }
    docker info 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "ERROR: Docker daemon not reachable. Start Docker Desktop / service."
        return $false
    }
    return $true
}

function Get-ClinicDockerTag {
    param([Parameter(Mandatory = $true)][string]$SessionId)
    return "clinic-docker-$SessionId"
}
