FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openssh-server \
    sudo

RUN mkdir /var/run/sshd

# สร้าง user
RUN useradd -m student && echo "student:1234" | chpasswd

# เปิด SSH
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config || true

EXPOSE 22

CMD ["/usr/sbin/sshd", "-D"]