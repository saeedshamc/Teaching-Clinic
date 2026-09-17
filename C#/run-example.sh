#!/usr/bin/env bash
# اجرای سریع یک فایل مثال C#
set -euo pipefail

if [ $# -lt 1 ]; then
  echo "Usage: ./run-example.sh path/to/example.cs"
  exit 1
fi

FILE="$1"
if [ ! -f "$FILE" ]; then
  echo "File not found: $FILE"
  exit 1
fi

TEMP_DIR="$(mktemp -d /tmp/csharp-run-XXXXXX)"
trap 'rm -rf "$TEMP_DIR"' EXIT

dotnet new console -o "$TEMP_DIR" -f net8.0 --force >/dev/null
cp "$FILE" "$TEMP_DIR/Program.cs"
dotnet run --project "$TEMP_DIR"
