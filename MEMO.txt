*** (美化PowerShell) 漂亮的命令提示行 -----------
https://github.com/ligz08/PowerShell-Profile/tree/master
將 PowerShell-Profile-master.zip 解壓縮，放進PowerShell目錄下:
C:\Users\Kang Chih Wei\Documents\WindowsPowerShell


*** (美化PowerShell) 漂亮的output --------------
https://github.com/Davlind/PSColor?tab=readme-ov-file
在PowerShell module paths底下(Run $env:PSModulePath to see my paths.)  
C:\Users\Kang Chih Wei\Documents\WindowsPowerShell\Modules
建立資料夾 PSColor, 將 PSColor.zip 解壓後所有檔案放進去

每次啟動PowerShell時都自動載入PSColor模組:
編輯 C:\Users\Kang Chih Wei\Documents\WindowsPowerShell\profile.ps1
在最後面增加這一行:  Import-Module PSColor

*** 軟體安裝
Java 17

# 設定環境變數
JAVA_HOME=D:\Applications\Java\java-17-openjdk-17.0.14.0.7-1.win.jdk.x86_64


*** 配置 Gradle -----------
# 建立全域的儲存庫
D:\gradle-user-home

# 設定環境變數
GRADLE_USER_HOME=D:\.gradle-user-home

 (可不用設定)
GRADLE_HOME=D:\Applications\Gradle\gradle-7.6.1


*** 配置 gradlew -----------

# 建立 run-gradlew.bat
'''
SET JAVA_HOME="D:/Applications/Java/java-17-openjdk-17.0.14.0.7-1.win.jdk.x86_64"
SET GRADLE_USER_HOME=D:/.gradle-user-home
call gradlew.bat %*
'''

# 修改 <專案目錄>\gradle\wrapper\gradle-wrapper.properties
## 告訴 gradlew 要使用哪個版本的 gradle 執行任務
'''
distributionUrl=https\://services.gradle.org/distributions/gradle-7.6.2-bin.zip
'''


*** 要用哪個版本的 Spring boot Gradle Plugin id?
我們是透過 指定 gradle plugin id 來指定 Spring Boot 的版本 
build.gradle 裡面
'''
plugins {
	id 'org.springframework.boot' version '3.3.10'
	id 'io.spring.dependency-management' version '1.1.7'
}
'''
其中 3.3.10 是怎麼來的?
搜尋 gradle plugin, 得到 https://plugins.gradle.org/
然後查找 org.springfradmework.boot,  就可以得到 gradle plugin 版本號


*** 如果 gradelw clean build 出現 Test 異常
它會有個url, 點進去是一份報告, 裡面說
'Spring Boot [3.3.10] is not compatible with this Spring Cloud release train', action = 'Change Spring Boot version to one of the following versions [3.4.x] .
If you want to learn more about the Spring Cloud Release train compatibility, you can visit this page [https://spring.io/projects/spring-cloud#overview] and check the [Release Trains] section.
就點進 [https://spring.io/projects/spring-cloud#overview] 看 [Release Train], 就有哪一版本的 springboot 應搭配哪個版本的 spring cloud 對照表


*** 如果 gradle 行為變得怪異, 說下載不到依賴 (但明明存在)
確認 eclipse > Window > preference > Gradle 使用的是 gradlew (才不會因 eclipse 和 gradles 使用不同gradle版本, 但又指向相同的 GRADLE_USER_HOME 互相干擾 )
清空 <專案資料夾>\.gradle


*** 用 gradelw 編譯出 jar
在專案目錄下:
.\run-gradlew.bat -x test clean build
其中, -x test <== 跳過 junit Test 

構建完成後，JAR檔案將位於`build/libs/my-rest-api-0.0.1-SNAPSHOT.jar`


*** Dockerfile -------------
在 <專案資料夾>/Dockerfile


*** build image -------------
cd .\totp-receiver\
docker image build -t totp-receiver:latest .

cd .\totp-client\;docker image build -t totp-client:latest .;cd ..
cd .\totp-receiver\;docker image build -t totp-receiver:latest .;cd ..


*** create and start a container from my image -------------
直接在後台運行
docker run -d -p 8080:8080 totp-receiver:latest

占用終端機
docker run -it -p 8081:8081 --name totp-client totp-client:latest
docker run -it -p 8080:8080 --name totp-receiver totp-receiver:latest

如果已經在 -it 模式運行，可以使用 Docker 的快捷鍵離開容器
Ctrl + P + Q


*** 需要再次進入容器內部查看 可以用 
docker container exec -it 1a53a2cd634bf3ad9adaf1a26a01ed460e475b78a298267cb2bdd7cbe7f30c6b /bin/bash
如果容器不是 Linux 發行版，或者沒有 bash，你可以嘗試：
docker container exec -it 1a53a2cd634bf3ad9adaf1a26a01ed460e475b78a298267cb2bdd7cbe7f30c6b /bin/sh


*** 查看當前運行的容器清單，可以使用：
docker container ls

查看所有的容器清單
docker container ls -a

*** 停止執行中的容器 -------------
docker container stop <my-container>

*** 啟動一個已停止的容器 -------------
docker container start <my-container> 

※ docker container start 沒有 -d 
對於 docker container start，如果要在後台運行，只需正常啟動即可：
docker container start my-container 
這個命令預設就是在後台運行。



同時啟動多個容器
docker container start <container1> <container2> <container3>

帶環境變量啟動
docker container start -e ENV_VAR=value <my-container>

以互動模式啟動容器
docker container start -i my-container 


*** 查看 STDOUT/STDERR 日誌
docker container logs <my-container>

持續查看 STDOUT/STDERR 日誌(類似 tail -f)
docker container logs -f <my-container>

*** 查看容器的 主進程 (Main Process)
$ docker container exec <my-container> ps -aux

USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0   2688  1696 pts/0    Ss+  13:34   0:00 faketime 2023-03-01 10:30:00 java -jar /opt/app/totp-receiver-0.0.1-SNAPSHOT.jar
root         8  1.5  2.0 7140352 204528 pts/0  Sl+  13:34   0:17 java -jar /opt/app/totp-receiver-0.0.1-SNAPSHOT.jar
root        61  100  0.0  10880  4280 ?        Rs   13:53   0:00 ps -aux

在容器内，主进程的PID通常是1

主进程crash，容器会自动退出
如果主进程被kill，容器也会停止


*** Dockerfile -------------

FROM ubuntu

# 安裝faketime
RUN apt-get update && apt-get install -y faketime

#WORKDIR /app
#COPY build/libs/my-java-app-1.0-SNAPSHOT.jar /app/app.jar

# 使用faketime啟動Java應用
ENTRYPOINT ["faketime", "2024-03-01 10:30:00", "date"]

*** END of Dockerfile -------------

*** build image -------------
$ docker image build -t lab-1:faketime


*** run image -------------
$ docker run -it lab-1:faketime







