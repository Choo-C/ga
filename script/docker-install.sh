### docker-compose ###
#1.下载docker compose：
sudo curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.1/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
#2.授予权限：
sudo chmod +x /usr/local/bin/docker-compose
#3.使用Compose构建并运行(docker-compose.yml同级)
docker-compose up

### mysql ###
docker pull mysql:8.0.31
docker run \
    --restart=always \
    --name mysql \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=7XepQT \
    -v /home/mysql/data/:/var/lib/mysql/ \
    -v /home/mysql/conf/:/etc/mysql/conf.d/ \
    -d mysql:8.0.31 \
    --lower_case_table_names=1

# 连接报错1251- Client does not support authentication protocol requested by server…的问题
# 进入容器
docker exec -it mysql /bin/bash
# 登录
mysql -u root -p
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'xnbs4H'
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'xnbs4H'
FLUSH privileges

### minio ###
# 创建宿主文件夹
mkdir -p /home/minio/data /home/minio/conf
docker pull minio/minio:latest
docker run -p 9000:9000 -p 9090:9090 \
    --name minio \
    -d --restart=always \
    -e "MINIO_ACCESS_KEY=admin" \
    -e "MINIO_SECRET_KEY=w8rnMyw9" \
    -v /home/minio/data:/data \
    -v /home/minio/conf:/root/.minio \
    minio/minio server \
    /data --console-address ":9090" -address ":9000"
# -e "MINIO_SERVER_URL=https://sbb.hz3861.gd.cn" \

### redis ###
# 创建宿主文件夹
mkdir -p /home/redis/data /home/redis/conf
touch /home/redis/conf/redis.conf
vi /home/redis/conf/redis.conf
# ----------------------------
# 设置密码:
# requirepass XnVJcM
# ----------------------------
docker pull redis:bullseye
docker run --name redis -p 6379:6379 \
    --restart=always \
    -v /home/redis/data:/data \
    -v /home/redis/conf/redis.conf:/etc/redis/redis.conf \
    -d redis:bullseye redis-server /etc/redis/redis.conf

### rabbitMQ ###
docker pull rabbitmq
docker run -d -p 15672:15672 -p 5672:5672 \
    --restart=always \
    -e RABBITMQ_DEFAULT_VHOST=vh_vhost \
    -e RABBITMQ_DEFAULT_USER=admin \
    -e RABBITMQ_DEFAULT_PASS=Omec2G \
    --hostname myRabbit \
    --name rabbitmq \
    rabbitmq
# 启动 rabbitmq_management
docker exec -it rabbitmq rabbitmq-plugins enable rabbitmq_management

### nginx ###
# 创建宿主文件夹
mkdir -p /home/nginx/www /home/nginx/conf /home/nginx/logs /home/nginx/cert
docker pull nginx
# 启动一次为后面拷贝默认nginx.conf做准备
docker run -d -p 80:80 -p 443:443 --name nginx \
    nginx
# 拷贝默认nginx.conf
docker cp bb007a464869:/etc/nginx/nginx.conf /home/nginx/conf
# 移除之前创建容器重新启动
docker run -d -p 80:80 -p 443:443 --name nginx \
    --restart=always \
    -v /home/nginx/www:/usr/share/nginx/html \
    -v /home/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
    -v /home/nginx/logs:/var/log/nginx \
    -v /home/nginx/cert:/etc/nginx/cert \
    nginx
vi /home/nginx/conf/nginx.conf
# ----------------------------
# ssl_certificate      /etc/nginx/cert/server.crt;
# ssl_certificate_key  /etc/nginx/cert/desaysv.com.key;
# ----------------------------

### JDK ###
# 解压JDK
tar -zxvf jdk-8u341-linux-x64.tar.gz
# 添加配置
vim /etc/profile
export JAVA_HOME=/home/jdk1.8.0_341
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
# 立即生效
source /etc/profile
