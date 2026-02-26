<?php
header("Content-Type: text/plain");

if (!isset($_GET['cmd'])) {
    echo "No command provided.";
    exit;
}

$cmd = $_GET['cmd'];

$javaPath = __DIR__ . '/../java';

$command = 'cd ' . escapeshellarg($javaPath) .
           ' && java -cp "lib/*;." client.SSHClient ' .
           escapeshellarg($cmd) .
           ' 2>&1';

$output = shell_exec($command);

echo $output;
?>