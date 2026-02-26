<?php

if (!isset($_GET['cmd'])) {
    echo "No command";
    exit;
}

$cmd = $_GET['cmd'];

// path ไปยัง java folder
$javaPath = "../java";

// command เรียก SSHClient
$command = "cd $javaPath && java -cp \"lib/*;.\" client.SSHClient $cmd";

// execute command
$output = shell_exec($command);

echo "<pre>";
echo $output;
echo "</pre>";

?>