<?php
// PDO مبانی
try {
    $pdo = new PDO('mysql:host=localhost;dbname=test', 'user', 'pass');
    echo 'Connected successfully\n';
} catch (PDOException $e) {
    echo 'Connection failed: ' . $e->getMessage();
}

