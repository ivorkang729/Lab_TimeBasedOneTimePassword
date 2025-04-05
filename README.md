# Lab_TimeBasedOneTimePassword
模擬 TOTP client 為未來時間的情境，學習：
 - Gradlew build Spring Boot Jar
 - 壓Docker映像
 - 起兩個Docker容器
 - 於容器內操弄作業系統日期時間

修正 [`/totp-receiver/src/main/java/com/example/totp/receiver/FixedTotp.java`](https://github.com/ivorkang729/Lab_TimeBasedOneTimePassword/blob/master/totp-receiver/src/main/java/com/example/totp/receiver/FixedTotp.java) 
```Java
for (int i = pastResponse; i >= -1; --i) {  // 表示時間段偏移值 (-1, 0, 1)：前一時段、當前時段或下一時段，用於容許TOTP客戶端與服務端之間的時間誤差
```
