FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openssh-server \
    sudo

# สร้าง folder sshd
RUN mkdir /var/run/sshd

# สร้าง user
RUN useradd -m student && echo "student:1234" | chpasswd

# เปิด password authentication
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# เปิด port
EXPOSE 22

# start ssh
CMD ["/usr/sbin/sshd", "-D"]