<?php
// کار با فرم‌ها
if (isset($_GET['name'])) {
    echo 'Hello, ' . htmlspecialchars($_GET['name']);
}

