# 基础镜像
FROM  openjdk:8-jre
# 复制jar文件到路径
COPY ./target/wa-node-0.0.1-SNAPSHOT.jar app.jar
# 启动认证服务
ENTRYPOINT ["java","-jar","app.jar"]


