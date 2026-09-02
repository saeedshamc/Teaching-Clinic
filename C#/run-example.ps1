# اجرای سریع یک فایل مثال C# بدون ساخت پروژه دائمی
param(
    [Parameter(Mandatory = $true)]
    [string]$File
)

if (-not (Test-Path $File)) {
    Write-Error "فایل پیدا نشد: $File"
    exit 1
}

$tempDir = Join-Path $env:TEMP ("csharp-run-" + [guid]::NewGuid().ToString())
New-Item -ItemType Directory -Path $tempDir | Out-Null

try {
    dotnet new console -o $tempDir -f net8.0 --force | Out-Null
    Copy-Item $File (Join-Path $tempDir "Program.cs") -Force
    dotnet run --project $tempDir
}
finally {
    Remove-Item $tempDir -Recurse -Force -ErrorAction SilentlyContinue
}
