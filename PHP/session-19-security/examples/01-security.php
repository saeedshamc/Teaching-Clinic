<?php
// امنیت در PHP
$input = '<script>alert(1)</script>';
echo htmlspecialchars($input) . '\n';
echo filter_var('test@example.com', FILTER_VALIDATE_EMAIL) ? 'Valid' : 'Invalid';

