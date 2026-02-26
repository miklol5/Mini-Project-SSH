<?php
header("Content-Type: text/plain");

$ip   = $_GET['ip']   ?? '';
$user = $_GET['user'] ?? '';
$pass = $_GET['pass'] ?? '';
$cmd  = $_GET['cmd']  ?? '';

if (!$ip || !$user || !$pass || !$cmd) {
    echo "Missing parameters";
    exit;
}

// connect
$connection = ssh2_connect($ip, 22);

if (!$connection) {
    echo "Connection failed";
    exit;
}

// login
if (!ssh2_auth_password($connection, $user, $pass)) {
    echo "Authentication failed";
    exit;
}

// execute
$stream = ssh2_exec($connection, $cmd);

if (!$stream) {
    echo "Command execution failed";
    exit;
}

stream_set_blocking($stream, true);
$output = stream_get_contents($stream);

echo $output;
?>