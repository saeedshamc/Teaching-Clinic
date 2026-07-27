<?php
// مدیریت خطا
try {
    if (!file_exists('nonexistent.txt')) {
        throw new Exception('File not found');
    }
} catch (Exception $e) {
    echo 'Error: ' . $e->getMessage();
}

