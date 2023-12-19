FROM jenkins/jenkins:latest

USER root

# Добавляем пользователя Jenkins в группу sudoers
RUN apt-get update && \
    apt-get install -y sudo && \
    usermod -aG sudo jenkins

# Установка пароля для пользователя Jenkins
RUN echo "jenkins:1234" | chpasswd

USER jenkins
