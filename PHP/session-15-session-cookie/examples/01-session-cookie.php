<?php
// Session و Cookie
session_start();
$_SESSION['user'] = 'Ali';
echo 'Session set for: ' . $_SESSION['user'] . '\n';

