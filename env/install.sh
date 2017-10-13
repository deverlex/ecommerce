#!---------
# Before run this file, you need run command below:
# sudo chmod +x install.sh
# ./install.sh
#!--------
# Copyright (C) Needy Co...Ldt

DIR=`pwd`

sudo apt-get update & upgrade
sudo apt-get -y install build-essential tcl libaio1 python libmecab2

cd /tmp
# Download java 8 source code version 1.8.0_144
wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-linux-x64.tar.gz

# Create folder storage JDK installed
sudo mkdir /opt/jdk
sudo tar -zxf jdk-8u144-linux-x64.tar.gz -C /opt/jdk

# Setup enviroment for Java Oracle
sudo update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_144/bin/java 100
sudo update-alternatives --install /usr/bin/javac javac /opt/jdk/jdk1.8.0_144/bin/javac 100

echo export JAVA_HOME=/opt/jdk/jdk1.8.0_144 >> ~/.bashrc
echo export PATH=$PATH:$JAVA_HOME  >> ~/.bashrc
source ~/.bashrc

# Install Tomcat8 version 8.5.5 from source
# References: https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-16-04
sudo mkdir /opt/tomcat
sudo groupadd tomcat
sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat

wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.5/bin/apache-tomcat-8.5.5.tar.gz
sudo tar xzvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1
cd /opt/tomcat
# Give the tomcat group ownership over the entire installation directory
sudo chgrp -R tomcat /opt/tomcat
# Next, give the tomcat group read access to the conf directory and all of its contents, 
# and execute access to the directory itself
sudo chmod -R g+r conf
sudo chmod g+x conf
sudo chown -R tomcat webapps/ work/ temp/ logs/

cd $DIR
sudo cp tomcat.service /etc/systemd/system
sudo systemctl daemon-reload
sudo systemctl start tomcat

# Allow throught ufw firewall
sudo ufw allow 8080
# Tomcat automatically starts at boot
sudo systemctl enable tomcat

# Need config pass admin for manager tomcat

echo export CATALINA_HOME=/opt/tomcat >> ~/.bashrc
echo export PATH=$PATH:$CATALINA_HOME  >> ~/.bashrc
source ~/.bashrc

# Install Redis version 3.2.10
cd /tmp
wget http://download.redis.io/releases/redis-3.2.10.tar.gz
sudo tar xzvf redis-*.tar.gz

cd redis-*
sudo make
# Install Redis
sudo make test
sudo make install
sudo mkdir /etc/redis

# Mofidy config for redis running
cd $DIR
sudo cp redis.conf /etc/redis
sudo cp redis.service /etc/systemd/system/

sudo adduser --system --group --no-create-home redis
sudo mkdir /var/lib/redis
sudo chown redis:redis /var/lib/redis
sudo chmod 770 /var/lib/redis

sudo systemctl start redis
sudo systemctl enable redis

# Install MySQL version 5.7.18
cd /tmp
wget https://downloads.mysql.com/archives/get/file/mysql-server_5.7.18-1ubuntu16.04_amd64.deb-bundle.tar
mkdir mysql-server
mv mysql-server_5.7*.tar mysql-server
cd mysql-server
tar -xvf mysql-server_5.7*.tar
sudo dpkg -i *.deb

sudo echo '[client]
default-character-set = utf8mb4

[mysqld]
innodb_file_format = Barracuda
innodb_file_per_table = 1
innodb_large_prefix

character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
skip-character-set-client-handshake

[mysql]
default-character-set = utf8mb4' >> /etc/mysql/my.cnf

sudo service mysql restart
