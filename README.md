# Lab_TimeBasedOneTimePassword
模擬 TOTP client 為未來時間的情境，學習：
 - Gradlew build Spring Boot Jar
 - 壓Docker映像
 - 起兩個Docker容器
 - 於容器內操弄作業系統日期時間

修正 `/totp-receiver/src/main/java/com/example/totp/receiver/FixedTotp.java`  檔案第 104 行 
```Java
for (int i = pastResponse; i >= -1; --i) {  // 當前時段的 (-1, 0, 1)，前一段、目前時段、未來一段，以容讓 client / service 時間誤差 
```
