const descriptions = {

    "uptime": "แสดงระยะเวลาที่ server เปิดใช้งาน และ load average",

    "df -h": "แสดงพื้นที่ disk ทั้งหมด และพื้นที่ที่ใช้ไป",

    "free -m": "แสดงการใช้ RAM หน่วย MB",

    "whoami": "แสดงชื่อ user ที่ login อยู่"

};

function showDescription() {

    let cmd = document.getElementById("command").value;

    document.getElementById("description").innerText =
        descriptions[cmd];

}

async function runCommand() {

    let ip = document.getElementById("ip").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let cmd = document.getElementById("command").value;

    let url =
        `../backend/run.php?ip=${ip}&username=${username}&password=${password}&cmd=${encodeURIComponent(cmd)}`;

    let res = await fetch(url);

    let text = await res.text();

    document.getElementById("output").innerText = text;

}

showDescription();