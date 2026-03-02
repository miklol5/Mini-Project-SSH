# Mini Project: SSH Web Terminal (Docker)

## ภาพรวมโปรเจกต์

โปรเจกต์นี้เป็นระบบ Web Terminal ที่สามารถสั่งคำสั่ง SSH ไปยัง Server ผ่านหน้าเว็บได้ โดยทำงานอยู่บน Docker ทุกส่วนของระบบ

เทคโนโลยีที่ใช้:
- Frontend: HTML, CSS, JavaScript
- Backend: PHP (ssh2)
- SSH Server: Ubuntu + OpenSSH (Docker)
- Container Management: Docker + Docker Compose

---

## โครงสร้างโปรเจกต์

Mini-Project-SSH/
│
├── frontend/
│   ├── index.html
│   ├── script.js
│   └── style.css
│
├── backend/
│   ├── Dockerfile
│   ├── run.php
│   └── commands.json
│
├── docker-ssh/
│   └── Dockerfile
│
├── docker-compose.yml
└── readme.md

---

## วิธีรันโปรเจกต์

1) เข้าโฟลเดอร์โปรเจกต์

cd Mini-Project-SSH

2) Build และรันระบบ

docker compose up -d --build

ระบบจะทำการ build และ start container ทั้งหมด เช่น SSH Server และ Backend

3) ตรวจสอบ container ที่กำลังทำงาน

docker ps

---

## วิธีเข้าใช้งานหน้าเว็บ

เปิด Web Browser แล้วไปที่:

http://localhost:8080/frontend/index.html

---

## ข้อมูลสำหรับ Login SSH

Server IP: ssh-server  
Username: student  
Password: 1234  

---

## วิธีใช้งาน

1. เปิดหน้าเว็บ
2. กรอกข้อมูล Server IP, Username และ Password
3. เลือกหรือพิมพ์คำสั่งที่ต้องการ เช่น:
   - whoami
   - uptime
   - df -h
   - free -m
4. กดปุ่ม Run
5. ระบบจะแสดงผลลัพธ์ในหน้าจอ Terminal บนหน้าเว็บ

---

## ตัวอย่างผลลัพธ์

$ whoami  
student  

$ uptime  
10:32:10 up 5 min, 1 user, load average: 0.00, 0.01, 0.05  

---

## สำหรับการนำเสนอ (Presentation)

ขั้นตอนสาธิต:

1. เปิด Terminal
2. เข้าโฟลเดอร์โปรเจกต์

cd Mini-Project-SSH

3. รันระบบ

docker compose up -d --build

4. เปิด Browser ไปที่

http://localhost:8080/frontend/index.html

5. Login ด้วยข้อมูล

ssh-server  
student  
1234  

6. ทดลองรันคำสั่ง เช่น

whoami  
uptime  
df -h  

---

## สรุปการทำงานของระบบ

ลำดับการทำงานของระบบ:

Browser  
↓  
Frontend (HTML / JavaScript)  
↓  
Backend (PHP + ssh2)  
↓  
SSH Server (Ubuntu Container)  
↓  
ส่งผลลัพธ์กลับมาแสดงบนหน้าเว็บ  

โปรเจกต์นี้แสดงให้เห็นการเชื่อมต่อระหว่าง Web Application กับ SSH Server ผ่าน Docker และการจัดการระบบแบบ Containerized Environment