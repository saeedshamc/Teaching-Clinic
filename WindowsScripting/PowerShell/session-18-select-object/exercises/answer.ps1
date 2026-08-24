# پاسخ تمرین - جلسه 18: Select-Object برای انتخاب ستون‌ها
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Get-Process | Select-Object -First 5 Name, @{Name="CPU";Expression={[math]::Round($_.CPU,2)}}
