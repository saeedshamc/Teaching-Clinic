# پاسخ تمرین‌های جلسه ۱۴۱

## آسان

**۱ و ۲.**
```java
// Symmetric — AES
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
keyGen.init(256);
SecretKey secretKey = keyGen.generateKey();
Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
byte[] encrypted = cipher.doFinal(plaintext.getBytes());

// Asymmetric — RSA
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
keyGen.initialize(2048);
KeyPair keyPair = keyGen.generateKeyPair();
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
```

## متوسط

**۳ و ۴.**
```java
public static String generateKey() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256);
    return Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
}

public static String encrypt(String plaintext, String key, String iv) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
    return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
}
```

## سخت

**۵ و ۶.**
```java
KeyStore keyStore = KeyStore.getInstance("JKS");
keyStore.load(null, password.toCharArray());
keyStore.setKeyEntry("myKey", secretKey, password.toCharArray(), null);

Certificate cert = CertificateFactory.getInstance("X.509")
    .generateCertificate(new FileInputStream("cert.crt"));
```
