#!/bin/bash
# This script will install dependencies.
# Pull this file down, make it executable and run it with sudo.
# 
# chmod u+x bootstrap.sh
# sudo ./bootstrap.sh
# 

if [ $(id -u) != "0" ]; then
echo "You must be the superuser to run this script" >&2
exit 1
fi
apt-get update
apt install snapd

# Install curl
apt-get -y install curl

# Install Java
apt install -y default-jdk

# Install Maven
apt-get install -y maven

# Install NodeJS
curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -
apt-get -y install nodejs

# Install Angular
npm install -g @angular/cli

# Install Bootstrap
npm install bootstrap

# Install PostgreSQL
apt install postgresql postgresql-contrib

# Update everything and install more dependencies.
npm update
ng update
npm install --save-dev @angular-devkit/build-angular

# Installing Docker
apt install apt-transport-https ca-certificates gnupg-agent software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
apt install docker-ce docker-ce-cli containerd.io docker.io
snap install docker

# Install docker-compose
curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose