# Lab_TimeBasedOneTimePassword
模擬 TOTP client 為未來時間的情境，學習：
 - Gradlew build Spring Boot Jar
 - 壓Docker映像
 - 起兩個Docker容器
 - 於容器內操弄作業系統日期時間

修正 `/totp-receiver/src/main/java/com/example/totp/receiver/FixedTotp.java`  檔案第 104 行 。
```Java
for (int i = pastResponse; i >= -1; --i) {  // 修正 當 client 是 未來日 
```
