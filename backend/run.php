<?php 
header("Content-Type: text/plain"); // กำหนดให้แสดงผลเป็นข้อความล้วน

// รับค่าพารามิเตอร์จาก URL (ip, user, pass, cmd)
$ip   = $_GET['ip']   ?? '';
$user = $_GET['user'] ?? '';
$pass = $_GET['pass'] ?? '';
$cmd  = $_GET['cmd']  ?? '';

// ตรวจสอบว่ากรอกข้อมูลมาครบหรือไม่
if (!$ip || !$user || !$pass || !$cmd) {
    echo "Missing parameters";
    exit;
}

// เชื่อมต่อไปยัง SSH Server ที่พอร์ต 22
$connection = ssh2_connect($ip, 22);

if (!$connection) {
    echo "Connection failed";
    exit;
}

// ทำการเข้าสู่ระบบด้วย username และ password
if (!ssh2_auth_password($connection, $user, $pass)) {
    echo "Authentication failed";
    exit;
}

// สั่งรันคำสั่งบน Server
$stream = ssh2_exec($connection, $cmd);

if (!$stream) {
    echo "Command execution failed";
    exit;
}

stream_set_blocking($stream, true); // รอให้คำสั่งทำงานเสร็จก่อน
$output = stream_get_contents($stream); // อ่านผลลัพธ์จากคำสั่ง

echo $output; // แสดงผลลัพธ์กลับไปยังผู้ใช้
?>