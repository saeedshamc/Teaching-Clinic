# پاسخ تمرین - جلسه 25: ConvertTo-Json و ConvertFrom-Json
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$books = @(
    @{Name="Clean Code"; Author="Robert Martin"; Year=2008}
    @{Name="The Pragmatic Programmer"; Author="David Thomas"; Year=1999}
    @{Name="Design Patterns"; Author="GoF"; Year=1994}
)
$json = $books | ConvertTo-Json -Depth 3
Write-Host $json
