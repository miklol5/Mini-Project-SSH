FROM php:8.2-apache

RUN apt-get update && apt-get install -y \
    libssh2-1-dev \
    && pecl install ssh2 \
    && docker-php-ext-enable ssh2

COPY . /var/www/html/