# 基本版本
FROM eclipse-temurin:17-jdk

# 安裝faketime
RUN apt-get update && apt-get install -y faketime

WORKDIR /opt/app

COPY build/libs/*.jar /opt/app

#EXPOSE 指令主要是一個metadata和文檔提示，並不實際發布或開放端口，真正開放端口是通過 docker run 時的 -p 參數
#但沒寫則docker desktop會以為這個container沒有發布任何端口。
EXPOSE 8080

# 進入點
#ENTRYPOINT ["java", "-jar", "/opt/app/totp-client-0.0.1-SNAPSHOT.jar"]

# 使用faketime啟動Java應用
ENTRYPOINT ["faketime", "2023-03-01 10:30:00 +0800", "java", "-Duser.timezone=Asia/Taipei", "-jar", "/opt/app/totp-client-0.0.1-SNAPSHOT.jar"]

