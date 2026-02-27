# ไฟล์สำหรับสร้าง Docker image ติดตั้ง Apache, PHP 8.2 และเปิดใช้งานส่วนเสริม SSH2
FROM php:8.2-apache

RUN apt-get update && apt-get install -y \
    libssh2-1-dev \
    && pecl install ssh2 \
    && docker-php-ext-enable ssh2

COPY . /var/www/html/