# Mini Project: SSH Web Terminal (Docker)

## ภาพรวมโปรเจกต์

โปรเจกต์นี้เป็น Web Terminal ที่สามารถสั่งคำสั่ง SSH ไปยัง Server ผ่านหน้าเว็บ โดยใช้:

* Frontend: HTML, CSS, JavaScript
* Backend: PHP (ssh2)
* SSH Server: Ubuntu + OpenSSH (Docker)
* Container: Docker + Docker Compose

---

## โครงสร้างโปรเจกต์

```
Mini-Project-SSH/
│
├── frontend/
│   └── index.html
│
├── backend/
│   ├── Dockerfile
│   └── run.php
│
├── docker-ssh/
│   └── Dockerfile
│
└── docker-compose.yml
```

---

## สิ่งที่ต้องติดตั้งก่อน

* Docker Desktop
* Docker Compose (มาพร้อม Docker Desktop)
* Web Browser เช่น Chrome

---

## วิธีรันโปรเจกต์

### 1. เปิด Terminal ในโฟลเดอร์โปรเจกต์

ไปที่โฟลเดอร์:

```
Mini-Project-SSH
```

ตัวอย่าง (Windows):

```
cd Mini-Project-SSH
```

---

### 2. รัน Docker Compose

```
docker compose up -d --build
```

ระบบจะทำการ:

* build SSH server container
* build PHP backend container
* start containers ทั้งหมด

---

### 3. ตรวจสอบว่า container ทำงาน

```
docker ps
```

ต้องเห็น:

```
ssh-server
php-backend
```

---

## วิธีเข้าใช้งานหน้าเว็บ

เปิด Browser แล้วไปที่:

```
http://localhost:8080/frontend/index.html
```

จะเห็นหน้า:

```
SSH Web Terminal
```

---

## ข้อมูลสำหรับ Login SSH

ใช้ข้อมูลนี้:

```
Server IP: ssh-server
Username: student
Password: 1234
```

---

## วิธีใช้งาน

1. เปิดเว็บ
2. กรอกข้อมูล:

```
Server IP: ssh-server
Username: student
Password: 1234
```

3. เลือก command เช่น:

```
whoami
uptime
df -h
free -m
```

4. กดปุ่ม Run

5. ระบบจะแสดงผลลัพธ์ใน terminal บนหน้าเว็บ

---

## ตัวอย่างผลลัพธ์

```
$ whoami
student
```

---

## วิธีหยุดโปรเจกต์

```
docker compose down
```

---

## สำหรับการนำเสนอ (Presentation)

ขั้นตอน:

1. เปิด Terminal
2. เข้าโฟลเดอร์โปรเจกต์

```
cd Mini-Project-SSH
```

3. รัน

```
docker compose up -d --build
```

4. เปิด Browser

```
http://localhost:8080/frontend/index.html
```

5. Login ด้วย

```
ssh-server
student
1234
```

6. สาธิตการรันคำสั่ง เช่น

```
whoami
uptime
```

---

## สรุปการทำงาน

Browser
↓
Frontend (HTML/JS)
↓
Backend (PHP)
↓
SSH Server (Docker Ubuntu)
↓
แสดงผลกลับหน้าเว็บ

---